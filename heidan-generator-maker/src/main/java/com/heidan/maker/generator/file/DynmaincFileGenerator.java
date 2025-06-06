package com.heidan.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynmaincFileGenerator {

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

        // 如果文件不存在，则创建
        if (!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }
        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);
        out.close();
    }
}
