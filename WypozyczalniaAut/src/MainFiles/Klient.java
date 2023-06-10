package MainFiles;

public class Klient {

    private String imie;
    private String nazwisko;
    private int telefon;
    private String id;

    private int iloscaut =0;

    public Klient(String imie, String nazwisko, String telefon, String id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = Integer.valueOf(telefon);
        this.id = id;
    }

    public void  inkrementacja(){

        iloscaut++;
    }

    public void  dekrementacja(){

        iloscaut++;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getTelefon() {
        return telefon;
    }

    public String getId() {
        return id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getIloscaut() {
        return iloscaut;
    }

    public void setIloscaut(int iloscaut) {
        this.iloscaut = iloscaut;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Klient{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", telefon=" + telefon +
                ", id='" + id + '\'' +
                ", iloscaut=" + iloscaut +
                '}';
    }
}
