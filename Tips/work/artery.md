#### js列表中传递参数

```
/**
 * onClickClient(jqButton05eac)
 * 
 * @param  rc 系统提供的AJAX调用对象
 * @param  {rs1.id}
 */
function jqButton05eac_onClickClient(rc, id) {
	Artery.confirmMsg("confirm", "確認要刪除嗎?", function(btn) {
		if (btn == "yes") {
			rc.put("user.id", id); //增加一个参数

			//发送请求，在回调函数中处理返回结果
			rc.send(function(result) {
				alert(result);
			});
		}
	})
}
```

#### 向form表单中传入参数

采用的是artery的通用js, 在web的artery文件夹中, 检测url来获取参数

