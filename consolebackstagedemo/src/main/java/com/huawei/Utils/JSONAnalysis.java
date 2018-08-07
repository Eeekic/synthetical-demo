package com.huawei.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class JSONAnalysis {
    private static Logger log = Logger.getLogger(JSONAnalysis.class);
    public static int NormalAnalysisForObjectCount(JSONObject jsonObject){
        int result = -1;
        try {
            JSONArray resMsgJsonArray = commonAnalysis(jsonObject);
            if(resMsgJsonArray != null && resMsgJsonArray.size() > 0){
                JSONObject resMsgJson = resMsgJsonArray.getJSONObject(0);
                if(resMsgJson != null && resMsgJson.getString("testUserCount") != null){
                    result = Integer.parseInt(resMsgJson.getString("testUserCount"));
                }
            }
        }catch (Exception e){
            result = -1;
            log.error(e);
        }
        return result;
    }



    private static JSONArray commonAnalysis(JSONObject jsonObject){
        JSONArray jsonArray = null;
        try {
            if (jsonObject != null && jsonObject.getString("errCode") != null &&
                    jsonObject.getString("errCode").equals(CommonUtils.DB_SERVICES_NORMAL_CODE)) {
                jsonArray = jsonObject.getJSONArray("resMsg");
            }
        }catch (Exception e){
            log.error(e);
        }
        return jsonArray;
    }
}
