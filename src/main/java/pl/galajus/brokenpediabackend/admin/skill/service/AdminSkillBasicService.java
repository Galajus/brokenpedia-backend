package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminSkillBasic;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminSkillBasicRepository;

@Service
@RequiredArgsConstructor
public class AdminSkillBasicService {

    private final AdminSkillBasicRepository adminSkillBasicRepository;

    public AdminSkillBasic saveSkillBasic(AdminSkillBasic adminSkillBasic) {
        return this.adminSkillBasicRepository.save(adminSkillBasic);
    }

    public void delete(Long id) {
        adminSkillBasicRepository.deleteById(id);
    }
}
