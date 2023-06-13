package MainFiles;

import java.util.HashMap;

public class SpisAut {
    private MenagerDanych menagerDanych;
    private HashMap<String,Samochod> samochody = new HashMap<>();

    public SpisAut(MenagerDanych menagerDanych){
        this.menagerDanych = menagerDanych;
    }


     public void dodajSamochod(Samochod samochod)
     {
         HashMap<String, Samochod> samochody = wczytajKopieSamochod();
         samochody.put(samochod.getId(),samochod);
         menagerDanych.zapiszPojazd(samochody);
     }

    public void usunPojazd(String id) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();

        Samochod usunietySamochod = samochody.remove(id);
        if (usunietySamochod != null && usunietySamochod.isDostepny()==false) {

            System.out.println("Ten pojaz jest wypozyczony, ureguluj ta sprawe przed usunieciem go.");
        }
        else if (usunietySamochod != null && usunietySamochod.isDostepny()==true) {
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został usunięty");

        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id);
        }
    }

     public void wyswietlPojazdy(){
         System.out.println("Wszystkie samochody");

         for(Samochod samochod : wczytajKopieSamochod().values()){
             System.out.println(samochod);
         }


     }

    public void wyswietlautaklienta(String idwlascicela){
        System.out.println("Wszystkie pojazdy wypozyczone przez klienta");
        for(Samochod samochod : wczytajKopieSamochod().values()){
            if(samochod.getIduzytkownika()==idwlascicela)
                System.out.println(samochod);
        }
    }


    public void wyswietldostepne(){
         System.out.println("Wszystkie dostepne pojazdy");
         for(Samochod samochod : wczytajKopieSamochod().values()){
             if(samochod.isDostepny()==true)
                 System.out.println(samochod);
         }


     }

    public void wypozycz(String id, String idk) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();
        Samochod samochod = samochody.get(id);
        if (samochod != null&& samochod.getIduzytkownika()==null) {
            samochod.setDostepny(false);
            samochod.setIduzytkownika(idk);  // Poprawiony fragment kodu
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został wypożyczony");
        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id +" lub zostal juz wypozyczony");
        }
    }

    public void zwroc(String id) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();
        Samochod samochod = samochody.get(id);
        if (samochod != null&&samochod.getIduzytkownika()!=null) {
            samochod.setDostepny(true);
            samochod.setIduzytkownika(null); // Ustawienie wartości null
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został zwrócony");
        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id+" lub nie jest on wypozyczony");
        }
    }

    private HashMap<String,Samochod> wczytajKopieSamochod(){
        return new HashMap<>( menagerDanych.wczytajPojazd());
    }
}
