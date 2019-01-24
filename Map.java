import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Map extends Mapper<Text, Text, Text, IntWritable> {
    private final static IntWritable posOne = new IntWritable(1);
    private final static IntWritable negOne = new IntWritable(-1);

    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        // load the pre-compiled classifier (accuracy = 66,8%)
        TweetClassifier tc = new TweetClassifier();

        // tweets
        String tweetFile = value.toString();
        String[] tweets = tweetFile.split("\\n");

        // keywords
        String keyFile = key.toString();
        String[] keys = keyFile.split("\\n");

        // keys and tweets have the same length
        for(int i = 0; i < keys.length; i++){
            String evaluation = tc.evaluate(tweets[i]); // this evaluation can be "pos" or "neg"
            if(evaluation.equals("neg")){
                context.write(new Text(keys[i]), negOne);
            }else{
                context.write(new Text(keys[i]), posOne);
            }
        }
    }

}
