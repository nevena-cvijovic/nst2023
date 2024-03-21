package nst.domaci.dto;

import java.util.List;

public class MemberHistoryDto {

    private Long id;


    private String firstName;


    private String lastName;


    private AcademicTitleDto academicTitle;


    private EducationTitleDto educationTitle;


    private ScientificFieldDto scientificField;


    private DepartmentDto department;

    private List<AcademicTitleHistoryDto> historyOfAcademicTitles;

    public MemberHistoryDto() {
    }

    public MemberHistoryDto(Long id, String firstName, String lastName, AcademicTitleDto academicTitle, EducationTitleDto educationTitle, ScientificFieldDto scientificField, DepartmentDto department, List<AcademicTitleHistoryDto> historyList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.scientificField = scientificField;
        this.department = department;
        this.historyOfAcademicTitles = historyList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AcademicTitleDto getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitleDto academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitleDto getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitleDto educationTitle) {
        this.educationTitle = educationTitle;
    }

    public ScientificFieldDto getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificFieldDto scientificField) {
        this.scientificField = scientificField;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public List<AcademicTitleHistoryDto> getHistoryOfAcademicTitles() {
        return historyOfAcademicTitles;
    }

    public void setHistoryOfAcademicTitles(List<AcademicTitleHistoryDto> historyOfAcademicTitles) {
        this.historyOfAcademicTitles = historyOfAcademicTitles;
    }
}
