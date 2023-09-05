package pl.galajus.brokenpediabackend.admin.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestion;
import pl.galajus.brokenpediabackend.admin.suggestion.model.AdminSuggestionStatus;

public interface AdminSuggestionRepository extends JpaRepository<AdminSuggestion, Long> {

    @Modifying
    @Query("update AdminSuggestion s set s.status = :status where s.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") AdminSuggestionStatus status);

    @Modifying
    @Query("update AdminSuggestion s set s.adminComment = :comment where s.id = :id")
    void updateAdminComment(@Param("id") Long id, @Param("comment") String comment);
}
