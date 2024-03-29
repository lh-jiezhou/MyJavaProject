<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 5/25/2021
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示所有书籍</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表——显示所有书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row" >
        <%--新增--%>
        <div class="col-md-4 column" class="form-inline">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/books/toAddBook">新增</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/books/allBook">显示所有书籍</a>
        </div>
        <%--查询--%>
        <div class="col-md-4 column" >
            <form action="${pageContext.request.contextPath}/books/queryBook" method="post" class="form-inline">
                <input type="text" name="queryBookName" placeholder="请输入要查询的书籍名称" class="form-control" >
                <input type="submit" value="查询" class="btn btn-primary">
            </form>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名字</th>
                    <th>书籍数量</th>
                    <th>书籍详情</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="book" items="${requestScope.get('list')}">
                    <tr>
                        <td>${book.getBookID()}</td>
                        <td>${book.getBookName()}</td>
                        <td>${book.getBookCounts()}</td>
                        <td>${book.getDetail()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/books/toUpdate/${book.getBookID()}">更改</a> |
                            <a href="${pageContext.request.contextPath}/books/deleteBook?id=${book.getBookID()}">删除</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>


</div>

</body>
</html>
