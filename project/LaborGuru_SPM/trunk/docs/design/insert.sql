-- Delete
delete from tbl_profiles_permissions;
delete from tbl_users_profiles;
delete from tbl_profiles;
delete from tbl_menu_items;
delete from tbl_permissions;
delete from tbl_users;

-- Profiles
insert into tbl_profiles (id, name, description) values (1, 'ROLE_USER', 'Profile Desc');

-- Users
insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'mcapurro@gmail.com', 1, null, 0, 'Mariano', '1234', 0, 'Capurro', 'mcapurro');
insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'cnunezre@gmail.com', 2, null, 0, 'Cristian', '1234', 0, 'Nuñez Rebolledo', 'cnunezre');
insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'fbarrera@gmail.com', 3, null, 0, 'Federico', '1234', 0, 'Barrera Oro', 'fbarrera');
insert into tbl_users (creation_date, email, id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'ignacio@laborguru.com', 4, null, 0, 'Ignacio', '1234', 0, 'Goris', 'igoris');

insert into tbl_users_profiles (profileid, userid) values (1, 1);
insert into tbl_users_profiles (profileid, userid) values (1, 2);
insert into tbl_users_profiles (profileid, userid) values (1, 3);
insert into tbl_users_profiles (profileid, userid) values (1, 4);

-- Permissions
insert into tbl_permissions (id, key, description) values (0, 'FREE_ACCESS', 'Dummy permission that all profiles should have');
insert into tbl_permissions (id, key, description) values (1, 'VIEW_EMPLOYEE', 'View and query employees of the own store');
insert into tbl_permissions (id, key, description) values (2, 'EDIT_EMPLOYEE', 'Edit existing employees of the own store');
insert into tbl_permissions (id, key, description) values (3, 'CREATE_EMPLOYEE', 'Create new employees in the own store');

insert into tbl_profiles_permissions (permissionid, profileid) values (0, 1);
insert into tbl_profiles_permissions (permissionid, profileid) values (1, 1);
insert into tbl_profiles_permissions (permissionid, profileid) values (2, 1);
insert into tbl_profiles_permissions (permissionid, profileid) values (3, 1);

-- Menu Items
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (1, 'home.menu.label', 'HOME_MENU_HELP', null, 0, 0, '/home/home.action');

insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (2, 'employee.menu.label', 'EMPLOYEE_MENU_HELP', null, 1, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (3, 'employee.submenu.list', 'EMPLOYEE_LIST_HELP', 2, 2, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (4, 'employee.submenu.create', 'EMPLOYEE_ADD_HELP', 2, 3, 1, '/employee/employee_add.action');

commit;


-- Other data Test
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (4, 'Otra opcion', 'EMPLOYEE_MENU_HELP', null, 1, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (5, 'Otra subopcion 1', 'EMPLOYEE_ADD_HELP', 4, 3, 1, '/employee/employee_add.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (6, 'Otra subopcion 2', 'EMPLOYEE_LIST_HELP', 4, 2, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permissionid, position, target) values (7, 'Otra subopcion sin permisos 3', 'EMPLOYEE_LIST_HELP', 4, 4, 0, '/employee/employee_list.action');

commit;