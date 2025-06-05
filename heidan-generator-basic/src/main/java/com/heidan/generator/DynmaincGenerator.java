package com.heidan.generator;

import com.heidan.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynmaincGenerator {

    public static void main(String[] args) throws IOException, TemplateException {
        String propertyPath = System.getProperty("user.dir") + File.separator + "heidan-generator-basic";
        String inputPath = propertyPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = propertyPath + File.separator + "MainTemplate.java";

        // 创建数据
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("heidan");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和输出：");

        // 生成
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    /**
     * 根据输入的模版和数据生成输出文件
     * @param inputPath
     * @param outputPath
     * @param model
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model ) throws IOException, TemplateException {
        // 创建FreeMarker配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

        // 指定模版文件所在路径
        File templateDir = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(templateDir);
        cfg.setDefaultEncoding("UTF-8");

        // 创建模版对象，加载指定模版
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);

        /*// 创建数据
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("heidan");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和输出：");*/

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);
        out.close();
    }
}
