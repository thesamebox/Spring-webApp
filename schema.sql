create table if not exists PRODUCT (
    id long auto_increment primary key,
    name varchar(255),
    cost double
);
create table if not exists USERS (
    id long auto_increment primary key,
    name varchar(255),
);
create table if not exists USERS_PRODUCT (
    userid long not null,
    productid long not null,
    ex_actual_cost double,
    foreign key (userid) references USERS (id),
    foreign key (productid) references PRODUCT (id)
);