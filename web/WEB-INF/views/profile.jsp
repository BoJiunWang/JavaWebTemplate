<%@page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:pageTemplate>
  <jsp:attribute name="subtitle">首頁</jsp:attribute>
  <jsp:attribute name="container">
    <div class="page-header text-center">
      <h2>
        <span class="glyphicon glyphicon-pencil"></span>
        &nbsp;編輯個人資料
      </h2>
    </div>
    <form id="form" class="form-horizontal">
      <div class="form-group">
        <label for="userAccount" class="col-sm-4 control-label">
          使用者帳號
        </label>
        <div class="col-sm-6">
          <div class="input-group">
            <span class="input-group-addon glyphicon glyphicon-user"></span>
            <input type="text" class="form-control" id="userAccount"
                   value="${userInfo.getUserAccount()}" readonly>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="userName" class="col-sm-4 control-label">
          使用者名稱
        </label>
        <div class="col-sm-6">
          <div class="input-group">
            <span class="input-group-addon glyphicon glyphicon-user"></span>
            <input type="text" class="form-control" id="userName"
                   value="${userInfo.getUserName()}" required>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="userEmail" class="col-sm-4 control-label">
          電子信箱地址
        </label>
        <div class="col-sm-6">
          <div class="input-group">
                    <span class="input-group-addon">
                        @
                    </span>
            <input type="email" class="form-control" id="userEmail"
                   value="${userInfo.getUserEmail()}" required>
          </div>
        </div>
      </div>
      <div class="clearfix">&nbsp;</div>
      <div class="col-sm-12 text-center">
        <button id="submit" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-ok"></span>
          &nbsp;更改
        </button>
      </div>
    </form>
    </jsp:attribute>
  <jsp:attribute name="script">
    <script>
      $("#form").submit(function (e) {
        $.ajax({
          type: "POST",
          url: "/Profile",
          data: {
            userName: $("#userName").val(),
            userEmail: $("#userEmail").val()
          },
          success: function (response) {
            alert(response);
            window.location.reload();
          },
          error: function (request, status, error) {
            alert(error);
          }
        });
        e.preventDefault();
      });
    </script>
  </jsp:attribute>
</tag:pageTemplate>
