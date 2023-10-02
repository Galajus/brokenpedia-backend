package pl.galajus.brokenpediabackend.user.drif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.drif.model.Drif;
import pl.galajus.brokenpediabackend.user.drif.service.DrifService;

import java.util.List;

@RestController
@RequestMapping("/drifs")
@RequiredArgsConstructor
public class DrifController {

    private final DrifService drifService;

    @GetMapping
    public List<Drif> getAllDrifs() {
        return drifService.getDrifs();
    }

}
