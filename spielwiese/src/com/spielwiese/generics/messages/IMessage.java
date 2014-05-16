
package com.spielwiese.generics.messages;

public interface IMessage<S, R, P> {

  public S getSender();

  public R getRecaeiver();

  public P getPayload();

  public void setSender(S sender);

  public void setReceiver(R receiver);

  public void setPayLoad(P payload);

}
