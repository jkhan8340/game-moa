insert into
    rune(rune_seq, number, name, eng_name, level, created, updated, enabled)
values
    (1, 1, '엘룬', 'El', 11, now(), now(), true),
    (2, 2, '엘드', 'Eld', 11, now(), now(), true),
    (3, 3, '티르', 'Tir', 13, now(), now(), true),
    (4, 4, '네프', 'Nef', 13, now(), now(), true),
    (5, 5, '에드', 'Eth', 15, now(), now(), true),
    (6, 6, '아이드', 'Ith', 15, now(), now(), true),
    (7, 7, '탈', 'Tal', 17, now(), now(), true),
    (8, 8, '랄', 'Ral', 19, now(), now(), true),
    (9, 9, '오르트', 'Ort', 21, now(), now(), true),
    (10, 10, '주울', 'Thul', 23, now(), now(), true),
    (11, 11, '앰', 'Amn', 25, now(), now(), true),
    (12, 12, '솔', 'Sol', 27, now(), now(), true),
    (13, 13, '샤에', 'Shael', 29, now(), now(), true),
    (14, 14, '돌', 'Dol', 31, now(), now(), true),
    (15, 15, '헬', 'Hel', 33, now(), now(), true),
    (16, 16, '이오', 'Io', 35, now(), now(), true),
    (17, 17, '룸', 'Lum', 37, now(), now(), true),
    (18, 18, '코', 'Ko', 39, now(), now(), true),
    (19, 19, '팔', 'Fal', 41, now(), now(), true),
    (20, 20, '렘', 'Lem', 43, now(), now(), true),
    (21, 21, '풀', 'Pul', 45, now(), now(), true),
    (22, 22, '우움', 'Um', 47, now(), now(), true),
    (23, 23, '말', 'Mal', 49, now(), now(), true),
    (24, 24, '아이스트', 'Ist', 51, now(), now(), true),
    (25, 25, '굴', 'Gul', 53, now(), now(), true),
    (26, 26, '벡스', 'Vex', 55, now(), now(), true),
    (27, 27, '오움', 'Ohm', 57, now(), now(), true),
    (28, 28, '로', 'Lo', 59, now(), now(), true),
    (29, 29, '수르', 'Sur', 61, now(), now(), true),
    (30, 30, '베르', 'Ber', 63, now(), now(), true),
    (31, 31, '자', 'Jah', 65, now(), now(), true),
    (32, 32, '참', 'Cham', 67, now(), now(), true),
    (33, 33, '조드', 'Zod', 69, now(), now(), true);


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
    (16, 4, '+30 원거리 공격 방어', 'SHIELD', now(), now(), true),

    (17, 5, '+25% 목표물의 방어', 'WEAPON', now(), now(), true),
    (18, 5, '마나 재생 15%', 'ARMOR', now(), now(), true),
    (19, 5, '마나 재생 15%', 'HELM', now(), now(), true),
    (20, 5, '마나 재생 15%', 'SHIELD', now(), now(), true),

    (21, 6, '+15% 마법 방어', 'WEAPON', now(), now(), true),
    (22, 6, '마나 재생 15%', 'ARMOR', now(), now(), true),
    (23, 6, '마나 재생 15%', 'HELM', now(), now(), true),
    (24, 6, '마나 재생 15%', 'SHIELD', now(), now(), true),

    (25, 7, '+75% 냉기 저항', 'WEAPON', now(), now(), true),
    (26, 7, '+30% 냉기 저항', 'ARMOR', now(), now(), true),
    (27, 7, '+30% 냉기 저항', 'HELM', now(), now(), true),
    (28, 7, '+30% 냉기 저항', 'SHIELD', now(), now(), true),

    (29, 8, '+35% 화염 저항', 'WEAPON', now(), now(), true),
    (30, 8, '+30% 화염 저항', 'ARMOR', now(), now(), true),
    (31, 8, '+30% 화염 저항', 'HELM', now(), now(), true),
    (32, 8, '+30% 화염 저항', 'SHIELD', now(), now(), true),

    (33, 9, '+30% 독 저항', 'WEAPON', now(), now(), true),
    (34, 9, '+35% 독 저항', 'ARMOR', now(), now(), true),
    (35, 9, '+35% 독 저항', 'HELM', now(), now(), true),
    (36, 9, '+35% 독 저항', 'SHIELD', now(), now(), true),

    (37, 10, '+30% 번개 저항', 'WEAPON', now(), now(), true),
    (38, 10, '+35% 번개 저항', 'ARMOR', now(), now(), true),
    (39, 10, '+35% 번개 저항', 'HELM', now(), now(), true),
    (40, 10, '+35% 번개 저항', 'SHIELD', now(), now(), true),

    (41, 11, '+7% 생명력 흡수', 'WEAPON', now(), now(), true),
    (42, 11, '+15 생명력 흡수', 'ARMOR', now(), now(), true),
    (43, 11, '+15 생명력 흡수', 'HELM', now(), now(), true),
    (44, 11, '+15 생명력 흡수', 'SHIELD', now(), now(), true),

    (45, 12, '+7% 생명력 흡수', 'WEAPON', now(), now(), true),
    (46, 12, '+15 생명력 흡수', 'ARMOR', now(), now(), true),
    (47, 12, '+15 생명력 흡수', 'HELM', now(), now(), true),
    (48, 12, '+15 생명력 흡수', 'SHIELD', now(), now(), true),

    (49, 13, '+20% 공격 속도', 'WEAPON', now(), now(), true),
    (50, 13, '+15% 공격 속도', 'ARMOR', now(), now(), true),
    (51, 13, '+15% 공격 속도', 'HELM', now(), now(), true),
    (52, 13, '+15% 공격 속도', 'SHIELD', now(), now(), true),

    (53, 14, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (54, 14, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (55, 14, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (56, 14, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (57, 15, '+5% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (58, 15, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (59, 15, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (60, 15, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (61, 16, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (62, 16, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (63, 16, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (64, 16, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (65, 17, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (66, 17, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (67, 17, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (68, 17, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (69, 18, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (70, 18, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (71, 18, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (72, 18, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (73, 19, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (74, 19, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (75, 19, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (76, 19, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (77, 20, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (78, 20, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (79, 20, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (80, 20, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (81, 21, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (82, 21, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (83, 21, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (84, 21, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (85, 22, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (86, 22, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (87, 22, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (88, 22, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (89, 23, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (90, 23, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (91, 23, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (92, 23, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (93, 24, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (94, 24, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (95, 24, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (96, 24, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (97, 25, '+10% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (98, 25, '+7% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (99, 25, '+7% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (100, 25, '+7% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (101, 26, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (102, 26, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (103, 26, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (104, 26, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (105, 27, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (106, 27, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (107, 27, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (108, 27, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (109, 28, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (110, 28, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (111, 28, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (112, 28, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (113, 29, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (114, 29, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (115, 29, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (116, 29, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (117, 30, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (118, 30, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (119, 30, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (120, 30, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (121, 31, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (122, 31, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (123, 31, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (124, 31, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (125, 32, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (126, 32, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (127, 32, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (128, 32, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true),

    (129, 33, '+7% 확률로 적을 밀쳐냄', 'WEAPON', now(), now(), true),
    (130, 33, '+5% 확률로 적을 밀쳐냄', 'ARMOR', now(), now(), true),
    (131, 33, '+5% 확률로 적을 밀쳐냄', 'HELM', now(), now(), true),
    (132, 33, '+5% 확률로 적을 밀쳐냄', 'SHIELD', now(), now(), true);








