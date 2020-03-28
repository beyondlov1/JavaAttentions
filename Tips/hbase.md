### hbase web 默认端口
16010

### hbase 批量导入
put(list)
底层原理可能是bulkload
如果spark导入时oom, 可能是每次list中元素太多, 可以每2000条put一次, 然后把集合clear一下