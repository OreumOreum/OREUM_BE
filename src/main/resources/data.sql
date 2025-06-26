INSERT INTO category (type) VALUES ('ACTIVITY');
INSERT INTO category (type) VALUES ('CULTURE');
INSERT INTO category (type) VALUES ('FOOD');
INSERT INTO category (type) VALUES ('FOREST');
INSERT INTO category (type) VALUES ('MOOD');
INSERT INTO category (type) VALUES ('SOCIAL');

INSERT INTO member (created_at, updated_at,login_id, name, email, fcm_token, phone_no, category_type)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19','KAKAO1','배철현', 'user0@test.com', 'token0', '0100000000', 'ACTIVITY');
INSERT INTO member (created_at, updated_at,login_id, name, email, fcm_token, phone_no, category_type)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'KAKAO2','양지원','user1@test.com', 'token1', '0100000001', 'SOCIAL');
INSERT INTO member (created_at, updated_at,login_id, name, email, fcm_token, phone_no, category_type)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'GOOGLE1','곽민철','user2@test.com', 'token2', '0100000002', 'MOOD');
INSERT INTO member (created_at, updated_at,login_id, name, email, fcm_token, phone_no, category_type)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'GOOGLE1','주해찬','user3@test.com', 'token3', '0100000003', 'FOREST');
INSERT INTO member (created_at, updated_at,login_id, name, email, fcm_token, phone_no, category_type)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'APPLE1','김태현','user4@test.com', 'token4', '0100000004', 'CULTURE');

