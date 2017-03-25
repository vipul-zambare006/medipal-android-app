package sg.edu.nus.medipalapplication.MedipalFolder;
import android.database.Cursor;

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

    public Person(){
    }
    public Person(int id,String name,String idno, String dateofbirth,String address,String bloodtype,String postalcode,String height ) {
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

    public void addPerson(Person person, PersonDAO personDAO)
    {
        personDAO.addPerson(person.name, person.idno, person.dateofbirth, person.address, person.bloodtype, person.postalcode, person.height);
    }
    public void UpdatePerson(Person persontoupdate, PersonDAO personDAO){
        personDAO.UpdatePerson(persontoupdate);
    }
    public Cursor getperson(PersonDAO personDAO) {

        return personDAO.GetPerson();
    }
}