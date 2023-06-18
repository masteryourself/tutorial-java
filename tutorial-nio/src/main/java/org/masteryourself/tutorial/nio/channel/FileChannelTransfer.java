package org.masteryourself.tutorial.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * <p>description : FileChannelTransfer
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 1:06 AM
 */
@Slf4j
public class FileChannelTransfer {

    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("file/tutorial-io/data.txt").getChannel();
                FileChannel to = new FileOutputStream("file/tutorial-io/to.txt").getChannel();
        ) {
            // 效率高, 底层会使用操作系统的零拷贝进行优化, 上限是 2G
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                log.info("position:{}, left:{}", size - left, left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
