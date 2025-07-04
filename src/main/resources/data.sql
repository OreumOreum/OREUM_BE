-- 제주도 관광 데이터베이스 목데이터 생성 SQL

-- 1. 카테고리 테이블 (enum 값들)
INSERT INTO category (type) VALUES
                                ('ACTIVITY'),
                                ('CULTURE'),
                                ('FOOD'),
                                ('FOREST'),
                                ('MOOD'),
                                ('SOCIAL');

-- 2. 회원 테이블
INSERT INTO member (created_at, updated_at, badge_count, email, fcm_token, login_id, name, phone_no, category_type) VALUES
                                                                                                                        (NOW(), NOW(), 5, 'kim.jeju@gmail.com', 'fcm_token_001', 'kim_jeju', '배철현', '010-1234-5678', 'CULTURE'),
                                                                                                                        (NOW(), NOW(), 3, 'lee.nature@naver.com', 'fcm_token_002', 'nature_lover', '양지원', '010-2345-6789', 'FOREST'),
                                                                                                                        (NOW(), NOW(), 8, 'park.foodie@kakao.com', 'fcm_token_003', 'jeju_foodie', '주해찬', '010-3456-7890', 'FOOD'),
                                                                                                                        (NOW(), NOW(), 2, 'choi.active@gmail.com', 'fcm_token_004', 'active_choi', '곽민철', '010-4567-8901', 'ACTIVITY'),
                                                                                                                        (NOW(), NOW(), 6, 'jung.social@naver.com', 'fcm_token_005', 'social_jung', '김태현', '010-5678-9012', 'SOCIAL'),
                                                                                                                        (NOW(), NOW(), 1, 'moon.mood@gmail.com', 'fcm_token_006', 'mood_moon', '김가명', '010-6789-0123', 'MOOD');

-- 3. 인증 테이블
INSERT INTO auth (refresh_token, session_id, member_id) VALUES
                                                            ('refresh_token_abcd1234', 'session_kim_001', 1),
                                                            ('refresh_token_efgh5678', 'session_lee_002', 2),
                                                            ('refresh_token_ijkl9012', 'session_park_003', 3),
                                                            ('refresh_token_mnop3456', 'session_choi_004', 4),
                                                            ('refresh_token_qrst7890', 'session_jung_005', 5),
                                                            ('refresh_token_uvwx1234', 'session_moon_006', 6);

-- 4. 코스 테이블
INSERT INTO course (created_at, updated_at, category_1, category_2, category_3, content_id, origin_image, overview, sigungu_code, thumbnail_image, title) VALUES
                                                                                                                                                              (NOW(), NOW(), '자연', '해안절경', '올레길', 'COURSE_001', 'https://example.com/jeju_olle_origin.jpg', '제주올레 1코스는 시작점 성산일출봉에서 광치기해변까지 이어지는 15km의 아름다운 해안길입니다.', 4, 'https://example.com/jeju_olle_thumb.jpg', '제주올레 1코스'),
                                                                                                                                                              (NOW(), NOW(), '문화', '역사', '전통', 'COURSE_002', 'https://example.com/traditional_course_origin.jpg', '제주도의 전통 문화를 체험할 수 있는 코스로 돌하르방, 성읍민속마을, 제주민속촌을 둘러봅니다.', 3, 'https://example.com/traditional_course_thumb.jpg', '제주 전통문화 체험코스'),
                                                                                                                                                              (NOW(), NOW(), '먹거리', '해산물', '특산품', 'COURSE_003', 'https://example.com/food_course_origin.jpg', '제주도만의 특별한 맛을 느낄 수 있는 미식 코스입니다. 흑돼지, 갈치조림, 한라봉 등을 맛보세요.', 4, 'https://example.com/food_course_thumb.jpg', '제주 미식 투어코스');

