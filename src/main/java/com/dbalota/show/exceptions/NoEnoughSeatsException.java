package com.dbalota.show.exceptions;

/**
 * Created by Dmytro_Balota on 3/31/2016.
 */
public class NoEnoughSeatsException extends RuntimeException{
    public NoEnoughSeatsException(String message) {
        super(message);
    }
}
