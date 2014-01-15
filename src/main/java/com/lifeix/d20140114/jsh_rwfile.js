var fs = require("fs")
var data = "hello, node js"
fs.writeFile('a.txt', data , 'utf-8', function(err) {
    if(err) {
        console.log('write fail');
    } else {
        console.log('write success');
    }
});

fs.readFile('a.txt', 'utf-8', function(err, data) {
    if(err) {
        console.log('read fail');
    } else {
        console.log(data);
    }
})
