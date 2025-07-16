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
INSERT INTO member (created_at, updated_at, badge_count, email, fcm_token, login_id, name, phone_no, category_type) VALUES
                                                                                                                        (NOW(), NOW(), 5, 'kim.jeju@email.com', 'fcm_token_001', '1111', '김제주', '010-1234-5678', 'CULTURE'),
                                                                                                                        (NOW(), NOW(), 3, 'lee.seogwipo@email.com', 'fcm_token_002', 'lee_seogwipo', '이서귀포', '010-2345-6789', 'FOOD'),
                                                                                                                        (NOW(), NOW(), 7, 'park.hallasan@email.com', 'fcm_token_003', 'park_hallasan', '박한라산', '010-3456-7890', 'FOREST'),
                                                                                                                        (NOW(), NOW(), 2, 'choi.seongeup@email.com', 'fcm_token_004', 'choi_seongeup', '최성읍', '010-4567-8901', 'ACTIVITY'),
                                                                                                                        (NOW(), NOW(), 4, 'jung.udo@email.com', 'fcm_token_005', 'jung_udo', '정우도', '010-5678-9012', 'MOOD');

-- 3. auth 테이블
INSERT INTO auth (refresh_token, session_id, member_id) VALUES
                                                            ('refresh_token_12345abcde', 'session_001', 1),
                                                            ('refresh_token_67890fghij', 'session_002', 2),
                                                            ('refresh_token_klmno54321', 'session_003', 3),
                                                            ('refresh_token_pqrst09876', 'session_004', 4),
                                                            ('refresh_token_uvwxy13579', 'session_005', 5);

-- 4. course_detail 테이블
INSERT INTO course_detail (created_at, updated_at, distance, infocentertourcourse, schedule, taketime, theme) VALUES
                                                                                                                  (NOW(), NOW(), '5.2km', '제주관광공사 064-740-6000', '09:00-17:00', '3시간', '해안 드라이브 코스'),
                                                                                                                  (NOW(), NOW(), '3.8km', '서귀포시청 064-760-2114', '10:00-16:00', '2시간 30분', '오름 트래킹 코스'),
                                                                                                                  (NOW(), NOW(), '7.1km', '제주시청 064-728-2751', '08:00-18:00', '4시간', '문화유적 탐방 코스'),
                                                                                                                  (NOW(), NOW(), '4.5km', '제주관광안내소 064-742-0033', '09:30-17:30', '3시간 30분', '맛집 투어 코스');

-- 5. course 테이블
INSERT INTO course (created_at, updated_at, category_1, category_2, category_3, content_id, origin_image, overview, sigungu_code, thumbnail_image, title, course_detail_id) VALUES
                                                                                                                                                                                (NOW(), NOW(), '자연', '해안', '해변', 'course_001', 'https://example.com/course1_origin.jpg', '제주 동쪽 해안을 따라 아름다운 풍경을 즐기는 드라이브 코스', 4, 'https://example.com/course1_thumb.jpg', '제주 동쪽 해안 드라이브', 1),
                                                                                                                                                                                (NOW(), NOW(), '레포츠', '산악', '등반', 'course_002', 'https://example.com/course2_origin.jpg', '한라산 둘레길과 주변 오름을 탐방하는 트래킹 코스', 3, 'https://example.com/course2_thumb.jpg', '한라산 둘레길 트래킹', 2),
                                                                                                                                                                                (NOW(), NOW(), '문화', '역사', '유적', 'course_003', 'https://example.com/course3_origin.jpg', '제주의 전통 문화와 역사 유적지를 둘러보는 코스', 4, 'https://example.com/course3_thumb.jpg', '제주 문화유적 탐방', 3),
                                                                                                                                                                                (NOW(), NOW(), '음식', '향토', '맛집', 'course_004', 'https://example.com/course4_origin.jpg', '제주 대표 음식점들을 순서대로 방문하는 미식 코스', 3, 'https://example.com/course4_thumb.jpg', '제주 향토음식 투어', 4);

-- 6. course_category 테이블
INSERT INTO course_category (category_type, course_id) VALUES
                                                           ('ACTIVITY', 1),
                                                           ('FOREST', 2),
                                                           ('CULTURE', 3),
                                                           ('FOOD', 4),
                                                           ('MOOD', 1),
                                                           ('SOCIAL', 4);

