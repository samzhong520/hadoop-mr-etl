import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LogETLDriver {
    public static void main(String[] args) throws Exception{
        if (args.length!=2){
            System.err.println("");
            System.exit(0);
        }


        String input = args[0];
        String output = args[1];

        Configuration conf = new Configuration();

        FileSystem fileSystem = FileSystem.get(conf);
        Path outputPath = new Path(output);
        if (fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath,true);
        }

        Job job = Job.getInstance(conf);
        job.setJarByClass(LogETLDriver.class);
        job.setMapperClass(LogETLMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path(input));
        FileOutputFormat.setOutputPath(job,new Path(output));

        job.waitForCompletion(true);

    }
}
