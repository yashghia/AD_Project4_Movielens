<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<script type="text/javascript" src="js/editstudy.js">
    </script>
<script type="text/javascript" src="js/browse.js">
    </script>
<%-- Code to display Page Name --%>
<h3 id="page_name">Editing a study</h3>
<%-- Code to go back to Main page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to input study details --%>
<section>
    <form class="form-horizontal" action="StudyController?action=update" method="post" enctype="multipart/form-data">
    
    	<div class="form-group">
        <label class="col-sm-4 control-label">Study Name *</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="study_name" required value="<c:out value='${study.studyName}' />" />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label">Question Text *</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="question_text" required value="<c:out value='${study.question}' />" />
         </div>
            </div>
        
        
        <%-- Img tag is used to import image --%>
        <div class="form-group">
        <label class="col-sm-4 control-label">Image *</label>
        <div class="col-sm-4">
        <img src="${study.getImageURL()}" class="img-responsive" height="50" width="75" alt="Edit"/>
        <button> <input type="file" name="imageURL"  accept="image/gif,image/jpeg" class="btn btn-primary">Browse</button>
<!--        <input type='file' onchange="readURL(this);" />
        <img id="blah" src="#" alt="your image" />-->
        <%-- 
        
        $("input").change(function(e) {
    for (var i = 0; i < e.originalEvent.srcElement.files.length; i++) {
    
        var file = e.originalEvent.srcElement.files[i];
        var img = document.createElement("img");
        var reader = new FileReader();
        reader.onloadend = function() {
             img.src = reader.result;
        }
        reader.readAsDataURL(file);
        $("input").after(img);
    }
});
 
        
        --%>
        
        
         </div>
            </div>
        
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Participants *</label>
         <div class="col-sm-4"> 
             <input type="text" class="form-control" name="participants" id="participants" maxlength="2" required value="<c:out value='${study.requestedParticipants}' />" />
         </div>
            </div>
        
        <div class="form-group">
        <label class="col-sm-4 control-label"># Answers *</label>
        <div class="col-sm-4">
        <select class="form-control" id="edit_study_answers" name="edit_study_answers">
        <option value="3">3</option>
       	<option value="4">4</option>
        <option value="5">5</option>
        </select> 
         </div>
            </div>
             <input type ="hidden" name="numbers" id="numbers" value="<c:out value='${fn:length(study.answers)}'/>"/>
 	<c:forEach var="i" begin="0" end="${fn:length(study.answers)-1}">
            <input type ="hidden" name="values" id="${i }" value="<c:out value='${study.answers.get(i)}'/>"/>
     </c:forEach>
            <input type ="hidden" name="code" id="code" value="<c:out value='${study.studyCode}'/>"/>
            <input type ="hidden" name="email" id="email" value="<c:out value='${study.email}'/>"/>
            <input type ="hidden" name="status" id="status" value="<c:out value='${study.status}'/>"/>
            <input type ="hidden" name="numparticipants" id="numparticipants" value="<c:out value='${study.numOfParticipants}'/>"/>
        <div id="TextBoxContainer1">
    <!--Textboxes will be added here -->
		</div>
       
       
       <div class="form-group">
        <label class="col-sm-4 control-label">Description *</label>
         <div class="col-sm-4"> 
             <textarea name="description" class="form-control"  required><c:out value="${study.description}"/></textarea>
         </div>
            </div>
        
        <div class="form-group">
        <div class="col-sm-offset-5 col-sm-4">
        <button type="submit"  class="btn btn-primary">Update</button>
         </div>
            </div>
            <br/><br/><br/>
    </form>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>