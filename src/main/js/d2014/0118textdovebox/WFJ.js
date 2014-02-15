var request = require('request');
var reqSignin = request('http://www.l99.com/EditAccount_login.action?e=1502929&m=860725', function (error, response, body) {
	var cookie = response.headers["set-cookie"]
	var reqPost = request.post({
		headers: {'content-type' : 'application/x-www-form-urlencoded','Cookie':cookie},
		url:     'http://www.l99.com/timeline_saveText.action',
		body:    "ztFlag_mark=true&edit_post_text_title=robot&text.textContent=robotpostdovebox&post_state=40&edit_post_text_category=598883"
	}, function(error, response, body){
		console.log(response.statusCode);
	});
})
