import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogUtils {
    DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    DateFormat targetFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 日志文件解析，对内容进行字段的处理
     * 按\t分割
     */

    public String parse(String log){

        String result ="";
        try {
            String[] splits = log.split("\t");
            String cdn = splits[0];
            String region = splits[1];
            String level = splits[2];
            String timeStr = splits[3];
            String time = targetFormat.format(sourceFormat.parse(timeStr));

            String ip = splits[4];
            String domain = splits[5];
            String url = splits[6];
            String traffic = splits[7];

            StringBuilder builder = new StringBuilder("");
            builder.append(cdn).append("\t")
                    .append(region).append("\t")
                    .append(level).append("\t")
                    .append(time).append("\t")
                    .append(ip).append("\t")
                    .append(domain).append("\t")
                    .append(url).append("\t")
                    .append(traffic);

            result = builder.toString();




        }catch (Exception e){
            e.printStackTrace();
        }



        return result;
    }
}
