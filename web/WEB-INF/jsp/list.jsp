<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!--web路径：
        不以/开始的相对路径，找资源时会以当前资源的路径为基准，很容易出问题
        以/开始的相对路径，找资源是以服务器的路径为标准(http://localhost:3306)需要加上项目名
            如：http://localhost:3306/crud
    -->
    <%--    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css"><!--引入bootstrap的css样式库-->--%>
    <%--    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script><!--引入jQuery-->--%>
    <%--    <!--bootstrap是基于jQuery库的，所以应该先导入jQuery-->--%>
    <%--    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script><!--引入bootstrap-->--%>
    <!--引入jQuery-->
    <!-- Bootstrap -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="${APP_PATH}/static/jQuery/jquery-1.12.4.min.js"></script>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<%--搭建显示页面--%>
<div class="container-fluid">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12"><h1>SSM-CRUD</h1></div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="button" class="btn btn-primary">新增</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="emps">
                    <tr>
                        <th>${emps.empId}</th>
                        <th>${emps.empName}</th>
                        <th>${emps.gender}</th>
                        <th>${emps.email}</th>
                        <th>${emps.department.deptName}</th>
                        <th>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                        </th>
                        <th>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </th>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--分页文字信息--%>
        <div class="col-md-6">
            当前第${pageInfo.pageNum}页,共有${pageInfo.pages}页，共有${pageInfo.total}条记录
        </div>
        <%--分页条信息--%>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
                        <li>
                            <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="${APP_PATH}/emps?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
