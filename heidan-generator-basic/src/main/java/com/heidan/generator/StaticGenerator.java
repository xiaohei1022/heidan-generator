package com.heidan.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        //输入路径
        String inputPath = projectPath + File.separator + "heidan-generator-demo-projects" + File.separator + "acm-template";

        //输出路径
        String outputPath = projectPath;

        copyFilesByHutool(inputPath, outputPath);
    }


    /**
     * 拷贝文件
     * @param inputPath 输入文件
     * @param outputPath 输出文件
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }
}