-- 5. 코스 상세 테이블
INSERT INTO course_detail (course_id, created_at, updated_at, distance, infocentertourcourse, schedule, taketime, theme) VALUES
                                                                                                                             (1, NOW(), NOW(), '15km', '제주관광공사 064-740-6000', '성산일출봉 → 신양해수욕장 → 종달리 → 하도리 → 광치기해변', '4-5시간', '해안절경, 올레길'),
                                                                                                                             (2, NOW(), NOW(), '25km', '제주민속촌 064-787-4501', '성읍민속마을 → 제주민속촌 → 돌하르방공원 → 제주목관아', '6-7시간', '전통문화, 역사체험'),
                                                                                                                             (3, NOW(), NOW(), '20km', '제주맛집연합 064-722-3456', '동문시장 → 흑돼지거리 → 갈치조림거리 → 한라봉체험농장', '5-6시간', '미식투어, 특산품');

-- 6. 코스 카테고리 테이블
INSERT INTO course_category (category_type, course_id) VALUES
                                                           ('ACTIVITY', 1),
                                                           ('CULTURE', 2),
                                                           ('FOOD', 3),
                                                           ('FOREST', 1),
                                                           ('MOOD', 1),
                                                           ('SOCIAL', 2);

-- 7. 장소 테이블
INSERT INTO place (created_at, updated_at, address, category_1, category_2, category_3, city_code, content_id, content_type_id, copyright_type, detail_address, mapx, mapy, origin_image, sigungucode, tel, thumbnail_image, title, course_id) VALUES
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 서귀포시 성산읍 성산리', '자연', '산', '일출명소', '130', 'PLACE_001', '12', 'TYPE1', '성산일출봉 일대', 126.942, 33.458, 'https://example.com/seongsan_origin.jpg', 3, '064-783-0959', 'https://example.com/seongsan_thumb.jpg', '성산일출봉', 1),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 구좌읍 종달리', '자연', '해변', '해수욕장', '110', 'PLACE_002', '12', 'TYPE1', '종달리 해안가', 126.927, 33.512, 'https://example.com/jongdal_origin.jpg', 4, '064-782-2810', 'https://example.com/jongdal_thumb.jpg', '종달리 해안', 1),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 서귀포시 표선면 성읍리', '문화', '민속', '전통마을', '130', 'PLACE_003', '14', 'TYPE1', '성읍민속마을 일대', 126.804, 33.328, 'https://example.com/seongeup_origin.jpg', 3, '064-787-1179', 'https://example.com/seongeup_thumb.jpg', '성읍민속마을', 2),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 한림읍 한림리', '문화', '박물관', '민속촌', '110', 'PLACE_004', '14', 'TYPE1', '제주민속촌 내', 126.244, 33.350, 'https://example.com/minsokchon_origin.jpg', 4, '064-787-4501', 'https://example.com/minsokchon_thumb.jpg', '제주민속촌', 2),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 일도이동', '음식', '특산품', '시장', '110', 'PLACE_005', '38', 'TYPE1', '동문시장 내', 126.524, 33.512, 'https://example.com/dongmun_origin.jpg', 4, '064-752-3001', 'https://example.com/dongmun_thumb.jpg', '동문시장', 3),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 건입동', '음식', '고기', '흑돼지', '110', 'PLACE_006', '39', 'TYPE1', '흑돼지거리 일대', 126.521, 33.499, 'https://example.com/blackpork_origin.jpg', 4, '064-758-7000', 'https://example.com/blackpork_thumb.jpg', '흑돼지거리', 3),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 한경면 금악리', '자연', '숲', '곶자왈', '110', 'PLACE_007', '12', 'TYPE1', '곶자왈 탐방로', 126.158, 33.329, 'https://example.com/gotjawal_origin.jpg', 4, '064-772-3700', 'https://example.com/gotjawal_thumb.jpg', '곶자왈', NULL),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 서귀포시 중문동', '레포츠', '해양', '다이빙', '130', 'PLACE_008', '28', 'TYPE1', '중문해수욕장 일대', 126.412, 33.246, 'https://example.com/jungmun_origin.jpg', 3, '064-739-1330', 'https://example.com/jungmun_thumb.jpg', '중문해수욕장', NULL),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 서광리', '숙박', '리조트', '호텔', '130', 'PLACE_009', '32', 'TYPE1', '신화월드 내', 126.308, 33.304, 'https://example.com/resort_origin.jpg', 3, '064-133-8000', 'https://example.com/resort_thumb.jpg', '신화월드 리조트', NULL),
                                                                                                                                                                                                                                                   (NOW(), NOW(), '제주특별자치도 제주시 애월읍 애월리', '쇼핑', '카페', '수제품', '110', 'PLACE_010', '76', 'TYPE1', '애월 카페거리', 126.311, 33.464, 'https://example.com/aewol_origin.jpg', 4, '064-867-2300', 'https://example.com/aewol_thumb.jpg', '애월 카페거리', NULL);

