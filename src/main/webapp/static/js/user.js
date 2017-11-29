$(function () {
    $.ajax({
        type:"POST",
        url:"/user/userinfo",
        dataType:"json",
        contentType:"application/json",
        data:{},
        success:function(data){
            $(".user_head_img").attr("src",data.headImage);
            $(".user_name").html(data.realName);
        }
    });
})