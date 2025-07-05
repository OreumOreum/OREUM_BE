-- 제주도 관광 애플리케이션 목 데이터 삽입 SQL

-- 1. 카테고리 테이블 (기본 참조 테이블)
INSERT INTO category (type) VALUES
                                ('ACTIVITY'),
                                ('CULTURE'),
                                ('FOOD'),
                                ('FOREST'),
                                ('MOOD'),
                                ('SOCIAL');

-- 2. 멤버 테이블
INSERT INTO member (created_at, updated_at, badge_count, email, fcm_token, login_id, name, phone_no, category_type) VALUES
                                                                                                                        (NOW(), NOW(), 5, 'kim.jeju@example.com', 'fcm_token_kim_123', '111', '김제주', '010-1234-5678', 'CULTURE'),
                                                                                                                        (NOW(), NOW(), 3, 'lee.seogwipo@example.com', 'fcm_token_lee_456', '222', '이서귀포', '010-2345-6789', 'FOOD'),
                                                                                                                        (NOW(), NOW(), 8, 'park.halla@example.com', 'fcm_token_park_789', '333', '박한라', '010-3456-7890', 'FOREST'),
                                                                                                                        (NOW(), NOW(), 2, 'choi.udo@example.com', 'fcm_token_choi_012', '444', '최우도', '010-4567-8901', 'ACTIVITY'),
                                                                                                                        (NOW(), NOW(), 6, 'jung.manjanggul@example.com', 'fcm_token_jung_345', '555', '정만장굴', '010-5678-9012', 'MOOD');

-- 3. 코스 상세 정보 테이블
INSERT INTO course_detail (created_at, updated_at, distance, infocentertourcourse, schedule, taketime, theme) VALUES
                                                                                                                  (NOW(), NOW(), '12.5km', '제주관광공사 064-740-6000', '오전 9시 ~ 오후 5시', '6시간', '해안둘레길'),
                                                                                                                  (NOW(), NOW(), '8.3km', '서귀포시청 관광과 064-760-2000', '오전 10시 ~ 오후 4시', '4시간', '오름탐방'),
                                                                                                                  (NOW(), NOW(), '15.2km', '제주시청 관광진흥과 064-728-2000', '오전 8시 ~ 오후 6시', '8시간', '문화유적지'),
                                                                                                                  (NOW(), NOW(), '6.7km', '한라산국립공원 064-713-9950', '오전 9시 ~ 오후 3시', '5시간', '자연생태'),
                                                                                                                  (NOW(), NOW(), '10.1km', '제주올레 064-762-2190', '오전 9시 ~ 오후 5시', '6시간', '전통마을');

-- 4. 코스 테이블
INSERT INTO course (created_at, updated_at, category_1, category_2, category_3, content_id, origin_image, overview, sigungu_code, thumbnail_image, title, course_detial_id) VALUES
                                                                                                                                                                                (NOW(), NOW(), '자연', '해안', '해안둘레길', 'C001', 'https://example.com/course1_origin.jpg', '제주 해안을 따라 걷는 아름다운 둘레길 코스', 4, 'https://example.com/course1_thumb.jpg', '제주 해안 둘레길', 1),
                                                                                                                                                                                (NOW(), NOW(), '자연', '산', '오름', 'C002', 'https://example.com/course2_origin.jpg', '제주의 대표 오름들을 탐방하는 코스', 3, 'https://example.com/course2_thumb.jpg', '서귀포 오름 탐방', 2),
                                                                                                                                                                                (NOW(), NOW(), '문화', '유적지', '전통', 'C003', 'https://example.com/course3_origin.jpg', '제주의 전통 문화유적지를 둘러보는 코스', 4, 'https://example.com/course3_thumb.jpg', '제주 문화유적 투어', 3),
                                                                                                                                                                                (NOW(), NOW(), '자연', '숲', '생태', 'C004', 'https://example.com/course4_origin.jpg', '한라산 자연 생태계를 체험하는 코스', 4, 'https://example.com/course4_thumb.jpg', '한라산 생태 체험', 4),
                                                                                                                                                                                (NOW(), NOW(), '문화', '마을', '전통', 'C005', 'https://example.com/course5_origin.jpg', '제주 전통 마을의 삶을 체험하는 코스', 3, 'https://example.com/course5_thumb.jpg', '제주 전통마을 체험', 5);

