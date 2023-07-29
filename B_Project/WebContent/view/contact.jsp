<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 시작하기</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cd8c844fb502523d87b94610e62a0d22&libraries=services"></script>


</head>
<body>
<div id="map" style="width:50%;height:350px;"></div>
<div class="container mt-5">
    <h1 class="mb-3">찾아오는 길</h1>
    <div class="row">
        <div class="col-md-6">
            <h3>회사 주소</h3>
            <p>서울 강남구 테헤란로7길 7 에스코빌딩 6,7층</p>
        </div>
        <div class="col-md-6">
            <h3>연락처</h3>
            <p>전화: 02-1234-5678</p>
            <p>이메일: info@example.com</p>
        </div>
    </div>
    <div class="row mt-4">
        <div class="col-md-12">
            <h3>찾아오는 방법</h3>
            <ol>
                <li>지하철: 2호선, 신분당선 강남역 12번 출구에서 도보로 5분</li>
                <li>버스: 146, 360, 740, 1100, 1700, 2000, 8146번 버스 정류장(강남역12번출구)에서 하차 후 도보로 5분</li>
            </ol>
        </div>
    </div>
</div>
    
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.499634, 127.030460), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('서울 강남구 테헤란로7길 7 에스코빌딩 6,7층 (우)06134', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">중앙정보처리학원</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>
</body>
</html>