package com.heidan.generator;

import com.heidan.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        // 创建数据
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("heidan");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和输出：");

        // 生成
        doGenerate(mainTemplateConfig);
    }


    public static void doGenerate(MainTemplateConfig mainTemplateConfig) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");

        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "heidan-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/heidan/acm/MainTemplate.java";
        DynmaincGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, mainTemplateConfig);
       /* // 输入路径
        String inputPath = projectPath + File.separator + "heidan-generator-demo-projects" + File.separator + "acm-template";

        // 输出路径
        String outputPath = projectPath + File.separator + "heidan-generator-basic";

        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        // 生成动态文件
        String inputDynamicPath = outputPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = outputPath + File.separator + "acm-template/src/com/heidan/acm/MainTemplate.java";
        DynmaincGenerator.doGenerate(inputDynamicPath, outputDynamicPath, mainTemplateConfig);*/
    }
}