-- 8. 장소 카테고리 테이블
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES
                                                                                 (NOW(), NOW(), 'ACTIVITY', 1),
                                                                                 (NOW(), NOW(), 'CULTURE', 1),
                                                                                 (NOW(), NOW(), 'MOOD', 2),
                                                                                 (NOW(), NOW(), 'CULTURE', 3),
                                                                                 (NOW(), NOW(), 'CULTURE', 4),
                                                                                 (NOW(), NOW(), 'FOOD', 5),
                                                                                 (NOW(), NOW(), 'FOOD', 6),
                                                                                 (NOW(), NOW(), 'FOREST', 7),
                                                                                 (NOW(), NOW(), 'ACTIVITY', 8),
                                                                                 (NOW(), NOW(), 'SOCIAL', 9),
                                                                                 (NOW(), NOW(), 'SOCIAL', 10);

-- 9. 관광지 상세 테이블
INSERT INTO tour_detail (place_id, created_at, updated_at, accomcount, chkbabycarriage, chkcreditcard, chkpet, expagerange, expguide, heritage1, heritage2, heritage3, infocenter, opendate, parking, restdate, useseason, usetime) VALUES
                                                                                                                                                                                                                                        (1, NOW(), NOW(), '1000', '가능', '가능', '불가능', '전연령', '가능', 182, '천연기념물', '세계자연유산', '064-783-0959', '1999-07-01', '가능', '연중무휴', '연중', '일출 30분전부터 20:00'),
                                                                                                                                                                                                                                        (3, NOW(), NOW(), '500', '가능', '가능', '가능', '전연령', '가능', 188, '중요민속문화재', '전통마을', '064-787-1179', '1984-06-01', '가능', '연중무휴', '연중', '09:00~18:00'),
                                                                                                                                                                                                                                        (4, NOW(), NOW(), '800', '가능', '가능', '가능', '전연령', '가능', 0, '', '', '064-787-4501', '1987-08-15', '가능', '연중무휴', '연중', '08:30~18:00'),
                                                                                                                                                                                                                                        (7, NOW(), NOW(), '300', '가능', '불가능', '가능', '전연령', '가능', 0, '', '', '064-772-3700', '2010-03-01', '가능', '연중무휴', '연중', '09:00~17:00');

-- 10. 문화시설 상세 테이블
INSERT INTO culture_detail (place_id, created_at, updated_at, accomcountculture, chkbabycarriageculture, chkcreditcardculture, chkpetculture, discountinfo, infocenterculture, parkingculture, parkingfee, restdateculture, scale, spendtime, usefee, usetimeculture) VALUES
                                                                                                                                                                                                                                                                          (3, NOW(), NOW(), '500명', '가능', '가능', '가능', '단체할인 20%', '064-787-1179', '가능', '무료', '연중무휴', '면적 15만㎡', '1시간 30분', '성인 4,000원', '09:00~18:00'),
                                                                                                                                                                                                                                                                          (4, NOW(), NOW(), '800명', '가능', '가능', '가능', '경로우대 50%', '064-787-4501', '가능', '무료', '연중무휴', '면적 15만㎡', '2시간', '성인 11,000원', '08:30~18:00');

