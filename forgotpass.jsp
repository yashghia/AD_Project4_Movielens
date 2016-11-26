<%-- Include tag is used to import header page --%>
<%@include file="header.jsp"%>

<br />

<form class="form-horizontal" action="UserController" method="post">

	<input type="hidden" name="action" value="forgot">
	<div class="form-group">
		<label class="col-sm-4 control-label">Email Address *</label> 
		<div class="col-sm-4">
			<input type="email" class="form-control" name="email" required />
	</div>
	</div>

	
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-10">
			<input type="submit" value="Reset Password" class="btn btn-primary">
			
		</div>
	</div>

</form>

<br />
<br />
<br />

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp"%>