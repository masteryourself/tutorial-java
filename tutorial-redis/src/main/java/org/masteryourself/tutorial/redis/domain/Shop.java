package org.masteryourself.tutorial.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>description : Shop
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/23 6:57 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    private Long id;

    private String name;

    private String desc;

}