INSERT INTO folder (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더1','1');
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더2','1');
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더1','2');
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더2','2');
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더3','2');

-- 제주시 데이터 (sigungucode = 4)
INSERT INTO place (created_at, updated_at, address, detail_address, category_1, category_2, category_3, city_code, sigungucode, content_id, content_type_id, copyright_type, mapx, mapy, origin_image, tel, thumbnail_image, title)
VALUES
    (NOW(), NOW(), '제주특별자치도 제주시 구좌읍 세화14길 3', '', 'A05', 'A0502', 'A05020900', 39, 4, 2850913, 39, 'Type3', 126.8606961680, 33.5205279098, 'http://tong.visitkorea.or.kr/cms/resource/05/2850905_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/05/2850905_image2_1.jpg', '가는곶 세화'),
    (NOW(), NOW(), '제주특별자치도 제주시 복지로북길 4', '1층', 'A05', 'A0502', 'A05020100', 39, 4, 2906155, 39, 'Type3', 126.5206569716, 33.4888787305, 'http://tong.visitkorea.or.kr/cms/resource/52/2906152_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/52/2906152_image2_1.jpg', '가람'),
    (NOW(), NOW(), '제주특별자치도 제주시 한경면 청수리', '1205', 'A01', 'A0101', 'A01010400', 39, 4, 1884191, 12, 'Type3', 126.2466098987, 33.3057279944, 'http://tong.visitkorea.or.kr/cms/resource/72/3477972_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/72/3477972_image2_1.jpg', '가마오름'),
    (NOW(), NOW(), '제주특별자치도 제주시 한경면 청수로 13-3', '', 'A01', 'A0101', 'A01010400', 39, 4, 1884202, 12, '', 126.2460707194, 33.3209235283, '', '064-728-2742', '', '가메창(암메)'),
    (NOW(), NOW(), '제주특별자치도 제주시 오라삼동', '2848', 'A01', 'A0101', 'A01010400', 39, 4, 1884521, 12, 'Type3', 126.5038611983, 33.4893700755, 'http://tong.visitkorea.or.kr/cms/resource/82/3477882_image3_1.jpg', '064-740-6001', 'http://tong.visitkorea.or.kr/cms/resource/82/3477882_image2_1.jpg', '가새기오름'),
    (NOW(), NOW(), '제주특별자치도 제주시 월랑로 36', '', 'A05', 'A0502', 'A05020100', 39, 4, 2837181, 39, 'Type3', 126.4763302701, 33.4888641378, 'http://tong.visitkorea.or.kr/cms/resource/75/2837175_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/75/2837175_image2_1.jpg', '가시어멍김밥'),
    (NOW(), NOW(), '제주특별자치도 제주시 해맞이해안로 1296', '', 'A05', 'A0502', 'A05020100', 39, 4, 2853435, 39, 'Type3', 126.8509745258, 33.5319348245, 'http://tong.visitkorea.or.kr/cms/resource/24/2853424_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/24/2853424_image2_1.jpg', '갈치공장'),
    (NOW(), NOW(), '제주특별자치도 제주시 오남로 12', '', 'A05', 'A0502', 'A05020100', 39, 4, 1866954, 39, '', 126.5195597249, 33.4941753491, '', '064-722-8292', '', '감나무집'),
    (NOW(), NOW(), '제주특별자치도 제주시 조천읍 함대로 362', '', 'A02', 'A0201', 'A02010700', 39, 4, 3082301, 12, 'Type3', 126.6611469938, 33.5129949735, 'http://tong.visitkorea.or.kr/cms/resource/96/3082296_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/96/3082296_image2_1.jpg', '감사공묘역'),
    (NOW(), NOW(), '제주특별자치도 제주시 구좌읍 평대7길 34', '', 'A02', 'A0203', 'A02030100', 39, 4, 3056401, 12, 'Type3', 126.8432807691, 33.5310570194, 'http://tong.visitkorea.or.kr/cms/resource/77/3056377_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/77/3056377_image2_1.jpg', '감수굴 밭담길'),
    (NOW(), NOW(), '제주특별자치도 제주시 조천읍 종인내길 133', '', 'A02', 'A0203', 'A02030100', 39, 4, 3057021, 12, 'Type3', 126.6227951824, 33.4949185817, 'http://tong.visitkorea.or.kr/cms/resource/62/3056962_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/62/3056962_image2_1.jpg', '개똥이네농장'),
    (NOW(), NOW(), '제주특별자치도 제주시 봉개동', '', 'A01', 'A0101', 'A01010400', 39, 4, 1889809, 12, 'Type3', 126.6125115262, 33.4262287923, 'http://tong.visitkorea.or.kr/cms/resource/47/3478047_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/47/3478047_image2_1.jpg', '개오리오름');

-- 서귀포시 데이터 (sigungucode = 3)
INSERT INTO place (created_at, updated_at, address, detail_address, category_1, category_2, category_3, city_code, sigungucode, content_id, content_type_id, copyright_type, mapx, mapy, origin_image, tel, thumbnail_image, title)
VALUES
    (NOW(), NOW(), '제주특별자치도 서귀포시 대정읍 영서중로 59', '', 'A05', 'A0502', 'A05020100', 39, 3, 2805240, 39, '', 126.2600494870, 33.2215375052, '', '', '', '가파도별미식당'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 대정읍', '가파로 67번길 가파도 일원', 'A02', 'A0207', 'A02070200', 39, 3, 712175, 15, 'Type3', 126.2717384970, 33.1701766897, 'http://tong.visitkorea.or.kr/cms/resource/21/3484921_image3_1.JPG', '064-764-7130', 'http://tong.visitkorea.or.kr/cms/resource/21/3484921_image2_1.JPG', '가파도청보리축제'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 호근동', '2124', 'A01', 'A0101', 'A01010400', 39, 3, 1887368, 12, 'Type3', 126.5259918298, 33.2777271543, 'http://tong.visitkorea.or.kr/cms/resource/64/3477364_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/64/3477364_image2_1.jpg', '각시바위오름'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 표선면 성읍리', '', 'A01', 'A0101', 'A01010500', 39, 3, 1887381, 12, 'Type3', 126.8116979172, 33.3873529469, 'http://tong.visitkorea.or.kr/cms/resource/31/3477231_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/31/3477231_image2_1.jpg', '갈마못(갈뫼못)'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 사계남로216번길 24-61 갈중이', '', 'A02', 'A0203', 'A02030200', 39, 3, 635593, 12, '', 126.3136584209, 33.2351423980, '', '', '', '갈중이(천연염색체험)'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 일주서로 1146', '(상예동)', 'A05', 'A0502', 'A05020100', 39, 3, 2783304, 39, 'Type3', 126.3907760719, 33.2646847924, 'http://tong.visitkorea.or.kr/cms/resource/91/2783691_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/91/2783691_image2_1.jpg', '갈치왕'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 효돈순환로 441', '', 'A02', 'A0206', 'A02060100', 39, 3, 590415, 14, 'Type3', 126.6080904173, 33.2711819574, 'http://tong.visitkorea.or.kr/cms/resource/30/3411130_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/30/3411130_image2_1.jpg', '감귤박물관'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 중문관광로 42 (색달동)', '', 'A03', 'A0302', 'A03020600', 39, 3, 3030930, 28, 'Type3', 126.4116189256, 33.2549473066, 'http://tong.visitkorea.or.kr/cms/resource/23/3030923_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/23/3030923_image2_1.jpg', '감귤카트'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 동광리', '산41', 'A01', 'A0101', 'A01010400', 39, 3, 1887493, 12, 'Type3', 126.3497088457, 33.3250274801, 'http://tong.visitkorea.or.kr/cms/resource/06/3477406_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/06/3477406_image2_1.jpg', '감낭오름'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 월산로 16', '1층', 'A05', 'A0502', 'A05020900', 39, 3, 2854455, 39, 'Type3', 126.4946032358, 33.2620894948, 'http://tong.visitkorea.or.kr/cms/resource/54/2854454_image3_1.jpg', '', 'http://tong.visitkorea.or.kr/cms/resource/54/2854454_image2_1.jpg', '감따남'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 표선면 가시로592번길', '(가시리) 일원', 'A01', 'A0101', 'A01010400', 39, 3, 1887866, 12, '', 126.7810400382, 33.3621052416, '', '', '', '갑선이오름'),
    (NOW(), NOW(), '제주특별자치도 서귀포시 이어도로 669 (강정동)', '', 'A02', 'A0207', 'A02070200', 39, 3, 3333177, 15, 'Type3', 126.4870795041, 33.2345451931, 'http://tong.visitkorea.or.kr/cms/resource/27/3492627_image3_1.jpg', '010-4749-3790', 'http://tong.visitkorea.or.kr/cms/resource/27/3492627_image2_1.jpg', '강정마을 생태축제');

-- 나머지 데이터는 변경 없음
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_001', 'http://example.com/course1_origin.jpg', 'http://example.com/course1_thumb.jpg', '제주 동부 해안 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '130', 'course_002', 'http://example.com/course2_origin.jpg', 'http://example.com/course2_thumb.jpg', '서귀포 관광 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_003', 'http://example.com/course3_origin.jpg', 'http://example.com/course3_thumb.jpg', '제주 서부 오름 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '130', 'course_004', 'http://example.com/course4_origin.jpg', 'http://example.com/course4_thumb.jpg', '한라산 등반 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_005', 'http://example.com/course5_origin.jpg', 'http://example.com/course5_thumb.jpg', '제주 시내 문화 코스');

INSERT INTO course_place (created_at, updated_at, course_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO course_place (created_at, updated_at, course_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 4);
INSERT INTO course_place (created_at, updated_at, course_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO course_place (created_at, updated_at, course_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 5);
INSERT INTO course_place (created_at, updated_at, course_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 6);

INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 4);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 5);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4, 6);

INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'ACTIVITY', 1);
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'FOOD', 2);
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'CULTURE', 3);
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'MOOD', 4);
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'FOREST', 5);

