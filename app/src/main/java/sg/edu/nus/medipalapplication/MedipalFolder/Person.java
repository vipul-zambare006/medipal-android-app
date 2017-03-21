package sg.edu.nus.medipalapplication.MedipalFolder;
import sg.edu.nus.medipalapplication.database.PersonDAO;
/**
 * Created by Gaurav on 19-03-2017.
 */
public class Person {
    private int id;
    private String name;
    private String idno;
    private String dateofbirth;
    private String address;
    private String bloodtype;
    private String postalcode;
    private String height;
    public Person(String name,String idno, String dateofbirth,String address,String bloodtype,String postalcode,String height,int id ) {
        this.name = name;
        this.idno = idno;
        this.dateofbirth = dateofbirth;
        this.address = address;
        this.bloodtype = bloodtype;
        this.postalcode = postalcode;
        this.height = height;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getIdno() {
        return idno;
    }
    public String getDateofbirth() {
        return dateofbirth;
    }
    public String getAddress() {
        return address;
    }
    public String getBloodtype() {
        return bloodtype;
    }
    public String getPostalcode() {
        return postalcode;
    }
    public String getHeight() {
        return height;
    }
    public int getId(){ return id; }
    public void addPerson(Person person, PersonDAO personDAO){
        personDAO.addPerson(person);
    }
    public void UpdatePerson(Person persontoupdate, PersonDAO personDAO){
        personDAO.UpdatePerson(persontoupdate);
    }
}