-- 7. culture_detail 테이블
INSERT INTO culture_detail (created_at, updated_at, accomcountculture, chkbabycarriageculture, chkcreditcardculture, chkpetculture, discountinfo, infocenterculture, parkingculture, parkingfee, restdateculture, scale, spendtime, usefee, usetimeculture) VALUES
                                                                                                                                                                                                                                                                (NOW(), NOW(), '100명', '가능', '가능', '불가능', '단체 20% 할인', '064-710-7801', '가능', '무료', '월요일', '대형', '1시간 30분', '성인 3000원', '09:00-18:00'),
                                                                                                                                                                                                                                                                (NOW(), NOW(), '200명', '가능', '가능', '가능', '경로 50% 할인', '064-760-4292', '가능', '유료 1000원', '화요일', '중형', '2시간', '성인 5000원', '09:30-17:30'),
                                                                                                                                                                                                                                                                (NOW(), NOW(), '150명', '불가능', '가능', '불가능', '학생 30% 할인', '064-740-6000', '가능', '무료', '연중무휴', '소형', '45분', '성인 2000원', '10:00-17:00');

-- 8. festival_detail 테이블
INSERT INTO festival_detail (created_at, updated_at, agelimit, bookingplace, discountinfofestival, eventenddate, eventhomepage, eventplace, eventstartdate, festivalgrade, placeinfo, playtime, program, spendtimefestival, sponsor1, sponsor1tel, sponsor2, sponsor2tel, subevent, usetimefestival) VALUES
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), '전연령', '제주컨벤션센터', '조기예약 20% 할인', '20241130', 'https://jejufestival.com', '제주월드컵경기장', '20241125', '국제', '제주시 월드컵로 33', '3시간', '음악공연, 전시', '반일', '제주관광공사', '064-740-6000', '제주시청', '064-728-2751', '체험부스 운영', '14:00-22:00'),
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), '성인', '인터파크', '단체 15% 할인', '20241025', 'https://jejuculture.kr', '제주아트센터', '20241020', '지역', '제주시 동광로 69', '2시간', '전통공연', '오후', '제주문화재단', '064-800-9114', '제주도청', '064-710-2114', '문화체험', '19:00-21:00');

-- 9. food_detail 테이블
INSERT INTO food_detail (created_at, updated_at, chkcreditcardfood, discountinfofood, firstmenu, infocenterfood, kidsfacility, lcnsno, opendatefood, opentimefood, packing, parkingfood, reservationfood, restdatefood, scalefood, seat, smoking, treatmenu) VALUES
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '현금결제 5% 할인', '흑돼지 구이', '064-742-3456', '키즈존 운영', '2023-12-0001', '20100315', '11:00-22:00', '가능', '20대', '가능', '매월 둘째주 일요일', '200㎡', '80석', '금연', '흑돼지, 갈치조림'),
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '포장 10% 할인', '전복죽', '064-733-7890', '유아의자 제공', '2023-11-0002', '20080720', '09:00-20:00', '가능', '15대', '필수', '연중무휴', '150㎡', '60석', '금연', '전복죽, 성게국'),
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '단골 적립 5%', '해물칼국수', '064-752-1234', '없음', '2023-10-0003', '20120410', '10:00-21:00', '가능', '10대', '불가능', '일요일', '120㎡', '40석', '분리흡연', '해물칼국수, 빙떡');

-- 10. leports_detail 테이블
INSERT INTO leports_detail (created_at, updated_at, accomcountleports, chkbabycarriageleports, chkcreditcardleports, chkpetleports, expagerangeleports, infocenterleports, openperiod, parkingfeeleports, parkingleports, reservation, restdateleports, scaleleports, usefeeleports, usetimeleports) VALUES
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), 50, '불가능', '가능', '불가능', '만 12세 이상', '064-740-1234', '3월-11월', '무료', '가능', '필수', '12월-2월', '대형', '성인 25000원', '09:00-17:00'),
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), 30, '불가능', '가능', '가능', '만 16세 이상', '064-760-5678', '연중', '유료 2000원', '가능', '권장', '없음', '중형', '성인 15000원', '08:00-18:00');

