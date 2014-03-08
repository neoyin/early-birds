// 重新定义Game的各个方法
Game.clickBoard = function(event) {
    event = event || window.event;

    var canvas = Game.canvas,
    x = event.pageX - canvas.offsetLeft,
    y = event.pageY - canvas.offsetTop;

    console.log(x + ' ' + y);

    // 计算点击的位置
    total = total + 1;
    var mod = total%3;
    if(mod == 0){
		Game.put(1, 0, 0);
    }
    if(mod == 1){
		Game.put(2, 2, 2);
    }
    if(mod == 2){
		Game.clearChess();
    }

    board.draw();
    Game.draw();
    
}
Game.put = function(color, x, y){
	var chess = new Chess(color, x, y);
	Game.chesses[x][y] = chess;
	Game.clearDead();
}
Game.draw = function(){
	var cxt = Game.canvas.getContext('2d');
	var chesses = Game.chesses;
	for(var i = 0; i<19;i++ ){
		for(var j=0;j<19;j++){
			var chess = chesses[i][j];
			if(chess != null){
				Game.drawChess(cxt, chess);
			}
		}
	}
}
Game.clearChess = function(){
	var chesses = Game.chesses;
	for(var i = 0; i<19;i++ ){
		for(var j=0;j<19;j++){
			chesses[i][j] = null;
		}
	}
}
Game.drawChess = function(cxt, chess){
	console.log("draw chess:");
	console.log(chess);
	var x = board.grid * chess.x + board.left;
	var y = board.grid * chess.y + board.top;
	cxt.arc(x, y, 15, 0, Math.PI*2,true);
	cxt.fill();
	cxt.closePath();
}

//缓存变量
Game.canvas = document.getElementById("chess");
var board = Board();
board.draw();
var total = 0;