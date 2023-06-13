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
    public void usunKlienta(String idKlienta, int ileaut ) {

            HashMap<String, Klient> klienci = menagerKlient.wczytajKlientow();
            if (klienci.containsKey(idKlienta)&&ileaut==0) {
                klienci.remove(idKlienta);
                menagerKlient.zapiszKlientow(klienci);
                System.out.println("Klient o identyfikatorze " + idKlienta + " został usunięty.");
            } else if(ileaut>0&&klienci.containsKey(idKlienta)) {
                System.out.println("Ten klient wciaz ma wypozyczone auto.");
            }
            else{
                System.out.println("Nie znaleziono klienta o podanym identyfikatorze.");
            }

    }
}

