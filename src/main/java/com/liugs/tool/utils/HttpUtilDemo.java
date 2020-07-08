package com.liugs.tool.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.HttpMethod;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @ClassName MyselfTest
 * @Description 测试
 * @Author liugs
 * @Date 2019/10/18 11:32:30
 * @Copyright 2019 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class HttpUtilDemo {
    private static final int SUCCESS = 200;

    public static void main(String[] args) {
//        String path = "http://localhost:8081/mmc/qryDictionary";
//        JSONObject msgObject = new JSONObject();
//        msgObject.put("type", "SHOP_INFO_CHANNEL");
//        String msg = msgObject.toJSONString();
//
//        Map<String, String> paraMap = new HashMap<>(16);
//        paraMap.put("type", "SHOP_INFO_CHANNEL");

        //java post Test
//        httpPostTest(path, msg);
        //java get Test
//        httpGet(path, msg);

        //apache  httpClient4.5
//        httpClientPost(path, paraMap, msg);
        testEnum();

    }

    /**
     * java原生的方式 POST
     */
    private static void httpPostTest(String path, String msg) {
        System.out.println("------------------HTTP-POST测试---------------");

        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        HttpURLConnection connection;
        try {
            URL url = new URL(path);
            //通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            //设置连接请求方式
            connection.setRequestMethod(HttpMethod.POST);
            //设置连接超时时间 15000 毫秒
            connection.setConnectTimeout(15000);
            //设置读取服务器相应的超时时间 60000 毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/json");

            /*// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            httpURLConnection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");*/

            // 通过连接对象获取一个输出流
            outputStream = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            outputStream.write(msg.getBytes(StandardCharsets.UTF_8));

            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == SUCCESS) {
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                StringBuilder stringBuffer = new StringBuilder();
                String temp;
                // 循环遍历一行一行读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuffer.append(temp);
                    stringBuffer.append("\r\n");
                }
                result = stringBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("返回的结果为：" + result);
    }

    /**
     * java原生 GET
     */
    private static void httpGet(String path, String msg) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String result = null;

        try {
            //创建 url连接对象
            URL url = new URL(path);
            //通过远程URL对象建立连接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置连接方式为 get
            httpURLConnection.setRequestMethod(HttpMethod.GET);
            //设置连接超时时间为 15000 mm
            httpURLConnection.setConnectTimeout(15000);
            //设置读取远程返回数据的超时时间为 60000mm
            httpURLConnection.setReadTimeout(60000);

            //发送请求
            httpURLConnection.connect();

            //通过connection连接 获取输入流
            if (httpURLConnection.getResponseCode() == SUCCESS) {
                inputStream = httpURLConnection.getInputStream();
                //封装输入流 并指点编码格式
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                //存放数据
                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                    stringBuilder.append("\r\n");
                }
                result = stringBuilder.toString();
            }
            System.out.println("result:" + result);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * apache  httpClient4.5 post
     */
    private static void httpClientPost(String path, Map<String, String> paramMap, String msg) {
        CloseableHttpClient httpClient;
        CloseableHttpResponse httpResponse = null;
        String result = "";

        //创建httpClient实例
        httpClient = HttpClients.createDefault();
        //创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(path);

        //配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom()
                /*设置连接服务器超时时间*/
                .setConnectTimeout(35000)
                /*设置连接请求超时时间*/
                .setConnectionRequestTimeout(35000)
                /*设置读取数据超时时间*/
                .setSocketTimeout(60000)
                .build();
        //为httpPost实例设置配置
        httpPost.setConfig(requestConfig);

        //FORM
        //封装POST请求参数
        /*if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            for (Map.Entry<String, String> mapEntry : entrySet) {
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue()));
            }

            //为httpPost设置封装好的请求参数
            try {
                //FORM
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        //JSON
        try {
            httpPost.setEntity(new StringEntity(msg));
            httpPost.addHeader("Content-Type", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("result:" + result);;
    }

    /**
     * apache httClient4.5 get
     */
    private static void httpClientGet(String path) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(path);
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(result);
    }

    /**------------------------------------------MD5加密 + 转换大写------------------------------------------------*/
    private static MessageDigest mdInst = null;
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static MessageDigest getMdInst() {
        if (mdInst == null) {
            try {
                mdInst = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mdInst;
    }

    private static String encode(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 使用指定的字节更新摘要
            getMdInst().update(btInput);
            // 获得密文
            byte[] md = getMdInst().digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void testEnum() {
        TestEnumMap testEnumMap = TestEnumMap.getTestEnumMap(2);
        assert testEnumMap != null;
        System.out.println("code:" + testEnumMap.getCode());
        System.out.println("value:" + testEnumMap.getValue());

    }

    public enum TestEnumMap {

        /**测试*/
        A(1, "ces"),

        /**ces*/
        B(2, "提【澳门");

        private int code;
        private String value;

        TestEnumMap(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }
        public String getValue() {
            return value;
        }

        public static TestEnumMap getTestEnumMap(int code) {
            for (TestEnumMap testEnumMap : TestEnumMap.values()) {
                if (testEnumMap.code == code) {
                    return testEnumMap;
                }
            }
            return null;
        }
    }

}
