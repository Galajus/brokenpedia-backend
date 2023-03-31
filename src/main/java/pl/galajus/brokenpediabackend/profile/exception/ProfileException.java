package pl.galajus.brokenpediabackend.profile.exception;

import pl.galajus.brokenpediabackend.common.exception.BusinessException;

public class ProfileException extends BusinessException {
    public ProfileException(String message) {
        super(message);
    }
}
