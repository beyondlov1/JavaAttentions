package com.beyond.mapreduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

public class Avg {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//1.设置HDFS配置信息
        String namenode_ip = "localhost";
        String hdfs = "hdfs://" + namenode_ip + ":9000";
        Configuration conf = new Configuration();	//Hadoop配置类
        conf.set("fs.defaultFS", hdfs);
        conf.set("mapreduce.app-submission.cross-platform", "true");	//集群交叉提交
/*		conf.set("hadoop.job.user", "hadoop");
		conf.set("mapreduce.framework.name", "yarn");
		conf.set("mapreduce.jobtracker.address", namenode_ip + ":9001");
		conf.set("yarn.resourcemanager.hostname", namenode_ip);
		conf.set("yarn.resourcemanager.resource-tracker.address", namenode_ip + ":8031");
		conf.set("yarn.resourcemtanager.address", namenode_ip + ":8032");
		conf.set("yarn.resourcemanager.admin.address", namenode_ip + ":8033");
		conf.set("yarn.resourcemanager.scheduler.address", namenode_ip + ":8034");
		conf.set("mapreduce.jobhistory.address", namenode_ip + ":10020"); */

        //2.设置MapReduce作业配置信息
        String jobName = "WordCount";					//定义作业名称
        Job job = Job.getInstance(conf, jobName);
        job.setJarByClass(Avg.class);			//指定作业类
        job.setMapperClass(AvgMapper.class);
        job.setCombinerClass(AvgReducer.class);		//指定Combiner类
        job.setReducerClass(AvgReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //3.设置作业输入和输出路径
        String dataDir = "/user/avg_input";		//实验数据目录
        String outputDir = "/user/output3";	//实验输出目录
        Path inPath = new Path(hdfs + dataDir);
        Path outPath = new Path(hdfs + outputDir);
        FileInputFormat.addInputPath(job, inPath);
        FileOutputFormat.setOutputPath(job, outPath);
        //如果输出目录已存在则删除
        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(outPath)) {
            fs.delete(outPath, true);
        }

        //4.运行作业
        System.out.println("Job: " + jobName + " is running...");
        if(job.waitForCompletion(true)) {
            System.out.println("success!");
            System.exit(0);
        } else {
            System.out.println("failed!");
            System.exit(1);
        }
    }


    static class AvgMapper extends Mapper<Object, Text, Text, IntWritable>{
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String text = value.toString();
            StringTokenizer lineTokenizer = new StringTokenizer(text,"\r\n");
            while (lineTokenizer.hasMoreTokens()){
                String line = lineTokenizer.nextToken();
                String[] words = StringUtils.split(line, " ");
                if (words!=null && words.length>1){
                    if (StringUtils.isNotBlank(words[1]) && StringUtils.isNumeric(words[1])){
                        context.write(new Text(words[0]), new IntWritable(Integer.parseInt(words[1])));
                    }
                }
            }
        }
    }


    static class AvgReducer extends Reducer<Text,IntWritable, Text ,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            int count = 0;
            for (IntWritable value : values) {
                int score = value.get();
                sum += score;
                count++;
            }
            int avg = sum/count;
            context.write(key,new IntWritable(avg));
        }
    }
}
