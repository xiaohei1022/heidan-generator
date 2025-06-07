package com.heidan.maker.meta;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.heidan.maker.meta.enums.FileGenerateTypeEnum;
import com.heidan.maker.meta.enums.FileTypeEnuum;
import com.heidan.maker.meta.enums.ModelTypeEnum;

import java.util.List;

/**
 * 元信息参数效验
 */
public class MetaValidator {

    public static void doValidAndFill(Meta meta){
        // 基础元数据效验
        validAndFileMetaRoot(meta);

        // fileConfig 参数效验
        validAndFileConfig(meta);

        // modelConfig 参数效验
        validAndFileModelConfig(meta);
    }

    private static void validAndFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (StrUtil.isBlankIfStr(fileConfig)){
            return;
        }
        if (StrUtil.isBlank(fileConfig.getSourceRootPath())){
            throw new MetaException("未填写 sourceRootPath 参数");
        }
        StrUtil.blankToDefault(fileConfig.getOutputRootPath(), "generated");
        StrUtil.blankToDefault(fileConfig.getType(), FileTypeEnuum.DIR.getValue());

        // FileConfig.file 效验
        List<Meta.FileConfig.FileInfo> files = fileConfig.getFiles();
        if (CollectionUtil.isEmpty(files)){
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : files) {
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)){
                throw new MetaException("未填写 inputRootPath 参数");
            }
            StrUtil.blankToDefault(fileInfo.getOutputPath(), fileInfo.getInputPath());

            // type:默认 inputPath 有文件后缀，如 .java 为File，否则为dir
            if (StrUtil.isBlank(fileInfo.getType())){
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                    fileInfo.setType(FileTypeEnuum.DIR.getValue());
                }else {
                    fileInfo.setType(FileTypeEnuum.FILE.getValue());
                }
            }

            // generateType: 如果文件结尾不是 ftl, generateType 默认为 static, 否则为 dynamic
            if (StrUtil.isBlank(fileInfo.getGenerateType())){
                if (StrUtil.endWithIgnoreCase(inputPath, ".ftl")){
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                }else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void validAndFileMetaRoot(Meta meta) {
        StrUtil.blankToDefault(meta.getName(), "my-generator");
        StrUtil.blankToDefault(meta.getDescription(), "我的模版代码生成器");
        StrUtil.blankToDefault(meta.getBasePackage(), "com.heidan");
        StrUtil.blankToDefault(meta.getVersion(), "1.0");
        StrUtil.blankToDefault(meta.getAuthor(), "xiaohei");
        StrUtil.blankToDefault(meta.getCreateTime(), DateUtil.now());
    }

    private static void validAndFileModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (StrUtil.isBlankIfStr(modelConfig)){
            return;
        }
        List<Meta.ModelConfig.ModelInfo> models = modelConfig.getModels();
        if (CollectionUtil.isEmpty(models)){
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : models) {
            if (StrUtil.isBlank(modelInfo.getFieldName())){
                throw new MetaException("未填写 fileName 参数");
            }
            StrUtil.blankToDefault(modelInfo.getType(), ModelTypeEnum.STRING.getValue());
        }
    }
}
