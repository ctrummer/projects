package com.spielwiese.generics.handlers;

import com.spielwiese.generics.messages.Message;

public interface IMessageHandler<T extends Message> {

    public boolean handle(T message);

}
