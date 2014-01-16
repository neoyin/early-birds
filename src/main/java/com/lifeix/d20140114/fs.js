var fs = require('fs');
console.log('---------------------');
console.log('读取hosts文件:');
fs.readFile('host.txt', 'utf8', function(err, data){
	if(err){
		return console.log(err);
	}
	console.log(data);
	console.log("修改hosts，将L99指向本地:");
	fs.appendFile('host.txt', '127.0.0.1   www.l99.com',function(err, data){
		if(err){
			return console.log(err);
		}
		fs.readFile('host.txt', 'utf8', function(err, data){
			if(err){
				return console.log(err);
			}
			console.log(data);
			console.log('---------------------');
		})

	})
})
