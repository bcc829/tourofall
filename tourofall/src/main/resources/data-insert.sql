INSERT INTO users(username,password,name,gender,birth,enabled) VALUES ('kmgeu123','1234','강민규',false,'1992-11-25', true);
INSERT INTO users(username,password,name,gender,birth,enabled) VALUES ('mkyong','123456','김미경',true,'1980-4-28', true);
INSERT INTO users(username,password,name,gender,birth,enabled) VALUES ('alex','123456','알렉스',false,'1990-9-6', true);
INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO user_roles (user_id, role) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_roles (user_id, role) VALUES (3, 'ROLE_USER');
INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id) VALUES('경복궁 재미 없어요','집에 가고 싶어요',1.5,1,126508,12);
INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id) VALUES('마쟁이들 천국입니다','돈 다일어요',1,2,131257,28);
INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id) VALUES('Good Man','i like the horse',4,3,131257,28);