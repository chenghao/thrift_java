package com.hao.demo.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.hao.demo.thrift.Demo;
import com.hao.demo.thrift.Hello;

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

            System.out.println(client.echoHello("chenghao_java"));

            Demo demo = new Demo();
            demo.setName("chenghao_java");
            demo.setAge(25);
            System.out.println(client.echoDemo(demo));

        } catch (TException e) {
            e.printStackTrace();
        } finally {
            // 最后一定要调用close来释放资源
            transport.close();
        }
    }

}
