create table tbl_academic_title_history(
    member_id bigint unsigned not null,
    academic_title_id bigint unsigned not null,
    start_date date,
    end_date date,
    scientific_field_id bigint unsigned,
    primary key (member_id,academic_title_id),
    constraint member_fk foreign key (member_id)references tbl_member(id),
    constraint academic_title_fk foreign key (academic_title_id)references tbl_academic_title(id),
    constraint scientific_field_fk foreign key (scientific_field_id) references tbl_scientific_field(id)
);