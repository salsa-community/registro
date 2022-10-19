package mx.conacyt.crip.rizoma.registro.service;

import java.util.*;
import mx.conacyt.crip.renapo.reactive.client.*;
import mx.conacyt.crip.renapo.reactive.client.model.RenapoDto;
import mx.conacyt.crip.rizoma.registro.config.Constants;
import mx.conacyt.crip.rizoma.registro.domain.Authority;
import mx.conacyt.crip.rizoma.registro.domain.User;
import mx.conacyt.crip.rizoma.registro.repository.AuthorityRepository;
import mx.conacyt.crip.rizoma.registro.repository.UserRepository;
import mx.conacyt.crip.rizoma.registro.security.AuthoritiesConstants;
import mx.conacyt.crip.rizoma.registro.security.SecurityUtils;
import mx.conacyt.crip.rizoma.registro.service.dto.RenapoUserDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tech.jhipster.security.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
public class RenapoService {

    @Autowired
    private RenapoApiClient renapoApiClient;

    private final Logger log = LoggerFactory.getLogger(RenapoService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public Mono<RenapoDto> getRenapoInfo(String curp) {
        return renapoApiClient.getRenapoUsingGET(curp);
    }

    public RenapoService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public Mono<RenapoDto> searchCurp(String curp) {
        return renapoApiClient.getRenapoUsingGET(curp);
    }

    @Transactional
    public Mono<User> registerUser(RenapoUserDTO userDTO) {
        return userRepository
            .findOneByLogin(userDTO.getLogin().toLowerCase())
            .flatMap(existingUser -> {
                if (!existingUser.isActivated()) {
                    return userRepository.delete(existingUser);
                } else {
                    return Mono.error(new UsernameAlreadyUsedException());
                }
            })
            .then(userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()))
            .flatMap(existingUser -> {
                if (!existingUser.isActivated()) {
                    return userRepository.delete(existingUser);
                } else {
                    return Mono.error(new EmailAlreadyUsedException());
                }
            })
            .publishOn(Schedulers.boundedElastic())
            .then(
                Mono.fromCallable(() -> {
                    User newUser = new User();
                    newUser.setLogin(userDTO.getLogin().toLowerCase());
                    newUser.setFirstName(userDTO.getFirstName());
                    newUser.setLastName(userDTO.getLastName());
                    if (userDTO.getEmail() != null) {
                        newUser.setEmail(userDTO.getEmail().toLowerCase());
                    }

                    userDTO.setTokenValidation(RandomStringUtils.randomNumeric(10));
                    newUser.setTokenValidation(userDTO.getTokenValidation());

                    newUser.setImageUrl(userDTO.getImageUrl());
                    newUser.setLangKey(userDTO.getLangKey());
                    // new user is not active
                    newUser.setActivated(false);
                    // new user gets registration key
                    newUser.setActivationKey(RandomUtil.generateActivationKey());

                    return newUser;
                })
            )
            .flatMap(newUser -> {
                Set<Authority> authorities = new HashSet<>();
                return authorityRepository
                    .findById(AuthoritiesConstants.USER)
                    .map(authorities::add)
                    .thenReturn(newUser)
                    .doOnNext(user -> user.setAuthorities(authorities))
                    .flatMap(this::saveUser)
                    .doOnNext(user -> log.debug("Created Information for User: {}", user));
            });
    }

    @Transactional
    public Mono<User> saveUser(User user) {
        return SecurityUtils
            .getCurrentUserLogin()
            .switchIfEmpty(Mono.just(Constants.SYSTEM))
            .flatMap(login -> {
                if (user.getCreatedBy() == null) {
                    user.setCreatedBy(login);
                }
                user.setLastModifiedBy(login);
                // Saving the relationship can be done in an entity callback
                // once https://github.com/spring-projects/spring-data-r2dbc/issues/215 is done
                return userRepository
                    .save(user)
                    .flatMap(savedUser ->
                        Flux
                            .fromIterable(user.getAuthorities())
                            .flatMap(authority -> userRepository.saveUserAuthority(savedUser.getId(), authority.getName()))
                            .then(Mono.just(savedUser))
                    );
            });
    }

    @Transactional
    public Mono<Void> changePassword(String email, String newPassword) {
        return userRepository
            .findOneByEmailIgnoreCase(email)
            .map(user -> {
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                return user;
            })
            .flatMap(this::saveUser)
            .doOnNext(user -> log.debug("Changed password for User: {}", user))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)))
            .then();
    }
}
