package org.abogdanov.oozie_mapreduce_action_test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Just an example of mapper that filters everything else but latin and cyrillic letters.
 *
 * Alexander Bogdanov
 * alexander.bogdanov.org@gmail.com
 * 10.09.2014
 */
public class MFilterPunctuation extends Mapper<Object, Text, Text, IntWritable> {

	private static final Text KEY 			= new Text();
	private static final IntWritable ONE 	= new IntWritable(1);

	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		StringTokenizer itr = new StringTokenizer(value.toString());

		while (itr.hasMoreTokens()) {
			String word = itr.nextToken().replaceAll("[^А-Яа-яA-Za-z]", "");
			KEY.set(word);
			context.write(KEY, ONE);
		}
	}

}
