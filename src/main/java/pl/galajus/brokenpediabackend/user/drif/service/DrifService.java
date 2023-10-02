package pl.galajus.brokenpediabackend.user.drif.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.drif.model.Drif;
import pl.galajus.brokenpediabackend.user.drif.repository.DrifRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrifService {

    private final DrifRepository drifRepository;

    public List<Drif> getDrifs() {
        return drifRepository.findAll();
    }

}
