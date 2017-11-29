package com.oursnail.common.resp;

import com.oursnail.common.constants.Constants;
import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 10:16
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class Result <T>{
    private int code = Constants.RESP_STATUS_OK;
    private String messgae;
    private T data;
}
