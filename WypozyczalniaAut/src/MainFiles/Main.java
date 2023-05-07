package MainFiles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     dodajPojazd();



    }

    public static void dodajPojazd(){


        System.out.println("Dodajemy samochody do bazy danych");

        String id = dodajDane("Id");
        String marka = dodajDane("Marka");
        String model = dodajDane("Model");
        String rocznik = dodajDane("Rocznik");

       Samochod samochod = new Samochod(Integer.valueOf(id), marka, model, Integer.valueOf(rocznik));
        System.out.println(samochod);
    }

    public static String dodajDane(String nazwa)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz "+ nazwa);
        String dane = scanner.nextLine();
        System.out.println("Wpisales "+ nazwa + " : " + dane);
      return dane;
    }

}