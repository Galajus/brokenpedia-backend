package pl.galajus.brokenpediabackend.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class DefaultErrorDto {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
