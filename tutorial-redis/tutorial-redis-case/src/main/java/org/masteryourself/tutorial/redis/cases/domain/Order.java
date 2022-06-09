package org.masteryourself.tutorial.redis.cases.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>description : VoucherOrder
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/5 12:44 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户 id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 优惠券 id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "ctime", updatable = false, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime ctime;

    @Column(name = "mtime", updatable = false, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime mtime;

}
