CREATE TABLE tbl_users (
       user_id INT NOT NULL
     , username VARCHAR(255)
     , password VARCHAR(255)
     , status INT
     , email VARCHAR(255)
     , name VARCHAR(255)
     , surname VARCHAR(255)
     , PRIMARY KEY (user_id)
);

CREATE TABLE tbl_permissions (
       permission_id INT NOT NULL
     , key VARCHAR(100) NOT NULL
     , description VARCHAR(255)
     , PRIMARY KEY (permission_id)
);

CREATE TABLE tbl_offices (
       office_id INT NOT NULL
     , name VARCHAR(255)
     , type CHAR(1)
     , parent_office_id INT
     , PRIMARY KEY (office_id)
);

CREATE TABLE tbl_profiles (
       profile_id INT NOT NULL
     , description VARCHAR(255) NOT NULL
     , PRIMARY KEY (profile_id)
);

CREATE TABLE tbl_office_ext_attributes (
       attribute_id INT NOT NULL
     , label_key VARCHAR(150)
     , type_id INT
     , help_key INT
     , office_id INT
     , PRIMARY KEY (attribute_id)
     , CONSTRAINT FK_tbl_office_ext_attributes_1 FOREIGN KEY (office_id)
                  REFERENCES tbl_offices (office_id)
);

CREATE TABLE tbl_stores (
       store_id INT NOT NULL
     , name VARCHAR(255) NOT NULL
     , office_id INT
     , PRIMARY KEY (store_id)
     , CONSTRAINT FK_tbl_stores_1 FOREIGN KEY (office_id)
                  REFERENCES tbl_offices (office_id)
);

CREATE TABLE tbl_user_ext_attributes (
       user_id INT NOT NULL
     , attribute_id INT NOT NULL
     , attribute_value TEXT
     , PRIMARY KEY (user_id, attribute_id)
     , CONSTRAINT FK_tbl_user_ext_attributes_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
     , CONSTRAINT FK_tbl_user_ext_attributes_2 FOREIGN KEY (attribute_id)
                  REFERENCES tbl_office_ext_attributes (attribute_id)
);

CREATE TABLE tbl_profile_permissions (
       profile_id INT NOT NULL
     , permission_id INT NOT NULL
     , PRIMARY KEY (profile_id, permission_id)
     , CONSTRAINT FK_tbl_profile_permissions_1 FOREIGN KEY (profile_id)
                  REFERENCES tbl_profiles (profile_id)
     , CONSTRAINT FK_tbl_profile_permissions_2 FOREIGN KEY (permission_id)
                  REFERENCES tbl_permissions (permission_id)
);

CREATE TABLE tbl_store_managers (
       user_id INT NOT NULL
     , store_id INT NOT NULL
     , PRIMARY KEY (user_id, store_id)
     , CONSTRAINT FK_tbl_store_managers_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
     , CONSTRAINT FK_tbl_store_managers_2 FOREIGN KEY (store_id)
                  REFERENCES tbl_stores (store_id)
);

CREATE TABLE tbl_store_employees (
       user_id INT NOT NULL
     , store_id INT NOT NULL
     , PRIMARY KEY (user_id, store_id)
     , CONSTRAINT FK_tbl_store_employees_1 FOREIGN KEY (store_id)
                  REFERENCES tbl_stores (store_id)
     , CONSTRAINT FK_tbl_store_employees_2 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
);

CREATE TABLE tbl_menu_items (
       menu_item_id INT NOT NULL
     , label_key VARCHAR(150) NOT NULL
     , help_key VARCHAR(150)
     , permission_id INT
     , position INT
     , target VARCHAR(255)
     , PRIMARY KEY (menu_item_id)
     , CONSTRAINT FK_tbl_menu_items_1 FOREIGN KEY (permission_id)
                  REFERENCES tbl_permissions (permission_id)
);

CREATE TABLE tbl_office_users (
       office_id INT NOT NULL
     , user_id INT NOT NULL
     , PRIMARY KEY (office_id, user_id)
     , CONSTRAINT FK_tbl_office_users_1 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
     , CONSTRAINT FK_tbl_office_users_2 FOREIGN KEY (office_id)
                  REFERENCES tbl_offices (office_id)
);

CREATE TABLE tbl_store_ext_attributes (
       store_id INT NOT NULL
     , attribute_id INT NOT NULL
     , attribute_value TEXT
     , PRIMARY KEY (store_id, attribute_id)
     , CONSTRAINT FK_tbl_store_ext_attributes_1 FOREIGN KEY (store_id)
                  REFERENCES tbl_stores (store_id)
     , CONSTRAINT FK_tbl_store_ext_attributes_2 FOREIGN KEY (attribute_id)
                  REFERENCES tbl_office_ext_attributes (attribute_id)
);

CREATE TABLE tbl_user_profiles (
       profile_id INT NOT NULL
     , user_id INT NOT NULL
     , PRIMARY KEY (profile_id, user_id)
     , CONSTRAINT FK_tbl_user_profiles_1 FOREIGN KEY (profile_id)
                  REFERENCES tbl_profiles (profile_id)
     , CONSTRAINT FK_tbl_user_profiles_2 FOREIGN KEY (user_id)
                  REFERENCES tbl_users (user_id)
);

