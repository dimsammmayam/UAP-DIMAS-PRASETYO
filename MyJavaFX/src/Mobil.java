public class Mobil {
    private String nomorPolisi;
    private String jenisMobil;
    private double hargaSewa;
    private String status;

    public Mobil(String nomorPolisi, String jenisMobil, double hargaSewa, String status) {
        this.nomorPolisi = nomorPolisi;
        this.jenisMobil = jenisMobil;
        this.hargaSewa = hargaSewa;
        this.status = status;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public String getStatus() {
        return status;
    }
}

