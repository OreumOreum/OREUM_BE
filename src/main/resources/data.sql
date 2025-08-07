-- 제주도 관광 시스템 목 데이터 INSERT SQL

-- 1. category 테이블 (enum 기반)
INSERT INTO category (type) VALUES
                                ('ACTIVITY'),
                                ('CULTURE'),
                                ('FOOD'),
                                ('FOREST'),
                                ('MOOD'),
                                ('SOCIAL');

-- 2. member 테이블
INSERT INTO member (created_at, updated_at, badge_count, email, fcm_token, login_id, name, phone_no, category_type, skip) VALUES
                                                                                                                        (NOW(), NOW(), 5, 'kim.jeju@email.com', 'fcm_token_001', '1111', '김제주', '010-1234-5678', 'CULTURE',FALSE),
                                                                                                                        (NOW(), NOW(), 3, 'lee.seogwipo@email.com', 'fcm_token_002', 'lee_seogwipo', '이서귀포', '010-2345-6789', 'FOOD',FALSE),
                                                                                                                        (NOW(), NOW(), 7, 'park.hallasan@email.com', 'fcm_token_003', 'park_hallasan', '박한라산', '010-3456-7890', 'FOREST',FALSE),
                                                                                                                        (NOW(), NOW(), 2, 'choi.seongeup@email.com', 'fcm_token_004', 'choi_seongeup', '최성읍', '010-4567-8901', 'ACTIVITY',FALSE),
                                                                                                                        (NOW(), NOW(), 4, 'jung.udo@email.com', 'fcm_token_005', 'jung_udo', '정우도', '010-5678-9012', 'MOOD',FALSE);

--
--
-- 5. course 테이블
INSERT INTO course (created_at, updated_at, category_1, category_2, category_3, content_id, origin_image, overview, sigungu_code, thumbnail_image, title) VALUES
                                                                                                                                                                                (NOW(), NOW(), '자연', '해안', '해변', 'course_001', 'https://example.com/course1_origin.jpg', '제주 동쪽 해안을 따라 아름다운 풍경을 즐기는 드라이브 코스', 4, 'https://example.com/course1_thumb.jpg', '제주 동쪽 해안 드라이브'),
                                                                                                                                                                                (NOW(), NOW(), '레포츠', '산악', '등반', 'course_002', 'https://example.com/course2_origin.jpg', '한라산 둘레길과 주변 오름을 탐방하는 트래킹 코스', 3, 'https://example.com/course2_thumb.jpg', '한라산 둘레길 트래킹'),
                                                                                                                                                                                (NOW(), NOW(), '문화', '역사', '유적', 'course_003', 'https://example.com/course3_origin.jpg', '제주의 전통 문화와 역사 유적지를 둘러보는 코스', 4, 'https://example.com/course3_thumb.jpg', '제주 문화유적 탐방'),
                                                                                                                                                                                (NOW(), NOW(), '음식', '향토', '맛집', 'course_004', 'https://example.com/course4_origin.jpg', '제주 대표 음식점들을 순서대로 방문하는 미식 코스', 3, 'https://example.com/course4_thumb.jpg', '제주 향토음식 투어');
-- INSERT INTO course_category (course_id,category_type) VALUES
--                                     (1,'CULTURE'),
--                                     (2,'CULTURE');

--
-- 14. place 테이블
INSERT INTO place (created_at, updated_at, address, origin_image, sigungucode, course_id, content_type_id) VALUES
                                                    (NOW(), NOW(), '제주특별자치도 제주시 조천읍 선흘리','image1',4,1,'12'),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 애월읍 고성리','image2',4,1,'14'),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 한림읍 한림리','image3',4,1,'15'),
                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 성산읍 성산리','image4',3,1,'25'),

                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 중문동','image5',3,2,'28'),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 이도이동','image6',4,2,'38'),
                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 사계리','image7',3,2,'39');
