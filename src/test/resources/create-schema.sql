create table ADDRESS (user_id int8 not null, city varchar(255), STREET_NAME varchar(255), zipcode varchar(255), primary key (user_id));
create table USER_DETAILS (user_id  bigserial not null, description text, joinDate date, USER_NAME varchar(255), primary key (user_id));