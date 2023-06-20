package pl.galajus.brokenpediabackend.user.security.exception;

import pl.galajus.brokenpediabackend.user.common.exception.BusinessException;

public class RequestAuthorizationException extends BusinessException {
    public RequestAuthorizationException(String message) {
        super(message);
    }
}
