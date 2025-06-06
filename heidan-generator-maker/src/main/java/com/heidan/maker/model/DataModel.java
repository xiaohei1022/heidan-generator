package com.heidan.maker.model;

import lombok.Data;

/**
 * 动态模版配置
 */
@Data
public class DataModel {
    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 作者
     */
    private String author = "xiaohei";

    /**
     * 输出信息
     */
    private String outputText = "结果输出：";
}
