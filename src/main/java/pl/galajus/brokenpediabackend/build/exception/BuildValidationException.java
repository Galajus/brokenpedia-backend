package pl.galajus.brokenpediabackend.build.exception;

import pl.galajus.brokenpediabackend.common.exception.BusinessException;

public class BuildValidationException extends BusinessException {

    public BuildValidationException(String message) {
        super(message);
    }
}