-- 5. 각종 상세 정보 테이블들
-- 관광지 상세 정보
INSERT INTO tour_detail (created_at, updated_at, accomcount, chkbabycarriage, chkcreditcard, chkpet, expagerange, expguide, heritage1, heritage2, heritage3, infocenter, opendate, parking, restdate, useseason, usetime) VALUES
                                                                                                                                                                                                                              (NOW(), NOW(), '500명', '가능', '가능', '불가능', '전연령', '가능', 1, '제주 화산섬과 용암동굴', '세계자연유산', '064-710-7777', '1970-01-01', '가능(무료)', '연중무휴', '사계절', '09:00~18:00'),
                                                                                                                                                                                                                              (NOW(), NOW(), '200명', '가능', '가능', '가능', '전연령', '가능', 0, '', '', '064-783-0959', '1988-05-15', '가능(유료)', '매주 월요일', '사계절', '09:00~17:00'),
                                                                                                                                                                                                                              (NOW(), NOW(), '300명', '가능', '가능', '불가능', '12세이상', '가능', 1, '제주 해녀문화', '무형문화유산', '064-782-0114', '1995-03-10', '가능(무료)', '연중무휴', '사계절', '08:00~19:00');

-- 문화시설 상세 정보
INSERT INTO culture_detail (created_at, updated_at, accomcountculture, chkbabycarriageculture, chkcreditcardculture, chkpetculture, discountinfo, infocenterculture, parkingculture, parkingfee, restdateculture, scale, spendtime, usefee, usetimeculture) VALUES
                                                                                                                                                                                                                                                                (NOW(), NOW(), '200명', '가능', '가능', '불가능', '단체할인 10%', '064-720-8000', '가능', '무료', '매주 월요일', '지상 3층 규모', '2시간', '성인 3000원, 청소년 2000원', '09:00~18:00'),
                                                                                                                                                                                                                                                                (NOW(), NOW(), '150명', '가능', '가능', '불가능', '경로우대 50%', '064-760-4000', '가능', '무료', '매주 화요일', '지상 2층 규모', '1.5시간', '성인 2000원, 청소년 1500원', '09:00~17:00'),
                                                                                                                                                                                                                                                                (NOW(), NOW(), '100명', '가능', '가능', '가능', '제주도민 무료', '064-750-3000', '가능', '1시간 무료', '연중무휴', '단층 규모', '1시간', '성인 1000원, 청소년 500원', '10:00~18:00');

-- 음식점 상세 정보
INSERT INTO food_detail (created_at, updated_at, chkcreditcardfood, discountinfofood, firstmenu, infocenterfood, kidsfacility, lcnsno, opendatefood, opentimefood, packing, parkingfood, reservationfood, restdatefood, scalefood, seat, smoking, treatmenu) VALUES
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '단체할인 10%', '흑돼지구이', '064-742-1234', '가능', '제주-음식-2020-001', '2015-03-15', '11:00~22:00', '가능', '가능', '가능', '매주 일요일', '80석', '80석', '금연', '흑돼지구이, 갈치조림, 전복죽'),
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '제주도민 5%', '고등어회', '064-783-5678', '가능', '제주-음식-2018-042', '2018-07-20', '10:00~21:00', '가능', '가능', '가능', '매월 첫째주 월요일', '60석', '60석', '금연', '고등어회, 성게국, 해물라면'),
                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '현금결제시 5%', '보말죽', '064-752-9012', '가능', '제주-음식-2019-083', '2019-11-10', '07:00~20:00', '가능', '가능', '불가능', '매주 화요일', '40석', '40석', '금연', '보말죽, 자리물회, 멸치국수');

