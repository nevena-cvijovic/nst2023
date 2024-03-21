package nst.domaci.repository;

import nst.domaci.domain.SecretaryOfDepartmentHistory;
import nst.domaci.domain.SecretaryOfDepartmentHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaryOfDepartmentHistoryRepository extends JpaRepository<SecretaryOfDepartmentHistory, SecretaryOfDepartmentHistoryId> {
}
