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
INSERT INTO folder (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더1','1');
INSERT INTO folder (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더2','1');
INSERT INTO folder (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더1','2');
INSERT INTO folder (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더2','2');
INSERT INTO folder (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '내폴더3','2');
INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
           '2025-06-15 10:39:19', '2025-06-15 10:39:19',
           '제주특별자치도 제주시 한경면 청수리', 'A01', 'A0101', 'A01010400',
           39, 1884191, 12, 'Type3',
           '1205', 126.2466098987, 33.3057279944,
           'http://tong.visitkorea.or.kr/cms/resource/72/3477972_image2_1.jpg',
           '',  -- tel 없음
           'http://tong.visitkorea.or.kr/cms/resource/72/3477972_image3_1.jpg',
           '가마오름'
       );
INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 서귀포시 성산읍 섭지코지로 10', 'A05', 'A0502', 'A05020100',
             39, 2791481, 39, 'Type3',
             '', 126.9180903016, 33.4386517763,
             'http://tong.visitkorea.or.kr/cms/resource/99/2800599_image2_1.jpg',
             '',  -- tel 없음
             'http://tong.visitkorea.or.kr/cms/resource/99/2800599_image2_1.jpg',
             '가시아방국수'
         );
INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 제주시 구좌읍 일주동로 1724', 'B02', 'B0201', 'B02010500',
             39, 735781, 32, 'Type3',
             '', 126.7168773670, 33.5521887531,
             'http://tong.visitkorea.or.kr/cms/resource/47/1618247_image2_1.jpg',
             '064-782-0800',
             'http://tong.visitkorea.or.kr/cms/resource/47/1618247_image3_1.jpg',
             '꼬뜨도르 호텔'
         );
-- course 테이블 추가 데이터 (5개)
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_001',
        'http://example.com/course1_origin.jpg', 'http://example.com/course1_thumb.jpg', '제주 동부 해안 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '130', 'course_002',
        'http://example.com/course2_origin.jpg', 'http://example.com/course2_thumb.jpg', '서귀포 관광 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_003',
        'http://example.com/course3_origin.jpg', 'http://example.com/course3_thumb.jpg', '제주 서부 오름 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '130', 'course_004',
        'http://example.com/course4_origin.jpg', 'http://example.com/course4_thumb.jpg', '한라산 등반 코스');
INSERT INTO course (created_at, updated_at, city_code, content_id, origin_image, thumbnail_image, title)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '110', 'course_005',
        'http://example.com/course5_origin.jpg', 'http://example.com/course5_thumb.jpg', '제주 시내 문화 코스');

-- place 테이블 추가 데이터 (5개)
INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 제주시 애월읍 고내리', 'A01', 'A0101', 'A01010200',
             '110', '1234567', '12', 'Type1',
             '산 15-1', 126.3234567890, 33.4012345678,
             'http://example.com/place4_origin.jpg',
             '064-123-4567',
             'http://example.com/place4_thumb.jpg',
             '애월 해안도로'
         );

INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 서귀포시 중문동', 'A02', 'A0202', 'A02020300',
             '130', '2345678', '12', 'Type3',
             '2663', 126.4123456789, 33.2543216789,
             'http://example.com/place5_origin.jpg',
             '064-738-1234',
             'http://example.com/place5_thumb.jpg',
             '천지연 폭포'
         );

INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 제주시 한림읍 협재리', 'A01', 'A0101', 'A01010100',
             '110', '3456789', '12', 'Type1',
             '2497-1', 126.2345678901, 33.3987654321,
             'http://example.com/place6_origin.jpg',
             '064-796-7890',
             'http://example.com/place6_thumb.jpg',
             '협재 해수욕장'
         );

INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 서귀포시 성산읍 성산리', 'A01', 'A0101', 'A01010300',
             '130', '4567890', '12', 'Type3',
             '114', 126.9421567890, 33.4598765432,
             'http://example.com/place7_origin.jpg',
             '064-783-0959',
             'http://example.com/place7_thumb.jpg',
             '성산 일출봉'
         );

