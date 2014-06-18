package com.hao.demo.client;

import com.hao.demo.thrift.Demo;
import com.hao.demo.thrift.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明
 *
 * @author 程豪
 * @version V1.0  创建时间：2014-6-12 上午11:56:59
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9000));
        TProtocol protocol = new TCompactProtocol(transport);

        Hello.Client client = new Hello.Client(protocol);
        try {
            transport.open();

            // 1
            System.out.println(client.echoHello("chenghao_java"));
            // 2
            Demo demo = new Demo();
            demo.setName("chenghao_java");
            demo.setAge(25);
            System.out.println(client.echoDemo(demo));
            // 3
            try {
                String sourceFilePath = "E:\\logs\\xiaohuoban.log.1";
                String targetFilePath = "xiaohuoban_t1.log";

                FileChannel fChannel = new FileInputStream(sourceFilePath).getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 10);

                short flag = 0;
                while(true){
                    //clear方法将Buffer从读模式切换到写模式
                    //Buffer被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
                    buffer.clear();
                    //将管道的数据写到Buffer里面
                    int r = fChannel.read(buffer);
                    if(r == -1){
                        break;
                    }
                    //flip方法将Buffer从写模式切换到读模式
                    buffer.flip();

                    client.uploadFile(buffer, targetFilePath, flag);

                    flag ++;
                }
                System.out.println("成功。");
            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (TException e) {
            e.printStackTrace();
        } finally {
            // 最后一定要调用close来释放资源
            transport.close();
        }
    }

}
