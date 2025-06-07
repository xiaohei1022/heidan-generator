package com.heidan.maker.meta.enums;

/**
 * 文件生成类型
 */
public enum FileGenerateTypeEnum {
    DYNAMIC("动态", "dynamic"),
    STATIC("静态", "static");

    private String text;

    private String value;

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
