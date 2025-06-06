package com.heidan.maker.generator;

import java.io.*;

public class JarGenerator {
    public static void doGenerator(String projectDir) throws IOException, InterruptedException {
        // 编写win 或者 其他脚本
        String winMaveCommand = "mvn.cmd clean package -DskipTests=true";
        String otheMaveCommand = "mvn clean package -DskipTests=true";
        String maveCommand = winMaveCommand;


        ProcessBuilder processBuilder = new ProcessBuilder(maveCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        // 读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine())!= null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int i = process.waitFor();
        System.out.println("命令执行完成，退出码：" + i);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerator("E:\\code\\heidan-generator\\heidan-generator-maker\\generated\\acm-template-pro-generator");
    }
}
