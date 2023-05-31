package pl.galajus.brokenpediabackend.user.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.galajus.brokenpediabackend.user.profile.model.Profile;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Modifying
    @Query("update User u set u.nickname = ?2 where u.uuid = ?1")
    void updateNickname(UUID uuid, String nickname);

    @Query("select (count(p) > 0) from Profile p where p.nickname = ?1")
    boolean existsByNickname(String nickname);
}
