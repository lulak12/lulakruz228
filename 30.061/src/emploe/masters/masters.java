package emploe.masters;

public class masters {
    private int id_masters;
    private String name_master;
    private String surname_master;
    private String position;
    private String phone;

    public masters(int id_masters, String name_master, String surname_master, String position, String phone) {
        this.id_masters = id_masters;
        this.name_master = name_master;
        this.surname_master = surname_master;
        this.position = position;
        this.phone = phone;
    }

    public int getId_masters() {
        return id_masters;
    }

    public String getName_master() {
        return name_master;
    }

    public String getSurname_master() {
        return surname_master;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }
}
