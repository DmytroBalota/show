package com.dbalota.show.exceptions;

/**
 * Created by Dmytro_Balota on 3/31/2016.
 */
public class SeatIsOccupiedException extends RuntimeException{
    public SeatIsOccupiedException(String message) {
        super(message);
    }
}
