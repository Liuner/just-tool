import com.alibaba.fastjson.JSONObject;
import com.liugs.tool.constants.ToolConstants;
import com.liugs.tool.utils.encode.EncodeExecuter;
import com.liugs.tool.utils.encode.RsaEncodeTool;

import java.util.Map;

/**
 * @ClassName ToolTest
 * @Description 工具测试类
 * @Author liugs
 * @Date 2020/7/9 9:38:22
 * @Copyright 2020 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class ToolTest {

    public static void main(String[] args) {
        try {
            enCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
