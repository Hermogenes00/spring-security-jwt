CREATE TABLE user_role (
   user_id varchar(25),
   role_name varchar(25),
   foreign key (user_id) references "user" (id),
   foreign key (role_name) references role (name)
);