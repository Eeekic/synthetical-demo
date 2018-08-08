package com.huawei.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.dmskfakaapi.ResponseMessage;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class JSONAnalysis {

    private static Logger log = Logger.getLogger(JSONAnalysis.class);

    public static int analysisForObjectCount(JSONObject jsonObject,String key){
        int result = -1;
        try {
            JSONObject resMsgJson = JsonObjectAnalysis(jsonObject);
            if(resMsgJson != null && resMsgJson.getString(key) != null){
                result = Integer.parseInt(resMsgJson.getString(key));
            }
        }catch (Exception e){
            result = -1;
            log.error(e);
            e.printStackTrace();
        }
        return result;
    }

    public static String analysisObjectString(JSONObject jsonObject ,String key){
        String result = "";
        try {
            JSONObject resMsgJson = JsonObjectAnalysis(jsonObject);
            if(resMsgJson != null && resMsgJson.getString(key) != null){
                result = resMsgJson.getString(key);
            }
        }catch (Exception e){
            result = "";
            log.error(e);
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject analysisObjectJson(JSONObject jsonObject){
        JSONObject resMsgJson = null;
        try {
            resMsgJson = JsonObjectAnalysis(jsonObject);
        }catch (Exception e){
            log.error(e);
            e.printStackTrace();
        }
        return resMsgJson;
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
            e.printStackTrace();
        }
        return jsonArray;
    }

    private static JSONObject JsonObjectAnalysis(JSONObject jsonObject){
        JSONObject resMsgJson = null;
        JSONArray resMsgJsonArray = commonAnalysis(jsonObject);
        try {
            if(resMsgJsonArray != null && resMsgJsonArray.size() > 0) {
                resMsgJson = resMsgJsonArray.getJSONObject(0);
            }
        }catch (Exception e){
            log.error(e);
            e.printStackTrace();
        }
        return resMsgJson;
    }


    public static int obtainAvailableMsgAmount(ResponseMessage res, String groupId){
        int result = -1;
        if(res.isSuccess()) {
            try {
                JSONObject bodyJson = JSONObject.parseObject(res.getBody());
                if (bodyJson != null) {
                    JSONArray jsonArray = bodyJson.getJSONArray("groups");
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonTemp = jsonArray.getJSONObject(i);
                            if (jsonTemp != null && jsonTemp.getString("id") != null
                                    && jsonTemp.getString("id").equals(groupId)) {
                                result = Integer.parseInt(jsonTemp.getString("available_messages"));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e);
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<String> analysisProduce(JSONObject jsonObject){
        ArrayList<String> strs=null;
        if(jsonObject==null)
            return strs;
        JSONArray jarray=jsonObject.getJSONArray("messages");
        if(jarray==null)
            return strs;
        String msgs="";
        for(int i=0;i!=jarray.size();++i){
            strs=new ArrayList<String>();
            JSONObject jsontemp=jarray.getJSONObject(i);
            if(jsontemp.get("error")==null)
                strs.add("success");
            else
                strs.add("fail");
        }
        return strs;
    }

    public static String ConstructMsg(int count){
        JSONObject msgJson = new JSONObject();
        JSONArray bodyJsonArray = new JSONArray();
        for(int i = 0;i < count;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("body",System.nanoTime()+"");
            bodyJsonArray.add(jsonObject);
        }
        msgJson.put("messages",bodyJsonArray);
        msgJson.put("returnId","true");

        return msgJson.toJSONString();
    }
}
