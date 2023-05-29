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

     public void wyswietlPojazdy(){
         System.out.println("Wszystkie samochody");

         for(Samochod samochod : wczytajKopieSamochod().values()){
             System.out.println(samochod);
         }


     }



    public void wyswietldostepne(){
         System.out.println("Wszystkie dostepne pojazdy");
         for(Samochod samochod : wczytajKopieSamochod().values()){
             if(samochod.isDostepny()==false)
                 System.out.println(samochod);
         }


     }

     public void wypozycz(String id){
         HashMap<String, Samochod> samochody = wczytajKopieSamochod();
         samochody.get(Integer.valueOf(id)).setDostepny(false);
         menagerDanych.zapiszPojazd(samochody);
         System.out.println("Pojazd o id "+id+" został wypozyczony");
     }

     public void zwroc(String id){
         HashMap<String, Samochod> samochody = wczytajKopieSamochod();

         samochody.get(Integer.valueOf(id)).setDostepny(true);
         menagerDanych.zapiszPojazd(samochody);
         System.out.println("Pojazd o id "+id+" został zwrocony");
     }
    private HashMap<String,Samochod> wczytajKopieSamochod(){
        return new HashMap<>( menagerDanych.wczytajPojazd());
    }
}
