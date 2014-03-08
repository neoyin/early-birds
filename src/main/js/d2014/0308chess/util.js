Util = {
	arc: function(cxt, x, y, radius, style){
		cxt.beginPath();
		if(style != null){
			cxt.fillStyle = style;
		}
		cxt.arc(x, y, radius, 0, Math.PI*2, true);
		cxt.fill();
		cxt.closePath();
	},
	drawButton: function(cxt, b, style){
		cxt.beginPath();
		if(style != null){
			cxt.fillStyle = style;
		}
		cxt.fillRect(b.x, b.y, b.w, b.h);
		cxt.fillStyle = "black";
		cxt.fillText(b.text,b.x, b.y + b.h);
		cxt.closePath();
	}
}
Button = function(x, y, w, h, text){
	return {
		x:x,
		y:y,
		w:w,
		h:h,
		text:text
	}
}