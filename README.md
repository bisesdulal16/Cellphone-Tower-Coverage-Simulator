# Cellphone Tower Coverage Simulator

## Project Description
The "Cellphone Tower Coverage Simulator" is a Java application designed to demonstrate the use of Object-Oriented Programming principles, data structures, and file operations in a practical setting. This project involves reading data from text files to create instances of `CellTower` and `Waypoint`, which represent physical locations and their respective coverage areas. The application calculates the coverage for various predefined journeys by determining the availability of signal coverage at each waypoint and its corresponding midpoints. This simulation aids in understanding how data can be manipulated and assessed in real-world scenarios like network coverage planning.

## Features

- **Robust Data Validation:** The program ensures that all input data from files is valid for creating instances of `CellTower` and `Waypoint`. It effectively handles errors and skips invalid records, providing console feedback about each skipped entry.

- **Modular Class Design:** Utilizes classes with encapsulated fields and methods, demonstrating the principles of Object-Oriented Design. Each class is responsible for a specific piece of functionality within the application.

- **Efficient Data Management:** Uses HashMaps to store and manage collections of `CellTower` and `Waypoint` objects, enabling efficient data retrieval necessary for the simulation processes.

- **Simulation of Journeys:** Analyzes and reports on the coverage for predefined journeys. Calculates distances between waypoints and the coordinates of midpoints, providing a detailed navigational and coverage map.

- **Coverage Analysis:** Determines the nearest cellphone tower in range for each waypoint and midpoint along the journey, reporting areas with and without signal coverage.

- **Comprehensive Reporting:** Outputs a detailed report in a text file, including journey descriptions, distances, midpoints, and coverage details, formatted for easy interpretation.

- **Interactive Console Output:** Displays processing details such as the number of objects created and reasons for any data record rejections, enhancing transparency and user interaction.

## How to Run

### Prerequisites
- Java 11 or newer.

### Setup and Execution
1. Clone the repository:
  git clone https://github.com/bisesdulal16/Cellphone-Tower-Coverage-Simulator
2. Compile the Java files:
  CellTower.java Waypoint.java BDProjectThree.java
3. Run the application

## Contact
For any additional questions or contributions, please contact:

**GitHub**: @bisesdulal16

**Email**: bisesdulal16@gmail.com
