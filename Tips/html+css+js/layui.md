### layui table自适应(手动解决方案)
CSS:
.fd-xxtx-content div[id^="layui-table-page"]{
	text-align: right;
}

.fd-xxtx-content .layui-table{
	width:100%;
}

.fd-xxtx-content .layui-table-header th div{
	margin:0 auto;
}

JS:
/** *窗口大小改变时执行** */
$(window).resize(function() {	
	resizeXxtx();
})

function resizeXxtx(){
	var width=$(".fd-xxtx-content").width();
	if(width<400){	
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CXxlbMc']").attr("style","width: 70px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CNr']").attr("style","width: 80px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CSfydMc']").attr("style","width: 50px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='-3']").attr("style","width: 60px;");
	}else{
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CXxlbMc']").attr("style","width: 120px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CNr']").attr("style","width: "+(width-300)+"px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='CSfydMc']").attr("style","width: 60px;");
		$(".fd-xxtx-content .layui-table-view .layui-table-box div[class$='-3']").attr("style","width: 70px;");
	}
}


### layui table 设置分页
table.reload({
		url : basePath + '/message/query',
		method : 'post',
		loading : true,
		contentType : 'application/json',
		where : DailyMsgQueryParams,
		skin : 'line',
		page: {
			layout: ['prev', 'page', 'next', 'count'],  // 重点
			curr : dailyMsgCurrPage,
			groups: 1
		},
		response : {
			statusCode : 200
		},
        done:function(){
        	dailyMsgCurrPage = $(".fd-xxtx-content .layui-laypage-em").next().html(); //当前页码值
        	resizeXxtx();
        }
	});
}


### layui table 刷新当前页
table.reload({
		url : basePath + '/message/query',
		method : 'post',
		loading : true,
		contentType : 'application/json',
		where : DailyMsgQueryParams,
		skin : 'line',
		page: {
			layout: ['prev', 'page', 'next', 'count'],
			curr : dailyMsgCurrPage,
			groups: 1
		},
		response : {
			statusCode : 200
		},
        done:function(){
        	dailyMsgCurrPage = $(".fd-xxtx-content .layui-laypage-em").next().html(); //当前页码值
        	resizeXxtx();
        }
	});
}


### table 渲染模板
/**
	 * 渲染列表
	 */
	table = LayuiTable.render({
		elem : '#daily_message_list',
		cols : [ dailyMsgcols ],
		height : '305',
		even : false,
		page : true,
		loading : true,
		cellMinWidth: 50,
		groups : 5,
		limit : 5,
		limits : [ 5, 20, 50, 100, 150 ],
		id: 'daily_message_list'
	});
	table.reload({
		url : basePath + '/message/query',
		method : 'post',
		loading : true,
		contentType : 'application/json',
		where : DailyMsgQueryParams,
		skin : 'line',
		page: {
			layout: ['prev', 'page', 'next', 'count'],
			curr : dailyMsgCurrPage,
			groups: 1
		},
		response : {
			statusCode : 200
		},
        done:function(){
        	dailyMsgCurrPage = $(".fd-xxtx-content .layui-laypage-em").next().html(); //当前页码值
        	resizeXxtx();
        }
	});
  
  
  
  function initMessageCols() {
	dailyMsgcols.push({
		field : 'CXxlbMc',
		align : 'center',
		style : 'color:red;',
		title : '<span style="color: #3e47ff;">类别</span>',
		width : 130,
		templet : function(d) {
			return '【' + d.CXxlbMc + '】';
		}
	});
	dailyMsgcols.push({
		field : 'CNr',
		align : 'center',
		minWidth: 80,
		title : '<span style="color: #3e47ff;">提醒内容</span>',
		templet : function(d) {
			return d.CNr+'&nbsp;'+'<a href="'+queryUrl+'/query/index.html?page='+d.linkPage
			+'&key=message_link&type=message_link'+'&messageId='+d.CId+'&corp='+d.corpId+'&messageType='+d.CXxlb
			+'" target="_blank" style="color: #3e47ff;font-weight:bold;">'+ d.NXxtxRs +'</a>&nbsp;人';
		}
	});
	dailyMsgcols.push({
		field : 'CSfydMc',
		align : 'center',
		title : '<span style="color: #3e47ff;">状态</span>',
		width: 60,
		templet : function(d) {
			if (d.CSfyd == '0') {
				return '<span class="daily-msg-table-sfyd-unread">' + d.CSfydMc
						+ '</span>';
			} else {
				return '<span class="daily-msg-table-sfyd-read">' + d.CSfydMc
						+ '</span>';
			}
		}
	});
	dailyMsgcols.push({
		title : '<span style="color: #3e47ff;">操作</span>',
		align : 'center',
		width: 70,
		toolbar : '#daily_msg_table_btn'
	});
}


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
