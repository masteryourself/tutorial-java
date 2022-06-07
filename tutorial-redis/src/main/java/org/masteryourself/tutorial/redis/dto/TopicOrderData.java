package org.masteryourself.tutorial.redis.dto;

import lombok.Data;

/**
 * <p>description : TopicOrderData
 *
 * @author : 其琛
 * @version : 1.1.1
 * @date : 2022/6/7 5:55 PM
 */
@Data
public class TopicOrderData {

    private Long userId;

    private Long orderId;

    private Long goodsId;

}
