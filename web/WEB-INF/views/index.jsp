<%@page contentType="text/html; charset=UTF-8" session="false" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<tag:pageTemplate>
    <jsp:attribute name="subtitle">首頁</jsp:attribute>
    <jsp:attribute name="container">
    <div class="row">
        <div class="col-xs-4 col-sm col-md-4 col-lg-4"></div>
        <div class="col-xs-4 col-sm col-md-4 col-lg-4 text-center">${homeInfo}</div>
        <div class="clearfix visible-xs-block"></div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"></div>
    </div>
    </jsp:attribute>
</tag:pageTemplate>
