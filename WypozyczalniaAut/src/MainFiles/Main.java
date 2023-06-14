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
        SpisKlientow spisklientow = stworzSpisKlientow();

        int a=1;

        while(a==1) {
            System.out.println("========================");
            System.out.println("   Wypożyczalnia aut");
            System.out.println("========================");
            System.out.println("1. Zarządzanie klientami");
            System.out.println("2. Zarządzanie autami");
            System.out.println("3. Wypożyczenia");
            System.out.println("4. Wyjście");
            System.out.println("========================");
            System.out.println("");


            String wybor = dodajDane("wybor");

            switch (Integer.valueOf(wybor)) {
                case 1:
                    obslugaKlientow(spisklientow, spisaut);
                    break;
                case 2:
                    obslugaAut(spisaut);
                    break;
                case 3:
                    obslugaWypozyczen(spisklientow, spisaut);
                    break;
                case 4:
                    a = 0;
                    break;
                default:
                    System.out.println("Błędny wybór");
            }


            System.out.println(""); // Pusta linia dla czytelności
                pressEnterToContinue();

            }
        }
    private static void obslugaKlientow(SpisKlientow spisklientow, SpisAut spisaut) {
        System.out.println("=============================");
        System.out.println("   Zarządzanie klientami");
        System.out.println("=============================");
        System.out.println("1. Wyświetl klientów");
        System.out.println("2. Dodaj klienta");
        System.out.println("3. Usuń klienta");

        String wybor = dodajDane("wybor");

        switch (Integer.valueOf(wybor)) {
            case 1:
                spisklientow.wyswietlKlientow();
                break;
            case 2:
                dodajKlienta(spisklientow);
                break;
            case 3:
                usunklienta(spisklientow, spisaut);
                break;
            default:
                System.out.println("Błędny wybór");
        }
    }

    private static void obslugaAut(SpisAut spisaut) {
        System.out.println("=============================");
        System.out.println("   Zarządzanie autami");
        System.out.println("=============================");
        System.out.println("1. Wyświetl auta");
        System.out.println("2. Dodaj auto");
        System.out.println("3. Usuń auto");

        String wybor = dodajDane("wybor");

        switch (Integer.valueOf(wybor)) {
            case 1:
                spisaut.wyswietlPojazdy();
                break;
            case 2:
                dodajPojazd(spisaut);
                break;
            case 3:
                usunPojazd(spisaut);
                break;
            default:
                System.out.println("Błędny wybór");
        }
    }

    private static void obslugaWypozyczen(SpisKlientow spisklientow, SpisAut spisaut) {
        System.out.println("=============================");
        System.out.println("   Wypożyczenia");
        System.out.println("=============================");
        System.out.println("1. Wypożycz auto");
        System.out.println("2. Zwróć auto");
        System.out.println("3. Wyświetl dostępne auta");
        System.out.println("4. Wyświetl auta posiadane przez klienta");

        String wybor = dodajDane("wybor");

        switch (Integer.valueOf(wybor)) {
            case 1:
                wypozycz(spisklientow, spisaut);
                break;
            case 2:
                String ida = dodajDane("id");
                spisaut.zwroc(ida);
                break;
            case 3:
                spisaut.wyswietldostepne();
                break;
            case 4:
                listaprzypisanychdoklienta(spisklientow, spisaut);
                break;
            default:
                System.out.println("Błędny wybór");
        }
    }

    private static SpisAut stworzSpisAut() throws IOException {
        Properties properties = new Properties();
        try (var fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        }

        MenagerDanych menagerDanych = new MenagerDanych(new File(properties.getProperty("plik.danych")));

        SpisAut spisaut = new SpisAut(menagerDanych);
        return spisaut;
    }
    private static SpisKlientow stworzSpisKlientow() throws IOException {
        Properties properties = new Properties();
        try (var fis = new FileInputStream("klient-dane.json")) {
            properties.load(fis);
        }
        MenagerKlient menagerKlient = new MenagerKlient(new File("klient-dane.json")); // Tworzenie obiektu MenagerKlient
        SpisKlientow spisKlientow = new SpisKlientow(menagerKlient); // Tworzenie obiektu SpisKlientow z argumentem MenagerKlient
        return spisKlientow;
    }
    private static void dodajPojazd(SpisAut spisaut) {
        System.out.println("Dodajemy samochody do bazy danych");

        String id;
        do {
            id = dodajDane("Id");
            if (spisaut.sprawdzCzyPojazdIstnieje(id)) {
                System.out.println("Pojazd o podanym ID już istnieje. Wprowadź inne ID.");
            }
        } while (spisaut.sprawdzCzyPojazdIstnieje(id));

        String marka = dodajDane("Marka");
        String model = dodajDane("Model");
        int rocznik = odczytajliczbe("Rocznik");
        Samochod samochod = new Samochod(id, marka, model, Integer.valueOf(rocznik));
        spisaut.dodajSamochod(samochod);
    }

    private static void usunPojazd(SpisAut spisaut) {
        System.out.println("Usuwamy pojazd podaj id");
        String id = dodajDane("Id");
        spisaut.usunPojazd(id);
    }

    private static void dodajKlienta(SpisKlientow spisklientow) {
        System.out.println("Dodajemy osoby do bazy danych");

        String imie = dodajDane("imie");
        String nazwisko = dodajDane("nazwisko");
        String telefon = dodajDane("telefon");
        String id = String.valueOf(odczytajliczbe("id"));
        Klient klient = new Klient(imie, nazwisko, telefon, id);
        spisklientow.dodajKlienta(klient);
    }

    public static void listaprzypisanychdoklienta(SpisKlientow spisklientow, SpisAut spisaut){
        System.out.println("Podaj identyfikator klienta, któremu chcesz sprawdzic posiadane auta:");
        String idKlienta = dodajDane("id");

        if (spisklientow.sprawdzKlienta(idKlienta)) {
            System.out.println(spisklientow.sprawdzKlienta(idKlienta));

            spisaut.wyswietlautaklienta(idKlienta);

        } else {
            System.out.println("Nie znaleziono klienta o podanym identyfikatorze.");
        }
    }

    public static void wypozycz(SpisKlientow spisklientow, SpisAut spisaut) {
        System.out.println("Podaj identyfikator klienta, któremu chcesz wypożyczyć auto:");
        String idKlienta = dodajDane("id");

        if (spisklientow.sprawdzKlienta(idKlienta)) {
            System.out.println(spisklientow.sprawdzKlienta(idKlienta));

            System.out.println("Wybierz auto do wypożyczenia:");
            spisaut.wyswietlPojazdy();
            String idAuta = dodajDane("id auta");

            spisaut.wypozycz(idAuta, idKlienta);


        } else {
            System.out.println("Nie znaleziono klienta o podanym identyfikatorze.");
        }
    }


    public static String dodajDane(String nazwa) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz " + nazwa);
        String dane = scanner.nextLine();
        System.out.println("Wpisales " + nazwa + " : " + dane);
        return dane;
    }

    public static int odczytajliczbe(String nazwa) {
        Scanner scanner = new Scanner(System.in);
        Optional<Integer> liczba = Optional.empty();
        do {
            System.out.println("Wpisz " + nazwa + ": ");
            try {
                liczba = Optional.of(Integer.valueOf(scanner.nextLine()));
            } catch (NumberFormatException numberFormatException) {
                System.out.println("wpisałeś błędne dane, wpisz jeszcze raz.");
            }
        }
        while (liczba.isEmpty());
        return liczba.get();
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Handle errors
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Naciśnij Enter, aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Handle errors
        }
    }
  public static void usunklienta(SpisKlientow spisklientow,SpisAut spisaut){

      System.out.println("Podaj identyfikator klienta, ktorego chcesz usunac:");
      String idKlienta = dodajDane("id");
      int ileaut =spisaut.pokazIloscWypozyczonychAut(idKlienta);
      spisklientow.usunKlienta(idKlienta,ileaut);
  }


}