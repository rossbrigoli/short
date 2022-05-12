package org.acme;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.HashMap;

public class URLCache {
    public static HashMap<String, LongURL> Urls = new HashMap<>();

    public static String addUrl(String longURL, int TTL) throws NoSuchAlgorithmException {
        String generatedId = generateId(longURL, TTL);
        while(Urls.containsKey(generatedId)) {
            generatedId = generateId(longURL, TTL);
        }

        Urls.put(generatedId, new LongURL(longURL, TTL));

        //TODO: Persist in Data store

        return generatedId;
    }

    private static String generateId(String longURL, int TTL) throws NoSuchAlgorithmException  {

        String originalString = longURL + TTL + Clock.systemDefaultZone().millis();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String encodedhash = digest.digest(
          originalString.getBytes(StandardCharsets.UTF_8)).toString();

        var shortHash = encodedhash.substring(encodedhash.length() - 5, encodedhash.length());
        
        return shortHash;
    }
}
