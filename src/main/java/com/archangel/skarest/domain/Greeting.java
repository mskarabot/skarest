package com.archangel.skarest.domain;

/**
 * Created by Mihael on 22.9.2017.
 */
public class Greeting {

    private long counter;

    private String description;

    public Greeting(long counter, String description) {
        this.counter = counter;
        this.description = description;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
