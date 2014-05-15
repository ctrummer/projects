package com.spielwiese.generics.handlers;

import org.apache.log4j.Logger;

import com.spielwiese.generics.messages.Message;
import com.spielwiese.generics.messages.MessageA;

public class MessageBHandler extends MessageHandler {

    static Logger logger = Logger.getLogger(MessageBHandler.class);

    public boolean handle(MessageA mesg) {
        return false;
    }

    @Override
    public boolean handle(Message mesg) {
        // TODO Auto-generated method stub
        return false;
    }

}
