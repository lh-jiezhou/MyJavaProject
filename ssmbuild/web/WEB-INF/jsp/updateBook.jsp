<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 5/31/2021
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加书籍</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/books/updateBook" method="post">

        <input type="text" hidden name="bookID" value="${QBook.bookID}" >

        <div class="form-group">
            <label for="bkname">书籍名称</label>
            <input type="text" name="bookName" value="${QBook.bookName}" class="form-control" id="bkname" required>
        </div>
        <div class="form-group">
            <label for="bkcount">书籍数量</label>
            <input type="text" name="bookCounts" value="${QBook.bookCounts}" class="form-control" id="bkcount" required>
        </div>
        <div class="form-group">
            <label for="bkdetail">书籍描述</label>
            <input type="text" name="detail" value="${QBook.detail}" class="form-control" id="bkdetail" required>
        </div>
        <div class="form-group">
            <input type="submit" class="form-control" value="修改">
        </div>


    </form>




</div>

</body>
</html>
