package com.oursnail.common.upload;

import java.util.List;
import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;

/**
 * @Author 【swg】.
 * @Date 2017/10/19 21:38
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class FileUploadUtil {
    public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpg","image/jpeg","image/png","image/gif");
    //文件重命名
    public static String rename(String fileName){
        int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime()+""+ new Random().nextInt(99999999) +str;
    }
    //校验文件类型是否是被允许的
    public static boolean allowUpload(String postfix){
        return ALLOW_TYPES.contains(postfix);
    }

    /**
     * 删除文件、文件夹
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            for (int i = 0; i < ff.length; i++) {
                deleteFile(ff[i].getPath());
            }
        }
        file.delete();

    }
}
