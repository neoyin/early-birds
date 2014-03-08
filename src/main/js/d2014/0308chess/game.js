Chess = function(color, x, y){
	return {color:color,x:x,y:y};
}
var Game = {
	chesses:null,  // 棋子容器
	canvas:null,  // 画布对象
	color:1, // 1 黑 2 白
	deads:null, // 死子检测
	put:function(color, x, y){ // 下一个棋子

	},
	putChess:function(chess){ // 下一个棋子, 直接放棋子对象

	},
	clearDead:function(){ //  提子, 清除掉已死的棋子

	},
	draw:function(){ // 画出所有棋子

	},
	clickBoard:function(){ // 棋盘点击事件

	}

}

// 初始化棋子容器, 19行19列
Game.chesses = new Array();         //先声明一维
for(var i=0;i<19;i++){                //一维长度为10
	Game.chesses[i]=new Array();         //在声明二维
	for(var j=0;j<19;j++){             //二维长度为20
		Game.chesses[i][j]=null;
	}
}