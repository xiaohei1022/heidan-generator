package com.heidan.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    /**
     * names：参数名称
     * required：是否必须
     * hidden：是否隐藏
     * order：参数排序
     * type：参数类型
     * description：参数描述
     * interactive：是否需要交互式输入
     * arity：参数个数，0..1表示0个或1个参数
     */
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true)
    String password;

    @Option(names = {"-cp", "--checkPassword"}, arity = "0..1", description = "check Password", interactive = true)
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user123", "-p", "xiaohei", "-cp");
    }
}
