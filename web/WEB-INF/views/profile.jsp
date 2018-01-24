<%@page contentType="text/html; charset=UTF-8" session="false" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:pageTemplate>
    <jsp:attribute name="subtitle">首頁</jsp:attribute>
    <jsp:attribute name="container">
    <div class="text-center">
        <h2>
            <span class="oi oi-pencil"></span>
            &nbsp;編輯個人資料
        </h2>
    </div>
    <form id="form">
        <div class="align-items-center">
            <div class="col-auto">
                <label for="userAccount">
                    使用者帳號
                </label>
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="oi oi-person"></span>
                        </div>
                    </div>
                    <input type="text" class="form-control" id="userAccount"
                           value="${userInfo.getUserAccount()}" readonly>
                </div>
            </div>
            <div class="col-auto">
                <label for="userName">
                    使用者名稱
                </label>
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="oi oi-person"></span>
                        </div>
                    </div>
                    <input type="text" class="form-control" id="userName"
                           value="${userInfo.getUserName()}" required>
                </div>
            </div>
            <div class="col-auto">
                <label for="userEmail">
                    電子信箱地址
                </label>
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <span class="oi oi-envelope-closed"></span>
                        </div>
                    </div>
                    <input type="email" class="form-control" id="userEmail"
                           value="${userInfo.getUserEmail()}" required>
                </div>
            </div>
            <div class="clearfix">&nbsp;</div>
            <div class="col-sm-12 text-center">
                <button type="submit" class="btn btn-primary btn-sm">
                    <span class="oi oi-check"></span>
                    &nbsp;更改
                </button>
            </div>
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
      })
    </script>
    </jsp:attribute>
</tag:pageTemplate>
