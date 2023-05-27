package pl.galajus.brokenpediabackend.user.build.exception;

import pl.galajus.brokenpediabackend.user.common.exception.BusinessException;

public class BuildValidationException extends BusinessException {

    public BuildValidationException(String message) {
        super(message);
    }
}
