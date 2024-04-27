package com.megadev.scoca.object.message;

import lombok.Getter;

/**
 * All messages that can be sent to player.
 */
@Getter
public enum Message {
    ;

    private final String configPath;

    Message(String configPath) {
        this.configPath = configPath;
    }
}