-- 11. lodge_detail 테이블
INSERT INTO lodge_detail (created_at, updated_at, accomcountlodging, barbecue, beauty, beverage, bicycle, campfire, checkintime, checkouttime, chkcooking, fitness, foodplace, infocenterlodging, karaoke, parkinglodging, pickup, publicbath, publicpc, refundregulation, reservationlodging, reservationurl, roomcount, roomtype, sauna, scalelodging, seminar, sports, subfacility) VALUES
                                                                                                                                                                                                                                                                                                                                                                                           (NOW(), NOW(), '200명', '가능', '있음', '있음', '대여 가능', '가능', '15:00', '11:00', '가능', '있음', '레스토랑', '064-740-9999', '있음', '가능', '가능', '있음', '로비', '취소 3일전까지', '전화예약', 'https://jeju-hotel.com', '80실', '온돌, 침대', '있음', '5층 건물', '가능', '수영장', '세탁실, 편의점'),
                                                                                                                                                                                                                                                                                                                                                                                           (NOW(), NOW(), '100명', '가능', '없음', '있음', '없음', '가능', '16:00', '10:00', '불가능', '없음', '카페', '064-760-1111', '없음', '가능', '불가능', '없음', '없음', '당일취소 불가', '온라인예약', 'https://jeju-pension.kr', '20실', '원룸형', '없음', '2층 건물', '불가능', '없음', '바베큐장');

-- 12. shopping_deatail 테이블 (오타 그대로 유지)
INSERT INTO shopping_deatail (created_at, updated_at, chkbabycarriageshopping, chkcreditcardshopping, chkpetshopping, culturecenter, fairday, infocentershopping, opendateshopping, opentime, parkingshopping, restdateshopping, restroom, saleitem, saleitemcost, scaleshopping, shopguide) VALUES
                                                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '가능', '불가능', '있음', '매주 토요일', '064-740-3333', '20000501', '09:00-21:00', '가능', '연중무휴', '있음', '한라봉, 감귤', '한라봉 1kg 15000원', '대형', '2층 안내데스크'),
                                                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '가능', '가능', '없음', '매주 일요일', '064-760-2222', '19980315', '10:00-22:00', '가능', '명절 당일', '있음', '흑돼지, 해산물', '흑돼지 1kg 25000원', '중형', '1층 종합안내소');

-- 13. tour_detail 테이블
INSERT INTO tour_detail (created_at, updated_at, accomcount, chkbabycarriage, chkcreditcard, chkpet, expagerange, expguide, heritage1, heritage2, heritage3, infocenter, opendate, parking, restdate, useseason, usetime) VALUES
                                                                                                                                                                                                                              (NOW(), NOW(), '300명', '가능', '가능', '불가능', '전연령', '한국어, 영어, 중국어', 1, '천연기념물', '용머리해안', '064-740-6000', '19620101', '가능', '연중무휴', '사계절', '08:00-18:00'),
                                                                                                                                                                                                                              (NOW(), NOW(), '150명', '가능', '가능', '가능', '만 7세 이상', '한국어', 0, '', '', '064-760-4000', '19851201', '가능', '월요일', '봄~가을', '09:00-17:00'),
                                                                                                                                                                                                                              (NOW(), NOW(), '500명', '가능', '가능', '불가능', '전연령', '한국어, 영어, 일본어', 1, '세계자연유산', '성산일출봉', '064-783-0959', '20071201', '가능', '연중무휴', '사계절', '05:00-19:00');

