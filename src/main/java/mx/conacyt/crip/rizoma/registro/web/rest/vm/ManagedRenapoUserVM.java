package mx.conacyt.crip.rizoma.registro.web.rest.vm;

import javax.validation.constraints.Size;
import mx.conacyt.crip.rizoma.registro.service.dto.AdminUserDTO;
import mx.conacyt.crip.rizoma.registro.service.dto.RenapoUserDTO;

/**
 * View Model extending the AdminUserDTO, which is meant to be used in the user management UI.
 */
public class ManagedRenapoUserVM extends RenapoUserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    // @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedRenapoUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedRenapoUserVM{" + super.toString() + "} ";
    }
}
