
package bdprojectthree;

/**
 *
 * @author Bishesh Dulal
 */
public class Waypoint {
    private String name; //A unique name used to identify each way point
    private double x; //the x-coordinate of the waypoint
    private double y; //the y-coordinate of the waypoint
    
    /**
     * Constructor for the Way point class.
     * @param name a unique name used to identify each way point
     * @param x the x-coordinate of the way point
     * @param y the y-coordinate of the way point
     */
    public Waypoint(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

     /**
     * Method to get the name of the way point.
     * @return name of the way point.
     */
    public String getName() {
        return name;
    }

     /**
     * Method to get the name of the way point.
     * @return name of the way point.
     */
    public double getX() {
        return x;
    }

     /**
     * Method to get the name of the way point.
     * @return name of the way point.
     */
    public double getY() {
        return y;
    }
    
    @Override
     /**
     * Method to get the string output of way point output.
     * @return string formatted output
     */
    public String toString(){
        return "Name: "+name+", X-coordinate: "+x+", Y-coordinate: "+y;
    }
}