-- 14. place 테이블
INSERT INTO place (created_at, updated_at, address, category_1, category_2, category_3, city_code, content_id, content_type_id, copyright_type, detail_address, mapx, mapy, origin_image, overview, sigungucode, tel, thumbnail_image, title, course_id, culture_detail_id, festival_detail_id, food_detail_id, leports_detail_id, lodge_detail_id, shopping_detail_id, tour_detail_id) VALUES
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 제주시 조천읍 선흘리', '자연', '산', '오름', '110', 'place_001', '12', 'TYPE1', '비자림로 55', 126.8011, 33.4996, 'https://example.com/place1_origin.jpg', '제주의 대표적인 오름 중 하나로 아름다운 자연경관을 자랑합니다.', 4, '064-710-7800', 'https://example.com/place1_thumb.jpg', '거문오름', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 제주시 애월읍 고성리', '문화시설', '박물관', '민속', '110', 'place_002', '14', 'TYPE3', '한림로 272', 126.2397, 33.4615, 'https://example.com/place2_origin.jpg', '제주의 전통 문화와 역사를 한눈에 볼 수 있는 박물관입니다.', 4, '064-710-7801', 'https://example.com/place2_thumb.jpg', '제주민속촌', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 제주시 한림읍 한림리', '음식점', '한식', '향토음식', '110', 'place_003', '39', 'TYPE1', '한림로 123', 126.2658, 33.4142, 'https://example.com/place3_origin.jpg', '제주 흑돼지 전문점으로 현지인들이 자주 찾는 맛집입니다.', 4, '064-796-1234', 'https://example.com/place3_thumb.jpg', '제주 흑돼지 전문점', 4, NULL, NULL, 1, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 서귀포시 성산읍 성산리', '자연', '해안', '절경', '130', 'place_004', '12', 'TYPE1', '성산읍 일출로 284-12', 126.9424, 33.4584, 'https://example.com/place4_origin.jpg', '일출로 유명한 제주의 대표 관광지입니다.', 3, '064-783-0959', 'https://example.com/place4_thumb.jpg', '성산일출봉', 1, NULL, NULL, NULL, NULL, NULL, NULL, 3),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 서귀포시 중문동', '숙박', '리조트', '특급호텔', '130', 'place_005', '32', 'TYPE3', '중문관광로 72번길 35', 126.4077, 33.2394, 'https://example.com/place5_origin.jpg', '중문관광단지 내 최고급 리조트 호텔입니다.', 3, '064-735-1234', 'https://example.com/place5_thumb.jpg', '중문 리조트 호텔', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 제주시 이도이동', '쇼핑', '전통시장', '특산품', '110', 'place_006', '38', 'TYPE1', '관덕로 14길 20', 126.5219, 33.5132, 'https://example.com/place6_origin.jpg', '제주 전통 시장으로 다양한 특산품을 만날 수 있습니다.', 4, '064-752-3001', 'https://example.com/place6_thumb.jpg', '제주 동문시장', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
                                                                                                                                                                                                                                                                                                                                                                                            (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 사계리', '레포츠', '해양스포츠', '스쿠버다이빙', '130', 'place_007', '28', 'TYPE1', '사계로 216', 126.2916, 33.2282, 'https://example.com/place7_origin.jpg', '제주 최고의 다이빙 포인트 중 하나입니다.', 3, '064-794-5678', 'https://example.com/place7_thumb.jpg', '사계 다이빙센터', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL);

-- 15. place_category 테이블
-- INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES
--                                                                          (NOW(), NOW(), 'GOLD', 1, 1),
--                                                                          (NOW(), NOW(), 'SILVER', 1, 2),
--                                                                          (NOW(), NOW(), 'BRONZE', 2, 3),
--                                                                          (NOW(), NOW(), 'GOLD', 3, 4),
--                                                                          (NOW(), NOW(), 'SILVER', 3, 5),
--                                                                          (NOW(), NOW(), 'BRONZE', 4, 6),
--                                                                          (NOW(), NOW(), 'GOLD', 5, 7);

-- 16. spot 테이블
INSERT INTO spot (created_at, updated_at, date,order_index, place_id) VALUES
                                                              (NOW(), NOW(), '2025-07-01',1, 1),
                                                              (NOW(), NOW(), '2025-07-01',2, 2),
                                                              (NOW(), NOW(), '2025-07-01',3, 3),
                                                              (NOW(), NOW(), '2025-07-01',4, 4),
                                                              (NOW(), NOW(), '2025-06-01',1, 5),
                                                              (NOW(), NOW(), '2025-06-01',2, 6),
                                                              (NOW(), NOW(), '2025-06-01',3, 7),
                                                              (NOW(), NOW(), '2025-06-01',4, 1),
                                                              (NOW(), NOW(), '2024-03-01',1, 2);
--
--
-- -- 17. spot_category_summary 테이블
INSERT INTO spot_category_summary (created_at, updated_at, visit_count, category_type, spot_id) VALUES
                                                                                                    (NOW(), NOW(), 25, 'FOREST', 1),
                                                                                                    (NOW(), NOW(), 18, 'CULTURE', 2),
                                                                                                    (NOW(), NOW(), 32, 'FOOD', 3),
                                                                                                    (NOW(), NOW(), 41, 'MOOD', 4),
                                                                                                    (NOW(), NOW(), 15, 'SOCIAL', 5),
                                                                                                    (NOW(), NOW(), 28, 'FOOD', 6),
                                                                                                    (NOW(), NOW(), 22, 'ACTIVITY', 7),
                                                                                                    (NOW(), NOW(), 19, 'ACTIVITY', 8);


-- 18. badge 테이블
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES
                                                                                 (NOW(), NOW(), 'FOREST', 1),
                                                                                 (NOW(), NOW(), 'CULTURE', 2),
                                                                                 (NOW(), NOW(), 'FOOD', 3),
                                                                                 (NOW(), NOW(), 'MOOD', 4),
                                                                                 (NOW(), NOW(), 'SOCIAL', 5),
                                                                                 (NOW(), NOW(), 'FOOD', 6),
                                                                                 (NOW(), NOW(), 'ACTIVITY', 7),
                                                                                 (NOW(), NOW(), 'ACTIVITY', 1),
                                                                                 (NOW(), NOW(), 'SOCIAL', 2);

-- 19. visit_log 테이블
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES
                                                                       (NOW(), NOW(), 1, 1),
                                                                       (NOW(), NOW(), 1, 2),
                                                                       (NOW(), NOW(), 1, 4),
                                                                       (NOW(), NOW(), 1, 5),
                                                                       (NOW(), NOW(), 1, 6),
                                                                       (NOW(), NOW(), 1, 7),
                                                                       (NOW(), NOW(), 1, 8),
                                                                       (NOW(), NOW(), 1, 9),

                                                                       (NOW(), NOW(), 3, 5),
                                                                       (NOW(), NOW(), 3, 7),
                                                                       (NOW(), NOW(), 4, 6),
                                                                       (NOW(), NOW(), 5, 7),
                                                                       (NOW(), NOW(), 5, 1),
                                                                       (NOW(), NOW(), 5, 2);

-- 20. folder 테이블
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES
                                                                 (NOW(), NOW(), '제주 맛집 모음', 1),
                                                                 (NOW(), NOW(), '문화유적지', 1),
                                                                 (NOW(), NOW(), '오름 투어', 2),
                                                                 (NOW(), NOW(), '가족 여행지', 3),
                                                                 (NOW(), NOW(), '액티비티 스팟', 4),
                                                                 (NOW(), NOW(), '힐링 장소', 5);

-- 21. folder_place 테이블
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES
                                                                           (NOW(), NOW(), 1, 3),
                                                                           (NOW(), NOW(), 1, 6),
                                                                           (NOW(), NOW(), 2, 2),
                                                                           (NOW(), NOW(), 2, 4),
                                                                           (NOW(), NOW(), 3, 1),
                                                                           (NOW(), NOW(), 3, 4),
                                                                           (NOW(), NOW(), 4, 2),
                                                                           (NOW(), NOW(), 4, 5),
                                                                           (NOW(), NOW(), 5, 7),
                                                                           (NOW(), NOW(), 5, 1),
                                                                           (NOW(), NOW(), 6, 4),
                                                                           (NOW(), NOW(), 6, 5);

-- 22. planner 테이블
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES
                                                                  (NOW(), NOW(), '제주 2박3일 여행', 1),
                                                                  (NOW(), NOW(), '제주 맛집 투어', 2),
                                                                  (NOW(), NOW(), '제주 문화 탐방', 3),
                                                                  (NOW(), NOW(), '제주 액티비티 여행', 4),
                                                                  (NOW(), NOW(), '제주 힐링 여행', 5);

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

-- 24. review 테이블
INSERT INTO review (created_at, updated_at, content, rate, course_id, member_id, place_id) VALUES
                                                                                               (NOW(), NOW(), '정말 아름다운 자연 경관이었습니다. 추천합니다!', 4.5, 1, 1, 1),
                                                                                               (NOW(), NOW(), '제주 문화를 잘 알 수 있는 좋은 곳이에요.', 4.0, 3, 2, 2),
                                                                                               (NOW(), NOW(), '흑돼지가 정말 맛있어요! 현지인 맛집 인정', 4.8, 4, 3, 3),
                                                                                               (NOW(), NOW(), '일출이 정말 장관이었습니다.', 4.7, 1, 4, 4),
                                                                                               (NOW(), NOW(), '호텔 시설이 훌륭하고 서비스가 좋았습니다.', 4.3, NULL, 5, 5),
                                                                                               (NOW(), NOW(), '전통 시장의 정취를 느낄 수 있었어요.', 3.8, NULL, 1, 6),
                                                                                               (NOW(), NOW(), '다이빙 체험이 정말 재미있었습니다!', 4.6, NULL, 2, 7),
                                                                                               (NOW(), NOW(), '코스가 잘 짜여져 있어서 편하게 관광했어요.', 4.2, 2, 3, 1),
                                                                                               (NOW(), NOW(), '가격 대비 만족스러운 식사였습니다.', 4.1, 4, 4, 3);

-- 데이터 삽입 완료
-- 모든 테이블에 실제 제주도 관광지, 맛집, 숙박업소 등을 참고한 그럴듯한 목 데이터가 포함되어 있습니다.