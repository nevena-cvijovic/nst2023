package nst.domaci.repository;

import nst.domaci.domain.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AcademicTitleRepository extends JpaRepository<AcademicTitle,Long> {
}
