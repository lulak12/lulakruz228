package emploe.cuctumers;

public class custumers {
    private int id;
    private String name;
    private String surname;
    private int contacts;
    private int sale;

    public custumers (int id, String name, String surname, int contacts, int sale) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contacts = contacts;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getContacts() {
        return contacts;
    }

    public int getSale() {
        return sale;
    }
}
