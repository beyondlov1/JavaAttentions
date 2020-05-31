### 判断是否包含并拼接
=IFERROR(IF(FIND("额",H560)>0,CONCATENATE(H560,"(元)"),""),H560)

FIND 方法如果找不到 会返回 #VALUE! , 所以外面包一层 IFERROR
