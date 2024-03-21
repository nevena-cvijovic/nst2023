package nst.domaci.repository;

import nst.domaci.domain.AcademicTitleHistory;
import nst.domaci.domain.AcademicTitleHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, AcademicTitleHistoryId> {

    List<AcademicTitleHistory> findByMemberId(Long memberId);
}
