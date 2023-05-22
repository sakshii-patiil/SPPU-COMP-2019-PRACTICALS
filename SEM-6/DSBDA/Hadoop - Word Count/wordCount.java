package tc175_wordCount;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapred.*;

public class wordCount {
	
	public static class wc_mapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,IntWritable>
	{
		private static final IntWritable one = new IntWritable(1);
		private Text word = new Text();
		@Override
		public void map(LongWritable arg0, Text arg1, OutputCollector<Text, IntWritable> arg2, Reporter arg3)
				throws IOException {
			// TODO Auto-generated method stub
			
			String line =  arg1.toString();
			StringTokenizer token = new StringTokenizer(line);
			
			while(token.hasMoreTokens())
			{
				word.set(token.nextToken());
				arg2.collect(word, one);
			}
			
		}
		
	}
	
	public static class wc_reducer extends MapReduceBase implements Reducer<Text,IntWritable,Text,IntWritable>
	{

		@Override
		public void reduce(Text arg0, Iterator<IntWritable> arg1, OutputCollector<Text, IntWritable> arg2,
				Reporter arg3) throws IOException {
			// TODO Auto-generated method stub
			
			int sum = 0;
			
			while(arg1.hasNext())
			{
				sum+= arg1.next().get();
			}
			arg2.collect(arg0, new IntWritable(sum));
		}
		
	}
	
	public static void main(String[] args) throws IOException{
        JobConf conf = new JobConf(wordCount.class);
        conf.setJobName("WordCount");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(wc_mapper.class);
        conf.setCombinerClass(wc_reducer.class);
        conf.setReducerClass(wc_reducer.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));
        JobClient.runJob(conf);
    }

}
