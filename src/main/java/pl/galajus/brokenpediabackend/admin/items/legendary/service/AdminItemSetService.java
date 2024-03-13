package pl.galajus.brokenpediabackend.admin.items.legendary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.items.legendary.model.AdminItemSet;
import pl.galajus.brokenpediabackend.admin.items.legendary.repository.AdminItemSetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminItemSetService {

    private final AdminItemSetRepository adminItemSetRepository;

    public List<AdminItemSet> getAll() {
        return adminItemSetRepository.findAll();
    }

    public AdminItemSet create(AdminItemSet adminSet) {
        return adminItemSetRepository.save(adminSet);
    }

    public AdminItemSet update(AdminItemSet adminSet) {
        return adminItemSetRepository.save(adminSet);
    }

    @Transactional
    public AdminItemSet getById(Long id) {
        AdminItemSet itemSet = adminItemSetRepository.findByIdWithPsychoEffects(id).orElseThrow();
        //Second fetch override to saved itemSet https://vladmihalcea.com/hibernate-multiplebagfetchexception/
        adminItemSetRepository.findByIdWithCustomEffects(id).orElseThrow();
        return itemSet;
    }
    public void deleteById(Long id) {
        adminItemSetRepository.deleteById(id);
    }
}
