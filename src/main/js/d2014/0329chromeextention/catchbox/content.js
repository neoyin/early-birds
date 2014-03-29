setInterval(function() {
	var xbox = document.getElementById("X_BOX_IMG");

	if(xbox && xbox.style.display != "none") {
		var event = document.createEvent("MouseEvents");
		event.initMouseEvent("click",true,false,document.defaultView,0,0,0,0,0,false,false,false,false,0,null);
		xbox.dispatchEvent(event);
	}
},1000);

