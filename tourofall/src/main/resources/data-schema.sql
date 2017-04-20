create table users (
  id varchar(45) not null,
  password varchar(255) not null,
  name varchar(45) not null,
  enabled tinyint not null default 1 ,
  constraint pk_users primary key(id)
);

create table userroles (
  user_role_id int(11) not NULL auto_increment,
  id varchar(45) not NULL,
  role varchar(45) not NULL,
  primary key (user_role_id),
  unique key uni_id_role (role,id),
  constraint fk_userroles foreign key (id) references users (id)
);