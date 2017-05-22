create table users (
  user_id bigint(20) auto_increment,
  username varchar(45) not null,
  password varchar(255) not null,
  first_name varchar(45) not null,
  last_name varchar(45) not null,
  role varchar(45) not null,
  gender tinyint not null,
  birth timestamp not null,
  sign_in_provider varchar(45),
  constraint uc_users unique key(username),
  constraint pk_users primary key(user_id)
);
create table userConnection(
	userId varchar(255) not null,
	providerId varchar(255) not null,
	providerUserId varchar(255),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId)
);
create table user_preferences(
	user_preference_id bigint(20) auto_increment,
	user_id bigint(20) not null,
	item_category_code varchar(45) not null,
	constraint pk_user_preferences primary key(user_preference_id),
	constraint unique_user_preferences unique(user_id,item_category_code),
	constraint fk_user_preferences foreign key(user_id) references users(user_id)
);

create table today_destinations(
	today_destination_id bigint(20) auto_increment,
	title varchar(45) not null,
	address varchar(128) not null,
	destination_type varchar(45) not null,
	image_url varchar(128) not null,
	item_id int(11) not null,
	constraint pk_today_destinations primary key(today_destination_id),
	constraint unique_today_destinations unique(item_id)
);

create table evaluations(
	evaluation_id bigint(20) auto_increment,
	user_id bigint(20) not null,
	item_id int(11) not null,
	score double default 0 not null,
	constraint pk_evaluations primary key(evaluation_id),
	constraint unique_evaluations unique(user_id,item_id),
	constraint fk_evaluations foreign key(user_id) references users(user_id)
);
create table reviews(
	review_id bigint(20) auto_increment,
	title varchar(45) not null,
	content varchar(255) not null,
	created_date timestamp default current_timestamp not null,
	user_id int(11) not null,
	item_id int(11) not null,
	evaluation_id bigint(20) not null,
	constraint pk_reviews primary key(review_id),
	constraint unique_reviews unique(user_id,item_id),
	constraint fk_reviews1 foreign key(user_id) references users(user_id),
	constraint fk_reviews2 foreign key(evaluation_id) references evaluations(evaluation_id)
);

create table questions(
	question_id bigint(20) auto_increment,
	title varchar(45) not null,
	content varchar(255) not null,
	created_date timestamp default current_timestamp not null,
	visitor int(11) default 0 not null,
	user_id int(11) not null,
	item_id int(11) not null,
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
