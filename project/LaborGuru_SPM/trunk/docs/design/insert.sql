insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'mail', 1, null, 0, 'Name', '1234', 0, 'Surname', 'user');

insert into tbl_profiles (id, name, description) values (1, 'ROLE_USER', 'Profile Desc');
update tbl_profiles set name = 'ROLE_USER';
insert into tbl_users_profiles (profileid, userid) values (1, 1);
commit;

select * from tbl_users;