--
--
-- -- 16. spot 테이블
-- INSERT INTO spot (created_at, updated_at, date,order_index, place_id) VALUES
--                                                               (NOW(), NOW(), '2025-07-01',1, 1),
--                                                               (NOW(), NOW(), '2025-07-01',2, 2),
--                                                               (NOW(), NOW(), '2025-07-01',3, 3),
--                                                               (NOW(), NOW(), '2025-07-01',4, 4),
--                                                               (NOW(), NOW(), '2025-06-01',1, 5),
--                                                               (NOW(), NOW(), '2025-06-01',2, 6),
--                                                               (NOW(), NOW(), '2025-06-01',3, 7),
--                                                               (NOW(), NOW(), '2025-06-01',4, 1),
--                                                               (NOW(), NOW(), '2024-03-01',1, 2);
--
--
--
--
-- -- 18. badge 테이블
-- INSERT INTO spot_category_summary (created_at, updated_at, visit_count, category_type, spot_id) VALUES
--                                                                                                     (NOW(), NOW(), 25, 'FOREST', 1),
--                                                                                                     (NOW(), NOW(), 18, 'CULTURE', 1),
--                                                                                                     (NOW(), NOW(), 32, 'FOOD', 1),
--                                                                                                     (NOW(), NOW(), 41, 'MOOD', 1),
--                                                                                                     (NOW(), NOW(), 15, 'SOCIAL', 5),
--                                                                                                     (NOW(), NOW(), 28, 'FOOD', 6),
--                                                                                                     (NOW(), NOW(), 22, 'ACTIVITY', 7),
--                                                                                                     (NOW(), NOW(), 19, 'ACTIVITY', 8);
--
-- -- 19. visit_log 테이블
-- INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES
--                                                                        (NOW(), NOW(), 1, 1),
--                                                                        (NOW(), NOW(), 1, 2),
--                                                                        (NOW(), NOW(), 1, 5),
--
--
--                                                                        (NOW(), NOW(), 3, 5),
--                                                                        (NOW(), NOW(), 3, 7),
--                                                                        (NOW(), NOW(), 4, 6),
--                                                                        (NOW(), NOW(), 5, 7),
--                                                                        (NOW(), NOW(), 5, 1),
--                                                                        (NOW(), NOW(), 5, 2);


INSERT INTO place_category (created_at, updated_at, place_id, category_type) VALUES
                                                                       (NOW(), NOW(), 1, 'CULTURE'),
                                                                       (NOW(), NOW(), 2, 'CULTURE'),
                                                                       (NOW(), NOW(), 3, 'CULTURE'),
                                                                       (NOW(), NOW(), 4, 'CULTURE'),
                                                                       (NOW(), NOW(), 5, 'CULTURE'),
                                                                       (NOW(), NOW(), 6, 'CULTURE'),
                                                                       (NOW(), NOW(), 7, 'CULTURE');

--
-- 20. folder 테이블
INSERT INTO folder (created_at, updated_at, name, member_id, is_default) VALUES
                                                                 (NOW(), NOW(), '제주 맛집 모음', 1,true),
                                                                 (NOW(), NOW(), '문화유적지', 1,false),
                                                                 (NOW(), NOW(), '오름 투어', 2,true),
                                                                 (NOW(), NOW(), '가족 여행지', 3,true),
                                                                 (NOW(), NOW(), '액티비티 스팟', 4,true),
                                                                 (NOW(), NOW(), '힐링 장소', 5,true);
