INSERT INTO tbl_academic_title_history (member_id, academic_title_id, scientific_field_id, start_date, end_date)
VALUES (
           (SELECT id FROM tbl_member WHERE first_name = 'Sofia'),
           (SELECT academic_title_id FROM tbl_member WHERE id = (SELECT id FROM tbl_member WHERE first_name = 'Sofia')),
           (SELECT scientific_field_id FROM tbl_member WHERE id = (SELECT id FROM tbl_member WHERE first_name = 'Sofia')),
           '2020-12-14 13:33:04',  '2022-11-15 14:56:00'

       );

INSERT INTO tbl_academic_title_history (member_id, academic_title_id, scientific_field_id, start_date, end_date)
VALUES (
           (SELECT id FROM tbl_member WHERE first_name = 'Jake'),
           (SELECT academic_title_id FROM tbl_member WHERE id = (SELECT id FROM tbl_member WHERE first_name = 'Jake')),
           (SELECT scientific_field_id FROM tbl_member WHERE id = (SELECT id FROM tbl_member WHERE first_name = 'Jake')),
           '2021-11-11 13:33:04',  '2023-12-10 14:56:00'

       );