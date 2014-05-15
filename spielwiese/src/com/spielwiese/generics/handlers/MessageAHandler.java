package com.spielwiese.generics.handlers;

import org.apache.log4j.Logger;

import com.spielwiese.generics.messages.MessageA;

public class MessageAHandler<T extends MessageA> extends MessageHandler<T> {

    static Logger logger = Logger.getLogger(MessageAHandler.class);

    @Override
    public boolean handle(T message) {
        return false;
    }

}
