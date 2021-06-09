package com.bjpowernode.settings.test;

import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {

        /*String lockState = "0";
        if("0".equals(lockState)){
            System.out.println("账号已锁定");
        }

         */
        //浏览器的ip地址
//        String ip = "192.168.1.1";
//        //允许访问的ip地址群
//        String allowIps = "192.168.1.1,192.168.1.2";
//        if (allowIps.contains(ip)){
//            System.out.println("ok");
//        }else{
//            System.out.println("no");
//        }

        String pwd = "1123";
        pwd = MD5Util.getMD5(pwd);
        System.out.println(pwd);


    }
}
