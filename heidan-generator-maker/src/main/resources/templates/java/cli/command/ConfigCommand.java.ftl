package ${basePackage}.cli.command;

import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.DataModel;
import picocli.CommandLine;

import java.lang.reflect.Field;


@CommandLine.Command(name = "config", mixinStandardHelpOptions = true, description = "查看参数信息")
public class ConfigCommand implements Runnable {


    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(DataModel.class);

        // 循环
        for (Field field : fields) {
            System.out.println("字段类型：" + field.getType());
            System.out.println("字段名称：" + field.getName());
        }
    }
}