INSERT INTO place (
    created_at, updated_at, address, category_1, category_2, category_3,
    city_code, content_id, content_type_id, copyright_type,
    detail_address, mapx, mapy, origin_image, tel, thumbnail_image, title
) VALUES (
             '2025-06-15 10:39:19', '2025-06-15 10:39:19',
             '제주특별자치도 제주시 조천읍 교래리', 'A03', 'A0303', 'A03030200',
             '110', '5678901', '14', 'Type1',
             '산 38', 126.5678901234, 33.3456789012,
             'http://example.com/place8_origin.jpg',
             '064-784-2888',
             'http://example.com/place8_thumb.jpg',
             '비자림'
         );

-- course_place 테이블 추가 데이터 (5개)
INSERT INTO course_place (created_at, updated_at, course_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO course_place (created_at, updated_at, course_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 4);
INSERT INTO course_place (created_at, updated_at, course_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO course_place (created_at, updated_at, course_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 5);
INSERT INTO course_place (created_at, updated_at, course_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 6);

-- folder_place 테이블 추가 데이터 (5개)
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 4);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 5);
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4, 6);

-- place_category 테이블 추가 데이터 (5개)
INSERT INTO place_category (created_at, updated_at, category_type, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'ACTIVITY', 1);
INSERT INTO place_category (created_at, updated_at, category_type, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'FOOD', 2);
INSERT INTO place_category (created_at, updated_at, category_type, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'CULTURE', 3);
INSERT INTO place_category (created_at, updated_at, category_type, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'MOOD', 4);
INSERT INTO place_category (created_at, updated_at, category_type, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'FOREST', 5);

-- planner 테이블 추가 데이터 (5개)
INSERT INTO planner (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 2박3일 여행', 1);
INSERT INTO planner (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '서귀포 힐링 여행', 2);
INSERT INTO planner (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 오름 탐방', 3);
INSERT INTO planner (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 맛집 투어', 4);
INSERT INTO planner (created_at, updated_at, name, member_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '제주 문화 체험', 5);

-- planner_place 테이블 추가 데이터 (5개)
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1, 1, 1);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 2, 4, 1);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 1, 2, 2);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2, 5, 2);
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1, 8, 3);

-- rating 테이블 추가 데이터 (5개)
INSERT INTO rating (created_at, updated_at, score, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4.5, 1, 1, 1);
INSERT INTO rating (created_at, updated_at, score, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4.8, 2, 2, 2);
INSERT INTO rating (created_at, updated_at, score, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4.2, NULL, 3, 4);
INSERT INTO rating (created_at, updated_at, score, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4.7, NULL, 4, 5);
INSERT INTO rating (created_at, updated_at, score, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4.3, 3, 5, 6);

-- review 테이블 추가 데이터 (5개)
INSERT INTO review (created_at, updated_at, content, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '가마오름 트레킹 정말 좋았어요!', 1, 1, 1);
INSERT INTO review (created_at, updated_at, content, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '국수가 정말 맛있네요. 추천합니다!', 2, 2, 2);
INSERT INTO review (created_at, updated_at, content, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '호텔 시설이 깔끔하고 좋아요', NULL, 3, 3);
INSERT INTO review (created_at, updated_at, content, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '애월 해안도로 드라이브 코스 최고!', NULL, 4, 4);
INSERT INTO review (created_at, updated_at, content, course_id, member_id, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '천지연 폭포의 웅장함에 감동', 3, 5, 5);

-- spot 테이블 추가 데이터 (5개)
INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '2025-06-20', 1);
INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '2025-06-21', 2);
INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '2025-06-22', 4);
INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '2025-06-23', 5);
INSERT INTO spot (created_at, updated_at, date, place_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', '2025-06-24', 7);

-- visit_log 테이블 추가 데이터 (5개)
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 1, 1);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 2, 2);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 3, 3);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 4, 4);
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 5, 5);

-- badge 테이블 추가 데이터 (5개)
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'BRONZE', 1, 1);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'SILVER', 2, 2);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'GOLD', 3, 3);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'PLATINUM', 4, 4);
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id)
VALUES ('2025-06-15 10:39:19', '2025-06-15 10:39:19', 'DIAMOND', 5, 5);