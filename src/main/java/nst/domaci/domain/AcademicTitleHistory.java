package nst.domaci.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity

@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {

    @EmbeddedId
    private AcademicTitleHistoryId id;
    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne
    @MapsId("academicTitleID")
    @JoinColumn(name= "academic_title_id")
    private AcademicTitle academicTitle;

    @PastOrPresent(message = "Start date must be in past or in present")
    @NotNull(message = "Start date - required field")
    private LocalDate startDate;

    @Future(message = "End date must be in future")
    @NotNull(message = "End date - required field")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;


    public AcademicTitleHistory() {
    }


    public AcademicTitleHistory(Member member, AcademicTitle academicTitle, LocalDate startDate, LocalDate endDate, ScientificField scientificField) {
        this.member = member;
        this.academicTitle = academicTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scientificField = scientificField;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
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

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }
}
