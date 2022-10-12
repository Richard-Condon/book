<%--
  Created by IntelliJ IDEA.
  User: 15298
  Date: 2022/8/1
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo>1?requestScope.page.pageNo-1:1}">上一页</a>
    </c:if>

    <c:if test="${1<=requestScope.page.pageNo-2}"><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-2}">${requestScope.page.pageNo-2}</a></c:if>

    <c:if test="${1<=requestScope.page.pageNo-1}"><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a></c:if>

    【${requestScope.page.pageNo}】

    <c:if test="${requestScope.page.pageTotal>=requestScope.page.pageNo+1}"><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a></c:if>

    <c:if test="${requestScope.page.pageTotal>=requestScope.page.pageNo+2}"><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+2}">${requestScope.page.pageNo+2}</a></c:if>


    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo<requestScope.page.pageTotal?requestScope.page.pageNo+1:requestScope.page.pageTotal}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，
    ${requestScope.page.pageTotalCount}条记录
    到第<input value="4" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNo=$("#pn_input").val();
                if (pageNo>0&&pageNo<=${requestScope.page.pageTotal}){
                    location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
                }else{
                    alert("输入的页码有误，请重新输入");
                }
            });
        });
    </script>
</div>
