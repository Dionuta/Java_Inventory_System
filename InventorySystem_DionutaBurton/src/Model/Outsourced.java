package Model;

/**
 * Outsource class
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class Outsourced extends Part{

    private String companyName; //name of the company that the part was made.

    /**
     * Class constructor.
     *
     * @param id a unique identifier for the part. Type: Integer
     * @param name the name of the Outsourced  part. Type: String
     * @param price the cost of the part. Type: Double
     * @param stock the total number of a part. Type: Integer
     * @param min the minimal amount of a part: Type: Integer
     * @param max the maximum amount of a part: Type: Integer
     * @param companyName name of the company that the part was made. Type: String
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * setCompanyName set the companyName;
     *
     * @param companyName used to set companyName;
     */

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * getCompanyName return the company Name
     *
     * @return getCompanyName Type: String
     */

    public String getCompanyName(){
        return this.companyName;
    }


}
