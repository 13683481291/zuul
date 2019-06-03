package com.yy.zuuldemo.filter;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5 {


    public static void main(String[] args) {


        try {
            String url = "cocoapiuser-serveruserInfo19";

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            url = base64Encoder.encodeToString(md5.digest(url.getBytes("utf-8")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
