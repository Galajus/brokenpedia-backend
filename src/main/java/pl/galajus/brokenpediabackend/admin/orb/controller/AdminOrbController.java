package pl.galajus.brokenpediabackend.admin.orb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.orb.model.AdminOrb;
import pl.galajus.brokenpediabackend.admin.orb.service.AdminOrbService;

import java.util.List;

@RestController
@RequestMapping("/admin/orbs")
@RequiredArgsConstructor
public class AdminOrbController {

    private final AdminOrbService adminOrbService;

    @GetMapping
    public List<AdminOrb> getAllOrbs() {
        return adminOrbService.getOrbs();
    }

    @PostMapping
    public List<AdminOrb> saveAllOrbs(@RequestBody List<AdminOrb> orbs) {
        return adminOrbService.saveAllOrbs(orbs);
    }

    @GetMapping("/{id}")
    public AdminOrb getOrb(@PathVariable Long id) {
        return adminOrbService.getOrb(id);
    }

    @PutMapping
    public AdminOrb updateOrb(@RequestBody AdminOrb orb) {
        return adminOrbService.updateOrb(orb);
    }
}
