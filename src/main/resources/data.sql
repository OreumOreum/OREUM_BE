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
