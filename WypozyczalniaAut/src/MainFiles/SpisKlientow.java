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
        return klienci.containsKey(id);
    }

    public void wyswietlKlientow() {
        HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
        System.out.println("Wszyscy klienci");
        for (Klient klient : klienci.values()) {
            System.out.println(klient);
        }
<<<<<<< HEAD
=======
}
    public Klient getKlient(String idKlienta) {
        return klienci.get(idKlienta);
>>>>>>> bd34d1299b720fbc97be6d64078b998783c05838
    }
}

