package com.oursnail.attend.service;

import com.oursnail.attend.dao.AttendMapper;
import com.oursnail.attend.entity.Attend;
import com.oursnail.attend.entity.CountInfo;
import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.common.utils.DateUtils;
import com.oursnail.user.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 19:33
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Service
@Slf4j
public class AttendServiceImpl implements AttendService{
    /**
     * 中午十二点 判定上下午
     */
    private static final int NOON_HOUR = 12 ;
    private static final int NOON_MINUTE = 00 ;
    private static final int ATTEND_STATUS_ABNORMAL = 2;
    private static final int ABSENCE_DAY = 480;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public PageQueryBean getAttendPage(QueryCondition queryCondition) {
        //根据条件查询 count记录数目
        int count = attendMapper.countByCondition(queryCondition);
        PageQueryBean pageResult = new PageQueryBean();
        if(count>0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(queryCondition.getCurrentPage());
            pageResult.setPageSize(queryCondition.getPageSize());
            List<Attend> attendList = attendMapper.getAttendPage(queryCondition);
            pageResult.setItems(attendList);
        }
        //如果有记录 才去查询分页数据 没有相关记录数目 没必要去查分页数据
        return pageResult;
    }

    @Override
    public void signAttend(Attend attend) {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte) DateUtils.getTodayWeek());
            //查询当天 此人有没有打卡记录
            Attend todayRecord=attendMapper.selectTodaySignRecord(attend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR,NOON_MINUTE);
            if(todayRecord==null){
                //打卡记录还不存在
                if(today.compareTo(noon)<=0){
                    //打卡时间 早于12点 上午打卡
                    attend.setAttendMorning(today);
                }else {
                    //晚上打卡,早上没有打卡  直接判定为缺席八个小时
                    attend.setAbsence(ABSENCE_DAY);
                    attend.setStatus((byte)ATTEND_STATUS_ABNORMAL);
                    attend.setAttendEvening(today);
                }
                String id = UUID.randomUUID().toString().replace("-", "");
                attend.setId(id);
                attendMapper.insertSelective(attend);
            }else{
                if(today.compareTo(noon)<=0){
                    //打卡时间 早于12点 上午打卡
                    return;
                }else {
                    //晚上打卡
                    todayRecord.setAttendEvening(today);
                    attendMapper.updateByPrimaryKeySelective(todayRecord);
                }
            }


        }catch (Exception e){
            log.error("用户签到异常",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void checkAttend() {
        System.out.println("==================检查===============");
//      查询缺勤用户id 插入打卡记录 并且设置为异常  缺勤480分钟
        List<Long> userIdList = attendMapper.selectTodayAbsence();
        if(CollectionUtils.isNotEmpty(userIdList)){
            List<Attend> attendList = new ArrayList<>();
            for(Long userId:userIdList){
                Attend attend = new Attend();
                attend.setId(UUID.randomUUID().toString().replace("-", ""));
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte)DateUtils.getTodayWeek());
                attend.setStatus((byte)ATTEND_STATUS_ABNORMAL);
                attendList.add(attend);
            }
            attendMapper.batchInsert(attendList);
        }
        //检查晚打卡时间  将未打卡设置为异常
        List<Attend> absenceList = attendMapper.selectTodayEveningAbsence();
        if(!CollectionUtils.isEmpty(absenceList)){
            for(Attend attend:absenceList){
                attend.setAbsence(ABSENCE_DAY);
                attend.setStatus((byte)ATTEND_STATUS_ABNORMAL);
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }
    }

    @Override
    public List<CountInfo> getCountInfo() {
        return userMapper.getCountInfo();
    }
}
