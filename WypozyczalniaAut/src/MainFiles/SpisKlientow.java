package MainFiles;

import java.util.HashMap;

public class SpisKlientow {
    private MenagerKlient menagerKlient;
    private HashMap<String,Klient> klienci = new HashMap<>();
    public SpisKlientow(MenagerKlient menagerKlient){
        this.menagerKlient = menagerKlient;
    }

    public void dodajKlienta(Klient klient) {
        HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
        klienci.put(klient.getId(), klient);
        menagerKlient.zapiszKlientow(klienci);
    }
    public boolean sprawdzKlienta(String id) {
        HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
        return klienci.containsKey(id);
    }

    public void wyswietlKlientow() {
        HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
        System.out.println("Wszyscy klienci");
        for (Klient klient : klienci.values()) {
            System.out.println(klient);
        }

}

    public Klient getKlient(String idKlienta) {
        HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
        return klienci.get(idKlienta);
    }

}

