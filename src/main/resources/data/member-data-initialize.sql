insert into member (member_seq, member_id, name, email, password, created, enabled) values (1, 'admin', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.', '2023-10-10 12:00:10', 1);
insert into member (member_seq, member_id, name, email, password, created, enabled) values (2, 'admin1', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.', '2023-10-10 12:00:10', 1);
insert into member (member_seq, member_id, name, email, password, created, enabled) values (3, 'admin2', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.', '2023-10-10 12:00:10', 1 );
insert into member (member_seq, member_id, name, email, password, created, enabled) values (4, 'admin3', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.', '2023-10-10 12:00:10', 1);
insert into member (member_seq, member_id, name, email, password, created, enabled) values (5, 'admin4', '관리자', 'gkswjdrl123@naver.com', '$2a$10$Jnt0DhMHHbEI5T7benKtgu4gEV4CyvdDrm6YMGnrdR3gkf3hw1m9.', '2023-10-10 12:00:10', 1);

insert into authority (authority_seq, name) values (1, 'ROLE_ADMIN');
insert into authority (authority_seq, name) values (2, 'ROLE_USER');

insert into member_authority (member_seq, authority_seq) values (1, 1);
insert into member_authority (member_seq, authority_seq) values (1, 2);

insert into member_authority (member_seq, authority_seq) values (2, 1);
insert into member_authority (member_seq, authority_seq) values (2, 2);

insert into member_authority (member_seq, authority_seq) values (3, 1);
insert into member_authority (member_seq, authority_seq) values (3, 2);

insert into member_authority (member_seq, authority_seq) values (4, 1);
insert into member_authority (member_seq, authority_seq) values (4, 2);

insert into member_authority (member_seq, authority_seq) values (5, 1);
insert into member_authority (member_seq, authority_seq) values (5, 2);