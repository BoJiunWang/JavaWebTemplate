<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="Description" content="Ivan Wang @ ISLab 2018">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/theme.css" rel="stylesheet">
    <link href="/resources/css/open-iconic-bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/ico" href="/resources/favicon.ico" sizes="32x32">
    <title>
        ${requestScope['javax.servlet.error.status_code']} | Template
    </title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container">
        <a class="navbar-brand" href="/">Template</a>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <div class="container" align="center">
            <h1 class="display-4" style="word-wrap: break-word;">
                Oops, error code: ${requestScope['javax.servlet.error.status_code']} <br>
            </h1>
            <!-- 在中型和大型裝置中顯示 -->
            <div class="d-none d-lg-block">
                <div class="row justify-content-center">
                    <div class="col-4">
                        <a href="javascript:history.back()" class="btn btn-success btn-lg">
                            <span class="oi oi-home"></span>&nbsp;Take Me Back
                        </a>
                    </div>
                    <div class="col-4">
                        <a href="mailto:bobo8347@gmail.com" class="btn btn-info btn-lg">
                            <span class="oi oi-envelope-closed"></span>&nbsp;Contact Support
                        </a>
                    </div>
                </div>
            </div>
            <!-- 顯示於小型裝置 -->
            <div class="d-lg-none">
                <div class="row justify-content-center">
                    <div class="col-sm-4">
                        <a href="javascript:history.back()" class="btn btn-success btn">
                            <span class="oi oi-home"></span>&nbsp;Take Me Back
                        </a>
                    </div>
                    <br>
                    <br>
                    <br>
                    <div class="col-sm-4">
                        <a href="mailto:bobo8347@gmail.com" class="btn btn-info btn">
                            <span class="oi oi-envelope-closed"></span>&nbsp;Contact Support
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer border-top">
    <div class="container">
        <div class="float-right">
            <span class="text-muted">Ivan Wang @ ISLab 2018</span>
        </div>
    </div>
</footer>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
