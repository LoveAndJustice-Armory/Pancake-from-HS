-- 应用启动时，会基于数据库执行 根类路径下的schema.sql文件
create table if not exists Pancake_Order (
    id identity,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    province varchar(50) not null,
    country varchar(10) not null,
    postCode varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Pancake (
    id identity,
    name varchar(50) not null,
    pancake_order bigint not null,
    pancake_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists Ingredient (
    id varchar(4) not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Ingredient_Ref (
    ingredient varchar(4) not null,
    pancake bigint not null,
    pancake_key bigint not null
);

alter table Pancake
    add foreign key (pancake_order) references Pancake_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);