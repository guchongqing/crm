package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.sevice.UserService;
import com.bjpowernode.crm.settings.sevice.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到用户控制器");

        String path = request.getServletPath();

        if ("/settings/user/login.do".equals(path)){
            login(request,response);

        }else if ("/settings/user/login.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入登录验证操作");

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接受浏览器端的ip地址
        String ip = request.getRemoteAddr();
        System.out.println("-----------------ip"+ ip);

        //创建service对象
        //未来的业务层开发，统一使用代理类形态的接口对象
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        //定义异常
        try{
            User user = userService.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            //程序执行到此处，说明业务层没有为controller抛出任何异常
            //表示登录成功
            /*
                给前端返回的信息
                 {"success":true}
             */
            PrintJson.printJsonFlag(response,true);
//            String str = "{\"success\":true}";
//            response.getWriter().print(str);


        }catch (Exception e){
            e.printStackTrace();

            //一旦程序执行了catch块信息，说明业务层为我们验证登录失败，为controller抛出异常
            //表示登录失败
            /*
                给前端返回的信息
                ｛"success":true，“msg”:?｝
             */
            String msg = e.getMessage();
            /*
                最重要的问题
                    我们现在作为controller，需要为ajax请求提供多项信息

                    可以有两种手段：
                        1.将多项信息打包成map,将map解析成json串
                        2.创建一个Vo
                            private boolean success;
                            private String msg;

                    如果对于展现的信息将来还会大量的使用，我们创建一个Vo来，使用方便
                    如果对于展现的信息只有在这个需求中能够使用，我们使用map就可以了
             */
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
