package mx.conacyt.crip.rizoma.registro.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import mx.conacyt.crip.rizoma.registro.service.dto.RenapoDTO;

/**
 * View Model extending the RenapoDTO, which is meant to be used in the person management UI.
 */
public class ManagedRenapoVM extends RenapoDTO {

    public static final int CURP_MIN_LENGTH = 18;
    public static final int CURP_MAX_LENGTH = 18;

    @NotNull
    @Size(min = CURP_MIN_LENGTH, max = CURP_MAX_LENGTH)
    private String curp;

    @NotNull
    private String email;

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedRenapoVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedRenapoVM(String curp, String email) {
        this.curp = curp;
        this.email = email;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEMail() {
        return email;
    }

    public void setEMail(String email) {
        this.email = email;
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
        return "ManagedRenapoVM{" + super.toString() + "} ";
    }
}
