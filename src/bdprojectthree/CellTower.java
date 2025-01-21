
package bdprojectthree;

/**
 *
 * @author Bishesh Dulal
 */
public class CellTower {
    private String name; //a unique name used to identify each cellphone tower
    private double x; //x-coordinate of the tower's location
    private double y; //y-coordinate of the tower's location
    private double range; //the effective range of the cellphone tower
    
    /**
     * Constructor for the cellTower class that requires all the instance fields to be specified as argument.
     * @param name a unique name used to identify each cellphone tower.
     * @param x the x-coordinate of the tower's location.
     * @param y the y-coordinate of the tower's location.
     * @param range the effective range of this cellphone tower.
     */
    public CellTower(String name, double x, double y, double range){
        //validate either x-coordinate, y-coordinate or the range and throw exception.
        if(x<0||y<0||range<=0){
            throw new IllegalArgumentException("Coordinates and range must be positive.");
        }
            this.name = name;
            this.x = x;
            this.y = y;
            this.range = range;
            
       
        
    }

    /**
     * Method to get the name of the cell tower.
     * @return name of the cell tower.
     */
    public String getName() {
        return name;
    }


    /**
     * Method to get the x-coordinate of the tower's location.
     * @return  x-coordinate of the tower's location.
     */
    public double getX() {
        return x;
    }

    /**
     * Method to get the y-coordinate of the tower's location.
     * @return y-coordinate of the tower's location.
     */
    public double getY() {
        return y;
    }

    /**
     * Method to get the effective range of the cellphone tower.
     * @return range of the cellphone tower.
     */
    public double getRange() {
        return range;
    }

    @Override
    /**
     * Method to get the string output of cellTower output.
     * @return string formatted output
     */
    public String toString(){
        return "CellTower{" +
               "name='" + name + '\'' +
               ", x=" + x +
               ", y=" + y +
               ", range=" + range +
               '}';
    }
    
    
}
