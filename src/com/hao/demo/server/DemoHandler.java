package com.hao.demo.server;

import com.hao.demo.thrift.Demo;
import com.hao.demo.thrift.Hello.Iface;
import org.apache.thrift.TException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明
 *
 * @author 程豪
 * @version V1.0  创建时间：2014-6-12 上午10:52:24
 */
public class DemoHandler implements Iface {

    @Override
    public String echoDemo(Demo demo) throws TException {
        return "Demo: name=" + demo.getName() + "  age=" + demo.getAge();
    }

    @Override
    public String echoHello(String str) throws TException {
        String result = "你发的字符串是： " + str;

        return result;
    }

    @Override
    public void uploadFile(ByteBuffer binaryData, String filePath, short flag) throws TException {
        try {
            File file = new File(filePath);
            if(flag == 0){
                if(file.exists())
                    file.delete();
            }
            // true表示对文件追加写入
            FileOutputStream fos = new FileOutputStream(file, true);
            FileChannel fileChannel = fos.getChannel();
            fileChannel.write(binaryData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
