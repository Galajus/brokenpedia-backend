package pl.galajus.brokenpediabackend.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BooleanResponse {
    private final boolean confirmed;
}
