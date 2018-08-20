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

