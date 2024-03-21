package nst.domaci.repository;

import nst.domaci.domain.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
}
