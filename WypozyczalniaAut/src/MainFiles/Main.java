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
        SpisKlientow spisklientow = new SpisKlientow();

        int a=1;

        while(a==1) {


            System.out.println("Witaj w wypozyczalni aut. Zdecyduj co chcesz zrobic");
            System.out.println("1.Wyswietl auta \n2.Dodaj auta");
            System.out.println("3.Wyswietl klientow \n4.Dodaj klientow");
            System.out.println("5.Wypozycz auta \n6.Zwroc auta");
            System.out.println("7.Wyswietl dostepne auta\n8.Wyjscie z programu");

            String wybor = dodajDane("wybor");

            switch (Integer.valueOf(wybor)) {

                case 1:
                    spisaut.wyswietlPojazdy();
                    break;
                case 2:
                    dodajPojazd(spisaut);
                    break;
                case 3:
                    spisklientow.wyswietlKlientow();
                    break;
                case 4:
                    dodajKlienta(spisklientow);
                    break;
                case 5:
                    wypozycz(spisklientow,spisaut);
                    break;
                case 6:
                    String ida = dodajDane("id");
                    spisaut.zwroc(ida);
                    break;
                case 7:
                    spisaut.wyswietldostepne();
                    break;
                case 8:
                    a=0;
                    break;
                case 9:
                    usunPojazd(spisaut);
                    break;
                    default:
                    System.out.println("Bledny wybor");

            }
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

    private static void dodajPojazd(SpisAut spisaut) {
        System.out.println("Dodajemy samochody do bazy danych");

        String id = dodajDane("Id");
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

    public static void wypozycz(SpisKlientow spisklientow, SpisAut spisaut) {
        System.out.println("Podaj identyfikator klienta, któremu chcesz wypożyczyć auto:");
        String idKlienta = dodajDane("id");

        // Sprawdź, czy istnieje klient o podanym identyfikatorze
        boolean klientIstnieje = spisklientow.sprawdzKlienta(idKlienta);
        if (klientIstnieje) {
            System.out.println("Wybierz auto do wypożyczenia:");
            spisaut.wyswietlPojazdy();
            String idAuta = dodajDane("id");

            spisaut.wypozycz(idAuta, idKlienta);

            // Zwiększanie ilości aut dla wypożyczającej osoby
            Klient klient = spisklientow.getKlient(idKlienta);
            if (klient != null) {
                klient.inkrementacja();
            }
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


}