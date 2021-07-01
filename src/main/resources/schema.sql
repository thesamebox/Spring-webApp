create table if not exists PRODUCT (
    id long auto_increment primary key,
    name varchar(255),
    cost double
);
create table if not exists USER (
    id long auto_increment primary key,
    name varchar(255)
);
create table if not exists USERS_PRODUCTS (
    user_id long not null,
    product_id long not null,
    ex_actual_cost double,
    foreign key (user_id) references USER (id),
    foreign key (product_id) references PRODUCT (id)
);