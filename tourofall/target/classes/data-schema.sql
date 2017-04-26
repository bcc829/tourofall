create table users (
  user_id int(11) not null auto_increment,
  username varchar(45) not null,
  password varchar(255) not null,
  name varchar(45) not null,
  enabled tinyint not null default 1 ,
  constraint uc_users unique key(username),
  constraint pk_users primary key(user_id)
);

create table user_roles (
  user_role_id int(11) not NULL auto_increment,
  user_id int(11) not NULL,
  role varchar(45) not NULL,
  primary key (user_role_id),
  unique key uni_id_role (role,user_id),
  constraint fk_user_roles foreign key (user_id) references users (user_id)
);


create table comments(
	comment_id int(11) auto_increment,
	title varchar(45) not null,
	content varchar(255) not null,
	created_date timestamp default current_timestamp not null,
	visitor int(11) default 0 not null,
	score double default 0 not null,
	user_id int(11) not null,
	item_id int(11) not null,
	constraint pk_comments primary key(comment_id),
	constraint fk_comments foreign key(user_id) references users(user_id)
);