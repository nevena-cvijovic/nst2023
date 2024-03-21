package nst.domaci.repository;

import nst.domaci.domain.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EducationTitleRepository extends JpaRepository<EducationTitle, Long> {
}
