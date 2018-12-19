package com.market.entities;

/**
 * This is the message payload that will be exchanged between the clients and the server.
 * By default, Spring will use the Jackson library to convert this model object to and from JSON.
 */
public class ChatMessage {

  private String from;
  private String text;
  
  /**
   * Ctor.
   * @param from Sender
   * @param text Text
   */
  public ChatMessage(final String from, final String text) {
    this.from = from;
    this.text = text;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(final String from) {
    this.from = from;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

}
