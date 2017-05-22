INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('kmgeu123','7d44478749342b0007a4c78be0ee8cb7c050df9934aa88306ad8c85606a5ca5950e3044a5555a120','형규','강','ROLE_USER',false,'1997-4-21');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('mkyong','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','미경','김','ROLE_USER',true,'1980-4-28');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('alex','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','키퍼','알렉스','ROLE_USER',false,'1990-9-6');

INSERT INTO evaluations(user_id,item_id,score) VALUES(1,126508,1.5);
INSERT INTO evaluations(user_id,item_id,score) VALUES(1,131257,4);
INSERT INTO evaluations(user_id,item_id,score) VALUES(2,131257,3);
INSERT INTO evaluations(user_id,item_id,score) VALUES(3,131257,2);

INSERT INTO user_preferences(user_id,item_category_code) VALUES(1,'A0101');
INSERT INTO user_preferences(user_id,item_category_code) VALUES(1,'A0202');

INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('경복궁 재미 없어요','집에 가고 싶어요',1,126508,1);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('경마장 재미 없어요','집에 가고 싶어요',1,131257,2);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('마쟁이들 천국입니다','돈 다일어요',2,131257,3);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('Good Man','i like the horse',3,131257,4);
INSERT INTO questions(title,content,user_id,item_id) VALUES('경복궁은 어떻게 가야 빠르게 갑니까?','빠른 답변 부탁드립니다.',1,126508);
INSERT INTO answers(content,user_id,question_id) VALUES('알려줘요',1,1);
INSERT INTO answers(content,user_id,question_id) VALUES('부톽해여.',1,1);

INSERT INTO today_destinations(title,address,destination_type,image_url,item_id) VALUES('제부도','경기도 화성시 서신면 제부말길 96(서신면)','자연관광지','http://tong.visitkorea.or.kr/cms/resource/34/2482734_image2_1.jpg','125503');
INSERT INTO today_destinations(title,address,destination_type,image_url,item_id) VALUES('북촌한옥마을','서울특별시 종로구 계동길 37(계동)','역사관광지','http://tong.visitkorea.or.kr/cms/resource/72/2363672_image2_1.jpg','126537');
INSERT INTO today_destinations(title,address,destination_type,image_url,item_id) VALUES('휴애리자연생활공원','제주특별자치도 서귀포시 남원읍 신례동로 256(남원읍)','휴양관광지','http://tong.visitkorea.or.kr/cms/resource/02/2038802_image2_1.jpg','322836');
