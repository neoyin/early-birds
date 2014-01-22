var http = require('http');
var querystring = require('querystring');


var post_data = querystring.stringify({
        'contentTime': new Date(),
        'dashboardAddress':'',	
         'initdraftId':0,
         'lat':0.0,
         'lng':0.0,	
         'mediaFlag':true,
          'postId': null,	
          'text.categoryId	':2634,
          'text.permissionType':40,
	'text.sourceUrl':'',	
	'text.textContent':'<p>wo jiao jiangfuqiang</p>',
	'text.textTitle':	'ff',
	'text.ztFlag':false,
	'text.ztFlag':false
});

var options = {
    host: 'www.l99.com',
    path: '/timeline_saveText.action',
    method: 'POST',
    headers:{  
        "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",  
        "Content-Length":post_data.length,         
        "Accept":"application/json, text/javascript, */*; q=0.01", 
        "Accept-Language":"zh-cn",  
        "Cache-Control":"no-cache",  
        "Connection":"Keep-Alive",    
       "Host":"www.l99.com",  
        "Referer":"http://www.l99.com/timeline_text.action",  
        "User-Agent":"	Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:25.0) Gecko/20100101 Firefox/25.0",  
        "Cookie":'_pk_id.1.4749=6f1842ca4dd23244.1390355671.5.1390385274.1390371857.; longno=10515; _pk_id.1.3c46=7ecb333d047683a7.1390356736.7.1390385272.1390380982.; _pk_ses.1.4749=*; JSESSIONID=9A03A64AE1F58EC991AE3E2691E42052-n2; accountid=683; xx="OWJhNDM1NDI5OWFhY2QxOTE5ZWQ5NGEzM2I0YWU=056xjMmYy"; accounttype=0; _auto=true; _pk_ref.1.3c46=%5B%22%22%2C%22%22%2C1390385270%2C%22http%3A%2F%2Fwww.l99.com%2FmoreNews.action%22%5D; _pk_ses.1.3c46=*'
    }  
};


var req = http.request(options, function(res) {
  console.log('HEADERS: ' + JSON.stringify(res.headers));
  res.setEncoding('utf8');

  res.on('data', function (chunk) {
    console.log('BODY: ' + chunk);
  });
});
//req.setHeader("Set-Cookie", 'longno:10515;JSESSIONID=9A03A64AE1F58EC991AE3E2691E42052-n2;accountId=683;accounttype=0;_auto=true;xx=OWJhNDM1NDI5OWFhY2QxOTE5ZWQ5NGEzM2I0YWU=056xjMmYy;');
req.write(post_data + "\n");
req.end();