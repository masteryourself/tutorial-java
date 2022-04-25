package org.masteryourself.tutorial.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * <p>description : FileChannelTest
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 1:06 AM
 */
@Slf4j
public class FileChannelTest {

    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("file/tutorial-io/data.txt").getChannel();
                FileChannel to = new FileOutputStream("file/tutorial-io/to.txt").getChannel();
        ) {
            // 效率高, 底层会使用操作系统的零拷贝进行优化, 上限是 2G
            from.transferTo(0, from.size(), to);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
