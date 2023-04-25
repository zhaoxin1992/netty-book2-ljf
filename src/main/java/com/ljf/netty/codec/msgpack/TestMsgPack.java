package com.ljf.netty.codec.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ray.zhao
 */
public class TestMsgPack {

    public static void main(String[] args) throws IOException {
        ArrayList<String> src = new ArrayList<>();
        src.add("a");
        src.add("b");
        src.add("c");
        MessagePack msgPack = new MessagePack();
        byte[] raw = msgPack.write(src);
        List<String> dst = msgPack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst.get(0));
        System.out.println(dst.get(1));
        System.out.println(dst.get(2));
    }

}
