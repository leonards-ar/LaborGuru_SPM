-- SPM#202
insert into tbl_permissions (permission_id, name, description) values (48, 'POST_SHIFT', 'Post Schedule Shifts');
insert into tbl_profiles_permissions (profile_id, permission_id) values (2, 48);

-- SPM#203
insert into tbl_menu_items (menu_item_id, label_key, help_key, target, position, parent_id, permission_id) values (34, 'employee.submenu.uploademployeesdefinition', 'EMPLOYEES_UPLOAD_DEFINITION_HELP', '/employee/uploadDefinition_edit.action', 5, 5, 6);


-- SPM#
insert into tbl_permissions (permission_id, name, description) values ("43","SHOW_CUSTOMER_REPORT","Show Report for Customer Manager");
insert into tbl_permissions (permission_id, name, description) values ("44","SHOW_REGIONAL_REPORT","Show Report for Regional Manager");
insert into tbl_permissions (permission_id, name, description) values ("45","SHOW_AREA_REPORT", "Show Report for Area Manager");

insert into tbl_menu_items (menu_item_id, label_key, help_key, target, position, parent_id, permission_id) values ("31","report.CustomerManager.title","CUSTOMER_MANAGER_REPORT","/report/customerReport_showFirstReport.action?selectedDate=${selectedDate}&amp;selectedWeekDay=${selectedWeekDay}", "1","21","43");
insert into tbl_menu_items (menu_item_id, label_key, help_key, target, position, parent_id, permission_id) values ("32","report.CustomerManager.title","CUSTOMER_REGIONAL_REPORT","/report/regionalReport_showFirstReport.action?selectedDate=${selectedDate}&amp;selectedWeekDay=${selectedWeekDay}","2","21","44");
insert into tbl_menu_items (menu_item_id, label_key, help_key, target, position, parent_id, permission_id) values ("33","report.CustomerManager.title","CUSTOMER_AREA_REPORT","/report/areaReport_showFirstReport.action?selectedDate=${selectedDate}&amp;selectedWeekDay=${selectedWeekDay}","3","21","45");

insert into tbl_profiles_permissions (profile_id, permission_id) values(4,43);
insert into tbl_profiles_permissions (profile_id, permission_id) values(5,44);
insert into tbl_profiles_permissions (profile_id, permission_id) values(6,45);
 