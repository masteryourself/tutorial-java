package org.masteryourself.tutorial.netty.rpc.server.service;

import org.masteryourself.tutorial.netty.rpc.client.HelloService;

/**
 * <p>description : HelloServiceImpl
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/18 12:59 AM
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String echoName(String name) {
        return "tutorial-netty-rpc:" + name;
    }

    @Override
    public Integer getAge() {
        int i = 10 / 0;
        return 19;
    }
}
