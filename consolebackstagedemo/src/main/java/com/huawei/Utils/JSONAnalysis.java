package com.huawei.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.projo.Message_available_status;
import com.huawei.projo.Messages_consume_status;
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
        }
        return result;
    }

    public static JSONObject analysisObjectJson(JSONObject jsonObject){
        JSONObject resMsgJson = null;
        try {
            resMsgJson = JsonObjectAnalysis(jsonObject);
        }catch (Exception e){
            log.error(e);
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
        }
        return resMsgJson;
    }
    public static Message_available_status analysisMessages(JSONObject jsonObject, String gid){
        Message_available_status re=new Message_available_status();
        if(jsonObject==null)
            return re;
        JSONArray jarray=jsonObject.getJSONArray("groups");
        if(jarray==null)
            return re;
        for(int i=0;i!=jarray.size();++i){
            JSONObject jsontemp=jarray.getJSONObject(i);
            if(jsontemp.getString("id").equals(gid)){
                re.consumedMsg=jsontemp.getString("consumed_messages");
                re.availableMsg=jsontemp.getString("available_messages");
                return re;
            }
        }
        return re;
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
    public static String GetMsgBody(JSONObject jsonObject){
        if(jsonObject==null||jsonObject.getJSONArray("messages")==null)
            return "null";
        else{
            JSONArray jarray=jsonObject.getJSONArray("messages");
            JSONObject temp=jarray.getJSONObject(0);
            return temp.getString("body");
        }
    }
    public static String ConstructMsg(int n){
        String msg2="{\"messages\":[{\"body\":\""+String.valueOf(n)+"\"}],\"returnId\":\"true\"}";
        return msg2;
    }
    public static Messages_consume_status analysisMessages_consume(JSONObject jsonObject){
        Messages_consume_status ms=new Messages_consume_status();
        System.out.println(jsonObject.getString("success"));
        System.out.println(jsonObject.getString("fail"));
        if(jsonObject!=null&&jsonObject.getString("success")!=null){
            ms.setSuccess(jsonObject.getString("success"));
        }
        if(jsonObject!=null&&jsonObject.getString("fail")!=null){
            ms.setFail(jsonObject.getString("fail"));
        }
        return ms;
    }
}
