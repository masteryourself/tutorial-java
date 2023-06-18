package org.masteryourself.tutorial.nio.server.selector.accept;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * <p>description : SelectorAcceptClient
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/6/18 17:26
 */
@Slf4j
public class SelectorAcceptClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 9527)) {
            log.info("建立连接 {}", socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
