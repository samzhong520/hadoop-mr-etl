import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogUtilsTest {

    private LogUtils utils;

    @Test
    public void LogUtilTest(){
        String log = "baidu\tCN\t2\t2019-01-10 16:02:54\t121.77.143.199\tv2.go2yd.com\thttp://v3.go2yd.com/user_upload/1531633977627104fdecdc68fe7a2c4b96b2226fd3f4c.mp4_bd.mp4\t97557845\n";
        String result = utils.parse(log);
        System.out.println(result);

    }

    @Before
    public void setup(){
        utils = new LogUtils();
    }

    @After
    public void release(){
        utils = null;
    }

}
