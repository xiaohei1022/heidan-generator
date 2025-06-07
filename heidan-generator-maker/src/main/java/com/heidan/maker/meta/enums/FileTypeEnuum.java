package com.heidan.maker.meta.enums;

/**
 * 文件类型
 */
public enum FileTypeEnuum {
    DIR("目录", "dir"),
    FILE("文件", "file");

    private String text;

    private String value;

     FileTypeEnuum(String text, String value) {
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
