/**
 *  Document: aboutl.jsp
 *	Created On: Feb 4, 2016
 *	Authors: Deepak Rohan
 */

//edit_study_answers
var i =1;
 $(document).ready(function() {
	 var answers = document.getElementById('numbers').value;
	 $("#edit_study_answers").val(answers);
         var j=0;
        while(j< answers) {
        var div = $("<div />");
         	div.html(GetDynamicTextBox(document.getElementById(j).value));
     		$("#TextBoxContainer1").append(div);
                i++;
                j++;
     }
	      $('#edit_study_answers').change(function() {
	    	  	answers = $(this).val();
	            $("#TextBoxContainer1").html("");
	            i = 1;
	            while(answers > 0){
		            var div = $("<div />");
	            		div.html(GetDynamicTextBox(""));
	            		$("#TextBoxContainer1").append(div);
	            		answers--;
	            		i++;
	            }
	      });
	});
 
 function GetDynamicTextBox(value) {
	 
	    return '<div class="form-group"><label class="col-sm-4 control-label">Answer '+i+' *</label><div class="col-sm-4"><input name = "DynamicTextBox'+i+'" class="form-control" type="text" required value = "' + value + '" /></div></div>'
	}

