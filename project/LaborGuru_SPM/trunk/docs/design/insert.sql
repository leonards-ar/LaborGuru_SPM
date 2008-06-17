insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'mail', 1, null, 0, 'Name', '1234', 0, 'Surname', 'user');

insert into tbl_profiles (id, name, description) values (1, 'ROLE_USER', 'Profile Desc');
update tbl_profiles set name = 'ROLE_USER';
insert into tbl_users_profiles (profileid, userid) values (1, 1);
commit;

select * from tbl_users;


insert into tbl_permissions (id, key, description) values (1, 'VIEW_EMPLOYEE', 'View and query employees of the own store');
insert into tbl_permissions (id, key, description) values (2, 'EDIT_EMPLOYEE', 'Edit existing employees of the own store');
insert into tbl_permissions (id, key, description) values (3, 'CREATE_EMPLOYEE', 'Create new employees in the own store');

insert into tbl_profiles_permissions (permissionid, profileid) values (1, 1);
insert into tbl_profiles_permissions (permissionid, profileid) values (2, 1);
insert into tbl_profiles_permissions (permissionid, profileid) values (3, 1);

insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (1, 'EMPLOYEE_MENU_LABEL', 'EMPLOYEE_MENU_HELP', null, 1, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (2, 'EMPLOYEE_CREATE_LABEL', 'EMPLOYEE_ADD_HELP', 1, 3, 0, '/employee/employee_add.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (3, 'EMPLOYEE_LIST_LABEL', 'EMPLOYEE_LIST_HELP', 1, 2, 0, '/employee/employee_list.action');

commit