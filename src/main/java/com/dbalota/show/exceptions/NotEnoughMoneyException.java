package com.dbalota.show.exceptions;

/**
 * Created by Dmytro_Balota on 3/31/2016.
 */
public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
