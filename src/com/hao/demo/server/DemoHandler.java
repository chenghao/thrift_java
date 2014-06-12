package com.hao.demo.server;

import org.apache.thrift.TException;

import com.hao.demo.thrift.Demo;
import com.hao.demo.thrift.Hello.Iface;

/** 
 * 类说明 
 * @author  程豪
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

}
