package org.masteryourself.tutorial.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>description : FileChannelWalkFile
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/8 11:36 AM
 */
@Slf4j
public class FileChannelWalkFile {

    public static void main(String[] args) throws IOException {
        AtomicInteger dirCount = new AtomicInteger(0);
        AtomicInteger fileCount = new AtomicInteger(0);
        Files.walkFileTree(Paths.get("file"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCount.incrementAndGet();
                log.info("fileName: {}", file.getFileName());
                return super.visitFile(file, attrs);
            }
        });
        log.info("dir count {}", dirCount.get());
        log.info("file count {}", fileCount.get());
    }

}
