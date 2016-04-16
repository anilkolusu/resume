function loginvalidation(form){
	if(form.username.value==""){
		document.getElementById("error").innerHTML="* Please enter username";
		return false;
	}else
	if(!validateEmail(form.username.value)){
		document.getElementById("error").innerHTML="* Enter valid email id";
		return false;
	}else
	if(form.password.value==""){
		document.getElementById("error").innerHTML="* Please enter password";
		return false;
	}else
		return true;
}
function regvalidation(form){
	if(form.fullname.value==""){
		document.getElementById("error").innerHTML="* Please enter full name";
		return false;
	}else
	if(form.fullname.value.length<6){
		document.getElementById("error").innerHTML="* Full name should be atleast 5 characters";
		return false;
	}else
	if(form.email.value==""){
		document.getElementById("error").innerHTML="* Please enter email";
		return false;
	}else
	if(!validateEmail(form.email.value)){
		document.getElementById("error").innerHTML="* Enter valid email id";
		return false;
	}else
	if(!allowemailid(form.email.value)){
		document.getElementById("error").innerHTML="* Your not part of the NPU University. Please enter valid email id";
		return false;
	}else
	if(form.password.value==""){
		document.getElementById("error").innerHTML="* Please enter password";
		return false;
	}else
	if(form.password.value.length<6){
		document.getElementById("error").innerHTML="* Password should be atleast 6 characters";
		return false;
	}else
	if(form.repassword.value==""){
		document.getElementById("error").innerHTML="* Please conform your password";
		return false;
	}else
	if(form.password.value!=form.repassword.value){
		document.getElementById("error").innerHTML="* Password should be match";
		return false;
	}else
	if(form.affiliation.value==""){
		document.getElementById("error").innerHTML="* Please select affiliation";
		return false;
	}else
		return true;
}
function ridevalidation(form){
	if(form.from.value==""){
		document.getElementById("error").innerHTML="* Please enter from city";
		return false;
	}else
	if(form.to.value==""){
		document.getElementById("error").innerHTML="* Please enter to city";
		return false;
	}else
	if(form.seats.value==""){
		document.getElementById("error").innerHTML="* Please enter No of seats available";
		return false;
	}else
		return true;
}
function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
}
function allowemailid(email){
	var e = email.split("@");
	if(e[1] == 'mail.npu.edu'){
		return true;
	}
	return false;
}
function sendcomment(event,comment,messageid) {
	var message;
	if(event.keyCode == 13 && event.shiftKey == 0)  {
		message = $(comment).val();
		message = message.replace(/^\s+|\s+$/g,"");
		if (message != '') {
			document.getElementById('c'+messageid).value='' ;
			message = message.replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/\"/g,"&quot;");
			$.ajax({
				type:"POST",
				url: "postcomment?messageid="+messageid+"&&comment="+message,
				cache: false,
				dataType: "json",
				success: function(data) { 
				$.each(data.items, function(i,item){
					if (item){
						if(item.s==0){
							$("#m"+item.m).append('<div class="post-comment-box-inner"><div class="comment-info-profile-photo-box"><img src="images/unknown.jpg" class="comment-info-profile-photo"/></div><div class="comment-info-message-box"><table><tr><td><span class="comment-info-fullname">'+item.n+'</span><span class="comment-info-message">'+item.c+'</span></td></tr></table></div></div>');
						}
						if(item.s==1){
						}
					}
				});
			}});
		}
	}
}
function updateoption(option,messageid){
	if(option.value != ''){
		document.getElementById('moption').value = option.value;
		document.getElementById('midv').value = messageid;
		document.getElementById("submitformid").submit();
	}
}
function like(messageid){
	$.ajax({
		type:"POST",
		url: "postlike?messageid="+messageid+"&&status=0",
		cache: false,
		dataType: "json",
		success: function(data) { 
		$.each(data.items, function(i,item){
			if (item){
				if(item.s==0){
					$("#spanm"+item.m).html('<a href="javascript:void(0)" onclick="javascript:unlike('+item.m+')" class="small-text-for-like">Unlike</a>');
					$("#m"+item.m).append('<div class="post-comment-box-inner"><div class="comment-info-profile-photo-box"><img src="images/unknown.jpg" class="comment-info-profile-photo"/></div><div class="comment-info-message-box"><table><tr><td><span class="comment-info-fullname">'+item.n+'</span><span class="comment-info-message">Liked this</span></td></tr></table></div></div>');
				}
				if(item.s==1){
					$("#spanm"+item.m).html('<a href="javascript:void(0)" onclick="javascript:unlike('+item.m+')" class="small-text-for-like">Unlike</a>');
				}
			}
		});
	}});
}
function unlike(messageid){
	$.ajax({
		type:"POST",
		url: "postlike?messageid="+messageid+"&&status=1",
		cache: false,
		dataType: "json",
		success: function(data) { 
		$.each(data.items, function(i,item){
			if (item){
				if(item.s==0){
					$("#spanm"+item.m).html('<a href="javascript:void(0)" onclick="javascript:like('+item.m+')" class="small-text-for-like">Like</a>');
				}
				if(item.s==1){
					$("#spanm"+item.m).html('<a href="javascript:void(0)" onclick="javascript:like('+item.m+')" class="small-text-for-like">Like</a>');
				}
			}
		});
	}});
}