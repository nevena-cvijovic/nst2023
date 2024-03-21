create table tbl_member(
    id bigint unsigned not null AUTO_INCREMENT,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    academic_title_id bigint unsigned,
    education_title_id bigint unsigned,
    scientific_field_id bigint unsigned,
    department_id bigint unsigned,
    primary key (id),
    constraint academic_title_member_fk foreign key (academic_title_id) references tbl_academic_title(id),
    constraint education_title_member_fk foreign key (education_title_id) references tbl_education_title(id),
    constraint scientific_field_member_fk foreign key (scientific_field_id) references tbl_scientific_field(id),
    constraint dept_fk foreign key (department_id) references tbl_department(id)
);