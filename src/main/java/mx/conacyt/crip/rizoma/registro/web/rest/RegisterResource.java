package mx.conacyt.crip.rizoma.registro.web.rest;

import javax.validation.Valid;
import mx.conacyt.crip.renapo.reactive.client.model.RenapoDto;
// Repositories
import mx.conacyt.crip.rizoma.registro.repository.UserRepository;
import mx.conacyt.crip.rizoma.registro.service.MailService;
// Services
import mx.conacyt.crip.rizoma.registro.service.RenapoService;
import mx.conacyt.crip.rizoma.registro.service.dto.AdminUserDTO;
import mx.conacyt.crip.rizoma.registro.service.dto.RegisterPasswordChangeDTO;
// DTO
import mx.conacyt.crip.rizoma.registro.service.dto.RenapoDTO;
import mx.conacyt.crip.rizoma.registro.web.rest.errors.*;
import mx.conacyt.crip.rizoma.registro.web.rest.vm.ManagedRenapoUserVM;
import mx.conacyt.crip.rizoma.registro.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class RegisterResource {

    private final UserRepository userRepository;
    private final RenapoService renapoService;
    private final MailService mailService;

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    public RegisterResource(RenapoService renapoService, UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.renapoService = renapoService;
        this.mailService = mailService;
    }

    /**
     * {@code POST  /validate-curp} : validate-curp the user.
     *
     * @param managedRenapoVM the managed user View Model.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     */
    @PostMapping("/validate-curp")
    // AdminUserDTO
    public Mono<ResponseEntity<RenapoDto>> validateCurp(@Valid @RequestBody RenapoDTO managedRenapoVM) {
        return userRepository
            .findOneByEmailIgnoreCase(managedRenapoVM.getEmail())
            .hasElement()
            .flatMap(emailExists -> {
                if (emailExists) {
                    return Mono.error(new EmailAlreadyUsedException());
                }

                return renapoService.searchCurp(managedRenapoVM.getCurp());
            })
            .map(person -> ResponseEntity.ok().body(person))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /**
     * {@code POST  /register-person} : register-person the user.
     *
     * @param managedRenapoVM the managed user View Model.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register-person")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> registerAccount(@Valid @RequestBody ManagedRenapoUserVM managedRenapoVM) {
        return renapoService.registerUser(managedRenapoVM).doOnSuccess(mailService::sendActivationEmail).then();
    }

    /**
     * {@code POST  /set-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping("/set-password")
    public Mono<Void> changePassword(@RequestBody RegisterPasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (isEmailLengthInvalid(passwordChangeDto.getEmail())) {
            throw new InvalidEmailException();
        }
        return renapoService.changePassword(passwordChangeDto.getEmail(), passwordChangeDto.getPassword());
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
            password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
            password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }

    private static boolean isEmailLengthInvalid(String email) {
        return (StringUtils.isEmpty(email));
    }
}
