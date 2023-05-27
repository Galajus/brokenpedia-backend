package pl.galajus.brokenpediabackend.user.security.exception;

import pl.galajus.brokenpediabackend.user.common.exception.BusinessException;

public class RegisterException extends BusinessException {
    public RegisterException(String message) {
        super(message);
    }
}
