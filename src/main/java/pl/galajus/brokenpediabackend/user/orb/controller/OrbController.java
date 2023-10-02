package pl.galajus.brokenpediabackend.user.orb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.user.orb.model.Orb;
import pl.galajus.brokenpediabackend.user.orb.service.OrbService;

import java.util.List;

@RestController
@RequestMapping("/orbs")
@RequiredArgsConstructor
public class OrbController {

    private final OrbService orbService;

    @GetMapping
    public List<Orb> getAllOrbs() {
        return orbService.getOrbs();
    }

}
