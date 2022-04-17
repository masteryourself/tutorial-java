package org.masteryourself.tutorial.netty.rpc.client;

/**
 * <p>description : RpcClientTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 12:34 AM
 */
public class RpcClientTest {

    public static void main(String[] args) {
        HelloService helloService = RpcClient.getProxyService(HelloService.class);
        System.out.println(helloService.echoName("张三"));
        System.out.println(helloService.getAge());
    }

}
