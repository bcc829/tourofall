INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('kmgeu123','7d44478749342b0007a4c78be0ee8cb7c050df9934aa88306ad8c85606a5ca5950e3044a5555a120','����','��','ROLE_USER',false,'1997-4-21');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('mkyong','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','�̰�','��','ROLE_USER',true,'1980-4-28');
INSERT INTO users(username,password,first_name,last_name,role,gender,birth) VALUES ('alex','292fbb4a203bff6b33654a9162a717bfc0f080e8af00e717ac4b817d3af10d2adeae0c82c3db60dd','Ű��','�˷���','ROLE_USER',false,'1990-9-6');

INSERT INTO evaluations(user_id,item_id,score) VALUES(1,126508,1.5);
INSERT INTO evaluations(user_id,item_id,score) VALUES(1,131257,4);
INSERT INTO evaluations(user_id,item_id,score) VALUES(2,131257,3);
INSERT INTO evaluations(user_id,item_id,score) VALUES(3,131257,2);


INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('�溹�� ��� �����','���� ���� �;��',1,126508,1);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('�渶�� ��� �����','���� ���� �;��',1,131257,2);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('�����̵� õ���Դϴ�','�� ���Ͼ��',2,131257,3);
INSERT INTO reviews(title,content,user_id,item_id,evaluation_id) VALUES('Good Man','i like the horse',3,131257,4);
INSERT INTO questions(title,content,user_id,item_id) VALUES('�溹���� ��� ���� ������ ���ϱ�?','���� �亯 ��Ź�帳�ϴ�.',1,126508);
INSERT INTO answers(content,user_id,question_id) VALUES('���� �𸨴ϴ�.',1,1);
