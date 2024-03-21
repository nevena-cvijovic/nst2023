package nst.domaci.dto;


import jakarta.validation.constraints.NotNull;


public class MemberDto {

    private Long id;

    @NotNull(message = "Member firstname - required field")
    private String firstName;

    @NotNull(message = "Member lastname - required field")
    private String lastName;

    @NotNull(message = "Member academic title - required field")
    private AcademicTitleDto academicTitle;

    @NotNull(message = "Member education title - required field")
    private EducationTitleDto educationTitle;

    @NotNull(message = "Member scientific field - required field")
    private ScientificFieldDto scientificField;

    @NotNull(message = "Member department - required field")
    private DepartmentDto department;


    public MemberDto() {
    }

    public MemberDto(Long id, String firstName, String lastName, AcademicTitleDto academicTitle, EducationTitleDto educationTitle, ScientificFieldDto scientificField, DepartmentDto department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.scientificField = scientificField;
        this.department = department;
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
}
