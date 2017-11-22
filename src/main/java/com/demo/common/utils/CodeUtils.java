package com.demo.common.utils;

import java.util.Date;

public class CodeUtils {
    public  final  static  String CURRENTDATE="yyyyMMddHHmmss";
    public static  String getOddNumbers(){
        Date date=new Date();
        String currentDate=DateUtils.format(date,CURRENTDATE);
        String randomNumber = String.valueOf((int)((Math.random()*9+1)*100000));
        String currentOddNumbers=currentDate+randomNumber;
        return currentOddNumbers;
    }
    public  static  void main(String[] args){
        System.out.print(getOddNumbers());
    }
    public static String getSmsCapatcha(){
        String smsCapatcha = String.valueOf((int)((Math.random()*9+1)*100000));
        return smsCapatcha;
    }
}
