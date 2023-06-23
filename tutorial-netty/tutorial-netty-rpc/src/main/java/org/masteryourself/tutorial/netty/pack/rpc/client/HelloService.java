package org.masteryourself.tutorial.netty.pack.rpc.client;

/**
 * <p>description : HelloService
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 12:59 AM
 */
public interface HelloService {

    String echoName(String name);

    Integer getAge();

}
