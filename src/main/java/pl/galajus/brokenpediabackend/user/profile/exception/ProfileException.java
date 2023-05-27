package pl.galajus.brokenpediabackend.user.profile.exception;

import pl.galajus.brokenpediabackend.user.common.exception.BusinessException;

public class ProfileException extends BusinessException {
    public ProfileException(String message) {
        super(message);
    }
}
