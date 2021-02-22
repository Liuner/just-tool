import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.liugs.tool.constants.Console;

/**
 * @ClassName ZNotifyCenterTest
 * @Description 通知中心测试
 * @Author liugs
 * @Date 2021/1/13 10:37:58
 */
public class ZNotifyCenterTest {

    public static void main(String[] args) {
        sendEmail();
        dingdangMessage();
    }

    private static void sendEmail() {
        String url = "http://101.200.57.239:8081/notifyApi/sendEmail";
        url = "http://39.98.46.250:9005/notifyApi/sendEmail";
//        url = "http://localhost:8091/notifyApi/sendEmail";

        JSONObject reqJson = new JSONObject();
        reqJson.put("to", "lgschn@qq.com");
        reqJson.put("content", "你好！邮件测试！");
        reqJson.put("subject", "测试邮件");

        String result = HttpUtil.post(url, reqJson.toJSONString());
        Console.show(result);
    }

    private static void dingdangMessage() {

        //本机地址
        String url = "http://localhost:8091/notifyApi/sendDingDangMessage";
        //叮当开发环境
        url = "http://39.98.241.232:9015/notifyApi/sendDingDangMessage";

        JSONObject reqJson = new JSONObject();
        reqJson.put("phoneNumbers", "18523310756");
        reqJson.put("templateCode", "SMS_209162326");

        JSONObject paramJson = new JSONObject();
        paramJson.put("code", "23432423");

        reqJson.put("templateParam", paramJson.toJSONString());

        Console.show(reqJson);

        String result = HttpUtil.post(url, reqJson.toJSONString());

        Console.show(result);
    }
}
