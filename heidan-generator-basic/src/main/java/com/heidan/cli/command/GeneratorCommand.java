package com.heidan.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.heidan.generator.MainGenerator;
import com.heidan.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true, description = "生成配置文件")
@Data
public class GeneratorCommand implements Callable<Integer> {
    /**
     * 是否循环
     */
    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true)
    private boolean loop;

    /**
     * 作者
     */
    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者名称", interactive = true)
    private String author = "xiaohei";

    /**
     * 输出信息
     */
    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "结果输出", interactive = true)
    private String outputText = "结果输出：";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
