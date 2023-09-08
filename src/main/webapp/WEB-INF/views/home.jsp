<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/common.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/home/home.css">
        <link rel="stylesheet" type="text/css" href="${path}/resources/static/css/nav.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <title>Starroad</title>
        <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            $(function() {
                $("#navbar").load("${path}/resources/navbar.jsp");
            });
        </script>

    </head>

    <body>
          <div id="navbar"></div>
          <!-- 본문 내용 -->
          <div class="container">
              <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                          <div class="carousel-item active" data-bs-interval="3000">
                              <img src="https://i0.wp.com/blog.findmybucketlist.com/wp-content/uploads/2020/10/%EC%A0%9C%EC%A3%BC%EB%8F%84-2.jpg?resize=792%2C446&ssl=1" class="d-block" alt="...">
                          </div>
                          <div class="carousel-item" data-bs-interval="3000">
                              <img src="https://i0.wp.com/blog.findmybucketlist.com/wp-content/uploads/2020/10/%EC%A0%9C%EC%A3%BC%EB%8F%84-2.jpg?resize=792%2C446&ssl=1" class="d-block" alt="...">
                          </div>
                          <div class="carousel-item" data-bs-interval="3000">
                              <img src="https://i0.wp.com/blog.findmybucketlist.com/wp-content/uploads/2020/10/%EC%A0%9C%EC%A3%BC%EB%8F%84-2.jpg?resize=792%2C446&ssl=1" class="d-block" alt="...">
                          </div>
                          </div>
                          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                              <span class="visually-hidden">Previous</span>
                          </button>
                          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                              <span class="carousel-control-next-icon" aria-hidden="true"></span>
                              <span class="visually-hidden">Next</span>
                    </button>
                  </div>
          </div>

        <div class="bottom-section">
            <div class="section">
                <img src="https://play-lh.googleusercontent.com/BRgz8ZiUGabAoSIG0Cy7J22VO2E_m4liOU3TJtuQD7iRaxCv0c8cOww_oMopzi9isKTr" alt="이미지1">
                <p>국민 은행 내의 금융상품을 추천한다</p>
            </div>
            <div class="section">
                <img src="https://play-lh.googleusercontent.com/BRgz8ZiUGabAoSIG0Cy7J22VO2E_m4liOU3TJtuQD7iRaxCv0c8cOww_oMopzi9isKTr" alt="이미지2">
                <p>청년 정책 및 청년에게 필요한 금융지식을 제공한다</p>
            </div>
            <div class="section">
                <img src="https://play-lh.googleusercontent.com/BRgz8ZiUGabAoSIG0Cy7J22VO2E_m4liOU3TJtuQD7iRaxCv0c8cOww_oMopzi9isKTr" alt="이미지3">
                <p>예적금을 지속할 수 있도록 돕는다</p>
            </div>
        </div>
    </body>
</html>