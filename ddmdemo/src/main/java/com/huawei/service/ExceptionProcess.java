package com.huawei.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import org.apache.log4j.Logger;

public class ExceptionProcess {

    private static Logger log = Logger.getLogger(DbServices.class);

    public static String getExceptionInfo(Exception e){
        log.error(e);
        return e.getCause() + ":" + e.getMessage();
    }

    public static JSONObject processExceptionWithJsonArray(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errCode",CommonUtils.ERROR_CODE);
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonTmp = new JSONObject();
        jsonTmp.put("errMsg",getExceptionInfo(e));

        jsonArray.add(jsonTmp);

        jsonObject.put("resMsg",jsonArray);
        return jsonObject;
    }
}
