<%--
  Created by: Ivan_Wang
  Date: 2017-07-01
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="subtitle" fragment="true" %>
<%@attribute name="navContainer" fragment="true" %>
<%@attribute name="container" fragment="true" %>
<%@attribute name="subContainer" fragment="true" %>
<%@attribute name="script" fragment="true" %>
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
  <link rel="icon" type="image/ico"
        href="/resources/favicon.ico" sizes="32x32">
  <title>
    <jsp:invoke fragment="subtitle"/>
    | Template
  </title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
              data-target="#navbar"
              aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Template</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${empty userInfo}">
          <li><a id="login"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Log In</a>
          </li>
        </c:if>
        <c:if test="${not empty userInfo}">
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="glyphicon glyphicon-user"></span>&nbsp;${userInfo.getUserName()}
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="/Profile">編輯個人資料</a></li>
            </ul>
          <li><a id="logout" href="/Logout"><span
              class="glyphicon glyphicon-log-out"></span>&nbsp;Log out</a>
          </li>
        </c:if>
      </ul>
      <jsp:invoke fragment="navContainer"/>
    </div>
  </div>
</nav>
<div class="container">
  <jsp:invoke fragment="container"/>
</div>
<jsp:invoke fragment="subContainer"/>
<c:if test="${empty userInfo}">
  <div class="modal fade" id="logonBox" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
          </button>
          <h4 class="modal-title">Sign In</h4>
        </div>
        <div class="modal-body">
          <div class="tab-content">
            <div class="tab-pane active" id="signin">
              <form style="padding-top: 5px" method="post" action="/Login">
                <div class="form-group">
                  <input type="text" class="form-control" id="userName"
                         name="userAccount"
                         placeholder="User Name" required>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control" id="password"
                         name="userPassword"
                         placeholder="Password" required>
                </div>
                <button type="submit" class="btn btn-success btn-block"
                        id="doLogon">Sign In
                </button>
              </form>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel
          </button>
        </div>
      </div>
    </div>
  </div>
</c:if>
<footer class="footer">
  <div class="container">
    <div class="pull-right">
      <p class="text-muted">Ivan Wang @ ISLab 2017</p>
    </div>
  </div>
</footer>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<jsp:invoke fragment="script"/>
<c:if test="${empty userInfo}">
  <script>
    $('#login').on('click', function (e) {
      $('#logonBox').modal({
        keyboard: false,
        backdrop: 'static'
      });
    });
  </script>
</c:if>
</body>
</html>