-- 11. 음식점 상세 테이블
INSERT INTO food_detail (place_id, created_at, updated_at, chkcreditcardfood, discountinfofood, firstmenu, infocenterfood, kidsfacility, lcnsno, opendatefood, opentimefood, packing, parkingfood, reservationfood, restdatefood, scalefood, seat, smoking, treatmenu) VALUES
                                                                                                                                                                                                                                                                           (5, NOW(), NOW(), '가능', '현금결제시 5%', '제주 전통시장 먹거리', '064-752-3001', '가능', '202300001', '1954-01-01', '05:00~20:00', '가능', '인근 공영주차장', '불가능', '연중무휴', '점포 200개', '좌석 없음', '금연', '회, 순대, 떡, 과일'),
                                                                                                                                                                                                                                                                           (6, NOW(), NOW(), '가능', '단체 10% 할인', '제주 흑돼지구이', '064-758-7000', '가능', '202300002', '1980-03-15', '11:00~23:00', '가능', '가능', '가능', '매월 둘째주 월요일', '음식점 50개', '1000석', '분연', '흑돼지구이, 된장찌개');

-- 12. 레포츠 상세 테이블
INSERT INTO leports_detail (place_id, created_at, updated_at, accomcountleports, chkbabycarriageleports, chkcreditcardleports, chkpetleports, expagerangeleports, infocenterleports, openperiod, parkingfeeleports, parkingleports, reservation, restdateleports, scaleleports, usefeeleports, usetimeleports) VALUES
    (8, NOW(), NOW(), 200, '불가능', '가능', '불가능', '만 12세 이상', '064-739-1330', '3월~11월', '2000원', '가능', '필수', '12월~2월', '해수욕장 면적 560m', '체험다이빙 80,000원', '09:00~17:00');

-- 13. 숙박시설 상세 테이블
INSERT INTO lodge_detail (place_id, created_at, updated_at, accomcountlodging, barbecue, beauty, beverage, bicycle, campfire, checkintime, checkouttime, chkcooking, fitness, foodplace, infocenterlodging, karaoke, parkinglodging, pickup, publicbath, publicpc, refundregulation, reservationlodging, reservationurl, roomcount, roomtype, sauna, scalelodging, seminar, sports, subfacility) VALUES
    (9, NOW(), NOW(), '객실 500개', '가능', '가능', '가능', '가능', '불가능', '15:00', '11:00', '불가능', '가능', '가능', '064-133-8000', '가능', '가능', '가능', '가능', '불가능', '취소시 환불 규정 별도', '가능', 'https://www.shinhwaworld.com', '500개', '스위트, 디럭스, 스탠다드', '가능', '부지면적 250만㎡', '가능', '가능', '워터파크, 테마파크');

-- 14. 쇼핑 상세 테이블
INSERT INTO shopping_deatail (place_id, created_at, updated_at, chkbabycarriageshopping, chkcreditcardshopping, chkpetshopping, culturecenter, fairday, infocentershopping, opendateshopping, opentime, parkingshopping, restdateshopping, restroom, saleitem, saleitemcost, scaleshopping, shopguide) VALUES
    (10, NOW(), NOW(), '가능', '가능', '가능', '없음', '매주 토요일', '064-867-2300', '2015-06-01', '10:00~22:00', '가능', '연중무휴', '가능', '수제 악세사리, 카페, 디저트', '10,000원~50,000원', '카페 30개', '애월 카페거리 안내소');

-- 15. 스팟 테이블
INSERT INTO spot (created_at, updated_at, date, place_id) VALUES
                                                              (NOW(), NOW(), '2024-01-15', 1),
                                                              (NOW(), NOW(), '2024-01-20', 2),
                                                              (NOW(), NOW(), '2024-02-01', 3),
                                                              (NOW(), NOW(), '2024-02-10', 4),
                                                              (NOW(), NOW(), '2024-02-15', 5),
                                                              (NOW(), NOW(), '2024-03-01', 6),
                                                              (NOW(), NOW(), '2024-03-10', 7),
                                                              (NOW(), NOW(), '2024-03-15', 8);

