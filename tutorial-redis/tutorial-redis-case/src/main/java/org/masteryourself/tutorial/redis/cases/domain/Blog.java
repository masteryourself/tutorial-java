package org.masteryourself.tutorial.redis.cases;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * <p>description : Blog
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/7 6:58 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 点赞数量
     */
    private int liked;

    @Column(name = "ctime", updatable = false, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime ctime;

    @Column(name = "mtime", updatable = false, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime mtime;

}
