

---

#### undefined null 在 == 判断是都是true

---

#### 重新声明变量不会丢失原来的值

var carname="Volvo";
var carname;

---

#### 在函数中 var xxx = ...为局部变量, xxx = ...为全局变量

---

#### onclick传递参数: select(this);

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

#### jquery  

attr(直接操作DOM上的属性, 有时候会出错)   
prop(操作DOM自带属性, 添加属性是添加jquery对象的属性, dom上看不见)

---

#### ajax 是异步的，所以要在回调函数中执行要执行的方法

---

#### 運算進度問題

https://blog.csdn.net/starscao/article/details/72828944

#### 页面自适应
/** *窗口大小改变时执行** */
$(window).resize(function() {	
	resetSizeClass();
	// 执行代码块
	myChart.resize();
	
	//自适应消息提醒
	resizeXxtx();
})

/**
 * 自适应消息提醒
 */
function resizeXxtx(){
	var width=$(".fd-xxtx-content").width();
	if(width<400){			
		$(".fd-xxtx-content .layui-table-view style").html(".laytable-cell-1-CXxlbMc{ width: 80px; }.laytable-cell-1-CNr{ width: 80px; }.laytable-cell-1-CSfydMc{ width: 60px; }.laytable-cell-1-3{ width: 70px; }");
	}else{
		$(".fd-xxtx-content .layui-table-view style").html(".laytable-cell-1-CXxlbMc{ width: 130px; }.laytable-cell-1-CNr{ width: "+(width-300)+"px; }.laytable-cell-1-CSfydMc{ width: 60px; }.laytable-cell-1-3{ width: 70px; }");
	}
}

#### IE8 不支持修改html中的style内容要$().html();

#### layui 设置下拉框的值 2.3.0
/**
 * 設置下拉框的值
 * @param id 控件id
 * @param value 值
 */
function setSelectValue(id,value){
	var select = 'dd[lay-value=' + value + ']';
	var selectedText = $('#'+id).siblings("div.layui-form-select").find('dl').find(select).text();
	$("#input_"+id).val(selectedText);
}
