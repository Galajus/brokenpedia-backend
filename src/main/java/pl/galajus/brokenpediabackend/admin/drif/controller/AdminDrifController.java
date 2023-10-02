package pl.galajus.brokenpediabackend.admin.drif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.galajus.brokenpediabackend.admin.drif.model.AdminDrif;
import pl.galajus.brokenpediabackend.admin.drif.service.AdminDrifService;

import java.util.List;

@RestController
@RequestMapping("/admin/drifs")
@RequiredArgsConstructor
public class AdminDrifController {

    private final AdminDrifService adminDrifService;

    @GetMapping
    public List<AdminDrif> getAllDrifs() {
        return adminDrifService.getDrifs();
    }

    @PostMapping
    public List<AdminDrif> saveAllDrifs(@RequestBody List<AdminDrif> drifs) {
        return adminDrifService.saveAll(drifs);
    }

}
