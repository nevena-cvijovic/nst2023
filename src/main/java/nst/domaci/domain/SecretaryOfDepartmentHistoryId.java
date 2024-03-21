package nst.domaci.domain;



import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SecretaryOfDepartmentHistoryId implements Serializable {

    private Long departmentId;

    private Long memberId;

    public SecretaryOfDepartmentHistoryId() {
    }

    public SecretaryOfDepartmentHistoryId(Long departmentId, Long memberId) {
        this.departmentId = departmentId;
        this.memberId = memberId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretaryOfDepartmentHistoryId that = (SecretaryOfDepartmentHistoryId) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, memberId);
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
