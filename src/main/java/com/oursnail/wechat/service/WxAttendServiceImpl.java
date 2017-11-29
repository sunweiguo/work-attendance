package com.oursnail.wechat.service;

import com.oursnail.attend.entity.Attend;
import com.oursnail.common.utils.DateUtils;
import com.oursnail.wechat.dao.WxAttendMapper;
import com.oursnail.wechat.entity.WxAttend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author 【swg】.
 * @Date 2017/11/18 14:06
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Service
@Slf4j
public class WxAttendServiceImpl implements WxAttendService{
    private static final int NOON_HOUR = 12 ;
    private static final int NOON_MINUTE = 00 ;
    private static final int ATTEND_STATUS_ABNORMAL = 2;
    private static final int ABSENCE_DAY = 480;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Autowired
    private WxAttendMapper wxAttendMapper;

    @Override
    public void signAttend(WxAttend wxAttend) {
        try {
            Date today = new Date();
            wxAttend.setAttendDate(today);
            wxAttend.setAttendWeek((byte) DateUtils.getTodayWeek());
            //查询当天 此人有没有打卡记录
            WxAttend todayRecord=wxAttendMapper.selectTodaySignRecord(wxAttend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR,NOON_MINUTE);
            if(todayRecord==null){
                if(today.compareTo(noon)<=0){
                    wxAttend.setAttendMorning(today);
                }else {
                    //晚上打卡,早上没有打卡  直接判定为缺席八个小时
                    wxAttend.setAbsence(ABSENCE_DAY);
                    wxAttend.setStatus((byte)ATTEND_STATUS_ABNORMAL);
                    wxAttend.setAttendEvening(today);
                }
                String id = UUID.randomUUID().toString().replace("-", "");
                wxAttend.setId(id);
                wxAttendMapper.insertSelective(wxAttend);
            }else{
                if(today.compareTo(noon)<=0){
                    //打卡时间 早于12点 上午打卡
                    return;
                }else {
                    //晚上打卡
                    todayRecord.setAttendEvening(today);
                    wxAttendMapper.updateByPrimaryKeySelective(todayRecord);
                }
            }


        }catch (Exception e){
            log.error("用户签到异常",e);
            throw e;
        }
    }
}
