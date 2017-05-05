create table users (
  user_id bigint(20) auto_increment,
  username varchar(45) not null,
  password varchar(255) not null,
  first_name varchar(45) not null,
  last_name varchar(45) not null,
  gender tinyint not null,
  birth timestamp not null,
  enabled tinyint not null default 1,
  constraint uc_users unique key(username),
  constraint pk_users primary key(user_id)
);

create table user_roles (
  user_role_id bigint(20) auto_increment,
  user_id int(11) not NULL,
  role varchar(45) not NULL,
  primary key (user_role_id),
  unique key uni_id_role (role,user_id),
  constraint fk_user_roles foreign key (user_id) references users (user_id)
);


create table reviews(
	review_id bigint(20) auto_increment,
	title varchar(45) not null,
	content varchar(255) not null,
	created_date timestamp default current_timestamp not null,
	score double default 0 not null,
	user_id int(11) not null,
	item_id int(11) not null,
	item_type_id int(11) not null,
	item_title varchar(45) not null,
	constraint pk_reviews primary key(review_id),
	constraint fk_reviews foreign key(user_id) references users(user_id)
);

create table questions(
	question_id bigint(20) auto_increment,
	title varchar(45) not null,
	content varchar(255) not null,
	created_date timestamp default current_timestamp not null,
	visitor int(11) default 0 not null,
	user_id int(11) not null,
	item_id int(11) not null,
	item_type_id int(11) not null,
	constraint pk_questions primary key(question_id),
	constraint fk_questions foreign key(user_id) references users(user_id)
);

create table answers(
	answer_id bigint(20) auto_increment,
	content varchar(100) not null,
	created_date timestamp default current_timestamp not null,
	user_id int(11) not null,
	question_id int(11) not null,
	constraint pk_answers primary key(answer_id),
	constraint fk_answers1 foreign key(user_id) references users(user_id),
	constraint fk_answers2 foreign key(question_id) references questions(question_id)
);