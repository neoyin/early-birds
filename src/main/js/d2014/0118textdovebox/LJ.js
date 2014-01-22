var http=require('http');
var qs=require('querystring');
var cookie;
var req;
var re;
var content;
var options = function(path, headers){
	var op = {
			hostname: 'www.l99.com',
			path: path,
			method: 'POST',
			headers:headers
	}
	return op;
};
 
var login = function(){
	var post_data={e:'3209027',m:'000000'};
	content=qs.stringify(post_data)
	var headers = {
				'Content-Type':'application/x-www-form-urlencoded',
				'Content-Length':content.length,
			}
	req = http.request(options('/EditAccount_login.action', headers), function(res) {
		console.log("statusCode: ", res.statusCode);
		console.log("header", res.headers);
		cookie = JSON.stringify(res.headers['set-cookie']);
		send();
	});
	req.write(content);
	req.end();
	
}
var send = function(){
	var post_data = {"text.textTitle":"hahaha","text.textContent":"dfsdfds", "contentTime":"2014-01-18 17:31:00"};
	content=qs.stringify(post_data)
	var headers = {
		'Content-Type':'application/x-www-form-urlencoded',
		'Content-Length':content.length,
			'Cookie':cookie
			}
	re = http.request(options('/timeline_saveText.action', headers), function(res) {
		console.log("statusCode: ", res.statusCode);
		console.log("header", res.headers);
	});
	re.write(content);
	re.end();
}

var run = function(){
	login();
}

run();

