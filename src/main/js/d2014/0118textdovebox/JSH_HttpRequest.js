var http = require('http');
var qs = require('querystring');
var cookies;
var options = {
  hostname: 'www.l99.com',
  port: 80,
  path: '/EditAccount_login.action?e=3213081&m=19881225',
  method: 'POST'
};

var req = http.request(options, function(res) {
  console.log('STATUS: ' + res.statusCode);
  // console.log('COOKIES: ' + JSON.stringify(res.headers['set-cookie']));
  // for(var item in res.headers) {
      // console.log(item + ": " + res.headers[item]);
  // }
  cookies = JSON.stringify(res.headers['set-cookie']);
  res.setEncoding('utf8');
  res.on('data', function (chunk) {
      console.log('BODY: ' + chunk);
  });

  var opt = {
      hostname: 'www.l99.com',
    port: 80,
    path: '/timeline_saveText.action?text.textTitle=title&text.textContent=content',
    method: 'POST',
    headers: {
        'Cookie': cookies 
    }
  };
  var req1 = http.request(opt, function(res) {
      console.log('STATUS: ' + res.statusCode);
      res.setEncoding('utf8');
      res.on('data', function (chunk) {
          console.log('BODY: ' + chunk);
      });
  }).on('error', function(e) {
      console.log('problem with request: ' + e.message);
  });
  // write data to request body
  var text = {textTitle:'title',textContent:'content'};
  var content = qs.stringify(text);
  req1.write(content);
  req1.end();

}).on('error', function(e) {
  console.log('problem with request: ' + e.message);
});

req.end();

// 第二次请求，跟上面的请求回调函数执行顺序是谁先返回先执行谁，
// 本例中需要先得到上面回调函数中的header，赋值后再执行下面的请求，所以将该请求放到上面的回调函数内部执行.
// var opt = {
    // hostname: 'www.l99.com',
    // port: 80,
    // path: '/timeline_saveText.action?text.textTitle=title&text.textContent=content',
    // method: 'POST',
    // headers: {
        // 'Cookie': cookies 
    // }
// };

// var req1 = http.request(opt, function(res) {
  // console.log('STATUS: ' + res.statusCode);
  // res.setEncoding('utf8');
  // res.on('data', function (chunk) {
      // console.log('BODY: ' + chunk);
  // });
// }).on('error', function(e) {
  // console.log('problem with request: ' + e.message);
// });

// // write data to request body
// var text = {textTitle:'title',textContent:'content'};
// var content = qs.stringify(text);
// req1.write(content);

// req1.end();
