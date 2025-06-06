package com.heidan.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.heidan.maker.generator.ScriptGenerator;
import com.heidan.maker.generator.file.DynmaincFileGenerator;
import com.heidan.maker.meta.Meta;
import com.heidan.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMete();
        System.out.println(meta);

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 读取 resources 目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // Java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // GeneratorCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GeneratorCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GeneratorCommand.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // DynmaincFileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/DynmaincFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/DynmaincFileGenerator.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // FileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/FileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/FileGenerator.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // StaticFileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/file/StaticFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/file/StaticFileGenerator.java";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // pom
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
    }
}
