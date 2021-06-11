package Model;


/**
 * In House class
 *
 * created by Dionuta Burton
 * Check out https://taycodes.com/
 */

public class InHouse extends Part {

    private int machineId; // special ID for parts Made In House


    /**
     * Class constructor.
     *
     * @param id a unique identifier for the part. Type: Integer
     * @param name the name of the InHouse part. Type: String
     * @param price the cost of the part. Type: Double
     * @param stock the total number of a part. Type: Integer
     * @param min the minimal amount of a part: Type: Integer
     * @param max the maximum amount of a part: Type: Integer
     * @param machineId identifier of the machine that made the part: Type: Integer
     */


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {

        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * setMachineId set the machine ID
     *
     * @param machineId used to set machine ID
     */

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * getMachineId return the machine ID
     *
     * @return machineId Type: Integer
     */

    public int getMachineId(){
        return this.machineId;
    }

}
