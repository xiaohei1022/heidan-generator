package com.heidan.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {

    private static volatile Meta mete;

    private MetaManager(){}

    /**
     * 获取元信息
     * @return
     */
    public static Meta getMete(){
        if (mete == null){
            synchronized (MetaManager.class){
                if (mete == null){
                    mete = initMeta();
                }
            }
        }
        return mete;
    }

    /**
     * 初始化元信息
     * @return
     */
    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // 参数效验
        MetaValidator.doValidAndFill(newMeta);
        return newMeta;
    }
}
