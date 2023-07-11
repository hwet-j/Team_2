-- 1. 데이터베이스 생성
CREATE DATABASE CAB DEFAULT CHARACTER SET utf8mb4;

-- 2. 유저 생성 
CREATE USER 'scott'@'%' IDENTIFIED BY 'tiger';

-- 3. 생성한 유저에 권한 부여 
GRANT ALL PRIVILEGES ON CAB.* TO 'scott'@'%';

-- 4. 권한 변경 적용 (메모리 재로딩 - 권한 즉시 적용) 
FLUSH PRIVILEGES;

-- 5. 회원 테이블 생성 
CREATE TABLE IF NOT EXISTS user_info (
    user_id 		VARCHAR(20) PRIMARY KEY,		-- 회원아이디
    user_pw 		VARCHAR(16) NOT NULL,			-- 비밀번호
    user_name 		VARCHAR(20),					-- 이름
	user_birth 		DATE,							-- 생일
    user_nickname 	VARCHAR(40) UNIQUE,				-- 닉네임
	user_gender 	ENUM('남성', '여성'),				-- 성별 
    user_tlno 		VARCHAR(20) UNIQUE,				-- 핸드폰번호
    user_joindate 	DATE DEFAULT (CURRENT_DATE)		-- 회원가입날짜 
);

-- 6. 회원테이블 더미 데이터 (옵션) 
INSERT INTO user_info (user_id, user_pw, user_name, user_birth, user_nickname, user_gender, user_tlno, user_joindate)
VALUES
('user1', 'password1', '홍길동', '1990-01-01', 'hong', '남성', '010-1234-5678', '2023-07-10'),
('user2', 'password2', '김영희', '1995-05-15', 'kim', '여성', '010-9876-5432', '2023-07-10'),
('user3', 'password3', '이철수', '1988-11-20', 'lee', '남성', '010-2468-1357', '2023-07-11'),
('user4', 'password4', '박지영', '1992-03-08', 'park', '여성', '010-5555-1234', '2023-07-11');


-- 7. 테이블 
CREATE TABLE IF NOT EXISTS hwet_board (
board_id 	INT PRIMARY KEY AUTO_INCREMENT,
writer 		VARCHAR(20) NOT NULL,
title 		VARCHAR(50) NOT NULL,
category 	VARCHAR(50) NOT NULL,
link 		VARCHAR(400) NOT NULL,
content 	VARCHAR(1000) NOT NULL,
regdate 	DATE NOT NULL DEFAULT (CURRENT_DATE),
hit 		INT DEFAULT 0 NOT NULL,
updatedate 	DATE,
FOREIGN KEY (writer) REFERENCES user_info(user_id)
);


SELECT * FROM user_info;

SELECT * FROM hwet_board;


