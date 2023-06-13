package MainFiles;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Klient {

    private String imie;
    private String nazwisko;
    private int telefon;
    private String id;


    @JsonCreator
    public Klient(
            @JsonProperty("imie") String imie,
            @JsonProperty("nazwisko") String nazwisko,
            @JsonProperty("telefon")String telefon,
            @JsonProperty("id")String id) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = Integer.valueOf(telefon);
        this.id = id;
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
                '}';
    }
}
