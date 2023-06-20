package Client;

public class Repair {
    private int id_repair;
    private String date;
    private String rcustumer;
    private String master;
    private String rdevice;
    private String material;
    private String rdop_info;

    public Repair(int id_repair, String date, String rcustumer, String master, String rdevice, String material, String rdop_info) {
        this.id_repair = id_repair;
        this.date = date;
        this.rcustumer = rcustumer;
        this.master = master;
        this.rdevice = rdevice;
        this.material = material;
        this.rdop_info = rdop_info;
    }

    public int getId_repair() {
        return id_repair;
    }

    public String getDate() {
        return date;
    }

    public String getRcustumer() {
        return rcustumer;
    }

    public String getMaster() {
        return master;
    }

    public String getRdevice() {
        return rdevice;
    }

    public String getMaterial() {
        return material;
    }

    public String getRdop_info() {
        return rdop_info;
    }
}


