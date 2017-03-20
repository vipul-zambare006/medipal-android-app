package sg.edu.nus.medipalapplication.MedipalFolder;

/**
 * Created by Govardhan on 20/3/2017.
 */

public class Category {

    private String categorycode, categoryname, categorydescription;
    private int id;

    public Category() {

    }

    public Category(int id, String categorycode, String categoryname, String categorydescription) {
        this.id = id;
        this.categorycode = categorycode;
        this.categoryname = categoryname;
        this.categorydescription = categorydescription;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}