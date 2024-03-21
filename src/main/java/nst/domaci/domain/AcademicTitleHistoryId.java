package nst.domaci.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AcademicTitleHistoryId implements Serializable {
    private Long memberId;
    private Long academicTitleID;

    public AcademicTitleHistoryId() {
    }

    public AcademicTitleHistoryId(Long memberId, Long academicTitleID) {
        this.memberId = memberId;
        this.academicTitleID = academicTitleID;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAcademicTitleID() {
        return academicTitleID;
    }

    public void setAcademicTitleID(Long academicTitleID) {
        this.academicTitleID = academicTitleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicTitleHistoryId that = (AcademicTitleHistoryId) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(academicTitleID, that.academicTitleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, academicTitleID);
    }
}
