package pl.galajus.brokenpediabackend.admin.orb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.orb.model.AdminOrb;
import pl.galajus.brokenpediabackend.admin.orb.repository.AdminOrbRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrbService {

    private final AdminOrbRepository adminOrbRepository;

    public List<AdminOrb> getOrbs() {
        return adminOrbRepository.findAll();
    }

    public List<AdminOrb> saveAllOrbs(List<AdminOrb> orbs) {
        return adminOrbRepository.saveAll(orbs);
    }
}