-- 16. 스팟 카테고리 요약 테이블
INSERT INTO spot_category_summary (created_at, updated_at, visit_count, category_type, spot_id) VALUES
                                                                                                    (NOW(), NOW(), 150, 'ACTIVITY', 1),
                                                                                                    (NOW(), NOW(), 80, 'MOOD', 2),
                                                                                                    (NOW(), NOW(), 200, 'CULTURE', 3),
                                                                                                    (NOW(), NOW(), 120, 'CULTURE', 4),
                                                                                                    (NOW(), NOW(), 300, 'FOOD', 5),
                                                                                                    (NOW(), NOW(), 250, 'FOOD', 6),
                                                                                                    (NOW(), NOW(), 90, 'FOREST', 7),
                                                                                                    (NOW(), NOW(), 60, 'ACTIVITY', 8);

-- 17. 배지 테이블
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES
                                                                         (NOW(), NOW(), 'GOLD', 1, 1),
                                                                         (NOW(), NOW(), 'SILVER', 1, 3),
                                                                         (NOW(), NOW(), 'BRONZE', 2, 7),
                                                                         (NOW(), NOW(), 'GOLD', 3, 5),
                                                                         (NOW(), NOW(), 'GOLD', 3, 6),
                                                                         (NOW(), NOW(), 'SILVER', 4, 8),
                                                                         (NOW(), NOW(), 'BRONZE', 5, 2),
                                                                         (NOW(), NOW(), 'GOLD', 6, 4);

-- 18. 방문 로그 테이블
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES
                                                                       (NOW(), NOW(), 1, 1),
                                                                       (NOW(), NOW(), 1, 3),
                                                                       (NOW(), NOW(), 2, 7),
                                                                       (NOW(), NOW(), 2, 1),
                                                                       (NOW(), NOW(), 3, 5),
                                                                       (NOW(), NOW(), 3, 6),
                                                                       (NOW(), NOW(), 4, 8),
                                                                       (NOW(), NOW(), 4, 1),
                                                                       (NOW(), NOW(), 5, 2),
                                                                       (NOW(), NOW(), 5, 4),
                                                                       (NOW(), NOW(), 6, 4),
                                                                       (NOW(), NOW(), 6, 3);

-- 19. 폴더 테이블
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES
                                                                 (NOW(), NOW(), '제주 필수 명소', 1),
                                                                 (NOW(), NOW(), '힐링 스팟', 2),
                                                                 (NOW(), NOW(), '맛집 리스트', 3),
                                                                 (NOW(), NOW(), '액티비티 모음', 4),
                                                                 (NOW(), NOW(), '친구들과 가볼 곳', 5),
                                                                 (NOW(), NOW(), '감성 카페', 6);

-- 20. 폴더 장소 테이블
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES
                                                                           (NOW(), NOW(), 1, 1),
                                                                           (NOW(), NOW(), 1, 3),
                                                                           (NOW(), NOW(), 1, 4),
                                                                           (NOW(), NOW(), 2, 7),
                                                                           (NOW(), NOW(), 2, 2),
                                                                           (NOW(), NOW(), 3, 5),
                                                                           (NOW(), NOW(), 3, 6),
                                                                           (NOW(), NOW(), 4, 8),
                                                                           (NOW(), NOW(), 4, 1),
                                                                           (NOW(), NOW(), 5, 9),
                                                                           (NOW(), NOW(), 5, 10),
                                                                           (NOW(), NOW(), 6, 10);

-- 21. 플래너 테이블
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES
                                                                  (NOW(), NOW(), '제주 3박 4일 완벽 일정', 1),
                                                                  (NOW(), NOW(), '제주 자연 힐링 여행', 2),
                                                                  (NOW(), NOW(), '제주 맛집 투어', 3),
                                                                  (NOW(), NOW(), '제주 액티비티 여행', 4),
                                                                  (NOW(), NOW(), '제주 친구들과 여행', 5),
                                                                  (NOW(), NOW(), '제주 감성 여행', 6);

