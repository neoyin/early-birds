function extend(subclass, superclass) {
	var F = function () {};
	F.prototype = superclass.prototype;
	subclass.prototype = new F();
	subclass.prototype.constructor = subclass;
}
function DrinkFactory(){}
DrinkFactory.prototype = {
	makeDrink: function(){
		this.boil();
		this.mix();
		this.pour();
	},
	boil: function(){
		console.log("1. 烧开水");
	},
	mix: function(){
		//...
	},
	pour: function(){
		console.log("3. 倒入杯子");
	}
}
function CoffeeFactory(){}
function TeaFactory(){}
extend(CoffeeFactory,DrinkFactory);
extend(TeaFactory,DrinkFactory);
CoffeeFactory.prototype.mix = function(){
	console.log("2. 混合水和咖啡");
}
TeaFactory.prototype.mix = function(){
	console.log("2. 混合水和茶");
}
console.log("冲一杯咖啡：");
var aCupOfCoffee = new CoffeeFactory();
aCupOfCoffee.makeDrink();
console.log("冲一杯茶：");
var aCupOfTea = new TeaFactory();
aCupOfTea.makeDrink();