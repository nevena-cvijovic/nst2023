create table tbl_secretary_of_department_history(
    department_id bigint unsigned not null,
    member_id bigint unsigned not null,
    start_date date,
    end_date date,
    primary key (department_id,member_id),
    constraint department_secretary_fk foreign key (department_id) references tbl_department(id),
    constraint member_secretary_fk foreign key (department_id) references tbl_member(id)
);