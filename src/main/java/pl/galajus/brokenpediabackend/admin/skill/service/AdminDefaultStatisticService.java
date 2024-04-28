package pl.galajus.brokenpediabackend.admin.skill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.admin.skill.model.AdminDefaultStatistic;
import pl.galajus.brokenpediabackend.admin.skill.repository.AdminDefaultStatisticRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDefaultStatisticService {

    private final AdminDefaultStatisticRepository adminDefaultStatisticRepository;

    public List<AdminDefaultStatistic> getAll() {
        return adminDefaultStatisticRepository.findAll();
    }

}
