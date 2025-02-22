package com.ljf.netty.codec.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Administrator
 * @version 1.0
 * @date 2014年2月23日
 */
public class TestUserInfo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + b.length);
        bos.close();
        System.out.println("-------------------------------------");
        System.out.println("The byte array serializable length is : "
                + info.codeC().length);

    }

}
