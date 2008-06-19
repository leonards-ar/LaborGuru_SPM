-- Delete
delete from tbl_profiles_permissions;
delete from tbl_users_profiles;
delete from tbl_profiles;
delete from tbl_menu_items;
delete from tbl_permissions;
delete from tbl_users;
delete from tbl_positions;
delete from tbl_stores;
delete from tbl_offices;

-- Profiles
insert into tbl_profiles (profile_id, name, description) values (null, 'ROLE_USER', 'Profile Desc');

-- Office
insert into tbl_offices (office_id, name, type, parent_office_id) values (null,'Mac Donalds', 'A', null);

-- Users
insert into tbl_users (creation_date, email, user_id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'mcapurro@gmail.com', null, null, 0, 'Mariano', '1234', 0, 'Capurro', 'mcapurro');
insert into tbl_users (creation_date, email, user_id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'cnunezre@gmail.com', null, null, 0, 'Cristian', '1234', 0, 'Nuñez Rebolledo', 'cnunezre');
insert into tbl_users (creation_date, email, user_id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'fbarrera@gmail.com', null, null, 0, 'Federico', '1234', 0, 'Barrera Oro', 'fbarrera');
insert into tbl_users (creation_date, email, user_id, last_update_date, login_count, name, password, status, surname, username) values (now(), 'ignacio@laborguru.com', null, null, 0, 'Ignacio', '1234', 0, 'Goris', 'igoris');

insert into tbl_users_profiles (profile_id, user_id) values (1, 1);
insert into tbl_users_profiles (profile_id, user_id) values (1, 2);
insert into tbl_users_profiles (profile_id, user_id) values (1, 3);
insert into tbl_users_profiles (profile_id, user_id) values (1, 4);


-- Permissions
insert into tbl_permissions (permission_id, key, description) values (null, 'FREE_ACCESS', 'Dummy permission that all profiles should have');
insert into tbl_permissions (permission_id, key, description) values (null, 'VIEW_EMPLOYEE', 'View and query employees of the own store');
insert into tbl_permissions (permission_id, key, description) values (null, 'EDIT_EMPLOYEE', 'Edit existing employees of the own store');
insert into tbl_permissions (permission_id, key, description) values (null, 'CREATE_EMPLOYEE', 'Create new employees in the own store');

insert into tbl_profiles_permissions (permission_id, profile_id) values (4, 1);
insert into tbl_profiles_permissions (permission_id, profile_id) values (1, 1);
insert into tbl_profiles_permissions (permission_id, profile_id) values (2, 1);
insert into tbl_profiles_permissions (permission_id, profile_id) values (3, 1);

-- Menu Items
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permission_id, position, target) values (null, 'home.menu.label', 'HOME_MENU_HELP', null, 1, 0, '/home/home.action');

insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permission_id, position, target) values (null, 'employee.menu.label', 'EMPLOYEE_MENU_HELP', null, 2, 0, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permission_id, position, target) values (null, 'employee.submenu.list', 'EMPLOYEE_LIST_HELP', 2, 3, 1, '/employee/employee_list.action');
insert into tbl_menu_items (menu_item_id, label_key, help_key, parent_id, permission_id, position, target) values (null, 'employee.submenu.create', 'EMPLOYEE_ADD_HELP', 2, 4, 0, '/employee/employee_add.action');

-- Store
insert into tbl_stores (store_id, name, office_id) values (null,'microcentro', 0);

-- Positions
insert into tbl_positions (position_id, name, store_id) values (null, 'chef',1);
insert into tbl_positions (position_id, name, store_id) values (null, 'waiter',1);
insert into tbl_positions (position_id, name, store_id) values (null, 'cook',1);
insert into tbl_positions (position_id, name, store_id) values (null, 'porter',1);

commit;

