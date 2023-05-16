package MainFiles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SpisAut spisaut = new SpisAut();

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

    private static void dodajPojazd(SpisAut spisaut) {
        System.out.println("Dodajemy samochody do bazy danych");

        String id = dodajDane("Id");
        String marka = dodajDane("Marka");
        String model = dodajDane("Model");
        String rocznik = dodajDane("Rocznik");
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

}