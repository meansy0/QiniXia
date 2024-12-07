package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.mapper.ManagerMapper;
import com.example.mybatisplus.model.domain.Manager;
import com.example.mybatisplus.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/api/email")
public class EmailController {





//    private String checkCode;
//    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);






    public ManagerMapper getManagerMapper() {
        return managerMapper;
    }

    public IMailService getMailService() {
        return mailService;
    }

    @Resource
    private ManagerMapper managerMapper;

    @Autowired
    private IMailService mailService;


    //获取邮箱+发送验证码
    //输入：管理员tel
    @PostMapping("/getemail")
    @ResponseBody
    public int getEmail(@RequestBody Map map){
        String tel =map.get("tel").toString();

        Manager manager=managerMapper.selectEmailById(tel);
        List<Manager> managers=managerMapper.selectByTel(tel);
        List<Manager> managers1=managerMapper.selectEmail(tel);
        if(managers.size()==0){

            //不存在
            return 0;
        }
        if(managers1.size()==0){
            //没有邮箱 无法忘记密码
            return 0;

        }
        //可以忘记密码

        //创建验证码
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);

        //把验证码放入数据库
        managerMapper.updateCode(tel,checkCode);


        String message= "正在找回密码，你的验证码为：" + managerMapper.selectCheckcode(tel);
        mailService.sendSimpleMail(manager.getEmail(), "帮您省微信小程序，找回密码验证码(●'◡'●)：", message);

        return 1;


    }


//    //获取邮箱验证的验证码
//    @PostMapping("/getCheck")
//    @ResponseBody
//    public JsonResponse getCheck( HttpServletRequest httpServletRequest) throws Exception {
////        HttpSession session = httpServletRequest.getSession();
//        //获取微信小程序get的参数值并打印
////        String userEmail = request.getParameter("userEmail");
////        user = userService.queryUserByUserEmail(userEmail);
////        if (null == user) {
//////            return new JsonResult(JsonResponseStatus.EMPTY.getCode(), JsonResponseStatus.EMPTY.getMsg());
////        }
//
//
////            email="2292530253@qq.com";
//
//
////        Map<String,String> map = new HashMap<>();
////        map.put("checkCode",checkCode);
////        map.put("email",email);
//
//        //验证码和邮箱，一起放入session中
////        session.setAttribute("checkCode",map);
////        Map<String,String> codeMap = (Map<String,String>) session.getAttribute("checkCode");
////        //创建计时线程池，到时间自动移除验证码
////        try {
////            scheduledExecutorService.schedule(new Thread(()->{
////                if(email.equals(codeMap.get("email"))){
////                    session.removeAttribute("checkCode");
////                }
////            }), 20, TimeUnit.SECONDS);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//
//            System.out.println(checkCode);
//            message= "您的注册验证码为："+checkCode;
//            mailService.sendSimpleMail(getEmail(), "帮您省微信小程序，找回密码验证码(●'◡'●)：", message);
////            try {
////                mailService.sendSimpleMail(email, "注册验证码", message);
////            }catch (Exception e){
//////                return new JsonResult(JsonResponseStatus.EMPTY.getCode(), JsonResponseStatus.EMPTY.getMsg());
////                System.out.println(email);
////                return JsonResponse.failure("fail").setCode(1);
////            }
////            return new JsonResult(JsonResponseStatus.SUCCESS.getCode(), JsonResponseStatus.SUCCESS.getMsg(),checkCode);
//            return JsonResponse.success("success");
//    }


    //验证验证码是否正确
    @PostMapping("/checkSecurityCode")
    @ResponseBody
    public boolean checkSecurityCode(@RequestBody Map map){

        String tel =map.get("tel").toString();
        String code1 =map.get("testcode").toString();
//        Map<String,String> codeMap = (Map<String,String>)session.getAttribute("checkCode");


        if(code1.equals(managerMapper.selectCheckcode(tel))){
            //验证成功
            //update密码
//            managerMapper.updateByEmail(getEmail());
            return true;
            //验证码使用完后session删除
//            session.removeAttribute("checkCode");
        }
        return false;
    }
    //update密码
    @PostMapping("/updateCode")
    @ResponseBody
    public boolean updateCode(@RequestBody Map map){

        String newpass = map.get("newpass").toString();
        String tel =map.get("tel").toString();
        managerMapper.updateByEmail(newpass,tel);
        return true;
    }
}