-- 숙박시설 상세 정보
INSERT INTO lodge_detail (created_at, updated_at, accomcountlodging, barbecue, beauty, beverage, bicycle, campfire, checkintime, checkouttime, chkcooking, fitness, foodplace, infocenterlodging, karaoke, parkinglodging, pickup, publicbath, publicpc, refundregulation, reservationlodging, reservationurl, roomcount, roomtype, sauna, scalelodging, seminar, sports, subfacility) VALUES
                                                                                                                                                                                                                                                                                                                                                                                           (NOW(), NOW(), '100명', '가능', '불가능', '가능', '가능', '가능', '15:00', '11:00', '가능', '가능', '가능', '064-730-1000', '가능', '가능', '가능', '가능', '가능', '3일전 100%환불', '가능', 'https://resort.jeju.com', '50실', '스위트룸, 스탠다드룸', '가능', '지상 5층', '가능', '가능', '수영장, 스파'),
                                                                                                                                                                                                                                                                                                                                                                                           (NOW(), NOW(), '80명', '가능', '가능', '가능', '대여가능', '가능', '16:00', '10:00', '가능', '불가능', '가능', '064-740-2000', '불가능', '가능', '불가능', '가능', '불가능', '당일 50%환불', '가능', 'https://pension.jeju.com', '20실', '복층펜션, 스탠다드', '불가능', '지상 2층', '불가능', '불가능', '바베큐장');

-- 레포츠 상세 정보
INSERT INTO leports_detail (created_at, updated_at, accomcountleports, chkbabycarriageleports, chkcreditcardleports, chkpetleports, expagerangeleports, infocenterleports, openperiod, parkingfeeleports, parkingleports, reservation, restdateleports, scaleleports, usefeeleports, usetimeleports) VALUES
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), 50, '불가능', '가능', '불가능', '12세이상', '064-780-1234', '연중', '무료', '가능', '필수', '악천후시', '대형', '성인 30000원', '09:00~18:00'),
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), 30, '불가능', '가능', '불가능', '16세이상', '064-790-5678', '4월~10월', '무료', '가능', '필수', '동절기', '중형', '성인 25000원', '10:00~17:00');

-- 쇼핑 상세 정보
INSERT INTO shopping_deatail (created_at, updated_at, chkbabycarriageshopping, chkcreditcardshopping, chkpetshopping, culturecenter, fairday, infocentershopping, opendateshopping, opentime, parkingshopping, restdateshopping, restroom, saleitem, saleitemcost, scaleshopping, shopguide) VALUES
                                                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '가능', '불가능', '가능', '매주 토요일', '064-720-3000', '2010-05-01', '09:00~21:00', '가능', '매월 첫째주 월요일', '가능', '제주 특산품, 한라봉, 흑돼지', '한라봉 1kg 15000원', '대형', '가능'),
                                                                                                                                                                                                                                                                                                 (NOW(), NOW(), '가능', '가능', '불가능', '불가능', '매주 일요일', '064-760-4000', '2015-08-15', '10:00~20:00', '가능', '연중무휴', '가능', '전통차, 꿀, 갈옷', '전통차 세트 25000원', '중형', '가능');

-- 축제 상세 정보
INSERT INTO festival_detail (created_at, updated_at, agelimit, bookingplace, discountinfofestival, eventenddate, eventhomepage, eventplace, eventstartdate, festivalgrade, placeinfo, playtime, program, spendtimefestival, sponsor1, sponsor1tel, sponsor2, sponsor2tel, subevent, usetimefestival) VALUES
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), '전연령', '제주관광공사', '조기예약 20%', '2024-12-31', 'https://jejufestival.com', '제주월드컵경기장', '2024-12-28', 'A급', '야외무대', '19:00~22:00', '전통공연, 불꽃축제', '3시간', '제주특별자치도', '064-710-2000', '제주관광공사', '064-740-6000', '먹거리장터', '19:00~22:00'),
                                                                                                                                                                                                                                                                                                         (NOW(), NOW(), '전연령', '서귀포문화원', '단체할인 10%', '2024-08-15', 'https://seogwipofest.com', '서귀포매일올레시장', '2024-08-10', 'B급', '시장 내', '10:00~18:00', '전통시장 체험', '6시간', '서귀포시', '064-760-2000', '서귀포상인회', '064-762-1234', '전통음식 체험', '10:00~18:00');

