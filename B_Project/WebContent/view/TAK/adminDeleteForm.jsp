<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Insert title here</title>
  <!-- Bootstrap 4 CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    /* 스타일 추가: 입력 폼을 화면 가운데로 정렬 */
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    /* 스타일 추가: 폼 입력 요소의 글씨 크기를 키움 */
    input[type="text"] {
      font-size: 20px;
    }

    /* 스타일 추가: 아이디를 입력하는 레이블 글씨를 각지게 */
    label[for="username"] {
      font-weight: bold;
    }
  </style>
</head>
<body>
<script>

  alert("해당아이디로 작성된 모든글을 삭제");

</script>

  <form action="/tak/article/adminDelete.do" method="post" class="text-center">
    <label for="username">아이디를 입력하세요:</label><br>
    <input type="text" id="username" name="username" required><br>
    <input type="submit" value="확인" class="btn btn-dark mt-3">
  </form>

  <!-- Bootstrap 4 JS -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
          integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
          integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
          integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
          crossorigin="anonymous"></script>
</body>
</html>
