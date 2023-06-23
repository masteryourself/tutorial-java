package org.masteryourself.tutorial.netty.protocol.customize;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>description : Message
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/15 8:13 PM
 */
@Data
@Builder
public class Message implements Serializable {

    private Byte messageType;

    private Integer sequenceId;

    private String username;

    private String password;

}
