package nst.domaci.repository;

import nst.domaci.domain.HeadOfDepartmentHistory;
import nst.domaci.domain.HeadOfDepartmentHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadOfDepartmentHistoryRepository extends JpaRepository<HeadOfDepartmentHistory, HeadOfDepartmentHistoryId> {
}
