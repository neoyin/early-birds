Board = function(){
	var grid = 30; 
    var width = grid *18;
	var topx = 50,left = 200;
	var w = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S'];
	var cxt = document.getElementById("chess").getContext('2d');

	// 画一条线
	function drawLine(x1,y1,x2,y2){
		cxt.moveTo(x1 + left, y1 + topx);
		cxt.lineTo(x2 + left, y2 + topx);
	}
	// 画棋盘
	function draw() {
		var border = 30;
		cxt.rect(left - border, topx - border, width + 2*border, width + 2*border);
		cxt.fillStyle = "#D8762F";
		cxt.fill();

		cxt.font = "bold 13px Arial";
		cxt.strokeStyle = "black";
		cxt.fillStyle = "black";

		cxt.beginPath();
		for(var i = 0; i < 19; i++) {
			//数字
			cxt.fillText(i+1,left+i*grid - 3,topx - 10);
			//字母
			cxt.fillText(w[i],left - 20,grid*i + topx + 5);

			//横线
			drawLine(0, i*grid, width, i*grid);
			//竖线
			drawLine(i*grid, 0, i*grid, width);
		}
		cxt.closePath();
		cxt.stroke();

		// 星位点
		for(var i=3;i<17;i+=6) {
			for(j=3;j<17;j+=6){
				cxt.arc(grid*i+left, j*grid+topx, 10, 0, Math.PI*2,true);
				cxt.fill();
				cxt.closePath();
			}
		}

	}
	return{
		grid:grid,
		width:width,
		top:topx,
		left:left,
		draw:draw
	}
}