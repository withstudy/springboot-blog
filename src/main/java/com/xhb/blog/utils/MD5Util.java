package com.xhb.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {


    public static void main(String[] args) {
        Md5Hash md5Hash =new  Md5Hash("123456","user",2);
        System.out.println(md5Hash.toString());
    }
}
