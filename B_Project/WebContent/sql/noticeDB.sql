CREATE TABLE `notice` (
  `notice_no` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) NOT NULL,
  `notice_title` varchar(50) NOT NULL,
  `notice_content` varchar(350) NOT NULL,
  `notice_views` int DEFAULT NULL,
  PRIMARY KEY (`notice_no`,`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3



-- 관리자
insert into notice(USER_ID,notice_title,notice_content,notice_views)
values('adminid','게시판사용안내', '깨끗하고 화목한 게시판이 되기 바랍니다',0),
('adminid2','게시판우수회원선정안내', '우수회원으로 선정됨을 알립니다',0),
('adminid3', '사이트접속에러공지', '이용에 불편을 끼쳐 죄송합니다',0);
commit;


-- 조회수 증가 
update notice
set notice_views=notice_views+1
where notice_no=1;
