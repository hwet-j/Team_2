<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Kakao 지도 시작하기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
  	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .container {
            margin-top: 50px;
            text-align: center;
        }
        .map-container {
            width: 65%;
            height: 450px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<%@ include file="/header.html" %>

    <div class="container">
        <h1 class="mb-3">찾아오는 길</h1>
        
        <div class="map-container" id="map"></div>

        <div class="row mt-4">
            <div class="col-md-12">
                <h3>회사 주소</h3>
                <p>서울 강남구 테헤란로7길 7 에스코빌딩 6,7층</p>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-12">
                <h3>연락처</h3>
                <p>전화: 02-1234-5678</p>
                <p>이메일: info@example.com</p>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-12">
                <h3>찾아오는 방법</h3>
                <ul>
                    <p><strong>지하철</strong>: 2호선, 신분당선 강남역 12번 출구에서 도보로 5분</p>
                    <p><strong>버스</strong>: 146, 360, 740, 1100, 1700, 2000, 8146번 버스 정류장(강남역12번출구)에서 하차 후 도보로 5분</p>
                </ul>
            </div>
        </div>
    </div>
<%@ include file="/footer.html" %>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cd8c844fb502523d87b94610e62a0d22&libraries=services"></script>
    <script>
        var mapContainer = document.getElementById('map');
        var mapOption = {
            center: new kakao.maps.LatLng(37.499634, 127.030460),
            level: 3
        };
        var map = new kakao.maps.Map(mapContainer, mapOption);
        var geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch('서울 강남구 테헤란로7길 7 에스코빌딩 6,7층 (우)06134', function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:180px;text-align:center;padding:6px 0;">중앙정보처리학원</div>'
                });
                infowindow.open(map, marker);
                map.setCenter(coords);
            }
        });
    </script>
</body>
</html>
