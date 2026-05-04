package com.hopik.task2.parser;

public abstract class AbstractParser implements Parser {
    protected Parser next;

    @Override
    public void setNext(Parser next){
        this.next = next;
    }
}
