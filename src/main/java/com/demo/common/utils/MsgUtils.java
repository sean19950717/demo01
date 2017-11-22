package com.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送工具类
 */
@Component
public class MsgUtils {
    private  static Logger logger= Logger.getLogger(MsgUtils.class);
    /**
     * 用户发送短信公共类
     * @param telePhone
     * @param smsConfigInfo
     */
    @Autowired
    private  Environment environment;
    @Autowired
    private RedisUtils redisUtils;
    public void sendMsg(String telePhone) throws com.aliyuncs.exceptions.ClientException {
        String product =environment.getProperty("aliyun.mns.YourProduct");
        //产品域名,开发者无需替换
        String domain =environment.getProperty("aliyun.mns.YourDoMain");
        String accessKeyId = environment.getProperty("aliyun.mns.YourAccessId");
        String accessKeySecret = environment.getProperty("aliyun.mns.YourAccessKey");
        String phone = telePhone;
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(environment.getProperty("aliyun.mns.YourSignName"));
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(environment.getProperty("aliyun.mns.YourSMSTemplateCode"));
        Map<String,String >map=new HashMap<>();
        String capatcha=CodeUtils.getSmsCapatcha();
        map.put("captcha",capatcha);
        request.setTemplateParam(JSON.toJSONString(map));
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            logger.info("发送短信失败:"+e.getMessage());
            e.printStackTrace();
            throw  new  RuntimeException(e);
        }
        redisUtils.set(telePhone,capatcha,RedisUtils.CAPACHA_EXPIRE);
    }

   /* *//**
     *
     * @param telePhone
     * @param smsConfigInfo
     * @throws com.aliyuncs.exceptions.ClientException
     *//*
    public static void sendMsg(String telePhone, SmsConfigInfo smsConfigInfo, Object msgTemp) throws Exception {
        String product =CommonUtils.getSysProperties("aliyun.mns.YourProduct");
        //产品域名,开发者无需替换
        String domain =CommonUtils.getSysProperties("aiiyun.mns.YourDoMain");
        String accessKeyId = CommonUtils.getSysProperties("aliyun.mns.YourAccessId");
        String accessKeySecret = CommonUtils.getSysProperties("aliyun.mns.YourAccessKey");
        String phone = telePhone;
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsConfigInfo.getAliyunSignname());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsConfigInfo.getAliyunTemplatecode());
        request.setTemplateParam(JSON.toJSONString(msgTemp));
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            logger.info("发送短信失败:"+e.getMessage());
            e.printStackTrace();
            throw  new  RuntimeException(e);
        }
    }*/

    public static void main(String [] args){
        /*SmsConfigInfo smsConfigInfo=new SmsConfigInfo();
        smsConfigInfo.setAliyunSignname("宝可思英语");
        smsConfigInfo.setAliyunTemplatecode("SMS_97140027");
        Map<String,String> map=new HashMap<>();
        map.put("realname","小明");
        map.put("courseName","语文");
        try{
            MsgUtils.sendMsg("15610028715",smsConfigInfo,map);
        }catch (Exception e){
            e.printStackTrace();
        }*/
    /*   try {
            MsgUtils.sendMsg("18363975109");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.print("短信发送成功");*/
    }

}
