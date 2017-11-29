package com.oursnail.common.resp;

import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 19:44
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class QueryCondition extends PageQueryBean{
    private Long userId;
    private String username;
    private String startDate ;
    private String endDate ;
    private String rangeDate;
    private Byte status;
    private String content;

}
