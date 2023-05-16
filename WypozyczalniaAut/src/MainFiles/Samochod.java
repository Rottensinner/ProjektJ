package MainFiles;

public class Samochod {

    private String id;
    private String marka;
    private String model;
    private int rocznik;

    private boolean dostepny = true;

   public Samochod(String id, String marka, String model, int rocznik)
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

    public boolean isDostepny() {
        return dostepny;
    }

    public void setDostepny(boolean dostepny) {
        this.dostepny = dostepny;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rocznik=" + rocznik +
                ", dostepny=" + dostepny +
                '}';
    }
}
