package MainFiles;

import java.util.HashMap;

public class SpisKlientow {

    private HashMap<String,Klient> klienci = new HashMap<>();


    public void dodajKlienta(Klient klient)
    {

        klienci.put(klient.getId(),klient);

    }

    public boolean sprawdzKlienta(String id) {
        return klienci.containsKey(id);
    }

    public void wyswietlKlientow(){
        System.out.println("Wszyscy klienci");

        for(Klient klient : klienci.values()){
            System.out.println(klient);
        }
}
    public Klient getKlient(String idKlienta) {
        return klienci.get(idKlienta);
    }
}

