package org.abogdanov.oozie_mapreduce_action_test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Yet another word count.
 *
 * Alexander Bogdanov
 * alexander.bogdanov.org@gmail.com
 * 10.09.2014
 */
public class RWordCount extends Reducer<Text, IntWritable, Text, IntWritable> {

	private static final IntWritable VALUE = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		VALUE.set(sum);
		context.write(key, VALUE);
	}

}
