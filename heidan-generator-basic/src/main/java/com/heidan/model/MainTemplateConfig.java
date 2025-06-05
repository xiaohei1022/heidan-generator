package com.heidan.model;

import lombok.Data;

/**
 * 动态模版配置
 */
@Data
public class MainTemplateConfig {
    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 作者
     */
    private String author;

    /**
     * 输出信息
     */
    private String outputText;
}
