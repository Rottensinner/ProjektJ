package MainFiles;

import java.util.HashMap;

public class SpisAut {

     private HashMap<Integer,Samochod> samochody = new HashMap<>();

     public void dodajSamochod(Samochod samochod)
     {
         samochody.put(Integer.valueOf(samochod.getId()),samochod);
     }

     public void wyswietlPojazdy(){
         System.out.println("Wszystkie samochody");

         for(Samochod samochod : samochody.values()){
             System.out.println(samochod);
         }


     }
     public void wyswietldostepne(){
         System.out.println("Wszystkie dostepne pojazdy");
         for(Samochod samochod : samochody.values()){
             if(samochod.isDostepny()==false)
                 System.out.println(samochod);
         }


     }

     public void wypozycz(String id){
         samochody.get(Integer.valueOf(id)).setDostepny(false);
         System.out.println("Pojazd o id "+id+" został wypozyczony");
     }

     public void zwroc(String id){
         samochody.get(Integer.valueOf(id)).setDostepny(true);
         System.out.println("Pojazd o id "+id+" został zwrocony");
     }
}
