package pl.galajus.brokenpediabackend.security.exception;

import pl.galajus.brokenpediabackend.common.exception.BusinessException;

public class RegisterException extends BusinessException {
    public RegisterException(String message) {
        super(message);
    }
}
