var fs = require('fs');
fs.readFile('/home/lifeix/data.txt',function(err,data){
    if(err) throw err;
    fs.writeFile('/home/lifeix/jiang/result.txt',data,function(err){
        if(err) throw err;
        console.log('has finished: ');
    });
    
});