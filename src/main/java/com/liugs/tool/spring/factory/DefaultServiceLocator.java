package com.liugs.tool.spring.factory;

/**
 * @ClassName DefaultServiceLocator
 * @Description <!-- the factory bean, which contains a method called createInstance() -->
 * @Author liugs
 * @Date 2020/8/12 14:23:06
 */
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientService();

//    private static AccountService accountService;

    public ClientService createClient() {
        return clientService;
    }

    public AccountService createAccount() {
        return new AccountService();
    }
}
