SELECT * FROM rune;
insert into
    rune(rune_seq, number, name, eng_name, level, created, updated, enabled)
values
    (1, 1, '엘룬', 'El', 11, now(), now(), true),
    (2, 2, '엘드', 'Eld', 11, now(), now(), true),
    (3, 3, '티르', 'Tir', 13, now(), now(), true),
    (4, 4, '네프', 'Nef', 13, now(), now(), true);

insert into
    rune_option(rune_option_seq, rune_seq, ability, item_type, created, updated, enabled)
values
    (1, 1, '+50 공격 등급, +1 시야', 'WEAPON', now(), now(), true),
    (2, 1, '시야 +1, 방어력 +15', 'ARMOR', now(), now(), true),
    (3, 1, '시야 +1, 방어력 +15', 'HELM', now(), now(), true),
    (4, 1, '시야 +1, 방어력 +15', 'SHIELD', now(), now(), true),

    (5, 2, '75% 언데드 데미지, +50% 언데드 공격 등급', 'WEAPON', now(), now(), true),
    (6, 2, '스테미너 소모량 15% 감소', 'ARMOR', now(), now(), true),
    (7, 2, '스테미너 소모량 15% 감소', 'HELM', now(), now(), true),
    (8, 2, '7% 공격저지 성공률', 'SHIELD', now(), now(), true),

    (9, 3, '+2 마나 상승(적 제거시)', 'WEAPON', now(), now(), true),
    (10, 3, '+2 마나 상승(적 제거시)', 'ARMOR', now(), now(), true),
    (11, 3, '+2 마나 상승(적 제거시)', 'HELM', now(), now(), true),
    (12, 3, '+2 마나 상승(적 제거시)', 'SHIELD', now(), now(), true),

    (13, 4, '적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (14, 4, '+30 원거리 공격 방어', 'ARMOR', now(), now(), true),
    (15, 4, '+30 원거리 공격 방어', 'HELM', now(), now(), true),
    (16, 4, '+30 원거리 공격 방어', 'SHIELD', now(), now(), true);


