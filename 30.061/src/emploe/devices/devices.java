package emploe.devices;

public class devices {
    private int id_dev;
    private String name_dev;
    private String description;

    public devices(int id_dev, String name_dev, String description) {
        this.id_dev = id_dev;
        this.name_dev = name_dev;
        this.description = description;
    }

    public int getId_dev() {
        return id_dev;
    }

    public String getName_dev() {
        return name_dev;
    }

    public String getDescription() {
        return description;
    }
}


