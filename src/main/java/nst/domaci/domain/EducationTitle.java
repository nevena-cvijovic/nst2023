package nst.domaci.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="tbl_education_title")
public class EducationTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Education title name - required field")
    private String name;

    public EducationTitle() {
    }

    public EducationTitle(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
