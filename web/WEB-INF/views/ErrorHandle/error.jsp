<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="Description" content="Ivan Wang @ ISLab 2017">
  <link href="/resources/css/bootstrap.min.css"
        rel="stylesheet">
  <link href="/resources/css/bootstrap-theme.min.css"
        rel="stylesheet">
  <!--[if lt IE 9]>
  <script src="/resources/js/html5shiv.min.js"></script>
  <script src="/resources/js/respond.min.js"></script>
  <![endif]-->
  <link rel="icon" type="image/ico" href="/resources/favicon.ico"
        sizes="32x32">
  <title>
    ${requestScope['javax.servlet.error.status_code']} | Template
  </title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Template</a>
    </div>
  </div>
</nav>
<div class="container">
  <div class="jumbotron">
    <div class="container" align="center">
      <h1>
        Oops, error code: ${requestScope['javax.servlet.error.status_code']} <br>
      </h1>
      <a href="javascript:history.back()" class="btn btn-success btn-lg">
        <span class="glyphicon glyphicon-home"></span> Take Me Back
      </a>
      <a href="mailto:bobo8347@gmail.com" class="btn btn-default btn-lg">
        <span class="glyphicon glyphicon-envelope"></span> Contact Support
      </a>
    </div>
  </div>
</div>
<footer class="footer">
  <div class="container">
    <div class="pull-right">
      <p class="text-muted">Ivan Wang @ ISLab 2017</p>
    </div>
  </div>
</footer>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
