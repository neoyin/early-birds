var im = require('imagemagick');
im.convert(['src.jpg', '-rotate', '90', 'src_rotate90.jpg'], function(err, stdout){
  if (err) throw err;
  console.log('stdout:', stdout);
});