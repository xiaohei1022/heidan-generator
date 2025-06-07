package com.heidan.maker.generator.main;

public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceRootPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不需要拷贝精简jar包 dist 啦！");
    }
}
