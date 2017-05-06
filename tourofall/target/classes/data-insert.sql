INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('kmgeu123','7d44478749342b0007a4c78be0ee8cb7c050df9934aa88306ad8c85606a5ca5950e3044a5555a120','민규','강','ROLE_USER',false,'1992-11-25');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('mkyong','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','미경','김','ROLE_USER',true,'1980-4-28');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('alex','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','키퍼','알렉스','ROLE_USER',false,'1990-9-6');

INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id,item_title) VALUES('경복궁 재미 없어요','집에 가고 싶어요',1.5,1,126508,12,'경복궁');
INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id,item_title) VALUES('마쟁이들 천국입니다','돈 다일어요',1,2,131257,28,'렛츠런파크서울 (서울경마공원)');
INSERT INTO reviews(title,content,score,user_id,item_id,item_type_id,item_title) VALUES('Good Man','i like the horse',4,3,131257,28,'렛츠런파크서울 (서울경마공원)');
INSERT INTO questions(title,content,user_id,item_id,item_type_id) VALUES('경복궁은 어떻게 가야 빠르게 갑니까?','빠른 답변 부탁드립니다.',1,126508,12);
INSERT INTO answers(content,user_id,question_id) VALUES('나도 모릅니다.',1,1);
