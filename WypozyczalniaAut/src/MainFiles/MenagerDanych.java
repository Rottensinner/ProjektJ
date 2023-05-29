package MainFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
public class MenagerDanych {
    private ObjectMapper objectMapper = new ObjectMapper();
    private File plikDanych;
    public  MenagerDanych(File plikDanych){
        this.plikDanych=plikDanych;
    }
    public HashMap<String,Samochod> wczytajPojazd(){
        if(!plikDanych.exists()){
            return new HashMap<>();
        }
        try {
           return objectMapper.readValue(plikDanych, new TypeReference<>() {
            });
        }catch (IOException ex){
            throw new RuntimeException("Wystąpił błąd w czasie wczytywania danych. ",ex);
        }
    }
    public void zapiszPojazd(HashMap<String,Samochod> samochody){
       try{
        objectMapper.writeValue(plikDanych,samochody);
    }catch (IOException ex){
           throw new RuntimeException("Wystąpił błąd w czasie zapisywania danych. ",ex);
       }
}

}



