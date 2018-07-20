package java2.project2;

/* 
 * TSPGUI.java
 * 
 * Program Class creates a user interface. The user chooses a set of cities and is able 
 * view the cities in the GUI. The user can confirm the shortest distance between
 * the cities and try to guess the shortest distance as a game. 
 *
 * @author Stephen Dudeney, stephen.dudeney@gmail.com
 * @version: 05/05/2017
 *
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class TSPGUI {
    
    public static void main (String[] args) {
        
        GraphGUI myScreen = new GraphGUI();
        myScreen.setVisible(true);
        
    }
}


class GraphGUI extends JFrame {
    
        //JFrame for Game
        //private static JFrame screen; 
        private static final int NUM_CIT = 25;      //number of city buttons
        private static JButton[] cities;            //array of city buttons
        private static JPanel citPanel;             //panel of city buttons
        private static JPanel commands;             //panel of command buttons
        private static JPanel bottomPanel;          //panel for buttons and text area
        private static JTextArea actions;           //text area for instructions
        private static JButton check;               //check command button    
        private static JButton reset;               //reset command button
        private static JButton getAnswer;           //get anser command button
        private static JButton exit;                //exit command button
        private static JButton directions;          //button provides directions for user
        private static Clicker newClicker;          //actionlistener and jpanel
        private static TSPBackend test;             //reference to TSPBackend object
        
       
        public GraphGUI() {
            
            do_layout();
        }
        
        private void do_layout() {
            //JFrame for Game
            this.setTitle("Traveling Salesperson Game"); 
            cities = new JButton[NUM_CIT];     //array of city buttons
            citPanel = new JPanel();              //panel of city buttons
            commands = new JPanel();              //panel of command buttons
            bottomPanel = new JPanel();           //panel for buttons and text area
            actions = new JTextArea();         //text area for instructions
            check = new JButton("Check");        //check command button    
            reset = new JButton("Reset");        //reset command button
            getAnswer = new JButton("Get Answer"); //get anser command button
            exit = new JButton("Exit");          //exit command button    
            newClicker = new Clicker();          //actionlistener and jpanel
            test = new TSPBackend();          //reference to TSPBackend object
            directions = new JButton("Directions");
        
            //JFrame initialization
            this.setSize(new Dimension(1200, 800));
            this.setLayout(new BorderLayout());
            //this.setVisible(true);
            this.setBackground(Color.RED);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Traveling Salesperson Game");
          
            //list of all the cities possible to visit
            String[] cityNames = {"Atlanta", "Birmingham", "Boise City", "Boston", 
            "Charleston", "Chicago", "Cincinnati", "Cleveland", "Dallas", 
            "Denver", "Detroit", "Houston", "Louisville", "Los Angeles", "Miami", 
            "Minneapolis", "Nashville", "New York", "Philadelphia", "Portland",  
            "San Antonio", "San Francisco", "Seattle", "St. Louis", "Washington DC"};
        
            //panel of buttons initialization
            citPanel.setLayout(new GridLayout(5, 0, 1, 1));
            commands.setLayout(new FlowLayout());
            bottomPanel.setLayout(new GridLayout(1, 2, 1, 1));
        
            //city buttons initialization
            for (int i = 0; i < NUM_CIT; i++ ) {
                cities[i] = new JButton();
                cities[i].setText(cityNames[i]);
                cities[i].addActionListener(newClicker);
                cities[i].setBackground(Color.MAGENTA);
                citPanel.add(cities[i]);
            }
        
            //initialize command buttons
            check.addActionListener(newClicker);
            reset.addActionListener(newClicker);
            getAnswer.addActionListener(newClicker);
            exit.addActionListener(new Exit());
            directions.addActionListener(new Directions());
        
            //initialize textArea
            actions.setText("Welcome to the Traveling Salesperson Game!"
                + "\nStart by clicking cities. Choose more than four up to ten."
                + "\nThe cities are added to scale."
                + "\nClick \"Check\" to find shortest route.\n"
                + "Click \"Reset\" to create new set.\nClick \"Exit\" to exit."
                + "\nStart at the same city every time and visit each city only once."
                + "\nSee if you can guess the shortest route!"
                + "\nDirections appear as the game proceeds so scroll down if "
                + "you have questions.\n");
            actions.setLineWrap(true);
            actions.setEditable(false);
        
            //add actions panel to scroll panel
            JScrollPane scroll = new JScrollPane (actions);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
            //set font for command buttons
            check.setFont(new Font("Times", Font.BOLD, 15));
            reset.setFont(new Font("Times", Font.BOLD, 15));
            exit.setFont(new Font("Times", Font.BOLD, 15));
            getAnswer.setFont(new Font("Times", Font.BOLD, 15));
            directions.setFont(new Font("Times", Font.BOLD, 15));
        
            //add command buttons to command panel
            commands.add(check);
            commands.add(reset);
            commands.add(getAnswer);
            commands.add(exit);
            commands.add(directions);
        
            //add text area and button panel to bottom panel
            bottomPanel.add(citPanel);
            bottomPanel.add(scroll);
        
            //add panels to JFrame
            this.add(commands, BorderLayout.NORTH);
            this.add(bottomPanel, BorderLayout.SOUTH);
            this.add(newClicker);
        }
    
    /*
    * class extends  JPanel and implements actionlistener, 
    * when button clicked, program completes various actions depending upon
    * which button is clicked and when. There are two major division of actions,
    * the creation stage and the guessing stage. In the creation stage, the user
    * clicks on buttons to create a master set of cities. Once this master set has
    * been created, and the user hasn't yet guessed the shortest route, the user
    * can try to guess different routes, and check to see if there guess was correct.
    */
    
    static class Clicker extends JPanel implements ActionListener{
        
        //these integer values hold the x and y locations of cities
        private int xloc1 = 0;
        private int yloc1 = 0;
        private int xloc2 = 0;
        private int yloc2 = 0;
        
        //reference to graph class, which confirms shortest distance
        Graph graph;
        
        //booleans control the flow of action for the buttons
        private boolean firstclick = true;      //controls drawing of cities
        private boolean secondclick = true;     //controls drawing of routes
        private boolean resetDone = false;      //controls reset button in guessing phase
        private boolean creation = true;        //controls phase of action
        private boolean startCity = true;       //confirms user always starts at same city
        private boolean youWin = false;         //confirms user has guessed correct routes
        private boolean getAnswer = false;      //confirms user has requested the answer
        
        //color of the cities and routes
        private Color color = Color.BLUE;
        
        //integer holds value of id number of particular city
        private int loc1 = 0;
        
        //double holds value of total distance accrued
        private static double currentCost = 0;

        //string holds value of current route between cities
        private String currentRoute = "";
        
        //string holds value of the start city
        private String startCityName;
        
        /*
        * method paints cities and routes onto screen. booleans control when
        * and if the cities and routes area drawn
        *@param         Graphics
        */
    
        public void paintComponent (Graphics g) {
            
            //initialize how the graphics will be drawn
            Graphics2D g2 = (Graphics2D) g;
            g2.setBackground(color);
            g2.setStroke(new BasicStroke(5));
            g2.setColor (color);
            
            
            //draws the shortest route if user requests answer
            if (getAnswer && !creation && !resetDone) {
                
                super.paintComponent(g);
                
                String shortRoute = graph.returnRoute();
                int num = shortRoute.length();
                
                //visit all the cities in the list
                for (int i = 0; i < num; i++) {
                    
                    
                    //get each city from the list from the string of cities
                    while (test.getOrder() != Character.getNumericValue(shortRoute.charAt(i))) {
                        test.move();
                    }
                    
                    //draw cities and routes
                    if (xloc1 == 0) {
                        xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                        yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                        firstclick = false;
                    }
                        
                    else {
                        xloc2 = xloc1;
                        yloc2 = yloc1;
                        xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                        yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                        secondclick = false;
                    }
                    
                    if (!firstclick) {
                        g2.fillOval(xloc1, yloc1, 15, 15);
                        g2.drawString(test.getId(), xloc1 - 10, yloc1 - 10);
                    }
                    
                    if (!secondclick) {
                        g2.drawLine(xloc1  + 6, yloc1  + 6, xloc2 + 6, yloc2 + 6); 
                    }
                    test.reset();
                }
                
                firstclick = true;
                secondclick = true;
                creation = true;
                test = new TSPBackend();
            }
            
            //clears the board 
            if (youWin) {
                super.paintComponent(g);
                youWin = false;
            }
            
            //redraws the cities after the reset button has been pressed
            if (resetDone) {
                
                super.paintComponent(g);
                
                int num = test.getLength();
                for (int i = 0; i < num; i++) {
                    xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                    yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                    g2.fillOval(xloc1, yloc1, 15, 15);
                    g2.drawString(test.getId(), xloc1 - 10, yloc1 - 10);
                    test.move();
                    resetDone = false;
                }
                test.reset();
            }
            
            //draws the cities and names
            if (!firstclick && !resetDone) {
                g2.fillOval(xloc1, yloc1, 15, 15);
                g2.drawString(test.getId(), xloc1 - 10, yloc1 - 10);
            }
            
            //draws the routes between the cities
            if (!secondclick && !resetDone) 
                g2.drawLine(xloc1  + 6, yloc1  + 6, xloc2 + 6, yloc2 + 6);        
        }   
        
        /*
        *   method controls when and what is drawn on the JFrame, depending on
        *   which button is clicked and when it is clicked
        *
        *   @param      ActionEvent
        */
        
        public void actionPerformed (ActionEvent e) {
            
         //string symbol takes the action event command  
         String symbol = e.getActionCommand();
            
         //user starts by creating a set of cities
            if (creation) {
                
                /* if user clicks check button, the set of cites is created, and
                *  game moves to guessing phase
                */
                
                if ((symbol.equals("Check") || test.getLength() >= 10) 
                        && !symbol.equals("Reset") ) {
                    
                    //set of cities much be at least 4 cities long to proceed
                    if (test.getLength() >= 4 && !youWin && !getAnswer) {
                        
             
                        //TSPBackend creates a matrix of distances between cities
                        test.createMatrix();
                        
                        //new graph class is initialized
                        graph = new Graph(test);
                        
                        //graph initialied from matrix
                        graph.initFromMatrix();
                        
                        //graph completes depth first search for shortest route
                        graph.depthFirstTrav();
                    
                        //get length of list of cities
                        int length = test.getLength();
                        
                        //reset search at beginning of list
                        test.reset();
                        
                        //initial route is confirmed
                        for(int i = 0; i < length; i++) {
                            currentRoute += i;
                            test.move();
                        }
                        
                        //initial distance of initial route is confirmed
                        for (int i = 1; i < length; i++) {
                            currentCost += test.returnDistance(i - 1, i);
                        }
                    
                        //search is reset
                        test.reset();
                        
                        //initial route is confirmed to be shortest route
                        if (graph.returnRoute().equals(currentRoute)) {
                            actions.append("\nYou Win!\nShortest Route: "
                                    + (Math.round(graph.returnCost() * 1000.0) / 1000.0) + " degrees"
                                    + "\nClick \"Reset\" to create new set of cities.\n");
                            youWin = true;
                        }
                        
                        //if initial route is not shortest, user given option to try again
                        else {
                            actions.append("\nShortest Route: " 
                                    + (Math.round(graph.returnCost() * 1000.0) / 1000.0) 
                                    + " degrees\nYour Route: " 
                                    + (Math.round(currentCost * 1000.0) / 1000.0) 
                                    + " degrees\nTo try again to guess shortest route click \"Reset\"\n"
                                    + "or \"Get Answer\" to confirm answer.\n");
                            creation = false;
                            firstclick = true;
                            secondclick = true;
                            xloc1 = 0;
                            yloc1 = 0;
                            xloc2 = 0;
                            yloc2 = 0;
                            currentRoute = "";
                            currentCost = 0;
                        }
                    }
                }
                
                //clears the frame if clicked
                else if (symbol.equals("Reset")) {
                    
                    
                    youWin = true;        
                    test = new TSPBackend();
                    firstclick = true;
                    secondclick = true;
                    xloc1 = 0;
                    yloc1 = 0;
                    xloc2 = 0;
                    yloc2 = 0;
                    currentRoute = "";
                    currentCost = 0;
                    repaint();
                    getAnswer = false;
                    actions.setText("");
                }
                
                //cities are added to the listed link of cities
                else if (!symbol.equals("Check") && !symbol.equals("Reset") 
                        && !symbol.equals("Get Answer") && !youWin && !getAnswer){
                    
                    do {
                        
                        //if city already added to list, break
                        if(symbol.equals(test.getId())) {
                            test.reset();
                            break;
                        }
                        
                        //traverse the listed looking for the city
                        else if (!test.atEnd() && !symbol.equals(test.getId())) {
                            test.move();
                        } 
                        
                        //if city not already part of list, add city
                        if (test.atEnd() && test.getLength() <= 9) {
                            
                            if (test.getLength() == 0) {
                                startCityName = symbol;
                            }
                            
                            test.addCity(symbol);
                            
                            if (xloc1 == 0 && yloc1 == 0) {
                                xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                                yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                                firstclick = false;
                            }
                            
                            else {
                                xloc2 = xloc1;
                                yloc2 = yloc1;
                                xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                                yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                                secondclick = false;
                            }
                            
                        }
                    
                    } while (test.hasNext());
                    
                    //repaint the frame
                    repaint(); 
                }  
            }
            
            /*Once the set of cities has been chosen, the guessing phase begins,
            * the user tries to guess the shortest route, the cities remain in place
            * however the routes are redrawn when reset button is clicked.
            */
            else {
                
                if (symbol.equals("Check") && currentRoute.length() == test.getLength()) {
                    
                    //if the user guesses the shortest route, they win
                    if (graph.returnRoute().equals(currentRoute)) {
                        
                        actions.append("\nYou Win!\nShortest Route: "
                                    + (Math.round(graph.returnCost() * 1000.0) / 1000.0) + " degrees"
                                    + "\nClick \"Reset\" to create new set of cities.\n");
                  
                            youWin = true;
                            creation = true;
                    }
                    
                    //otherwise the user can keep guessing or start again
                    else {
                        actions.append("\nShortest Route: " 
                                    + (Math.round(graph.returnCost() * 1000.0) / 1000.0) 
                                    + " degrees\nYour Route: " 
                                    + (Math.round(currentCost * 1000.0) / 1000.0) 
                                    + " degrees\nTo try again to guess the shortest route click \"Reset\"\n"
                                    + "or \"Get Answer\" to confirm answer.\n");
                        firstclick = true;
                        secondclick = true;
                        xloc1 = 0;
                        yloc1 = 0;
                        xloc2 = 0;
                        yloc2 = 0;
                        currentRoute = "";
                        currentCost = 0;
                        loc1 = 0;
                        startCity = false;
                    }
                    
                }
                
                //reset removes the routes, but keep the cities in place
                else if (symbol.equals("Reset") && !resetDone ) {
                    test.reset();
                    xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                    yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                    resetDone = true;
                    secondclick = true;
                    repaint();
                    loc1 = 0;
                    startCity = true;
                    test.reinitCity();
                    currentRoute = "";
                    //actions.setText("");
                }
                
                //user adds cities to the linked list, which are painted on frame
                else if (!symbol.equals("Check") && !symbol.equals("Reset") 
                        && !symbol.equals("Get Answer") && currentRoute.length() < test.getLength() 
                        && startCity){
                    
                    resetDone = false;
                    test.reset();
                    
                    boolean hasCity = false;
                    
                    //program traverses linked list to confirm if the city already clicked
                    do {
                    
                        if(symbol.equals(test.getId())) {
                            
                            if (!test.getVisited()) {
                                test.setVisited(true);
                                hasCity = true;
                            }
                            
                            break;
                        }
                        
                        else if (!test.atEnd() && !symbol.equals(test.getId())) {
                            test.move();
                        } 
                            
                    } while (test.hasNext());
                    
                    if (hasCity) {
                        
                        if (currentRoute.length() == 0) {
                            xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                            yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                            loc1 = test.getOrder();
                            currentRoute += test.getOrder();
                            
                        }
                        
                        else {
                           
                            xloc2 = xloc1;
                            yloc2 = yloc1;
                            xloc1 = (int) Math.round((60 -(test.getXloc() - 65)) * 20);
                            yloc1 = (int) Math.round((27 - (test.getYloc() - 23)) * 20);
                            secondclick = false;
                            
                            currentCost += test.returnDistance(loc1, test.getOrder());
                            loc1 = test.getOrder();
                            currentRoute += test.getOrder();
                            repaint(); 
                        }  
                    }
                    
                    //the program only allows the user to start at the same starting city
                    if (currentRoute.length() != 0 && currentRoute.charAt(0) != '0') {
                        
                        actions.append("\nClick \"Reset\" then " + startCityName + " to keep guessing.\n"
                                + startCityName + " is the start city, and you must choose other cities in the set."
                                + "\nClick \"Get Answer\" and \"Reset\" to start over.\n");
                        startCity = false;
                        test.reinitCity();
                        loc1 = 0;
                        currentRoute = "";
                        
                    }
                    
         
                    test.reset();
                    
                }
                
                //get answer function allows user to get the answer and start over
                else if (symbol.equals("Get Answer") && test.getLength() >= 4) {
                    
                    actions.append("\nShortest Route: "
                                    + (Math.round(graph.returnCost() * 1000.0) / 1000.0) + " degrees"
                            + "\nTo create new set click \"Reset\"\n");
                    
                    getAnswer = true;
                    firstclick = true;
                    secondclick = true;
                    startCity = true;
                    xloc1 = 0;
                    yloc1 = 0;
                    xloc2 = 0;
                    yloc2 = 0;
                    currentRoute = "";
                    loc1 = 0;
                    currentCost = 0;
                    startCityName = "";
                    test.reset();
                    repaint();
                }
            }
        }
    }
    
    /*
    * method allows user to click on exit button to exit the game
    */
    
    static class Exit implements ActionListener{
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
        
    }
    
    static class Directions implements ActionListener{
        public void actionPerformed (ActionEvent e) {
            actions.setText("Welcome to the Traveling Salesperson Game!"
                + "\nStart by clicking cities. Choose more than four up to ten."
                + "\nThe cities are added to scale."
                + "\nClick \"Check\" to find shortest route.\n"
                + "Click \"Reset\" to create new set.\nClick \"Exit\" to exit."
                + "\nStart at the same city every time and visit each city only once."
                + "\nSee if you can guess the shortest route!"
                + "\nDirections appear as the game proceeds so scroll down if "
                + "you have questions.\n");
        }
        
    }
    
}

    
  


    

