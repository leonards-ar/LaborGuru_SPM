    create table tbl_store_variable_definitions (
        variable_definition_id integer not null auto_increment,
        name varchar(100),
        store_id integer,
        variable_index integer not null,
        primary key (variable_definition_id)
    );
    ALTER TABLE tbl_store_variable_definitions AUTO_INCREMENT=1000;

    alter table tbl_store_variable_definitions
        add index fk_stores_variable_definitions (store_id),
        add constraint fk_stores_variable_definitions
        foreign key (store_id)
        references tbl_stores (store_id);


ALTER TABLE tbl_stores ADD distribution_type varchar(255);