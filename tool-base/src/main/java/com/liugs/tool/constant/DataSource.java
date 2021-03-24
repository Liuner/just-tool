package com.liugs.tool.constant;


import com.alibaba.druid.filter.config.ConfigTools;
import com.liugs.tool.base.Console;

/**
 * @ClassName DataSource
 * @Description
 * @Author liugs
 * @Date 2020/8/4 9:57:16
 */
public class DataSource {

    private static String password;
    private static String publicKey;
    private static String privateKey;

    public static void main(String[] args) {
        encryptDbPassword();

        Console.show("公钥解密======================");
//        publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALjpdmH4PUA0oqwOMTsPH3WYarhCTqCACczRQ7TdVJE8b8Nx5uBooZgG0gjxBk/KKJbh8HmIvA4R2Zmv+jfij2ECAwEAAQ==";
//        password = "Rjmir7Ga5URt0Jtpg2aLdsSFFzdalLXewGj6XOIGeGNx8gjQEic0YGmU26mQppabgCp0NGaorfQlepw+zhLopg==";
        decryptDbPassword(publicKey, password);
    }

    private static void encryptDbPassword() {
        password = "123456";

        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            Console.show("私钥：" + privateKey);
            Console.show("公钥：" + publicKey);

            Console.show("私钥加密======================");
            password = ConfigTools.encrypt(privateKey, password);
            Console.show("加密后的password：" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptDbPassword(String publicKey, String password) {
        try {
            password = ConfigTools.decrypt(publicKey, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Console.show("解密后的：" + password);
    }
}