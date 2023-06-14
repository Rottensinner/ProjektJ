package MainFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
public class MenagerKlient {
    private ObjectMapper objectMapper = new ObjectMapper();
    private File plikKlienci;

    public MenagerKlient(File plikKlienci) {
        this.plikKlienci = plikKlienci;
    }

    public HashMap<String, Klient> wczytajKlientow() {
        if (!plikKlienci.exists() || plikKlienci.length() == 0) {
            // Tworzenie nowego pliku
            try {
                plikKlienci.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException("Wystąpił błąd podczas tworzenia nowego pliku klientów.", ex);
            }
            return new HashMap<>();
        }
        // Wczytywanie danych z istniejącego pliku
        try {
            return objectMapper.readValue(plikKlienci, new TypeReference<>() {});
        } catch (IOException ex) {
            throw new RuntimeException("Wystąpił błąd w czasie wczytywania danych.", ex);
        }
    }

    public void zapiszKlientow(HashMap<String, Klient> klienci) {
        try {
            objectMapper.writeValue(plikKlienci, klienci);
        } catch (IOException ex) {
            throw new RuntimeException("Wystąpił błąd w czasie zapisywania danych. ", ex);
        }
    }

}
