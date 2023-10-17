insert into authority (seq, authority) values (1, 'ROLE_ADMIN');
insert into authority (seq, authority) values (2, 'ROLE_USER');

insert into member_authority (member_seq, authority_seq) values (1, 1);
insert into member_authority (member_seq, authority_seq) values (1, 2);