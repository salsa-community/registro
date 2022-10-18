package mx.conacyt.crip.rizoma.registro.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidEmailException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidEmailException() {
        super(ErrorConstants.INVALID_PASSWORD_TYPE, "Incorrect email", Status.BAD_REQUEST);
    }
}
