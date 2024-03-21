package nst.domaci.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

public class HeadOfDepartmentHistoryDto implements Serializable {
    @NotNull(message = "Department - required field")
    private DepartmentDto departmentDto;

    @NotNull(message = "Member - required field")
    private MemberDto memberDto;

    @PastOrPresent(message = "Start date must be in past or in present")
    @NotNull(message = "Start date - required field")
    private LocalDate startDate;

    @Future(message = "End date must be in future")
    @NotNull(message = "End date - required field")
    private LocalDate endDate;

    public HeadOfDepartmentHistoryDto() {
    }

    public HeadOfDepartmentHistoryDto(DepartmentDto departmentDto, MemberDto memberDto, LocalDate startDate, LocalDate endDate) {
        this.departmentDto = departmentDto;
        this.memberDto = memberDto;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
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
