insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("Jake", "Smith",
(select (id) from tbl_academic_title where name = "full professor"),
(select (id) from tbl_education_title where name = "Doctoral degree"),
(select (id) from tbl_scientific_field where name = "Algorithms"),
(select(id) from tbl_department where name = "department-6"));


insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("Sofia", "Carson",
        (select (id) from tbl_academic_title where name = "associate professor"),
        (select (id) from tbl_education_title where name = "Master's degree"),
        (select (id) from tbl_scientific_field where name = "Databases"),
        (select(id) from tbl_department where name = "department-3"));

insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("Robbie", "McAvoy",
        (select (id) from tbl_academic_title where name = "teaching assistant without doctorate"),
        (select (id) from tbl_education_title where name = "Bachelor's degree"),
        (select (id) from tbl_scientific_field where name = "Data structures"),
        (select(id) from tbl_department where name = "department-4"));