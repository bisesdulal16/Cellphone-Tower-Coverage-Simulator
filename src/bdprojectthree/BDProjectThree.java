package bdprojectthree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;

/**
 * Main class for program that manages cell towers, way points, and generate
 * reports
 *
 * @author Bishesh Dulal
 */
public class BDProjectThree {

    /**
     * Hash map to store cell towers.
     */
    private static HashMap<String, CellTower> cellTowers = new HashMap<>();

    /**
     * Hash map to store way points.
     */
    private static HashMap<String, Waypoint> waypoints = new HashMap<>();

    /**
     * ArrayList to store the journeys. Each journey is represented as an array
     * of way points.
     */
    private static ArrayList<String[]> journeys = new ArrayList<>();

    /**
     * Main method to run the application. Prompts user for the file name and
     * call other methods.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instance of a scanner class
        Scanner input = new Scanner(System.in);
        
        //prompt user for a file containg cell towers,waypoint and journeys info, and read the file name
        System.out.print("Enter a file containing the CellTower defintions: ");
        String cellTowerFile = input.nextLine();
        System.out.println(""); //empty line

        System.out.print("Enter a file containing the Waypoint defintions: ");
        String waypointFile = input.nextLine();
        System.out.println("");//empty line

        System.out.print("Enter a file containing the Journeys defintions: ");
        String journeyFile = input.nextLine();
        System.out.println(""); //empty line

        //call methods to process journey and generate report
        processCellTowers(cellTowerFile);
        System.out.println("");//empty line
        processWaypoints(waypointFile);
        System.out.println("");//empty line
        processJourneys(journeyFile);
        generateReport("report.txt");

    }

    /**
     * Processes the cell towers file to store valid cell tower data.
     *
     * @param cellTowerFile Path/name to the cell tower data file.
     */
    private static void processCellTowers(String cellTowerFile) {
        //read cellTowerFile
        File file = new File(cellTowerFile);

        //records in the file
        int recordCreated = 0, recordSkipped = 0, totalRecords = 0;

        //try catch exception while reading the file
        try (Scanner inputFile = new Scanner(file)) {

            //loop to iterate until the file has next line
            while (inputFile.hasNextLine()) {
                //line of the file
                String line = inputFile.nextLine();
                //increment total records in the file
                totalRecords++;
                try {
                    //split comma-delimited text
                    String[] tokens = line.split(",");

                    //throw exception if the length is not equal to 4
                    if (tokens.length != 4) {
                        throw new IllegalArgumentException("Incorrect number of fields.");
                    }
                    //assign fields of the celltowers
                    String name = tokens[0];
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    double range = Double.parseDouble(tokens[3]);

                    //validate x-coordinate, y-coordinate and range of the record and throw exception 
                    if (x < 0 || y < 0) {
                        throw new IllegalArgumentException("Celltower record " + totalRecords + " has invalid coordinate (s).");
                    } else if (range <= 0) {
                        throw new IllegalArgumentException("Celltower record " + totalRecords + " has an invalid radius.");
                    }

                    //new instance of a celltower
                    CellTower tower = new CellTower(name, x, y, range);
                    //put celltowers in the hashmap
                    cellTowers.put(name, tower);
                    //increment valid records
                    recordCreated++;

                } //catch exceptions thrown
                catch (NumberFormatException e) {
                    recordSkipped++; //increment skipped records
                    System.out.printf("%-2sSkipping celltower record %d: %s%n", "", totalRecords, line);
                    System.out.printf("%-4sNumber Format Exception in celltower record %d.%n ", "", totalRecords);
                    System.out.printf("%-3sSystem message: %s%n", "", e.getMessage());
                } catch (IllegalArgumentException e) {
                    recordSkipped++;
                    System.out.printf("%-2sSkipping celltower record %d: %s%n", "", totalRecords, line);
                    System.out.printf("%-4s%s%n", "", e.getMessage());
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("File " + cellTowerFile + " not found. Check your file path and try again!");
        }
        System.out.println("Information for " + recordCreated + " cell towers defined. " + recordSkipped + " Records Skipped.");
    }

    /**
     * Process the way point file to store valid way point data.
     *
     * @param waypointFile Path/name to the way points data file.
     */
    private static void processWaypoints(String waypointFile) {
        File file = new File(waypointFile);

        //keep record of file data
        int recordCreated = 0, recordSkipped = 0, totalRecords = 0;
        try (Scanner inputFile = new Scanner(file)) {

            //loop to continue while the file has next line
            while (inputFile.hasNextLine()) {
                //line of the file and trim unnecessary spaces
                String line = inputFile.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                } //continue when the line is empty
                //increment records count
                totalRecords++;
                try {
                    String[] tokens = line.split(",");
                    //throw exception when the length is not 3
                    if (tokens.length != 3) {
                        throw new IllegalArgumentException("Incorrect number of fields.");
                    }
                    //assign fields of waypoints
                    String name = tokens[0];
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);

                    //validate x,y coordinate of the waypoints data and throw exception
                    if (x < 0 || y < 0) {
                        throw new IllegalArgumentException(String.format("%-2sWaypoint record %d has invalid coordinate (s).", "", totalRecords));
                    }

                    //instance of a waypoint
                    Waypoint waypoint = new Waypoint(name, x, y);
                    //put waypoint in the waypoints hashmap
                    waypoints.put(name, waypoint);
                    //increment valid records
                    recordCreated++;

                } //catch all the excemption thrown and get messages related to it
                catch (NumberFormatException e) {
                    //increment skipped records
                    recordSkipped++;
                    System.out.printf("%-2sSkipping waypoint record %d: %s%n", "", totalRecords, line);
                    System.out.printf("%-2sNumber Format Exception at record %d.%n", "", totalRecords);
                    System.out.printf("%-2sSystem message: %s%n", "", e.getMessage());
                } catch (IllegalArgumentException e) {
                    recordSkipped++;
                    System.out.printf("%-2sSkipping waypoint record %d: %s%n", "", totalRecords, line);
                    System.out.printf("%-2s%s%n", "", e.getMessage());
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File " + waypointFile + " not found. Check your file path and try again!");
        }
        System.out.println("Information for " + recordCreated + " waypoints defined. " + recordSkipped + " Records Skipped.");

    }

    /**
     * Process the file containing journeys data to store defined way point.
     *
     * @param journeyFile Path/name to the journeys file.
     */
    private static void processJourneys(String journeyFile) {
        File file = new File(journeyFile);
        //total journey record
        int journeysCount = 0;
        //number of comma delimited waypoint in journey
        int totalWaypoints;
        boolean skippedWaypoint;

        try (Scanner inputFile = new Scanner(file)) {
            //loop until the file has next line
            while (inputFile.hasNextLine()) {
                //increment journey records
                skippedWaypoint = false;
                journeysCount++;
                totalWaypoints = 0;
                String line = inputFile.nextLine().trim();
                if (line.trim().isEmpty()) {
                    continue; //skip if the line is empty
                }
                String[] tokens = line.split(",");
                //new arraylist for valid way points
                ArrayList<String> validWaypoints = new ArrayList<>();

                for (String token : tokens) {
                    String name = token.trim();
                    if (waypoints.containsKey(name)) {
                        validWaypoints.add(name);
                        totalWaypoints++;
                    } else {
                        skippedWaypoint = true;
                        System.out.printf("%-2sSkipping undefined waypoint %s in %s journey.%n", "", name, getOrdinal(journeysCount));

                    }

                }
                if (!validWaypoints.isEmpty()) {
                    String[] waypointsArray = validWaypoints.toArray(new String[0]); //convert validWaypoints to array
                    journeys.add(waypointsArray);//add waypoints to  journeys if not empty
                }
                if (skippedWaypoint) {
                    System.out.println("There are " + totalWaypoints + " waypoints in this journey.");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + journeyFile + " not found. Check your file path and try again!");
        }

    }

    /**
     * Create a report file with date and time and valid journeys with defined
     * way points.
     *
     * @param reportFileName name/path of the report file
     */
    private static void generateReport(String reportFileName) {
        //generate the current time and date
        Date now = new Date();
        String date = now.toString();
        try (PrintWriter writer = new PrintWriter(reportFileName)) {
            writer.printf("%-5sCellphone Tower Coverage Report%n", "");
            writer.println("Prepared on: " + date);
            writer.println();

            for (int journeyIndex = 0; journeyIndex < journeys.size(); journeyIndex++) {
                String[] journey = journeys.get(journeyIndex);

                writer.println("The " + getOrdinal(journeyIndex + 1) + " journey consists of " + journey.length + " waypoints:");
                for (String waypoint : journey) {
                    writer.print(waypoint + " ");
                }
                writer.println("");

                writer.printf("%-29sMid-point:%n", "");
                writer.printf("%-1sFrom%-4sTo%-5sDistance%-2sX-coord%-2sY-coord%n", "", "", "", "", "");
                writer.printf("------%-2s------%-2s--------%-2s-------%-2s-------%n", "", "", "", "");

                if (journey.length > 1) {
                    for (int i = 0; i < journey.length - 1; i++) {
                        Waypoint wp1 = waypoints.get(journey[i]);
                        Waypoint wp2 = waypoints.get(journey[i + 1]);
                        double distance = calculateDistance(wp1, wp2);
                        double midpointX = calculateMidpointX(wp1, wp2);
                        double midpointY = calculateMidpointY(wp1, wp2);
                        writer.printf("%2s%4s%4s%4s%5s%4.2f%4s%5.2f%4s%5.2f%n", "", wp1.getName(), "", wp2.getName(), "", distance, "", midpointX, "", midpointY);
                    }
                }
                writer.println();
                writer.printf("Location%-3sTower%-3sProximity%n", "", "");
                writer.printf("--------%-2s-------%-2s---------%n", "", "");

                for (int j = 0; j < journey.length; j++) {
                    Waypoint wp1 = waypoints.get(journey[j]);
                    writer.printf("%-1s%-4s%-6s%-15s%n", "", wp1.getName(), "", checkCoverage(wp1.getX(), wp1.getY()));

                    if (j < journey.length - 1) {
                        Waypoint wp2 = waypoints.get(journey[j + 1]);
                        double midX = calculateMidpointX(wp1, wp2);
                        double midY = calculateMidpointY(wp1, wp2);
                        writer.printf("%-1sMid-Pt%-4s%-15s%n", "", "", checkCoverage(midX, midY));
                    }
                }
                writer.println();

            }
            writer.flush();

        } catch (FileNotFoundException ex) {
            System.out.println("The file " + reportFileName + "could not be found/created. Please try again.");
        }
    }

    /**
     * Get the ordinal numbering to identify the position of each journey
     * processed.
     *
     * @param number the number of the journey data in journeys file.
     * @return ordinal number
     *
     */
    private static String getOrdinal(int number) {
        switch (number) {
            case 1:
                return "First";
            case 2:
                return "Second";
            case 3:
                return "Third";
            case 4:
                return "Fourth";
            case 5:
                return "Fifth";
            case 6:
                return "Sixth";
            case 7:
                return "Seventh";
            case 8:
                return "Eighth";
            case 9:
                return "Ninth";
            case 10:
                return "Tenth";
            default:
                int lastTwoDigits = number % 100;
                int lastDigit = number % 10;
                //11,12, and 13 donot end with 1st,2nd or 3rd
                if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
                    return number + "th";
                } else {
                    switch (lastDigit) {
                        case 1:
                            return number + "st";
                        case 2:
                            return number + "nd";
                        case 3:
                            return number + "rd";
                        default:
                            return number + "th";
                    }
                }
        }
    }

    /**
     * Calculate the distance between two way points.
     *
     * @param wp1 the from way point.
     * @param wp2 the to way point.
     * @return distance between the from and to way point.
     */
    private static double calculateDistance(Waypoint wp1, Waypoint wp2) {
        return Math.sqrt(Math.pow(wp2.getX() - wp1.getX(), 2) + Math.pow(wp2.getY() - wp1.getY(), 2));
    }

    /**
     * Calculate the x-coordinate of the midpoint.
     *
     * @param wp1 first way point
     * @param wp2 second way point
     * @return x-coordinate of the midpoint.
     */
    private static double calculateMidpointX(Waypoint wp1, Waypoint wp2) {
        return (wp1.getX() + wp2.getX()) / 2;
    }

    /**
     * Calculate the y-coordinate of the midpoint.
     *
     * @param wp1 first way point
     * @param wp2 second way point
     * @return y-coordinate of the midpoint.
     */
    private static double calculateMidpointY(Waypoint wp1, Waypoint wp2) {
        return (wp1.getY() + wp2.getY()) / 2;
    }

    /**
     * Check and report whether there is cell phone coverage along the way from
     * one way point to the next.
     *
     * @param x x-coordinate of the midpoint
     * @param y y-coordinate of the midpoint
     * @return whether there is cell phone coverage
     */
    private static String checkCoverage(double x, double y) {
        CellTower nearestTower = null;
        double minDistance = Double.MAX_VALUE;

        for (CellTower tower : cellTowers.values()) {
            double distance = Math.sqrt(Math.pow(tower.getX() - x, 2) + Math.pow(tower.getY() - y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestTower = tower;
            }
        }

        if (nearestTower != null && minDistance <= nearestTower.getRange()) {
            String tower = nearestTower.getName();

            return String.format("%-5s%-7s%-3.1f", tower, "", minDistance);
        }
        return String.format("%-1sNo Coverage.", "");
    }

}
