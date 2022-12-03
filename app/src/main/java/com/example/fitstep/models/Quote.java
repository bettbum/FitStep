package com.example.fitstep.models;

public class Quote {
    private String quote;
    private String author;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }
    public Quote(){}

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
