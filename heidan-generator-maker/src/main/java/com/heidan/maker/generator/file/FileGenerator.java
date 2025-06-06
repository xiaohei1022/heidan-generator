package com.heidan.maker.generator.file;

import com.heidan.maker.model.DataModel;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.io.File;
import java.io.IOException;

public class FileGenerator {

    public static void doGenerate(DataModel dataModel) throws TemplateException, IOException {
        String inputRootPath = "E:/code/heidan-generator/heidan-generator-demo-projects/acm-template-pro";
        String outputRootPath = "E:/code/heidan-generator/heidan-generator-maker/generated";

        String inputPath;
        String outputPath;

        // 生成动态文件
        inputPath = new File(inputRootPath, "src/com/heidan/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/heidan/acm/MainTemplate.java").getAbsolutePath();
        DynmaincFileGenerator.doGenerate(inputPath, outputPath, dataModel);

        // 生成静态文件
        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    }
}
