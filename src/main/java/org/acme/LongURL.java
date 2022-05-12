package org.acme;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LongURL {

    private String longURL;
    private int ttl;

    public LongURL(String longURL, int TTL) {
        this.longURL = longURL;
        this.ttl = TTL;
    }

    public String getLongURL() { return this.longURL; }
    public int getTTL() { return this.ttl; }

    public LongURL() {}
}
