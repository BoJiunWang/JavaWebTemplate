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
<html lang="zh-Hant-TW">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Ivan Wang @ ISLab 2018">
    <meta name="author" content="Ivan Wang, bobo8347@gmail.com">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/resources/favicon.ico" sizes="32x32">
    <title>
        <jsp:invoke fragment="subtitle"/>
        | Template
    </title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Template</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars"
                aria-controls="navbars" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbars">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Info">Info</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Info/Link">Link</a>
                </li>
                <jsp:invoke fragment="navContainer"/>
            </ul>
            <ul class="navbar-nav">
                <c:if test="${empty userInfo}">
                    <li class="nav-item active">
                        <a id="login"><span class="oi oi-account-login"></span>&nbsp;Log
                            In</a>
                    </li>
                </c:if>
                <c:if test="${not empty userInfo}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           id="dropdown"
                           data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">${userInfo.getUserName()}</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/Profile">編輯個人資料</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">
                                <span class="oi oi-account-logout"></span>&nbsp;Log out</a>
                        </div>
                    </li>
                </c:if>
            </ul>
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
                    <h5 class="modal-title">Log In</h5>
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="tab-content">
                        <div class="tab-pane active" id="signin">
                            <form style="padding-top: 5px" method="post" action="${pageContext.request.contextPath}/Login">
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
                                        id="doLogon">Log In
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

<footer class="footer border-top">
    <div class="container">
        <div class="float-right">
            <span class="text-muted">Ivan Wang @ ISLab 2018</span>
        </div>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script>
  $(document).ready(function() {
    $('li.active').removeClass('active');
    $('a[href="' + location.pathname + '"]').closest('li').addClass('active');
  });
</script>
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