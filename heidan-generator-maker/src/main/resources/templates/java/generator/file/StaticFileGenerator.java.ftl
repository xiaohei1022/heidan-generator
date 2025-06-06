package ${basePackage}.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 */
public class StaticFileGenerator {

    /**
     * 拷贝文件
     * @param inputPath 输入文件
     * @param outputPath 输出文件
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }
}
