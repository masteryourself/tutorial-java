package org.masteryourself.tutorial.redis.cases.dto;

import lombok.Data;

/**
 * <p>description : TopicOrderData
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/6/7 5:55 PM
 */
@Data
public class TopicOrderData {

    private Long userId;

    private Long orderId;

    private Long goodsId;

}