-- 22. 플래너 장소 테이블
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES
                                                                                                           (NOW(), NOW(), 1, 1, 1, 1),
                                                                                                           (NOW(), NOW(), 1, 2, 2, 1),
                                                                                                           (NOW(), NOW(), 2, 1, 3, 1),
                                                                                                           (NOW(), NOW(), 2, 2, 4, 1),
                                                                                                           (NOW(), NOW(), 3, 1, 5, 1),
                                                                                                           (NOW(), NOW(), 3, 2, 6, 1),
                                                                                                           (NOW(), NOW(), 1, 1, 7, 2),
                                                                                                           (NOW(), NOW(), 1, 2, 2, 2),
                                                                                                           (NOW(), NOW(), 2, 1, 1, 2),
                                                                                                           (NOW(), NOW(), 1, 1, 5, 3),
                                                                                                           (NOW(), NOW(), 1, 2, 6, 3),
                                                                                                           (NOW(), NOW(), 1, 1, 8, 4),
                                                                                                           (NOW(), NOW(), 1, 2, 1, 4),
                                                                                                           (NOW(), NOW(), 1, 1, 9, 5),
                                                                                                           (NOW(), NOW(), 1, 2, 10, 5),
                                                                                                           (NOW(), NOW(), 1, 1, 10, 6);

-- 23. 리뷰 테이블
INSERT INTO review (created_at, updated_at, content, rate, member_id, place_id) VALUES
                                                                                      (NOW(), NOW(), '일출이 정말 아름다웠어요! 새벽 일찍 올라가는 보람이 있습니다.', 4.8, 1, 1),
                                                                                      (NOW(), NOW(), '전통 가옥이 잘 보존되어 있고 설명도 자세해서 좋았어요.', 4.5, 1, 3),
                                                                                      (NOW(), NOW(), '곶자왈의 신비로운 분위기를 만끽할 수 있었습니다.', 4.7, 2, 7),
                                                                                      (NOW(), NOW(), '다양한 먹거리가 있어서 구경하는 재미가 쏠쏠해요.', 4.3, 3, 5),
                                                                                      (NOW(), NOW(), '제주 흑돼지 맛이 정말 일품이에요! 강력 추천합니다.', 4.9, 3, 6),
                                                                                      (NOW(), NOW(), '바다가 너무 깨끗하고 다이빙 체험도 재미있었어요.', 4.6, 4, 8),
                                                                                      (NOW(), NOW(), '리조트 시설이 좋고 가족 여행하기에 최고예요.', 4.4, 5, 9),
                                                                                      (NOW(), NOW(), '카페 분위기가 너무 좋고 바다 뷰가 환상적이에요.', 4.8, 6, 10);

-- 24. 축제 상세 테이블 (추가 데이터)
INSERT INTO festival_detail (place_id, created_at, updated_at, agelimit, bookingplace, discountinfofestival, eventenddate, eventhomepage, eventplace, eventstartdate, festivalgrade, placeinfo, playtime, program, spendtimefestival, sponsor1, sponsor1tel, sponsor2, sponsor2tel, subevent, usetimefestival) VALUES
    (1, NOW(), NOW(), '전연령', '현장 및 온라인', '조기예약 10%', '2024-05-05', 'https://festival.jeju.go.kr', '성산일출봉 광장', '2024-05-01', 'A등급', '성산일출봉 일대', '120분', '일출 페스티벌, 전통 공연', '3시간', '제주관광공사', '064-740-6000', '서귀포시청', '064-760-2114', '포토존, 체험부스', '04:00~07:00');

-- 데이터 확인용 쿼리
SELECT 'member' as table_name, COUNT(*) as count FROM member
UNION ALL
SELECT 'place' as table_name, COUNT(*) as count FROM place
UNION ALL
SELECT 'course' as table_name, COUNT(*) as count FROM course
UNION ALL
SELECT 'spot' as table_name, COUNT(*) as count FROM spot
UNION ALL
SELECT 'review' as table_name, COUNT(*) as count FROM review
UNION ALL
SELECT 'badge' as table_name, COUNT(*) as count FROM badge
UNION ALL
SELECT 'planner' as table_name, COUNT(*) as count FROM planner
UNION ALL
SELECT 'folder' as table_name, COUNT(*) as count FROM folder;