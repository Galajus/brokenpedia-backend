package pl.galajus.brokenpediabackend.user.items.legendary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.user.items.legendary.model.EpicDedicatedMod;
import pl.galajus.brokenpediabackend.user.items.legendary.repository.EpicDedicatedModRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicDedicatedModService {

    private final EpicDedicatedModRepository epicDedicatedModRepository;

    public List<EpicDedicatedMod> getAll() {
        return epicDedicatedModRepository.findAll();
    }

}
