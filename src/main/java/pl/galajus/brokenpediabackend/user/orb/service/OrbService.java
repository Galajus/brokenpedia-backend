package pl.galajus.brokenpediabackend.user.orb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.orb.model.Orb;
import pl.galajus.brokenpediabackend.user.orb.repository.OrbRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrbService {

    private final OrbRepository orbRepository;

    public List<Orb> getOrbs() {
        return orbRepository.findAll();
    }
}
