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
INSERT INTO course_category (course_id,category_type) VALUES
                                    (1,'CULTURE'),
                                    (2,'CULTURE');


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
INSERT INTO place (created_at, updated_at, address, origin_image, sigungucode, course_id) VALUES
                                                    (NOW(), NOW(), '제주특별자치도 제주시 조천읍 선흘리','image1',4,1),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 애월읍 고성리','image2',4,1),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 한림읍 한림리','image3',4,1),
                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 성산읍 성산리','image4',3,1),
                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 중문동','image5',3,2),
                                                    (NOW(), NOW(), '제주특별자치도 제주시 이도이동','image6',4,2),
                                                    (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 사계리','image7',3,2);


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




-- 18. badge 테이블
INSERT INTO spot_category_summary (created_at, updated_at, visit_count, category_type, spot_id) VALUES
                                                                                                    (NOW(), NOW(), 25, 'FOREST', 1),
                                                                                                    (NOW(), NOW(), 18, 'CULTURE', 1),
                                                                                                    (NOW(), NOW(), 32, 'FOOD', 1),
                                                                                                    (NOW(), NOW(), 41, 'MOOD', 1),
                                                                                                    (NOW(), NOW(), 15, 'SOCIAL', 5),
                                                                                                    (NOW(), NOW(), 28, 'FOOD', 6),
                                                                                                    (NOW(), NOW(), 22, 'ACTIVITY', 7),
                                                                                                    (NOW(), NOW(), 19, 'ACTIVITY', 8);

-- 19. visit_log 테이블
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES
                                                                       (NOW(), NOW(), 1, 1),
                                                                       (NOW(), NOW(), 1, 2),
                                                                       (NOW(), NOW(), 1, 5),


                                                                       (NOW(), NOW(), 3, 5),
                                                                       (NOW(), NOW(), 3, 7),
                                                                       (NOW(), NOW(), 4, 6),
                                                                       (NOW(), NOW(), 5, 7),
                                                                       (NOW(), NOW(), 5, 1),
                                                                       (NOW(), NOW(), 5, 2);

-- 20. folder 테이블
INSERT INTO folder (created_at, updated_at, name, member_id, is_default) VALUES
                                                                 (NOW(), NOW(), '제주 맛집 모음', 1,true),
                                                                 (NOW(), NOW(), '문화유적지', 1,false),
                                                                 (NOW(), NOW(), '오름 투어', 2,true),
                                                                 (NOW(), NOW(), '가족 여행지', 3,true),
                                                                 (NOW(), NOW(), '액티비티 스팟', 4,true),
                                                                 (NOW(), NOW(), '힐링 장소', 5,true);

-- 21. folder_place 테이블
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES
                                                                           (CURRENT_TIMESTAMP,             CURRENT_TIMESTAMP,             1, 1),
                                                                           (DATEADD('SECOND', 1, CURRENT_TIMESTAMP), DATEADD('SECOND', 1, CURRENT_TIMESTAMP), 1, 2),
                                                                           (DATEADD('SECOND', 2, CURRENT_TIMESTAMP), DATEADD('SECOND', 2, CURRENT_TIMESTAMP), 1, 3),
                                                                           (DATEADD('SECOND', 3, CURRENT_TIMESTAMP), DATEADD('SECOND', 3, CURRENT_TIMESTAMP), 1, 4),
                                                                           (DATEADD('SECOND', 4, CURRENT_TIMESTAMP), DATEADD('SECOND', 4, CURRENT_TIMESTAMP), 1, 5),
                                                                           (DATEADD('SECOND', 5, CURRENT_TIMESTAMP), DATEADD('SECOND', 5, CURRENT_TIMESTAMP), 1, 6),
                                                                           (DATEADD('SECOND', 6, CURRENT_TIMESTAMP), DATEADD('SECOND', 6, CURRENT_TIMESTAMP), 1, 7),
                                                                           (DATEADD('SECOND', 7, CURRENT_TIMESTAMP), DATEADD('SECOND', 7, CURRENT_TIMESTAMP), 2, 2),
                                                                           (DATEADD('SECOND', 8, CURRENT_TIMESTAMP), DATEADD('SECOND', 8, CURRENT_TIMESTAMP), 2, 4),
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
--
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
INSERT INTO category_map (category3, category_type) VALUES
-- 자연 (A01)
('A01010100', 'FOREST'),
('A01010100', 'ACTIVITY'),
('A01010200', 'FOREST'),
('A01010200', 'MOOD'),
('A01010300', 'FOREST'),
('A01010400', 'FOREST'),
('A01010400', 'ACTIVITY'),
('A01010400', 'MOOD'),
('A01010500', 'FOREST'),
('A01010500', 'CULTURE'),
('A01010600', 'FOREST'),
('A01010600', 'MOOD'),
('A01010700', 'FOREST'),
('A01010700', 'CULTURE'),
('A01010700', 'ACTIVITY'),
('A01010800', 'FOREST'),
('A01010800', 'MOOD'),
('A01010900', 'FOREST'),
('A01010900', 'ACTIVITY'),
('A01011000', 'FOREST'),
('A01011100', 'FOREST'),
('A01011100', 'MOOD'),
('A01011100', 'ACTIVITY'),
('A01011200', 'ACTIVITY'),
('A01011200', 'MOOD'),
('A01011300', 'FOREST'),
('A01011300', 'ACTIVITY'),
('A01011400', 'CULTURE'),
('A01011400', 'FOOD'),
('A01011600', 'CULTURE'),
('A01011600', 'MOOD'),
('A01011700', 'FOREST'),
('A01011700', 'MOOD'),
('A01011800', 'FOREST'),
('A01011800', 'ACTIVITY'),
('A01011900', 'FOREST'),
('A01011900', 'CULTURE'),
('A01020100', 'FOREST'),
('A01020100', 'CULTURE'),
('A01020200', 'FOREST'),
('A01020200', 'CULTURE'),

-- 인문(문화/예술/역사) (A02)
('A02010100', 'CULTURE'),
('A02010100', 'ACTIVITY'),
('A02010200', 'CULTURE'),
('A02010200', 'ACTIVITY'),
('A02010300', 'CULTURE'),
('A02010400', 'CULTURE'),
('A02010400', 'MOOD'),
('A02010500', 'CULTURE'),
('A02010600', 'CULTURE'),
('A02010600', 'ACTIVITY'),
('A02010600', 'FOOD'),
('A02010700', 'CULTURE'),
('A02010700', 'ACTIVITY'),
('A02010800', 'CULTURE'),
('A02010800', 'MOOD'),
('A02010900', 'CULTURE'),
('A02010900', 'MOOD'),
('A02011000', 'CULTURE'),
('A02011000', 'ACTIVITY'),
('A02020200', 'ACTIVITY'),
('A02020200', 'FOOD'),
('A02020200', 'MOOD'),
('A02020300', 'MOOD'),
('A02020300', 'FOREST'),
('A02020400', 'MOOD'),
('A02020400', 'SOCIAL'),
('A02020500', 'MOOD'),
('A02020500', 'ACTIVITY'),
('A02020600', 'ACTIVITY'),
('A02020600', 'MOOD'),
('A02020600', 'SOCIAL'),
('A02020700', 'MOOD'),
('A02020700', 'ACTIVITY'),
('A02020800', 'ACTIVITY'),
('A02020800', 'CULTURE'),
('A02030100', 'ACTIVITY'),
('A02030100', 'CULTURE'),
('A02030100', 'FOOD'),
('A02030200', 'CULTURE'),
('A02030200', 'ACTIVITY'),
('A02030300', 'CULTURE'),
('A02030300', 'MOOD'),
('A02030400', 'ACTIVITY'),
('A02030400', 'SOCIAL'),
('A02030600', 'CULTURE'),
('A02030600', 'SOCIAL'),
('A02040400', 'CULTURE'),
('A02040400', 'ACTIVITY'),
('A02040600', 'FOOD'),
('A02040600', 'CULTURE'),
('A02040800', 'CULTURE'),
('A02040800', 'ACTIVITY'),
('A02040900', 'CULTURE'),
('A02040900', 'ACTIVITY'),
('A02041000', 'CULTURE'),
('A02041000', 'ACTIVITY'),
('A02050100', 'CULTURE'),
('A02050100', 'ACTIVITY'),
('A02050100', 'MOOD'),
('A02050200', 'CULTURE'),
('A02050200', 'MOOD'),
('A02050300', 'CULTURE'),
('A02050300', 'MOOD'),
('A02050400', 'CULTURE'),
('A02050500', 'CULTURE'),
('A02050500', 'ACTIVITY'),
('A02050600', 'CULTURE'),
('A02050600', 'ACTIVITY'),
('A02060100', 'CULTURE'),
('A02060100', 'ACTIVITY'),
('A02060200', 'CULTURE'),
('A02060200', 'ACTIVITY'),
('A02060300', 'CULTURE'),
('A02060300', 'ACTIVITY'),
('A02060400', 'CULTURE'),
('A02060400', 'SOCIAL'),
('A02060400', 'FOOD'),
('A02060500', 'CULTURE'),
('A02060500', 'MOOD'),
('A02060600', 'CULTURE'),
('A02060600', 'ACTIVITY'),
('A02060700', 'CULTURE'),
('A02060700', 'ACTIVITY'),
('A02060800', 'CULTURE'),
('A02060800', 'ACTIVITY'),
('A02060900', 'CULTURE'),
('A02060900', 'MOOD'),
('A02061000', 'CULTURE'),
('A02061000', 'MOOD'),
('A02061100', 'CULTURE'),
('A02061100', 'ACTIVITY'),
('A02061200', 'CULTURE'),
('A02061200', 'MOOD'),
('A02061300', 'CULTURE'),
('A02061300', 'ACTIVITY'),
('A02061400', 'CULTURE'),
('A02061400', 'ACTIVITY'),
('A02070100', 'CULTURE'),
('A02070100', 'SOCIAL'),
('A02070100', 'ACTIVITY'),
('A02070200', 'SOCIAL'),
('A02070200', 'ACTIVITY'),
('A02080100', 'CULTURE'),
('A02080100', 'ACTIVITY'),
('A02080200', 'CULTURE'),
('A02080200', 'MOOD'),
('A02080300', 'CULTURE'),
('A02080300', 'MOOD'),
('A02080400', 'CULTURE'),
('A02080400', 'MOOD'),
('A02080500', 'CULTURE'),
('A02080500', 'ACTIVITY'),
('A02080600', 'CULTURE'),
('A02080600', 'ACTIVITY'),
('A02080800', 'CULTURE'),
('A02080800', 'ACTIVITY'),
('A02080900', 'CULTURE'),
('A02080900', 'MOOD'),
('A02081000', 'CULTURE'),
('A02081000', 'SOCIAL'),
('A02081100', 'CULTURE'),
('A02081100', 'MOOD'),
('A02081200', 'ACTIVITY'),
('A02081200', 'SOCIAL'),
('A02081300', 'ACTIVITY'),
('A02081300', 'SOCIAL'),
('A02081400', 'CULTURE'),
('A02081400', 'ACTIVITY'),

-- 추천코스 (C01)
('C01120001', 'ACTIVITY'),
('C01120001', 'SOCIAL'),
('C01120001', 'MOOD'),
('C01130001', 'MOOD'),
('C01130001', 'CULTURE'),
('C01140001', 'MOOD'),
('C01140001', 'FOREST'),
('C01150001', 'ACTIVITY'),
('C01150001', 'MOOD'),
('C01160001', 'ACTIVITY'),
('C01160001', 'FOREST'),
('C01170001', 'FOOD'),
('C01170001', 'SOCIAL'),

-- 레포츠 (A03)
('A03010200', 'ACTIVITY'),
('A03010200', 'MOOD'),
('A03010300', 'ACTIVITY'),
('A03010300', 'MOOD'),
('A03020200', 'ACTIVITY'),
('A03020300', 'ACTIVITY'),
('A03020300', 'SOCIAL'),
('A03020400', 'ACTIVITY'),
('A03020400', 'SOCIAL'),
('A03020500', 'ACTIVITY'),
('A03020500', 'FOREST'),
('A03020600', 'ACTIVITY'),
('A03020600', 'SOCIAL'),
('A03020700', 'ACTIVITY'),
('A03020700', 'SOCIAL'),
('A03020800', 'ACTIVITY'),
('A03020800', 'SOCIAL'),
('A03020900', 'ACTIVITY'),
('A03020900', 'SOCIAL'),
('A03021000', 'ACTIVITY'),
('A03021000', 'SOCIAL'),
('A03021100', 'ACTIVITY'),
('A03021100', 'FOREST'),
('A03021200', 'ACTIVITY'),
('A03021200', 'SOCIAL'),
('A03021300', 'ACTIVITY'),
('A03021300', 'SOCIAL'),
('A03021400', 'ACTIVITY'),
('A03021400', 'SOCIAL'),
('A03021500', 'ACTIVITY'),
('A03021500', 'FOREST'),
('A03021600', 'ACTIVITY'),
('A03021700', 'ACTIVITY'),
('A03021700', 'FOREST'),
('A03021700', 'SOCIAL'),
('A03021800', 'ACTIVITY'),
('A03021800', 'FOREST'),
('A03022000', 'ACTIVITY'),
('A03022000', 'SOCIAL'),
('A03022100', 'ACTIVITY'),
('A03022100', 'FOREST'),
('A03022200', 'ACTIVITY'),
('A03022200', 'FOREST'),
('A03022300', 'ACTIVITY'),
('A03022300', 'FOREST'),
('A03022400', 'ACTIVITY'),
('A03022400', 'MOOD'),
('A03022700', 'ACTIVITY'),
('A03022700', 'FOREST'),
('A03030100', 'ACTIVITY'),
('A03030100', 'MOOD'),
('A03030200', 'ACTIVITY'),
('A03030200', 'FOREST'),
('A03030300', 'ACTIVITY'),
('A03030300', 'MOOD'),
('A03030400', 'ACTIVITY'),
('A03030400', 'FOREST'),
('A03030500', 'ACTIVITY'),
('A03030500', 'MOOD'),
('A03030600', 'ACTIVITY'),
('A03030600', 'MOOD'),
('A03030700', 'ACTIVITY'),
('A03030700', 'MOOD'),
('A03030800', 'ACTIVITY'),
('A03030800', 'FOREST'),
('A03040100', 'ACTIVITY'),
('A03040100', 'MOOD'),
('A03040200', 'ACTIVITY'),
('A03040200', 'MOOD'),
('A03040300', 'ACTIVITY'),
('A03040300', 'MOOD'),
('A03040400', 'ACTIVITY'),
('A03040400', 'MOOD'),
('A03050100', 'ACTIVITY'),
('A03050100', 'SOCIAL'),

-- 숙박 (B02)
('B02010100', 'MOOD'),
('B02010100', 'SOCIAL'),
('B02010500', 'MOOD'),
('B02010500', 'SOCIAL'),
('B02010600', 'SOCIAL'),
('B02010600', 'ACTIVITY'),
('B02010700', 'MOOD'),
('B02010700', 'FOREST'),
('B02010900', 'MOOD'),
('B02011000', 'CULTURE'),
('B02011000', 'MOOD'),
('B02011100', 'SOCIAL'),
('B02011100', 'CULTURE'),
('B02011200', 'CULTURE'),
('B02011200', 'SOCIAL'),
('B02011300', 'MOOD'),
('B02011300', 'SOCIAL'),
('B02011600', 'CULTURE'),
('B02011600', 'MOOD'),

-- 쇼핑 (A04)
('A04010100', 'CULTURE'),
('A04010100', 'FOOD'),
('A04010200', 'CULTURE'),
('A04010200', 'FOOD'),
('A04010300', 'SOCIAL'),
('A04010300', 'MOOD'),
('A04010400', 'SOCIAL'),
('A04010500', 'SOCIAL'),
('A04010500', 'FOOD'),
('A04010600', 'CULTURE'),
('A04010600', 'SOCIAL'),
('A04010700', 'CULTURE'),
('A04010700', 'ACTIVITY'),
('A04010900', 'CULTURE'),
('A04010900', 'FOOD'),
('A04011000', 'SOCIAL'),
('A04011200', 'ACTIVITY'),

-- 음식 (A05)
('A05020100', 'FOOD'),
('A05020100', 'CULTURE'),
('A05020200', 'FOOD'),
('A05020200', 'SOCIAL'),
('A05020300', 'FOOD'),
('A05020300', 'CULTURE'),
('A05020400', 'FOOD'),
('A05020400', 'CULTURE'),
('A05020700', 'FOOD'),
('A05020700', 'SOCIAL'),
('A05020900', 'FOOD'),
('A05020900', 'MOOD'),
('A05021000', 'SOCIAL'),
('A05021000', 'MOOD');


