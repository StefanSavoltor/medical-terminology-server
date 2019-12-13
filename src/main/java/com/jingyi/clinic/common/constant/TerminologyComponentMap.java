package com.jingyi.clinic.common.constant;

import java.util.HashMap;
import java.util.Map;

public class TerminologyComponentMap {
    //语义标签对照
    public static Map<String,String> segmanticTagMap = new HashMap<>();
    static{
        segmanticTagMap.put("T001","临床所见");
    }

    public static final Number ALLOWABLE_TERM = 0;
    public static final Number PREFERRED_TERM = 1;
    public static final Number UNDETERMINED_PREFERRED_TERM = 2;
    //术语类型对照
    public static Map<Integer,String> termTypeMap = new HashMap<>();
    static{
        termTypeMap.put(0,"许用术语");
        termTypeMap.put(1,"首选术语");
        termTypeMap.put(2,"待定首选术语");
    }
    //关系类型描述对照
    public static Map<String,String> relationshipTypeMap = new HashMap<>();
    static{
        relationshipTypeMap.put("R001","子类");
    }
    //映射字典对照
    public static Map<String,String> mapVocabulary = new HashMap<>();
    static{
        mapVocabulary.put("ICD10CN","《疾病分类与代码（修订版）》亚目表 全国 2011 版");
    }
}
