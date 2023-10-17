insert into member (seq, member_id, name, email, password) values (1, 'admin', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.');

insert into authority (seq, name) values (1, 'ROLE_ADMIN');
insert into authority (seq, name) values (2, 'ROLE_USER');

insert into member_authority (member_seq, authority_seq) values (1, 1);
insert into member_authority (member_seq, authority_seq) values (1, 2);