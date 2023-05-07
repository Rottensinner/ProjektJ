package MainFiles;

public class Samochod {

    private int id;
    private String marka;
    private String model;
    private int rocznik;

   public Samochod(int id, String marka, String model, int rocznik)
   {
       this.id=id;
       this.marka=marka;
       this.model=model;
       this.rocznik=rocznik;
   }

    public int getId() {
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

    @Override
    public String toString() {
        return "Samochod{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rocznik=" + rocznik +
                '}';
    }
}