-- 6. 장소 테이블
INSERT INTO place (created_at, updated_at, address, category_1, category_2, category_3, city_code, content_id, content_type_id, copyright_type, detail_address, mapx, mapy, origin_image, sigungucode, tel, thumbnail_image, title, course_id, culture_detail_id, festival_detail_id, food_detail_id, leports_detail_id, lodge_detail_id, shopping_detail_id, tour_detail_id) VALUES
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 제주시 조천읍 만장굴길 182', '자연', '동굴', '용암동굴', '110', 'P001', '12', 'TYPE1', '만장굴 입구', 126.771523, 33.527411, 'https://example.com/manjanggul_origin.jpg', 4, '064-783-0959', 'https://example.com/manjanggul_thumb.jpg', '만장굴', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 제주시 한림읍 한림로 300', '문화', '박물관', '미술관', '110', 'P002', '14', 'TYPE1', '한림공원 내', 126.240089, 33.395756, 'https://example.com/museum_origin.jpg', 4, '064-796-0001', 'https://example.com/museum_thumb.jpg', '제주민속자연사박물관', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 서귀포시 남원읍 태위로 522', '음식', '한식', '제주향토음식', '130', 'P003', '39', 'TYPE1', '남원읍 소재', 126.680556, 33.302778, 'https://example.com/restaurant_origin.jpg', 3, '064-764-1234', 'https://example.com/restaurant_thumb.jpg', '제주흑돼지전문점', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 서귀포시 안덕면 일주서로 2454-32', '숙박', '리조트', '특급호텔', '130', 'P004', '32', 'TYPE1', '중문관광단지 내', 126.416667, 33.246389, 'https://example.com/resort_origin.jpg', 3, '064-738-1234', 'https://example.com/resort_thumb.jpg', '제주신라호텔', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 제주시 건입동 1436-1', '쇼핑', '전통시장', '특산품', '110', 'P005', '38', 'TYPE1', '동문시장 내', 126.525278, 33.515556, 'https://example.com/market_origin.jpg', 4, '064-752-3001', 'https://example.com/market_thumb.jpg', '제주동문시장', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 서귀포시 성산읍 성산리 1', '레포츠', '수상스포츠', '스쿠버다이빙', '130', 'P006', '28', 'TYPE1', '성산포항 인근', 126.927778, 33.460556, 'https://example.com/diving_origin.jpg', 3, '064-782-5678', 'https://example.com/diving_thumb.jpg', '성산포다이빙센터', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 제주시 애월읍 애월리 1181', '문화', '공연장', '전통공연', '110', 'P007', '15', 'TYPE1', '애월해안도로변', 126.310556, 33.460833, 'https://example.com/culture_origin.jpg', 4, '064-799-1234', 'https://example.com/culture_thumb.jpg', '제주전통문화센터', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 서귀포시 표선면 표선리 40', '음식', '해산물', '회센터', '130', 'P008', '39', 'TYPE1', '표선해변 인근', 126.831944, 33.324167, 'https://example.com/seafood_origin.jpg', 3, '064-787-9012', 'https://example.com/seafood_thumb.jpg', '표선해산물센터', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 서귀포시 중문동 2624-1', '축제', '문화행사', '전통축제', '130', 'P009', '85', 'TYPE1', '중문색달해수욕장', 126.411944, 33.245278, 'https://example.com/festival_origin.jpg', 3, '064-739-1234', 'https://example.com/festival_thumb.jpg', '제주겨울축제', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                                                                                                                                                                                  (NOW(), NOW(), '제주특별자치도 제주시 조천읍 조천리 1880', '음식', '한식', '죽전문', '110', 'P010', '39', 'TYPE1', '조천해변가', 126.642222, 33.542222, 'https://example.com/porridge_origin.jpg', 4, '064-784-3456', 'https://example.com/porridge_thumb.jpg', '조천보말죽집', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL);

-- 7. 스팟 테이블
INSERT INTO spot (created_at, updated_at, date, place_id) VALUES
                                                              (NOW(), NOW(), '2024-01-15', 1),
                                                              (NOW(), NOW(), '2024-01-16', 2),
                                                              (NOW(), NOW(), '2024-01-17', 3),
                                                              (NOW(), NOW(), '2024-01-18', 4),
                                                              (NOW(), NOW(), '2024-01-19', 5),
                                                              (NOW(), NOW(), '2024-01-20', 6),
                                                              (NOW(), NOW(), '2024-01-21', 7),
                                                              (NOW(), NOW(), '2024-01-22', 8),
                                                              (NOW(), NOW(), '2024-01-23', 9),
                                                              (NOW(), NOW(), '2024-01-24', 10);