--
-- -- 21. folder_place 테이블
-- INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES
--                                                                            (CURRENT_TIMESTAMP,             CURRENT_TIMESTAMP,             1, 1),
--                                                                            (DATEADD('SECOND', 1, CURRENT_TIMESTAMP), DATEADD('SECOND', 1, CURRENT_TIMESTAMP), 1, 2),
--                                                                            (DATEADD('SECOND', 2, CURRENT_TIMESTAMP), DATEADD('SECOND', 2, CURRENT_TIMESTAMP), 1, 3),
--                                                                            (DATEADD('SECOND', 3, CURRENT_TIMESTAMP), DATEADD('SECOND', 3, CURRENT_TIMESTAMP), 1, 4),
--                                                                            (DATEADD('SECOND', 4, CURRENT_TIMESTAMP), DATEADD('SECOND', 4, CURRENT_TIMESTAMP), 1, 5),
--                                                                            (DATEADD('SECOND', 5, CURRENT_TIMESTAMP), DATEADD('SECOND', 5, CURRENT_TIMESTAMP), 1, 6),
--                                                                            (DATEADD('SECOND', 6, CURRENT_TIMESTAMP), DATEADD('SECOND', 6, CURRENT_TIMESTAMP), 1, 7),
--                                                                            (DATEADD('SECOND', 7, CURRENT_TIMESTAMP), DATEADD('SECOND', 7, CURRENT_TIMESTAMP), 2, 2),
--                                                                            (DATEADD('SECOND', 8, CURRENT_TIMESTAMP), DATEADD('SECOND', 8, CURRENT_TIMESTAMP), 2, 4),
--                                                                            (NOW(), NOW(), 3, 1),
--                                                                            (NOW(), NOW(), 3, 4),
--                                                                            (NOW(), NOW(), 4, 2),
--                                                                            (NOW(), NOW(), 4, 5),
--                                                                            (NOW(), NOW(), 5, 7),
--                                                                            (NOW(), NOW(), 5, 1),
--                                                                            (NOW(), NOW(), 6, 4),
--                                                                            (NOW(), NOW(), 6, 5);
--
-- 22. planner 테이블
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES
                                                                  (NOW(), NOW(), '제주 2박3일 여행', 1),
                                                                  (NOW(), NOW(), '제주 맛집 투어', 1),
                                                                  (NOW(), NOW(), '제주 문화 탐방', 1),
                                                                  (NOW(), NOW(), '제주 액티비티 여행', 1),
                                                                  (NOW(), NOW(), '제주 힐링 여행', 1);
--
-- 23. planner_place 테이블
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES
                                                                                                           (NOW(), NOW(), 1, 1, 1, 1),
                                                                                                           (NOW(), NOW(), 1, 2, 2, 1),
                                                                                                           (NOW(), NOW(), 2, 1, 3, 1),
                                                                                                           (NOW(), NOW(), 2, 2, 4, 1),
                                                                                                           (NOW(), NOW(), 3, 1, 5, 1),
                                                                                                           (NOW(), NOW(), 1, 1, 3, 2),
                                                                                                           (NOW(), NOW(), 1, 2, 6, 2),
                                                                                                           (NOW(), NOW(), 2, 1, 3, 2),
                                                                                                           (NOW(), NOW(), 1, 1, 2, 3),
                                                                                                           (NOW(), NOW(), 1, 2, 4, 3),
                                                                                                           (NOW(), NOW(), 1, 1, 7, 4),
                                                                                                           (NOW(), NOW(), 1, 2, 1, 4),
                                                                                                           (NOW(), NOW(), 1, 1, 4, 5),
                                                                                                           (NOW(), NOW(), 1, 2, 5, 5);
-- --
-- -- 24. review 테이블
-- INSERT INTO review (created_at, updated_at, content, rate, course_id, member_id, place_id) VALUES
--                                                                                                (NOW(), NOW(), '정말 아름다운 자연 경관이었습니다. 추천합니다!', 4.5, 1, 1, 1),
--                                                                                                (NOW(), NOW(), '제주 문화를 잘 알 수 있는 좋은 곳이에요.', 4.0, 3, 2, 2),
--                                                                                                (NOW(), NOW(), '흑돼지가 정말 맛있어요! 현지인 맛집 인정', 4.8, 4, 3, 3),
--                                                                                                (NOW(), NOW(), '일출이 정말 장관이었습니다.', 4.7, 1, 4, 4),
--                                                                                                (NOW(), NOW(), '호텔 시설이 훌륭하고 서비스가 좋았습니다.', 4.3, NULL, 5, 5),
--                                                                                                (NOW(), NOW(), '전통 시장의 정취를 느낄 수 있었어요.', 3.8, NULL, 1, 6),
--                                                                                                (NOW(), NOW(), '다이빙 체험이 정말 재미있었습니다!', 4.6, NULL, 2, 7),
--                                                                                                (NOW(), NOW(), '코스가 잘 짜여져 있어서 편하게 관광했어요.', 4.2, 2, 3, 1),
--                                                                                                (NOW(), NOW(), '가격 대비 만족스러운 식사였습니다.', 4.1, 4, 4, 3);

