import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapred.*;

public class logDriver {
	
	public static class LogMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	    private final static IntWritable one = new IntWritable(1);
	    private Text word = new Text();

	    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter rep)
	            throws IOException {
	        String line = value.toString();
	        for (String token : line.split(" ")) {
	            if (token.startsWith("Error:") || token.startsWith("Info:") || token.startsWith("Warning:")) {
	                word.set(token);
	                output.collect(word, one);
	            }
	        }
	    }
	}
	
	public static class LogReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	  
	    public void reduce(Text key, Iterator<IntWritable> value,
	            OutputCollector<Text, IntWritable> output,
	            Reporter rep) throws IOException {
	        int sum = 0;
	        while (value.hasNext()) {
	            sum += value.next().get();
	        }
	        
	        output.collect(key, new IntWritable(sum));
	    }
	}

	// Main Method
	public static void main(String args[]) throws Exception
	{
		if (args.length < 2)
		{
			System.out.println("Please give valid inputs");
			return;
		}

		JobConf conf = new JobConf(logDriver.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setMapperClass(LogMapper.class);
		conf.setReducerClass(LogReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		JobClient.runJob(conf);
	}
}

