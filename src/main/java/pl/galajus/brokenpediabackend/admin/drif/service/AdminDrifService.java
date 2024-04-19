package pl.galajus.brokenpediabackend.admin.drif.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.drif.model.AdminDrif;
import pl.galajus.brokenpediabackend.admin.drif.repository.AdminDrifRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDrifService {

    private final AdminDrifRepository adminDrifRepository;

    public List<AdminDrif> getDrifs() {
        return adminDrifRepository.findAll();
    }

    public List<AdminDrif> saveAll(List<AdminDrif> drifs) {
        return adminDrifRepository.saveAll(drifs);
    }

    public AdminDrif getDrif(Long id) {
        return adminDrifRepository.findById(id).orElseThrow();
    }

    public AdminDrif updateDrif(AdminDrif drif) {
        return adminDrifRepository.save(drif);
    }
}