-- 데이터 삽입 완료
INSERT INTO category_map (category3, category_type) VALUES ('A01010400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A01010800','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A01011100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A01011200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02020500','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02020600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02020800','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02030400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02030600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02040800','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A02050100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('B02010600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('B02011100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('B02010900','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A04010200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A04010600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A04011200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03020400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03020500','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03020600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021500','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021600','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03021800','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022000','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03022700','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03020200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03020300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03010200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03030100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03030200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03030400','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03030700','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03030800','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03010300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03040100','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03040200','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03040300','ACTIVITY');
INSERT INTO category_map (category3, category_type) VALUES ('A03040400','ACTIVITY');
-- MOOD
INSERT INTO category_map (category3, category_type) VALUES ('A01010500','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A01010600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A01010700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A01011600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A01011700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02020200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02010500','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02010800','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02010900','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02020700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02020800','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02030400','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02030600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02050600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02060500','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02060600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02061300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020900','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010100','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010500','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02011300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02011600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010400','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010600','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04011000','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03020700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03021200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03021700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03022700','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03010200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03030300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03010300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03040400','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02070100','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02070200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02081200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02080500','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02080200','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02080300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02080400','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02080900','MOOD');
-- FOOD
INSERT INTO category_map (category3, category_type) VALUES ('A02081300','MOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A01011000','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A02040600','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010500','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010900','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02011000','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('B02010100','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010500','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010200','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A04010900','FOOD');
INSERT INTO category_map (category3, category_type) VALUES ('A03021700','FOOD');
--FOREST
INSERT INTO category_map (category3, category_type) VALUES ('A01010200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010400','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010500','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010600','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010800','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01010900','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011000','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011100','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011300','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011600','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011800','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01011900','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01020100','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A01020200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A02050500','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('B02010100','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('B02010500','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('B02010700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A04010900','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A04010200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A04010700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A03021700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A03022700','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A03010200','FOREST');
INSERT INTO category_map (category3, category_type) VALUES ('A03030600','FOREST');
--CULTURE
INSERT INTO category_map (category3, category_type) VALUES ('A01010200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A01010400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010500','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010700','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010800','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02010900','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020500','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02020700','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02030100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02030200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02030400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02030600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02040400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02040900','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02050100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02050200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02050600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060500','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060700','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060800','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02060900','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02061000','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02061100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02061200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02061300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02061400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('B02011000','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('B02011100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A04010700','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A04010900','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A04010200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A03021600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A03030600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02070100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02070200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080400','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080500','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080600','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080800','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02080900','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02081000','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02081100','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02081200','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02081300','CULTURE');
INSERT INTO category_map (category3, category_type) VALUES ('A02081400','CULTURE');
--SOCIAL
INSERT INTO category_map (category3, category_type) VALUES ('A01011200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A02010900','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05020100','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05020200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05020300','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05020400','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05020700','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A05021000','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('B02010600','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('B02011100','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('B02011200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A04010300','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A04010400','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A04010700','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A04010100','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A04010200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03020600','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03020700','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03020800','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03020900','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03021000','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03021100','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03021200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03021700','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03022000','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03022100','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03030800','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03010200','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03030300','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A03010300','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A02081300','SOCIAL');
INSERT INTO category_map (category3, category_type) VALUES ('A02081000','SOCIAL');





