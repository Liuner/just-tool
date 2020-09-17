import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liugs.tool.constants.Console;
import com.liugs.tool.constants.ToolConstants;
import com.liugs.tool.constants.ToolException;
import com.liugs.tool.utils.encode.EncodeExecuter;
import com.liugs.tool.utils.encode.RsaEncodeTool;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

//        sort();

        test();

//        listToMap();

//        listPage();

    }



    private static void test() {
        /*String url = "http://172.16.100.36:18000/upload/cms/column/10001_banner/index.html";
        String result = HttpUtil.get(url);
        System.out.println("result:" + result);
        JSONObject jsonObject = JSON.parseObject(result);

        JSONArray rows = (JSONArray) jsonObject.get("rows");
        System.out.println(rows);*/

//        Set<String> keySet = ToolPropertiesKey.getKeySet();
//        System.out.println(JSON.toJSONString(keySet));

        /*String testStr = "key-value&name-liugs&age-24&";
        String enCodeStr = null;
        try {
            enCodeStr = URLEncoder.encode(testStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String deCodeStr = null;
        try {
            deCodeStr = URLDecoder.decode(enCodeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] strs = deCodeStr.split("&");
        Map<String, String> map = new HashMap<>(16);
        for (String s : strs) {
            String[] str = s.split("-");
            map.put(str[0], str[1]);
        }

        Console.show(JSON.toJSONString(map));*/

        String dateStr = "2020-09-15 16:17:22";
        DateTime tradeTime = DateTime.parse(dateStr, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Console.show(tradeTime.toString("yyyyHHdd"));
        Console.show(tradeTime.toString("HHmmss"));


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

        /*Collections.sort(listDemos, new Comparator<ListDemo>() {
            @Override
            public int compare(ListDemo o1, ListDemo o2) {
                if (o1.getDateTime().before(o2.getDateTime())) {
                    return 1;
                } else if (o1.getDateTime().equals(o2.getDateTime())) {
                    return 0;
                }
                return -1;
            }
        });*/

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


    public static void sort() {
     /*   List<String> strs = new ArrayList<>();
        char[] chars = {'a', 'b', 'c', 'd'};
        char[] target = new char[4];
        for (int i = 0; i < 4; i ++) {
            char first = chars[i];
            for (int j = 0; j < 4; j ++) {
                char second = chars[j];
                if (first == second) {
                    continue;
                }
                for (int k = 0; k < 4; k ++) {
                    char third = chars[k];
                    if (first == third || second == third) {
                        continue;
                    }
                    for (int l = 0; l < 4; l ++) {
                        char fouth = chars[l];
                        if (first == fouth || second == fouth || third == fouth) {
                            continue;
                        }
                        target[0]=first;
                        target[1]=second;
                        target[2]=third;
                        target[3]=fouth;
                        Console.show(new String(target));
                    }
                }
            }
        }*/
    }

    private static void listToMap() {
        //将列表转为Map<key=name, value = Demo>
        List<Demo> list = new ArrayList<>();
        Demo demo = new Demo();
        demo.setName("ceshi");
        list.add(demo);

        Demo demo2 = new Demo();
        demo2.setName("ceshi2");
        list.add(demo2);

        //list 转 map
        Map<String, Demo> relMap = list.stream().collect(Collectors.toMap(Demo::getName, retRelPo -> retRelPo));

        //遍历map
        Iterator iterator = relMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Console.show(JSON.toJSONString(iterator.next()));
        }
        for (String demo1 : relMap.keySet()) {
            Console.show(relMap.get(demo1).getName());
        }
    }

    private static void listPage() {
        int pageNo = 2;
        int pageSize = 1;

        List<Demo> demos = new ArrayList<>();
        Demo demo1 = new Demo();
        demo1.setName("demo1");
        demos.add(demo1);

        Demo demo2 = new Demo();
        demo2.setName("demo2");
        demos.add(demo2);

        List<Demo> retList = new ArrayList<>();

        //获取指针 (如果第一页，就从0开始，如果是其他页码，就用 当前码*页面大小)
        int index = ( pageNo > 1 ? (pageNo - 1) * pageSize : 0);

        for (int i = 0; i < pageSize && i < demos.size() - index; i++) {
            Demo dataBo = demos.get(index + i);
            retList.add(dataBo);
        }

        int total = demos.size() % pageSize == 0 ? demos.size()/pageSize : demos.size()/pageSize +1;
        int recodeTotal = demos.size();

        Console.show("total:" + total);
        Console.show("recodeTotal:" + recodeTotal);
        Console.show("pageNo:" + pageNo);
        Console.show("pageSize:" + pageSize);
        Console.show("retList:" + JSON.toJSONString(retList));


    }

    private static class Demo {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
