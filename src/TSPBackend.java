

/* 
 * TSPBackend.java
 * 
 * Template Class receives user input in the form of a series of cities chosen by the 
 * user, the TSPBackend then adds each city to a linked list. The class, and 
 * the inner class cities provide a number of public methods that allow the 
 * program to manipulate private data in the class
 *
 * @author Stephen Dudeney, stephen.dudeney@gmail.com
 * @version: 05/05/2017
 *
 * 
 */

import java.util.*;

public class TSPBackend {
    
    private class City {
        private int id;
        private boolean visited;
        private String name;
        private double xloc;
        private double yloc;
        private City next;
        
    }
    
    private City head;
    private City trav;
    private int length;
    private double xlocTSP;
    private double ylocTSP;
    private static double[][] matrix;
    
    public TSPBackend() {
        
        head = new City();
        trav = new City();
        length = 0;
        matrix = null;
        xlocTSP = 0;
        ylocTSP = 0;
    }
    
    /*
    * public method, receives a string paramenter and adds a city with that name
    * to the beginning of the linked list of cities, depending on the name of 
    * the city, the x location and y location of the city are updated,
    * it also records id, which is the order in which the city was added and 
    * sets the visited field of the link to false.
    *
    * @param String s
    */
    
    public void addCity (String s) {
        
        City newCity = new City();
        
        switch (s) {
            case "Boston" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Boston"; 
                newCity.xloc = 71.0588801;
                newCity.yloc = 42.3600825;
               
                if (head == null) { 
                    newCity.next = null;
                }
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
            
            case "New York" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "New York"; 
                newCity.xloc = 74.0059413;
                newCity.yloc = 40.7127837;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Washington DC" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Washington DC"; 
                newCity.xloc = 77.0368707;
                newCity.yloc = 38.9071923;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                    //head = newCity;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Chicago" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Chicago"; 
                newCity.xloc = 87.6297982;
                newCity.yloc = 41.8781136;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Denver" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Denver"; 
                newCity.xloc = 104.990251;
                newCity.yloc = 39.7392358;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                } 
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Dallas" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Dallas"; 
                newCity.xloc = 96.796987899;
                newCity.yloc = 32.7766642;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Los Angeles" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Los Angeles"; 
                newCity.xloc = 118.2436849;
                newCity.yloc = 34.0522342;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Miami" : 
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Miami"; 
                newCity.xloc = 80.1917902;
                newCity.yloc = 25.7616798;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                    //head = newCity;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Portland" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Portland"; 
                newCity.xloc = 122.6764816;
                newCity.yloc = 45.5230622;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Seattle" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Seattle"; 
                newCity.xloc = 122.3320708;
                newCity.yloc = 47.6062095;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Minneapolis" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Minneapolis"; 
                newCity.xloc = 93.2650108;
                newCity.yloc = 44.977753;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Cleveland" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Cleveland"; 
                newCity.xloc = 81.6943605;
                newCity.yloc = 41.49932;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                    
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Philadelphia" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Philadelphia"; 
                newCity.xloc = 75.1652215;
                newCity.yloc = 39.9525839;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;    
                
            case "Detroit" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Detroit"; 
                newCity.xloc = 83.0457538;
                newCity.yloc = 42.331427;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break; 
               
            case "St. Louis" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "St. Louis"; 
                newCity.xloc = 90.19940419999999;
                newCity.yloc = 38.6270025;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Atlanta" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Atlanta"; 
                newCity.xloc = 84.3879824;
                newCity.yloc = 33.7489954;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Houston" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Houston"; 
                newCity.xloc = 95.3698028;
                newCity.yloc = 29.7604267;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "San Antonio" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "San Antonio"; 
                newCity.xloc = 98.49362819999999;
                newCity.yloc = 29.4241219;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "San Francisco" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "San Francisco"; 
                newCity.xloc = 122.4194155;
                newCity.yloc = 37.7749295;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Boise City" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Boise City"; 
                newCity.xloc = 116.2146068;
                newCity.yloc = 43.6187102;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Charleston" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Charleston"; 
                newCity.xloc = 79.93105120000001;
                newCity.yloc = 32.7764749;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Nashville" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Nashville"; 
                newCity.xloc = 86.7816016;
                newCity.yloc = 36.1626638;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Birmingham" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Birmingham"; 
                newCity.xloc = 86.80248999999999;
                newCity.yloc = 33.5206608;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Cincinnati" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Cincinnati"; 
                newCity.xloc = 84.5120196;
                newCity.yloc = 39.1031182;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            case "Louisville" :
                newCity.id =length;
                newCity.visited = false;
                newCity.name = "Louisville"; 
                newCity.xloc = 85.7584557;
                newCity.yloc = 38.2526647;
               
                if (head == null) newCity.next = null; 
                else {
                    newCity.next = head;
                }
                
                head = newCity;
                trav = head;
                xlocTSP = newCity.xloc;
                ylocTSP = newCity.yloc;
                length++;
                break;
                
            default: break;
        }
    }
    
    /*
    *   public method traverses along the linked list of cities, creating
    *   a 2D matrix of the distances between the cities for easy retrieval
    *   of distances
    */
    
    public void createMatrix() {
        
        City start = new City();
        City move = new City();
        start = head;
        move = head;
        
        if (length > 1) {
            matrix = new double[length][length];
        
            for (int i = length - 1; i >= 0 ; i--) {
                for (int j = length - 1; j >= 0; j--) {
                    
                    if(!start.name.equals(move.name)) {
                        double x = start.xloc - move.xloc;
                        double y = start.yloc - move.yloc;
                        matrix[i][j] = Math.sqrt(x*x + y*y);
                    }
                    move = move.next;
                }
                move = head;
                start = start.next;
            }
        }
    }
    
    //public method retruns trav to the head of the linked list of cities
    
    public void reset() {
        trav = head;
    }
    
    //public method returns boolean if trav reaches the end of the linked list
    public boolean atEnd() {
        if (trav.next == null) {
            return true;
        }
        
        return false;
    }
    
    //public method returns boolean has anoth link lin in the list
    public boolean hasNext() {
        
        if (trav.next != null) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    //public getter method returns the name of the city
    public String getId() {
        return trav.name;
    }
    
    //public getter method returns the id of the city
    public int getOrder() {
        return trav.id;
    }
    
    //public getter method returns the x location of the city
    public double getXloc() {
        
        return trav.xloc;
    }
    
    //public getter method returns the y location of the city
    public double getYloc() {
        return trav.yloc;
    }
    
    //public setter method updates the visited field of the city link
    public void setVisited(boolean n) {
        trav.visited = n;
    }
    
    //public getter method returns boolean if city visited
    public boolean getVisited() {
        return trav.visited;
    }
    
    //resets all the visited fields of the cities in the linked list
    public void reinitCity() {
        
        while (trav != null) {
            trav.visited =false;
            trav = trav.next;
        }
        
        trav = head;
    }
    
    //public method moves trav along the linked list
    public void move() {
       
        trav = trav.next;
    }
    
    //public getter method returns the lenght of the linked list
    public int getLength() {
        return length;
    }
    
    //public getter method returns a reference to the matrix created by the program
    public double[][] getMatrix() {
        return matrix;
    }
    
    //public getter method returns the distance between two cities from the matrix
    public double returnDistance(int n, int x) {
        
        double returnDb = matrix[n][x];
        
        return returnDb;
    }
} 
