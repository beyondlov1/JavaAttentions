## 选择器
	$("")
	# . _(空)  * >  , + ~ :first :last :not(span) :even :odd :eq(index) :gt(index) :lt(index)

## 属性

	attr() prop() text() val() html()
	removeAttr() removeProp() addClass() removeClass() toggleClass()

## css

	css() position()

## 文档处理

	CRUD
	C: 内部添加 append() prepend() appendTo() prependTo() wrap() unwrap() clone()(结合添加定位)
	外部添加 after() before() insertAfter() insertBefore() 
	D: remove() empty() detach()
	U: C的方法传参为 $() , 如果文档中同级位置原来有相同的就直接移动位置, 不会创建新的
	R: 选择器

## 筛选

	筛选选中的:
	eq() first() last() hasClass() filter() is() has() not() slice() 

	选择其他的元素:
	children() closest() find() next() nextAll() nextUntil() offsetParent() parent() parents() parentsUntil() prev() prevAll() prevUtil() sliblings() 

	添加其他元素到选择中:
	add() addBack() end()

	选择所有内容, 包括文本:
	contents()

	转换数组:
	map()

## 事件

	ready() on() off() one() hover() trigger() click()

	function(event){
		event.stopPropagation();
	}

## 效果

	hide(t, fn) show(t, fn) toggle() slideUp() slideDown() slideToggle() fadeIn() fadeOut() fadeTo() fadeToggle() 

## 工具

	$.each() $.map() $.parseXml() $.trim()
