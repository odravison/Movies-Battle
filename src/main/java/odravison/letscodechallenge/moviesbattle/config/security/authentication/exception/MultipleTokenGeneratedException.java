package odravison.letscodechallenge.moviesbattle.config.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is only thrown when there's more than one token generated for the same user;
 */
public class MultipleTokenGeneratedException extends AuthenticationException {

    public MultipleTokenGeneratedException(String msg, Throwable t) {
        super(msg, t);
    }

    public MultipleTokenGeneratedException(String msg) {
        super(msg);
    }
}