-- 8. 배지 테이블
INSERT INTO badge (created_at, updated_at, rank, member_id, spot_id) VALUES
                                                                         (NOW(), NOW(), 'GOLD', 1, 1),
                                                                         (NOW(), NOW(), 'SILVER', 1, 2),
                                                                         (NOW(), NOW(), 'BRONZE', 2, 3),
                                                                         (NOW(), NOW(), 'GOLD', 3, 4),
                                                                         (NOW(), NOW(), 'SILVER', 3, 5),
                                                                         (NOW(), NOW(), 'BRONZE', 4, 6),
                                                                         (NOW(), NOW(), 'GOLD', 5, 7),
                                                                         (NOW(), NOW(), 'SILVER', 5, 8),
                                                                         (NOW(), NOW(), 'BRONZE', 1, 9),
                                                                         (NOW(), NOW(), 'GOLD', 2, 10);

-- 9. 폴더 테이블
INSERT INTO folder (created_at, updated_at, name, member_id) VALUES
                                                                 (NOW(), NOW(), '내가 가고 싶은 곳', 1),
                                                                 (NOW(), NOW(), '맛집 리스트', 2),
                                                                 (NOW(), NOW(), '힐링 스팟', 3),
                                                                 (NOW(), NOW(), '액티비티 장소', 4),
                                                                 (NOW(), NOW(), '문화 체험', 5);

-- 10. 폴더-장소 테이블
INSERT INTO folder_place (created_at, updated_at, folder_id, place_id) VALUES
                                                                           (NOW(), NOW(), 1, 1),
                                                                           (NOW(), NOW(), 1, 2),
                                                                           (NOW(), NOW(), 2, 3),
                                                                           (NOW(), NOW(), 2, 8),
                                                                           (NOW(), NOW(), 3, 4),
                                                                           (NOW(), NOW(), 3, 7),
                                                                           (NOW(), NOW(), 4, 6),
                                                                           (NOW(), NOW(), 4, 9),
                                                                           (NOW(), NOW(), 5, 2),
                                                                           (NOW(), NOW(), 5, 7);

-- 11. 플래너 테이블
INSERT INTO planner (created_at, updated_at, name, member_id) VALUES
                                                                  (NOW(), NOW(), '제주도 2박3일 여행', 1),
                                                                  (NOW(), NOW(), '서귀포 당일치기', 2),
                                                                  (NOW(), NOW(), '제주 힐링 여행', 3),
                                                                  (NOW(), NOW(), '제주 액티비티 투어', 4),
                                                                  (NOW(), NOW(), '제주 문화 탐방', 5);

-- 12. 플래너-장소 테이블
INSERT INTO planner_place (created_at, updated_at, sequence_day, sequence_order, place_id, planner_id) VALUES
                                                                                                           (NOW(), NOW(), 1, 1, 1, 1),
                                                                                                           (NOW(), NOW(), 1, 2, 2, 1),
                                                                                                           (NOW(), NOW(), 2, 1, 3, 1),
                                                                                                           (NOW(), NOW(), 2, 2, 4, 1),
                                                                                                           (NOW(), NOW(), 1, 1, 8, 2),
                                                                                                           (NOW(), NOW(), 1, 2, 9, 2),
                                                                                                           (NOW(), NOW(), 1, 1, 4, 3),
                                                                                                           (NOW(), NOW(), 1, 2, 7, 3),
                                                                                                           (NOW(), NOW(), 1, 1, 6, 4),
                                                                                                           (NOW(), NOW(), 1, 2, 9, 4);

