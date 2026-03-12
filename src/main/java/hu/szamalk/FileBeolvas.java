package hu.szamalk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class FileBeolvas {


    private FileBeolvas() {}

    public static String elsoSor(String resourceNev) throws IOException {
        var is = FileBeolvas.class.getClassLoader().getResourceAsStream(resourceNev);
        if (is == null) {
            throw new IOException("Nem található a resource: " + resourceNev);
        }

        try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String sor = reader.readLine();
            if (sor == null || sor.isBlank()) {
                throw new IOException("Üres a fájl: " + resourceNev);
            }
            return sor;
        }
    }


}
