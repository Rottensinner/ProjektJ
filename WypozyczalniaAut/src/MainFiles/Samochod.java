package MainFiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Samochod {

    private String id;
    private String marka;
    private String model;
    private int rocznik;

    private String iduzytkownika=null;

    private boolean dostepny = true;
    @JsonCreator
   public  Samochod(@JsonProperty("id") String id,
                    @JsonProperty("marka") String marka,
                    @JsonProperty("model") String model,
                    @JsonProperty("rocznik") int rocznik)

    {
       this.id=id;
       this.marka=marka;
       this.model=model;
       this.rocznik=rocznik;
    }

    public String getId() {
        return id;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRocznik() {
        return rocznik;
    }

    public String getIduzytkownika() {
        return iduzytkownika;
    }


    public void setIduzytkownika(String iduzytkownika) {
        this.iduzytkownika = iduzytkownika;
    }

    public boolean isDostepny() {
        return dostepny;
    }

    public void setDostepny(boolean dostepny) {
        this.dostepny = dostepny;
    }



    @Override
    public String toString() {
        return "Samochod{" +
                "id='" + id + '\'' +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rocznik=" + rocznik +
                ", iduzytkownika=" + iduzytkownika +
                ", dostepny=" + dostepny +
                '}';
    }
}
