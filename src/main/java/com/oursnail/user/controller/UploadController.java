package com.oursnail.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.oursnail.common.constants.Constants;
import com.oursnail.common.resp.Result;
import com.oursnail.common.upload.FileUploadUtil;
import com.oursnail.common.upload.ImgCut;
import com.oursnail.user.entity.User;
import com.oursnail.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    private UserService userService;

    /*
	 * ajax上传头像：保存头像，切割头像，存储新的头像，返回结果
	 */
    @RequestMapping(value = "/avatarupload", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public Result avatarupload(HttpSession session,
                               @RequestParam(value = "avatar_file", required = false) MultipartFile avatar_file, String avatar_src,
                               String avatar_data, HttpServletRequest request) {
        Result result = new Result();
        try{
            User user = (User) session.getAttribute("loginUser");
            System.out.println("==========开始上传=============");
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String resourcePath = "/upload/image/";
            // 判断文件的MIMEtype
            String type = avatar_file.getContentType();
            if (type == null || !FileUploadUtil.allowUpload(type)){
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                result.setMessgae("不支持的图片类型");
                return result;
            }
            System.out.println("file type:" + type);
            String fileName = FileUploadUtil.rename(avatar_file.getOriginalFilename());
            int end = fileName.lastIndexOf(".");
            String saveName = fileName.substring(0, end);
            try {
                File dir = new File(realPath + resourcePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, saveName + "_src.jpg");
                avatar_file.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                result.setMessgae("上传失败");
                return result;
            }
            String srcImagePath = realPath + resourcePath + saveName;
            JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
            // 用户经过剪辑后的图片的大小
            // 用户经过剪辑后的图片的大小
            float x = joData.getFloatValue("x");
            float y = joData.getFloatValue("y");
            float w = joData.getFloatValue("width");
            float h = joData.getFloatValue("height");
            float r = joData.getFloatValue("rotate");
            // 这里开始截取操作并且上传到oss服务器
            System.out.println("==========开始截取=============");
            String ossName = ImgCut.cutAndRotateImage(srcImagePath, (int) x, (int) y, (int) w, (int) h, (int) r);
            System.out.println("==========截取结束=============http://swg2018.oss-cn-beijing.aliyuncs.com/"+ossName);
            /*将图片名字存到数据库*/
            user.setHeadimgurl(ossName);
            userService.updateUser(user);
            session.setAttribute("loginUser",user);
            result.setCode(Constants.RESP_STATUS_OK);
            result.setMessgae("上传成功");
            System.out.println("==========上传成功=============");
            return result;
        }catch (Exception e){
            log.error("上传头像失败",e);
            throw e;
        }
    }

}
