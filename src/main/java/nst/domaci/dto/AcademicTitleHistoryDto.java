package nst.domaci.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

public class AcademicTitleHistoryDto implements Serializable {

    @NotNull(message = "Member - required field")
    private MemberDto memberDto;

    @NotNull(message = "Academic title - required field")
    private AcademicTitleDto academicTitleDto;

    @PastOrPresent(message = "Start date must be in past or in present")
    @NotNull(message = "Start date - required field")
    private LocalDate startDate;

    @Future(message = "End date must be in future")
    @NotNull(message = "End date - required field")
    private LocalDate endDate;

    @NotNull(message = "Scientific field - required field")
    private ScientificFieldDto scientificFieldDto;

    public AcademicTitleHistoryDto() {
    }

    public AcademicTitleHistoryDto(MemberDto memberDto, AcademicTitleDto academicTitleDto, LocalDate startDate, LocalDate endDate, ScientificFieldDto scientificFieldDto) {
        this.memberDto = memberDto;
        this.academicTitleDto = academicTitleDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scientificFieldDto = scientificFieldDto;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public AcademicTitleDto getAcademicTitleDto() {
        return academicTitleDto;
    }

    public void setAcademicTitleDto(AcademicTitleDto academicTitleDto) {
        this.academicTitleDto = academicTitleDto;
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

    public ScientificFieldDto getScientificFieldDto() {
        return scientificFieldDto;
    }

    public void setScientificFieldDto(ScientificFieldDto scientificFieldDto) {
        this.scientificFieldDto = scientificFieldDto;
    }
}
