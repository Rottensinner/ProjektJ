package MainFiles;

import java.util.HashMap;

public class SpisAut {

     private HashMap<Integer,Samochod> samochody = new HashMap<>();

     public void dodajSamochod(Samochod samochod)
     {
         samochody.put(samochod.getId(),samochod);
     }

}
