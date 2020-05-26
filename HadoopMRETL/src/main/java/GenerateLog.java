import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateLog {
    public static void main(String[] args){
        makeLog();
    }

    public static String makeLog(){
        try {
            File file = new File("access.log");
            if (!file.exists()){
                file.createNewFile();
            }

            for (int i=1; i<1000; i++){
                Random random = new Random();


                String[] domainStr = new String[]{
                        "a1.baidu.com",
                        "a2.baidu.com",
                        "a3.baidu.com",
                        "a4.baidu.com",
                        "a5.baidu.com"
                };

                int domainNum = random.nextInt(domainStr.length-1);

                String[] trafficStr = new String[]{
                        "12345",
                        "23523",
                        "56745",
                        "57375",
                        "67848",
                        ""
                };


                int trafficNum = random.nextInt(trafficStr.length - 1);

                StringBuilder builder = new StringBuilder();
                Date date = randomDate("2019-02-01","2020-01-02");


                builder.append("baidu").append("\t")
                        .append("CN").append("\t")
                        .append("2").append("\t")
                        .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).append("\t")
                        .append(getRandomIp()).append("\t")
                        .append(domainStr[domainNum]).append("\t")
                        .append("http://v1.go2yd.com/user_upload/1531633977627104fdecdc68fe7a2c4b96b2226fd3f4c.mp4_bd.mp4").append("\t")
                        .append(trafficStr[trafficNum]).append("\t");

                FileWriter fw = new FileWriter(file.getName(),true);
                fw.write(builder.toString()+"\n");
                fw.close();

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Date randomDate(String startDate, String endDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            if (start.getTime()>=end.getTime()){
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);


        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        return null;
    }

    public static long random(long start, long end){
        long rtn = start + (long) (Math.random()*(end-start));
        if (rtn==start || rtn==end){
            return random(start,end);
        }

        return rtn;

    }

    /**
     * random ip
     */

    public static String getRandomIp(){
        int[][] range = {
                {607649792, 608174079},
                {1038614528, 1039007743},
                {1783627776, 1784676351},
                {2035023872, 2035154943},
                {2078801920, 2079064063},
                {-1950089216, -1948778497},
                {-1425539072, -1425014785},
                {-1236271104, -1235419137},
                {-770113536, -768606209},
                {-569376768, -564133889},
        };

        Random rdint = new Random();
        int i = rdint.nextInt(10);
        String ip = num2ip(range[i][0] + new Random().nextInt(range[i][1] - range[i][0]));
        return ip;
    }

    /**
     * 十进制转换成ip
     *
     */
    public static String num2ip(int ip){
        int[] b = new int[4];
        String x = "";
        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);

        x = Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]);
        return x;

    }

}
