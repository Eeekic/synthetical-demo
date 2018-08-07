package com.huawei.tools;

import com.huawei.Utils.CommonUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrePareRushToBuyTools {

    private static final int THREAD_NUM = 10;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);

    private static int COMMIT_FLAG = 0;
    private static int COMMIT_SUCCESS = 0;
    private static int executeCount = 0;

    public static void execute(Runnable runnable){
        synchronized (PrePareRushToBuyTools.class){
            executeCount++;
        }
        executorService.execute(runnable);
    }

    public static boolean getPrivilegeOfCommitData(){
        synchronized (PrePareRushToBuyTools.class){
            if(COMMIT_FLAG == 0){
                COMMIT_FLAG = 1;
                return true;
            }
        }
        return false;
    }
    public static void resetPrivilegeOfCommitData(){
        synchronized (PrePareRushToBuyTools.class){
            COMMIT_SUCCESS++;
            if(COMMIT_SUCCESS >= executeCount){
                COMMIT_SUCCESS = 0;
                COMMIT_FLAG = 0;
                executeCount=0;
            }
        }
    }
}