INSERT INTO planner (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 2박3일 여행', 1);
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '서귀포 힐링 여행', 2);
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 오름 탐방', 3);
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 맛집 투어', 4);
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 문화 체험', 5);

INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1, 1, 1);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 2, 4, 1);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 1, 2, 2);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2, 5, 2);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1, 8, 3);

INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES
    -- 5월의 여행지 (제주시 2곳, 서귀포시 2곳)
    (NOW(), NOW(), '2025-05-01', 1),
    (NOW(), NOW(), '2025-05-01', 3),
    (NOW(), NOW(), '2025-05-01', 2),
    (NOW(), NOW(), '2025-05-01', 5),

    -- 6월의 여행지 (제주시 2곳, 서귀포시 2곳)
    (NOW(), NOW(), '2025-06-01', 4),
    (NOW(), NOW(), '2025-06-01', 6),
    (NOW(), NOW(), '2025-06-01', 7),
    (NOW(), NOW(), '2025-06-01', 8);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 3);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4, 4);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 5, 5);
INSERT INTO spot_category_summary (created_at, updated_at, spot_id, category_type, visit_count)
VALUES
    -- '가마오름'(spot_id=1)에 대한 집계 데이터
    (NOW(), NOW(), 1, 'ACTIVITY', 15),
    (NOW(), NOW(), 1, 'FOREST', 10),
    (NOW(), NOW(), 1, 'MOOD', 3),
    -- '가시아방국수'(spot_id=2)에 대한 집계 데이터
    (NOW(), NOW(), 2, 'FOOD', 25),
    (NOW(), NOW(), 2, 'SOCIAL', 8);

INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'GOLD', 3, 3);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'PLATINUM', 4, 4);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'DIAMOND', 5, 5);