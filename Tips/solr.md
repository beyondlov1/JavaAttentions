### ����core
solr create -c <name>

### �ϴ��ĵ�
windows:
java -Dauto=yes -Dc=core_test2 -jar example\exampledocs\post.jar example\exampledocs\*.xml
�ο��� https://www.cnblogs.com/shaosks/p/7390523.html

### field/fieldType ... 
�ο���https://blog.csdn.net/supermao1013/article/details/83628344

### df
schema�п�������Ĭ�ϵĲ�ѯ�ֶ�, ��ᵼ��ָ����qfȴ�������documnet
����, ���qfʧЧ��, �����ȿ�һ���ǲ���������df(solrconfig.xml)
�ο�: https://lucene.apache.org/solr/guide/6_6/the-standard-query-parser.html#TheStandardQueryParser-StandardQueryParserParameters

### �������ֶν��бȽϵ�ɸѡ����



q=+"����"&fq={!frange l=0}sub(stock_available,mul(drug_id,0.5))&fq=provider_id:781&fq=wholesale_type:9&qf=drugname_boolean^2&qf=drugname_pinyin_text&qf=druginfo_cn_name_boolean^4&qf=druginfo_common_name_boolean^4&qf=drugname_text&qf=druginfo_cn_name_text&qf=approval&sort=is_recommend desc,order asc&start=0&rows=10

https://blog.csdn.net/jiangchao858/article/details/53844584

http://lucene.apache.org/solr/guide/7_2/function-queries.html#available-functions

http://lucene.apache.org/solr/guide/7_2/other-parsers.html#function-query-parser





### dataimport��Ҫ��������

  <lib dir="${solr.install.dir:../../../..}/dist/" regex="solr-dataimporthandler-.*\.jar" />

�� mysql driver����

https://www.jianshu.com/p/4e9eecd3dce4


�����ö������Դ

����entity���Խ��й���, ��������ΪǶ��: �ڵ���solrʱ, �����entity�����Ժ͸�entity�����Խ��кϲ�, �ظ����������ƻḲ��.

��������ʱ, ֻ�и�entity�ı��˲Ż������һ������.

�޸�ʱ��--����������ʱ��Ҫ�ж�ʱ���, ����Ĭ����ʹ��UTC ʱ��, ��ʱ������
��Ҫ�� solr.in.sh ���� solr.in.cmd ������  set SOLR_TIMEZONE=UTC+8  (ǰ�ߵ�REMҪȥ��)
����ο�: https://blog.csdn.net/weixin_42170236/article/details/102834886

dataimport ȡ��:

http://192.168.0.42:8983/solr/erp_order/dataimport?command=status

### reRank

���Զ�reRankQueryѡ���Ľ��з������¼���, ����ʵ�����Ƶ�����Ч��

q=+"999"&qf=drugname_text&fl=id,score,drugname_text&rq={!rerank reRankQuery=drugname_text:��ð reRankDocs=1000 reRankWeight=3}