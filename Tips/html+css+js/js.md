


---

undefined null 在 == 判断是都是true

---

重新声明变量不会丢失原来的值

var carname="Volvo";
var carname;

---

在函数中 var xxx = ...为局部变量, xxx = ...为全局变量

---

onclick传递参数: select(this);

js:
function select(e){
e.innerHTML = ...
e.type=....
e.value=....
e.name=...
}

jquery:
function select(e){
$(e).animate({"left":"-100%","right":"-100%"}, 1000);
}


ps: js(DOM对象)->jquery(jquery对象) $(DOM对象)

jquery(jquery对象)->js(DOM对象) var v=$v.get(0);

https://www.cnblogs.com/theWayToAce/p/5591221.html


---


