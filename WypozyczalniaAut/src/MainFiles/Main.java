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


            System.out.println("Witaj w wypozyczalni aut. Zdecyduj co chcesz zrobic");
            System.out.println("1.Wyswietl auta \n2.Dodaj auta");
            System.out.println("3.Wyswietl klientow \n4.Dodaj klientow");
            System.out.println("5.Wypozycz auta \n6.Zwroc auta");
            System.out.println("7.Wyswietl dostepne auta\n8.Usun auta");
            System.out.println("9.Wyswietl auta posiadane przez klienta\n10. Wyjdz z programu");

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
                    usunPojazd(spisaut);
                    break;
                case 9:
                    listaprzypisanychdoklienta(spisklientow,spisaut);
                    break;
                    case 10:
                    a=0;
                    break;
                case 11:
                    int x =spisaut.pokazIloscWypozyczonychAut("1");
                    System.out.println(x);
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

            // Zwiększanie ilości aut dla wypożyczającej osoby
            Klient klient = spisklientow.getKlient(idKlienta);
            System.out.println(klient);
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