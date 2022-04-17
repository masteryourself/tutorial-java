package org.masteryourself.tutorial.netty.chat.message;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {
    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
