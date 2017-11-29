package com.oursnail.common.exception;

import com.oursnail.common.constants.Constants;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 10:22
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class WxRecordException extends Exception{

    public WxRecordException(String message){
        super(message);
    }

    public int getStatusCode(){
        return Constants.RESP_STATUS_INTERNAL_ERROR;
    }
}
