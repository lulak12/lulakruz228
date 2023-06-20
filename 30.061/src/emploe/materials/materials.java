package emploe.materials;

public class materials {
private int id_materials;
private String title;
    private String price;
    private String tipe;
    private String amount;

    public materials(int id_materials, String title, String price, String tipe, String amount) {
        this.id_materials = id_materials;
        this.title = title;
        this.price = price;
        this.tipe = tipe;
        this.amount = amount;
    }

    public int getId_materials() {
        return id_materials;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getTipe() {
        return tipe;
    }

    public String getAmount() {
        return amount;
    }
}
