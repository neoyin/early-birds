var http = require('http');
var querystring = require('querystring');
var contents = querystring.stringify({
	e: '3251315',
	m: '123123'
});

var loginOptions = {
        host:"www.l99.com",
        path:"/EditAccount_login.action",
        method:"post",
        headers:{
                "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
                "Content-Length":contents.length
        }

};

var cookie;

var loginReq = http.request(loginOptions,function(res){
        var headers=res.headers;
        cookie = JSON.stringify(headers["set-cookie"]);
        res.on("data",function(data){
                console.log(data);
        });

	var options = {
        	host: 'www.l99.com',
        	path: '/timeline_saveText.action?text.textContent=AAAAAAAAAAAA',
        	method: 'POST',
        	headers:{
                	"Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
                	"Content-Length":contents.length,
                	"Cookie":cookie
        	}
	
	};


	var req = http.request(options, function(res) {
        	var headers=res.headers;
        	console.log(cookie);
        	res.on('data', function (data) {
                	console.log(data);
        	});
	});

	req.write(contents);
        req.end();
});


loginReq.write(contents);
loginReq.end();
