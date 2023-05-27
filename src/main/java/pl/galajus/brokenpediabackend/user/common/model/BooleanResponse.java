package pl.galajus.brokenpediabackend.user.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BooleanResponse {
    private final boolean confirmed;
}
