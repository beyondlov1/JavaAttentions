#### 多个excel文件中的页签复制到一个excel中
代码如下：
```
Sub 汇总数据()
Application.ScreenUpdating = False
Dim wb, wb1 As Excel.Workbook
Dim sh As Excel.Worksheet
s = Split(ThisWorkbook.Name, ".")(1)
f = Dir(ThisWorkbook.Path & "\*" & "*.xls") '生成查找EXCEL的目录
Do While f <> "" '在目录中循环
on error resume next '忽略报错
If f <> ThisWorkbook.Name Then  '如果不是打开的工作簿
Set wb = Workbooks.Open(ThisWorkbook.Path & "\" & f)
wb.Worksheets("COL").Copy after:=ThisWorkbook.Worksheets(ThisWorkbook.Worksheets.Count)
ActiveSheet.Name = wb.Name
wb.Close
End If
f = Dir
Loop
ThisWorkbook.Worksheets("汇总").Activate
Application.ScreenUpdating = True
End Sub
```
启动可更改的地方： ActiveSheet.Name = wb.Name 代表页签名。可用函数： Split(wb.Name, ".")(0)

#### 其他常用脚本

时不时就有同学在问，一个工作簿中每天一份报表，一个月下来30份报表需要汇总成一张表，复制粘贴来的比较慢，还有的是有很多个格式一样的表位于不同的工作簿中，需要合并到一个工作表里，等等……你可以到本公众号后台回复excel扩展，去下载小工具，里面有多表合并功能，也可以利用数据查询功能合并。今天我们来讲讲利用VBA实现多表合并的技巧，大家可以把代码收藏好，使用的时候非常的方便。1工作簿内多个sheet合并到一个sheet<img src="https://pic4.zhimg.com/v2-839b3f7ede30f9e6ec4c5746ddfeff93_b.gif" data-caption="" data-size="normal" data-rawwidth="452" data-rawheight="383" data-thumbnail="https://pic4.zhimg.com/v2-839b3f7ede30f9e6ec4c5746ddfeff93_b.jpg" class="origin_image zh-lightbox-thumb" width="452" data-original="https://pic4.zhimg.com/v2-839b3f7ede30f9e6ec4c5746ddfeff93_r.jpg">上边动图中有1、2、3、4，4个sheet，分别是不同部门的人员信息，需要合并到汇总sheet里。步骤：右键点击汇总sheet表名，查看代码，把代码复制进去，点击运行，很快就可以看到合并后的结果了。代码如下：Sub 合并当前工作簿下的所有工作表()Application.ScreenUpdating = FalseFor j = 1 To Sheets.Count  If Sheets(j).Name <> ActiveSheet.Name Then      X = Range("A65536").End(xlUp).Row + 1      Sheets(j).UsedRange.Copy Cells(X, 1)  End IfNextRange("B1").SelectApplication.ScreenUpdating = TrueMsgBox "当前工作簿下的全部工作表已经合并完毕！", vbInformation, "提示"End Sub2多个工作簿中的sheet合并到一个sheet<img src="https://pic2.zhimg.com/v2-3c40829a650f96ab57be6975d78f8601_b.jpg" data-caption="" data-size="normal" class="content_image">大家仔细观察，工作簿1中有两个sheet，合并的时候都会合并进去。代码如下：Sub 合并当前目录下所有工作簿的全部工作表()Dim MyPath, MyName, AWbNameDim Wb As Workbook, WbN As StringDim G As LongDim Num As LongDim BOX As StringApplication.ScreenUpdating = FalseMyPath = ActiveWorkbook.PathMyName = Dir(MyPath & "\" & "*.xlsx")AWbName = ActiveWorkbook.NameNum = 0Do While MyName <> ""If MyName <> AWbName ThenSet Wb = Workbooks.Open(MyPath & "\" & MyName)Num = Num + 1With Workbooks(1).ActiveSheet.Cells(.Range("B65536").End(xlUp).Row + 2, 1) = Left(MyName, Len(MyName) - 4)For G = 1 To Sheets.CountWb.Sheets(G).UsedRange.Copy .Cells(.Range("B65536").End(xlUp).Row + 1, 1)NextWbN = WbN & Chr(13) & Wb.NameWb.Close FalseEnd WithEnd IfMyName = DirLoopRange("B1").SelectApplication.ScreenUpdating = TrueMsgBox "共合并了" & Num & "个工作薄下的全部工作表。如下：" & Chr(13) & WbN, vbInformation, "提示"End Sub注意代码红色字体部分，根据自己的版本更改。3多个工作簿中的sheet1合并到新的工作簿中<img src="https://pic3.zhimg.com/v2-a051eb5c0520aa137b04973f8c0c7cd2_b.gif" data-caption="" data-size="normal" data-rawwidth="452" data-rawheight="383" data-thumbnail="https://pic3.zhimg.com/v2-a051eb5c0520aa137b04973f8c0c7cd2_b.jpg" class="origin_image zh-lightbox-thumb" width="452" data-original="https://pic3.zhimg.com/v2-a051eb5c0520aa137b04973f8c0c7cd2_r.jpg">多个工作簿中的表合并到一个工作簿中，不进行汇总，只是放到一个工作簿，保留原来的表名。代码如下：Sub 汇总数据()Application.ScreenUpdating = FalseDim wb, wb1 As Excel.WorkbookDim sh As Excel.Worksheets = Split(ThisWorkbook.Name, ".")(1)f = Dir(ThisWorkbook.Path & "\*" & s) '生成查找EXCEL的目录Do While f <> "" '在目录中循环If f <> ThisWorkbook.Name Then  '如果不是打开的工作簿Set wb = Workbooks.Open(ThisWorkbook.Path & "\" & f)wb.Worksheets("sheet1").Copy after:=ThisWorkbook.Worksheets(ThisWorkbook.Worksheets.Count)ActiveSheet.Name = Split(wb.Name, ".")(0)   wb.Close   End If   f = Dir   LoopThisWorkbook.Worksheets("汇总").Activate   Application.ScreenUpdating = TrueEnd Sub三种情况下的合并全在此了，不需要懂得VBA，只要复制上面代码运行下就OK了，方便吧！后台回复下列红色关键词可以获取相关资源：1、office365, office 365, 365，可以获取office365版本2、excel学习, Excel学习，可以获取相关的学习资料3、VBA学习，可以获取相关的学习资料4、excel扩展，可以获取excel扩展工具，能让excel更加高效方便的工作5、百度云破解限速，可以获取相关软件6、PPT模板, ppt模板，可以获取相关资源7、快查手册，可以获取函数快查手册

地址： https://www.zhihu.com/question/20366713 搜索：武旭鹏
