package mx.conacyt.crip.rizoma.registro.service.dto;

import java.io.Serializable;

/**
 * A DTO representing a password change required data - current and new password.
 */
public class RegisterPasswordChangeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
    private String email;

    public RegisterPasswordChangeDTO() {
        // Empty constructor needed for Jackson.
    }

    public RegisterPasswordChangeDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
