package ${basePackage}.generator.file;

import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.io.File;
import java.io.IOException;

public class FileGenerator {

    public static void doGenerate(DataModel dataModel) throws TemplateException, IOException {
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

        // 生成文件
<#list fileConfig.files as fileInfo>
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo == "static">
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        <#else>
        DynmaincFileGenerator.doGenerate(inputPath, outputPath, dataModel);
    </#if>

</#list>
    }
}
