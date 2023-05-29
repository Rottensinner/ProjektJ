package MainFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final String CONFIG_FILE_PATH = "config.properties";
    public static void main(String[] args) throws IOException {
        SpisAut spisaut = stworzSpisAut();


        String wybor= "";
       do{
          wybor=dodajDane("wybor");
          if("dodaj pojazd".equalsIgnoreCase(wybor))
              dodajPojazd(spisaut);
          else if("wyswietl pojazdy".equalsIgnoreCase(wybor))
              spisaut.wyswietlPojazdy();
             else if("wypozycz pojazdy".equalsIgnoreCase(wybor)){
                 String id=dodajDane("id");
                 spisaut.wypozycz(id);
             }
                else if("zwroc pojazdy".equalsIgnoreCase(wybor)){
                    String id=dodajDane("id");
                    spisaut.zwroc(id);
                }
                    else if("wyswietl dostepne".equalsIgnoreCase(wybor))
                        spisaut.wyswietldostepne();
                        
       }while(!"wyjdz".equalsIgnoreCase(wybor));




    }

    private static SpisAut stworzSpisAut() throws IOException {
        Properties properties = new Properties();
        try(var fis =  new FileInputStream("config.properties")) {
            properties.load(fis);
        }

         MenagerDanych menagerDanych = new MenagerDanych(new File(properties.getProperty("plik.danych")));

        SpisAut spisaut = new SpisAut(menagerDanych);
        return spisaut;
    }

    private static void dodajPojazd(SpisAut spisaut) {
        System.out.println("Dodajemy samochody do bazy danych");

        String id = dodajDane("Id");
        String marka = dodajDane("Marka");
        String model = dodajDane("Model");
        int rocznik = odczytajliczbe("Rocznik");
        Samochod samochod = new Samochod(id, marka, model, Integer.valueOf(rocznik));
        spisaut.dodajSamochod(samochod);
    }


    public static String dodajDane(String nazwa)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz "+ nazwa);
        String dane = scanner.nextLine();
        System.out.println("Wpisales "+ nazwa + " : " + dane);
      return dane;
    }
    public static int odczytajliczbe(String nazwa)
    {
        Scanner scanner = new Scanner(System.in);
        Optional<Integer> liczba = Optional.empty();
        do {
            System.out.println("Wpisz "+ nazwa+": ");
            try{
                liczba=Optional.of(Integer.valueOf(scanner.nextLine()));
            }catch (NumberFormatException numberFormatException){
                System.out.println("wpisałeś błędne dane, wpisz jeszcze raz.");
            }
        }
        while(liczba.isEmpty());
        return liczba.get();
    }


}