<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<a href="admin.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section tag is used to display Message Sent   --%>
<br/>
<br/>
<br/>
<h3 class="text-center"><c:out value="Question ${param.status} .." /></h3>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
