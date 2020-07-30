import com.alibaba.fastjson.JSONObject;
import com.liugs.tool.constants.Console;
import com.liugs.tool.constants.ToolConstants;
import com.liugs.tool.constants.ToolException;
import com.liugs.tool.utils.encode.EncodeExecuter;
import com.liugs.tool.utils.encode.RsaEncodeTool;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName ToolTest
 * @Description 工具测试类
 * @Author liugs
 * @Date 2020/7/9 9:38:22
 */
public class ToolTest {

    public static void main(String[] args) {
        /*try {
            enCode();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*dealNum();*/

//        dealListSort();

//        ToolExceptionTest();

    }

    /**
     * 描述 list排序
     * @author liugs
     * @date 2020/7/29 10:51:04
     */
    private static void dealListSort() {
        DateTime date = new DateTime();
        Console.show(date);

        ListDemo demo1 = new ListDemo();
        demo1.setDateTime(date.toDate());
        demo1.setIndex(1);
        ListDemo demo2 = new ListDemo();
        demo2.setDateTime(date.plusDays(1).toDate());
        demo2.setIndex(2);
        ListDemo demo3 = new ListDemo();
        demo3.setDateTime(date.plusHours(2).toDate());
        demo3.setIndex(3);

        List<ListDemo> listDemos = new ArrayList<>();
        listDemos.add(demo1);
        listDemos.add(demo2);
        listDemos.add(demo3);

        Console.show("==========before sort=========");
        for (ListDemo demo : listDemos) {
            Console.show("index：" + demo.getIndex() + ", dateTime：" + new DateTime(demo.getDateTime()).toString("yyyy年MM月dd日HH时mm分ss秒"));
        }

        Collections.sort(listDemos, (o1, o2) -> {
            if (o1.getDateTime().before(o2.getDateTime())) {
                return 1;
            } else if (o1.getDateTime().equals(o2.getDateTime())) {
                return 0;
            }
            return -1;
        });

        Console.show("==========after sort=========");
       for (ListDemo demo : listDemos) {
           Console.show("index：" + demo.getIndex() + ", dateTime：" + new DateTime(demo.getDateTime()).toString("yyyy年MM月dd日HH时mm分ss秒"));
       }
    }

    /**
     * list 排序demo
     */
    private static class ListDemo {
        public Date dateTime;
        public Integer index;

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }

    /**
     * 描述 测试异常类
     * @author liugs
     * @date 2020/7/16 11:02:49
     */
    private static void ToolExceptionTest() {
        throw new ToolException("1000001", "错误");
    }

    /**
     * 描述 测试RSA加密
     * @author liugs
     * @date 2020/7/9 9:39:26
     */
    private static void enCode() throws Exception {
        Map<String, String> keyMap = RsaEncodeTool.generateRSAKeys();

        String publicKey = keyMap.get(ToolConstants.EncodeConstants.PUBLIC_KEY);
        String privateKey = keyMap.get(ToolConstants.EncodeConstants.PRIVATE_KEY);
        console("publicKey：" + publicKey);
        console("privateKey：" + privateKey);
        //获取一个签名Key
        String signKey = RsaEncodeTool.getRandomStringByLength(32);
        //待加密对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "tester");
        jsonObject.put("age", "100");
        jsonObject.put("sex", "man");
        String resourceStr = jsonObject.toJSONString();

        console("==================公钥加密，私钥解密==================");
        //公钥加密串
        String encodeStr = EncodeExecuter.publicEncode(publicKey, resourceStr, signKey);
        console("encodeStr：" + encodeStr);
        //私钥解密
        String decodeStr = EncodeExecuter.privateDecode(privateKey, encodeStr);
        console("decodeStr：" + decodeStr);

        console("==================私钥加密，公钥解密==================");
        //私钥加密
        String encodeStrByPriKey = EncodeExecuter.privateEncode(privateKey, resourceStr, signKey);
        console("encodeStrByPriKey：" + encodeStrByPriKey);
        //公钥解密
        String decodeStrByPubKey = EncodeExecuter.publicDeCode(publicKey, encodeStrByPriKey);
        console("decodeStrByPubKey：" + decodeStrByPubKey);

        //校验签名
        console(EncodeExecuter.validateSign(decodeStr, signKey));
    }

    /**
     * 描述 金额单位转换，及保留位数
     * @return void
     * @author liugs
     * @date 2020/7/10 9:36:14
     */
    private static void dealNum() {
        BigDecimal yuan = new BigDecimal(1);
        BigDecimal wan = yuan.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_DOWN);
        BigDecimal fen = yuan.multiply(new BigDecimal(100)).divide(BigDecimal.ONE, 2, BigDecimal.ROUND_DOWN);
        console("元：" + yuan);
        console("万：" + wan);
        console("分：" +fen);
    }

    /**
     * 描述 打印
     * @param obj
     * @return void
     * @author liugs
     * @date 2020/7/9 10:31:10
     */
    private static void console(Object obj) {
        System.out.println(obj);
    }
}
