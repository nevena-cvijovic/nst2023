package nst.domaci.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity

@Table(name = "tbl_head_of_department_history")
public class HeadOfDepartmentHistory {

    @EmbeddedId
    private HeadOfDepartmentHistoryId id;
    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    private Department department;


    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @PastOrPresent(message = "Start date must be in past or in present")
    @NotNull(message = "Start date - required field")
    private LocalDate startDate;

    @Future(message = "End date must be in future")
    @NotNull(message = "End date - required field")
    private LocalDate endDate;

    public HeadOfDepartmentHistory() {
    }

    public HeadOfDepartmentHistory(Department department, Member member, LocalDate startDate, LocalDate endDate) {
        this.department = department;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
