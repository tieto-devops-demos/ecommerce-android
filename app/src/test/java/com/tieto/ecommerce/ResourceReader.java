package com.tieto.ecommerce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceReader {

    public static String read(String resource) throws IOException {
        return readStream(ResourceReader.class.getResourceAsStream(resource));
    }

    private static String readStream(InputStream iStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        StringBuilder text = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
            text.append(line + '\n');
        return text.toString();
    }
}
