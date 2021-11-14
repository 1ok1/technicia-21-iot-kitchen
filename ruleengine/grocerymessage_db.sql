drop database grocerymessagedb;
drop user groceryuser;
create user groceryuser with password 'password';
create database grocerymessagedb owner=groceryuser;
\connect grocerymessage;
alter default privileges grant all on tables to groceryuser;
alter default privileges grant all on sequences to groceryuser;

create table grocery_message(
        id integer primary key not null,
        grocery_id varchar(20) not null,
        available_quantity varchar(20) not null,
        user_id varchar(30) not null
);


