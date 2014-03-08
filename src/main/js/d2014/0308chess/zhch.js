// 重新定义Game的各个方法
// Game 初始化
Game.init = function(){
	Game.canvas = document.getElementById("chess");
	var cxt = Game.canvas.getContext("2d");

	var sideX = Game.sideX = board.left + board.width + 10;
	var y = board.top + 80;
	Game.clearButton = Button(sideX, y, 50, 30, "Clear");
	Game.backButton = Button(sideX, y + 50, 50, 30, "Back");
	Game.drawNowColor(cxt);
	Game.drawButton(cxt);
	Game.ttt = 0;
	Game.step = [];
}
// 画布点击事件
Game.clickBoard = function(event) {
    event = event || window.event;

    var canvas = Game.canvas;
    var x = event.pageX - canvas.offsetLeft - board.left;
    var y = event.pageY - canvas.offsetTop - board.top;

    console.log(x + ' ' + y);

    

    // 计算点击的位置
    var chess = Game.getChess(Game.color, x, y);
    Game.putChess(chess);
    canvas.width = canvas.width;
    board.draw();
    if(document.getElementById("flag").value == 3){
    	Game.draw();
    }
    Game.checkClick(event);
}
Game.checkClick = function(event){
	var canvas = Game.canvas;
	var x = event.pageX - canvas.offsetLeft;
	var y = event.pageY - canvas.offsetTop;

	if(x > Game.sideX){ // 点到右边
		if(y >= Game.clearButton.y && y <= Game.clearButton.y + Game.clearButton.h){ // clearBtn
			Game.clearChess();
			board.draw();
			Game.draw();
		}
		if(y >= Game.backButton.y && y <= Game.backButton.y + Game.backButton.h){ // clearBtn
			Game.back();
			board.draw();
			Game.draw();
		}
	}
}
// 得到在点击处棋子的对象
Game.getChess = function(color, x, y){
	// 点到边界外面
	if(x < -10 || x > board.width + 10 || y < -10 || y > board.width + 10){
		console.log("click out of board.");
		return null;
	}
	x = Math.floor((x + board.grid/2 )/board.grid);
	y = Math.floor((y + board.grid/2 )/board.grid);
	return Chess(color, x, y);
}
Game.put = function(color, x, y){
	var chess = Chess(color, x, y);
	Game.putChess(chess);
}
Game.putChess = function(chess){
	if(chess != null){
		Game.chesses[chess.x][chess.y] = chess;
		Game.step[Game.step.length] = chess;
		Game.clearDead(chess);
	}
}
Game.clearDead = function(chess){
	var chesses = Game.chesses;
	var x = chess.x;
	var y = chess.y;
	var color = chess.color;
	
	var points = [{x:x,y:y-1},{x:x,y:y+1},{x:x-1,y:y},{x:x+1,y:y}];
	var checkColor = color == 1 ? 2 : 1;
	for(var i=0;i<points.length;i++){
		Game.deads = [];
		var live = Game.findLife(checkColor, points[i]);
		if(!live){
			for(var j=0;j<Game.deads.length;j++){
				var deadChess = Game.deads[j];
				Game.chesses[deadChess.x][deadChess.y] = null;
			}
		}
	}
	
}
Game.findLife = function(color, point){
	console.log("find life point:" + point.x + "," + point.y );
	
	if(!Game.valide(point)){
		return false;
	}
	var chess = Game.chessAt(point.x,point.y);
	if(chess == null){
		return true;
	}
	if(chess.color != color){
		return false;
	}
	// 已经检测过的直接返回false
	for(var i=0;i<Game.deads.length;i++){
		var dead = Game.deads[i];
		if(dead.x == point.x && dead.y == point.y){
			return false;
		}
	}
	Game.deads[Game.deads.length] = chess;

	var x = point.x;
	var y = point.y;
	var points = [{x:x,y:y-1},{x:x,y:y+1},{x:x-1,y:y},{x:x+1,y:y}];
	var live1 = Game.findLife(color, points[0]);
	var live2 = Game.findLife(color, points[1]);
	var live3 = Game.findLife(color, points[2]);
	var live4 = Game.findLife(color, points[3]);
	
	return live1 || live2 || live3 || live4;
}
Game.valide = function(point){
	return point.x >=0 && point.x <=18 && point.y >=0 && point.y <=18;
}
Game.chessAt = function(x, y){
	if(Game.chesses[x]){
		return Game.chesses[x][y];
	}
	return null;
}
Game.draw = function(){
    if(Game.color == 1){
    	Game.color = 2;
    }else{
    	Game.color = 1;
    }

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
	Game.drawNowColor(cxt);
	Game.drawButton(cxt);
}
Game.drawNowColor = function(cxt){
	var r = 30;
	cxt.beginPath();
	var style = Game.color == 1 ? "black" : "white";
	
	var x = board.left + board.width + 10 + r;
	var y = board.top + 10 + r;
	Util.arc(cxt, x, y, r,style);
}
Game.drawButton = function(cxt){
	Util.drawButton(cxt, Game.clearButton, "green");
	Util.drawButton(cxt, Game.backButton, "green");
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
	//console.log("draw chess:");
	//console.log(chess);

	var style = chess.color == 1 ? "black" : "white";
	var x = board.grid * chess.x + board.left;
	var y = board.grid * chess.y + board.top;
	Util.arc(cxt, x, y, 15, style);
}
Game.back = function(){
	console.log("back");
	if(Game.step.length <1 ){
		return;
	}
	var p = Game.step[Game.step.length-1];
	Game.chesses[p.x][p.y] = null;
	Game.step.length = Game.step.length -1;
	board.draw();
	Game.draw();
}

var board = Board();
board.draw();
// Game初始化
Game.init();

var a = Game.chessAt(33,22);
console.log("nono");
console.log(a);