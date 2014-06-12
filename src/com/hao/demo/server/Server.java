package com.hao.demo.server;

import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.hao.demo.thrift.Hello;

/** 
 * 类说明 
 * @author  程豪
 * @version V1.0  创建时间：2014-6-12 上午10:50:58 
 */
public class Server {

	public static void main(String[] args) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TBaseProcessor<DemoHandler> processor = new Hello.Processor(new DemoHandler());
		
		try {
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9000);
			TProtocolFactory factory = new TCompactProtocol.Factory();
			TServer server = new TThreadedSelectorServer(new TThreadedSelectorServer.Args(serverTransport).processor(processor).protocolFactory(factory));
			
			System.err.println("java start server port: 9000");
			
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
