import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Driver extends Configured implements Tool{
    public int run(String[] args) throws Exception{
        Configuration conf = getConf();

        Job job = Job.getInstance(conf, "TweetsSecondJob");
        job.setJarByClass(Driver.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class); // added to work with Text as Input Key
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.out.println("Input dir: " + "/home/mauroliuzzo/IdeaProjects/Tweets-Second-Job/input/");
        System.out.println("Output dir: " + "/home/mauroliuzzo/IdeaProjects/Tweets-Second-Job/output/");
        FileInputFormat.addInputPath(job, new Path("/home/mauroliuzzo/IdeaProjects/Tweets-Second-Job/input/"));
        FileOutputFormat.setOutputPath(job, new Path("/home/mauroliuzzo/IdeaProjects/Tweets-Second-Job/output/"));
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Driver(), args);
        System.exit(res);
    }
}