-- 13. 리뷰 테이블
INSERT INTO review (created_at, updated_at, content, rate, course_id, member_id, place_id) VALUES
                                                                                               (NOW(), NOW(), '정말 아름다운 용암동굴이에요! 자연의 신비를 느낄 수 있어요.', 4.5, NULL, 1, 1),
                                                                                               (NOW(), NOW(), '제주 문화를 잘 보여주는 박물관입니다.', 4.0, NULL, 2, 2),
                                                                                               (NOW(), NOW(), '흑돼지가 정말 맛있어요! 제주 여행 필수 코스', 4.8, NULL, 3, 3),
                                                                                               (NOW(), NOW(), '숙박 시설이 깔끔하고 서비스가 좋아요.', 4.2, NULL, 4, 4),
                                                                                               (NOW(), NOW(), '전통시장의 정취를 느낄 수 있어요.', 3.8, NULL, 5, 5),
                                                                                               (NOW(), NOW(), '해안 둘레길 코스가 정말 좋아요!', 4.7, 1, 1, NULL),
                                                                                               (NOW(), NOW(), '오름 탐방 코스 추천합니다!', 4.3, 2, 2, NULL),
                                                                                               (NOW(), NOW(), '문화유적 투어가 알차네요.', 4.1, 3, 3, NULL);

-- 14. 인증 테이블
INSERT INTO auth (refresh_token, session_id, member_id) VALUES
                                                            ('refresh_token_kim_abc123', 'session_kim_def456', 1),
                                                            ('refresh_token_lee_ghi789', 'session_lee_jkl012', 2),
                                                            ('refresh_token_park_mno345', 'session_park_pqr678', 3),
                                                            ('refresh_token_choi_stu901', 'session_choi_vwx234', 4),
                                                            ('refresh_token_jung_yzab567', 'session_jung_cdef890', 5);

-- 15. 방문 로그 테이블
INSERT INTO visit_log (created_at, updated_at, member_id, spot_id) VALUES
                                                                       (NOW(), NOW(), 1, 1),
                                                                       (NOW(), NOW(), 1, 2),
                                                                       (NOW(), NOW(), 2, 3),
                                                                       (NOW(), NOW(), 3, 4),
                                                                       (NOW(), NOW(), 3, 5),
                                                                       (NOW(), NOW(), 4, 6),
                                                                       (NOW(), NOW(), 5, 7),
                                                                       (NOW(), NOW(), 5, 8),
                                                                       (NOW(), NOW(), 1, 9),
                                                                       (NOW(), NOW(), 2, 10);

-- 16. 코스 카테고리 테이블
INSERT INTO course_category (category_type, course_id) VALUES
                                                           ('FOREST', 1),
                                                           ('CULTURE', 2),
                                                           ('CULTURE', 3),
                                                           ('FOREST', 4),
                                                           ('CULTURE', 5);

-- 17. 장소 카테고리 테이블
INSERT INTO place_category (created_at, updated_at, category_type, place_id) VALUES
                                                                                 (NOW(), NOW(), 'FOREST', 1),
                                                                                 (NOW(), NOW(), 'CULTURE', 2),
                                                                                 (NOW(), NOW(), 'FOOD', 3),
                                                                                 (NOW(), NOW(), 'MOOD', 4),
                                                                                 (NOW(), NOW(), 'SOCIAL', 5),
                                                                                 (NOW(), NOW(), 'ACTIVITY', 6),
                                                                                 (NOW(), NOW(), 'CULTURE', 7),
                                                                                 (NOW(), NOW(), 'FOOD', 8),
                                                                                 (NOW(), NOW(), 'SOCIAL', 9),
                                                                                 (NOW(), NOW(), 'FOOD', 10);

-- 18. 스팟 카테고리 요약 테이블
INSERT INTO spot_category_summary (created_at, updated_at, visit_count, category_type, spot_id) VALUES
                                                                                                    (NOW(), NOW(), 15, 'FOREST', 1),
                                                                                                    (NOW(), NOW(), 12, 'CULTURE', 2),
                                                                                                    (NOW(), NOW(), 25, 'FOOD', 3),
                                                                                                    (NOW(), NOW(), 8, 'MOOD', 4),
                                                                                                    (NOW(), NOW(), 20, 'SOCIAL', 5),
                                                                                                    (NOW(), NOW(), 18, 'ACTIVITY', 6),
                                                                                                    (NOW(), NOW(), 10, 'CULTURE', 7),
                                                                                                    (NOW(), NOW(), 22, 'FOOD', 8),
                                                                                                    (NOW(), NOW(), 5, 'SOCIAL', 9),
                                                                                                    (NOW(), NOW(), 28, 'FOOD', 10);