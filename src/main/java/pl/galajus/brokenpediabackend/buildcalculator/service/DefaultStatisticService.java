package pl.galajus.brokenpediabackend.buildcalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.buildcalculator.model.DefaultStatistic;
import pl.galajus.brokenpediabackend.buildcalculator.repository.DefaultStatisticRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultStatisticService {

    private final DefaultStatisticRepository defaultStatisticRepository;

    public List<DefaultStatistic> getAll() {
        return defaultStatisticRepository.findAll();
    }

}
