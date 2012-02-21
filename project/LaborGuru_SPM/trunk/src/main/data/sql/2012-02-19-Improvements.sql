-- SPM#202
insert into tbl_permissions (permission_id, name, description) values (48, 'POST_SHIFT', 'Post Schedule Shifts');
insert into tbl_profiles_permissions (profile_id, permission_id) values (2, 48);

-- SPM#203
insert into tbl_menu_items (menu_item_id, label_key, help_key, target, position, parent_id, permission_id) values (34, 'employee.submenu.uploademployeesdefinition', 'EMPLOYEES_UPLOAD_DEFINITION_HELP', '/employee/uploadDefinition_edit.action', 5, 5, 6);
