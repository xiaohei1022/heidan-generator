package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.file.FileGenerator;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true, description = "生成配置文件")
@Data
public class GeneratorCommand implements Callable<Integer> {
<#list modelConfig.models as modelInfo>
    @CommandLine.Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}</#if>", <#if modelInfo.fieldName??>"--${modelInfo.fieldName}"</#if>}, arity = "0..1", description = <#if modelInfo.description??>"${modelInfo.description}"</#if>, interactive = true, echo = true)
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#list>

    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        FileGenerator.doGenerate(dataModel);
        return 0;
    }
}
