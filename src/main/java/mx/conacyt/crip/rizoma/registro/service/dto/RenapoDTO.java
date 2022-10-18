package mx.conacyt.crip.rizoma.registro.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;
import mx.conacyt.crip.rizoma.registro.domain.Person;

// Domain
/* import mx.conacyt.crip.rizoma.registro.domain.Person;
import mx.conacyt.crip.rizoma.registro.domain.User; */
/**
 * A DTO representing a user, with his authorities.
 */
public class RenapoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    private String curp;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    public RenapoDTO() {
        // Empty constructor needed for Jackson.
    }

    public RenapoDTO(Person person) {
        this.curp = person.getCurp();
        this.email = person.getEmail();
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RenapoDTO{" +
            "curp='" + curp + '\'' +
            ", email='" + email + '\'' +
            "}";
    }
}
