import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogETLMapper extends Mapper<LongWritable,Text,NullWritable,Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        int length = value.toString().split("\t").length;
        String traffic = value.toString().split("\t")[7];
        if (length==8 && traffic!=null){
            LogUtils logUtils = new LogUtils();
            String result = logUtils.parse(value.toString());
            if (org.apache.commons.lang.StringUtils.isNotBlank(result)){
                context.write(NullWritable.get(),new Text(result));
            }


        }

    }
}
