package com.heidan.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.heidan.maker.generator.JarGenerator;
import com.heidan.maker.generator.ScriptGenerator;
import com.heidan.maker.generator.file.DynmaincFileGenerator;
import com.heidan.maker.meta.Meta;
import com.heidan.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMete();

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 复制原始文件
        String sourceRootPath = copySource(meta, outputPath);

        // 读取 resources 目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // 生成代码
        generateCode(meta, outputPath, inputResourcePath);

        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        // 构建jar包
        String jarPath = buildJar(meta, shellOutputFilePath, outputPath);

        // 生成精简jar包
        buildDist(outputPath, sourceRootPath, shellOutputFilePath, jarPath);
    }

    protected void buildDist(String outputPath, String sourceRootPath, String shellOutputFilePath, String jarPath) {
        String distOutputName = outputPath + "-dist";
        // 拷贝jar包
        String targetAbsolutePath = distOutputName + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        //拷贝脚本
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputName, true);
        FileUtil.copy(shellOutputFilePath, distOutputName, true);
        // 拷贝模板
        FileUtil.copy(sourceRootPath, distOutputName, true);
    }

    protected String buildJar(Meta meta, String shellOutputFilePath, String outputPath) throws IOException, InterruptedException {
        JarGenerator.doGenerator(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return jarPath;
    }

    protected void generateCode(Meta meta, String outputPath, String inputResourcePath) throws IOException, TemplateException {
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

        // README.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynmaincFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);
    }

    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, false);
        return sourceRootPath;
    }
}
