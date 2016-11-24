<%-- Include tag is used to import header page --%>
<%@include file="header.jsp"%>

<br />
<%-- Code to create login form--%>
<form class="form-horizontal" action="UserController" method="post">
    <p>
        ${msg}
    </p>
	<input type="hidden" name="action" value="updatepass">
	<div class="form-group">
            <label class="col-sm-4 control-label">Email Address :</label> 
            <div class="col-sm-4">
                <input type="email" class="form-control" name="email" value="<c:out value='${sessionScope.email}'/>" readonly>
            </div>
		
	
	</div>

	<div class="form-group">
            <label class="col-sm-4 control-label">Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" name="password" required/>
            </div>
            </div>
            <div class="form-group">
            <label class="col-sm-4 control-label">Confirm Password *</label> 
            <div class="col-sm-4">
            <input type="password" class="form-control" name="confirm_password" required />  
            
            </div>
			</div>
			<div class="form-group">
    <div class="col-sm-offset-5">
            <input type="submit" value="Reset Password" class="btn btn-primary">
            </div>
            </div>
            <br><br/><br/>
</form>

<br />
<br />
<br />

<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp"%>