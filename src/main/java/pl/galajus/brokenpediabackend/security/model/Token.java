package pl.galajus.brokenpediabackend.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String token;
    private boolean adminAccess;
    private boolean moderatorAccess;
}
