# TravelingSalesPerson

Project Write-up:

The project is a game based on the Traveling Salesperson problem. In it the user attempts to beat the computer in choosing the fastest route for the Traveling Salesperson. The rules of the game are that the user must start their journey at the same city each time on their journey, and travel to each other city in their chosen set at least once, but not twice. If the user can guess the shortest route, they win. The Traveling Salesperson Problem (TSP) is an example of how computing power is used to solve a real world problem. Any type of service that involves travel to multiple destinations struggles with this problem. As the number of destinations grows, the number of possible routes grow in a factorial fashion. This game is designed to introduce the user to the concept of the TSP in a fun way, and to test their skill at guessing. This write-up will explain the game in more detail, explain the backend of the program, and discuss some of the programming concepts that make this game possible.


Set up:

In order to play this game, download the three programs, TSPGUI.java, Graph.java and TSPBackend.java into the same folder. Run the TSPGUI.java file and in order to load the user interface.

Game Play:
	
The user starts the game at a mostly blank user interface with buttons at both the top and bottom the frame. The buttons at top of the frame are the command buttons. The buttons at the bottom are the city buttons. In addition, at the bottom, to the right is a text area that records the user’s actions, displays route totals and gives the user instructions as they go. At the start of the game the text area has instructions printed.

Game play is broken into two separate phases: 
		
	Phase 1: Creation Phase 

The user begins by clicking city bottoms at random, or in an attempt to choose the shortest route. These cities are added to the interface to scale so that the user can judge distances more accurately and the routes between subsequent cities are painted as well. At any point, the user can click either “Reset” to clear the frame, “Exit” to exit the program or “Directions” to get the directions back. The “Get Answer” button will not do anything at this phase. After the user has painted as many cities as they would like, they click “Check”. If they have clicked less the 4 cities nothing will happen, and if they clicked more the 10 the system will check automatically. If the user chose the shortest route, the user wins and the distance in degrees in printed in the text area.

	Phase 2: Guessing Phase

If the user does not win, the user is prompted to make one of a number choices. The user can either click “Reset” to clear all the routes from the screen but keeps the cities in place. The user will then have to click on the first city they chose, then the rest of the cities in any order. The route will be redrawn. If the user does not click the start city first, then the program will prompt the user to click that city. The user will have to click “Reset”, then the start city to continue. If the user clicks one of the buttons that was not visited previously, nothing happens. The user can keep doing this, choosing cities, clicking “Check”, then “Reset” until they guess the shortest route. After each click of “Check”, the distance traveled on the current route will be printed in the text area. If the user grows tired of this, they can click “Get Answer” at any time and the shortest route will be printed. At that point the user can click “Reset” and start the game over at Phase 1, or else exit the program.

Backend Function:

The TSP game is set up in MVC structure. The View is a GUI, which is a JFrame with JButtons added to the top and the bottom of the frame and the middle space unfilled. The buttons at top of the frame are the command buttons. The buttons at the bottom are the city buttons. The text field is a JTextArea with a scroll bar. The GraphGUI class extends JFrame, and it holds the widgets added to the frame. These include two JPanels, which hold the buttons at the bottom and the top of the frame and the text area at the bottom. The top panel has Flow Layout and the bottom panel has Grid Layout, with two columns to divide the city buttons and the text area.

The Control function is provided by the Clicker class, which is the Programming Class. Based on which buttons the user presses and when, the Clicker class creates and manipulates the Template Classes. The Template Classes are TSPBackend and Graph, which are the Model function of MVC. 

The Clicker class is an inner class of the TSPGUI class, along with GraphGUI, and is thus able to control what is painted on the frame, or printed in the text area. In order to control and manipulate the data stored in the Template classes, the Clicker class uses getter and setter methods. It extends the JPanel interface and implements the ActionListener interface, and is able to both paint and respond to clicks by the user. 

	Painting:

To paint, Clicker utilizes the paintComponent method, which takes a Graphics parameter. This method is called by using the “repaint()” method. What is painted is controlled by different boolean primitives; what is painted is determined by whether booleans are turned on and off. The boolean primitives are controlled by the buttons the user clicks and in what order they are clicked.
	
	ActionListener:

The ActionListener interface requires the class to implement the actionPerformed(ActionEvent e) method. This method is called whenever the user clicks on a button. The string returned by calling the getActionCommand() method is assigned to a string value “symbol”. Depending on what that string value is, different actions are taken. In addition to the primitive variables of the Clicker class, there is also the Graph class referenced.

As with the game itself the backend clicker action event listener is split into two phases, the creation phase and the guessing phase:

	Phase 1: Creation

During the creation phase, the user is clicking cities, potentially trying to guess the shortest route, but more importantly creating a linked list. The City class is an inner class of the TSPBackend class. As cities are clicked, the symbol string that captures the name of a city is run through a setter method of TSPBacked by the Clicker class, and City objects are added to the linked list one by one. At the end of the creation phase, the createMatrix() setter method of TSPBackend is called by the Clicker class, and a 2D array of distances between cities is created. Once the matrix is created, the Clicker class calls the constructor for the Graph and the initFromMatrix() method of that class. This method creates an Adjacency List of Vertex vertices referenced by the Graph class. The Vertex class is an inner class of Graph. The Clicker class then calls the depth first search method of Graph class, which runs through all the different routes possible from the starting city to the other cities. This value is stored in the Graph class.  As the user continues to click cities and guess new routes, the Clicker class uses a getter method to confirm if the user has chosen the shortest route. The game ends when the user either guesses the shortest route, or else requests the correct answer. At that point both the TSPBackend object and Graph object are dereferenced, and the game begins again.

Programming Concepts:

There are a number of important programming concepts and methods that allow this particular game to run:
    
	1. Linked Lists: 
Linked lists are lists of objects. Each object is linked to its neighbor object by references. The object can reference any number of its neighbors. Neighbors may not actually be adjacent pieces of data. In the case of the Cities linked list, the objects only have references to one other link. In the case of the Adjacency list, the objects reference every other object in the list. 
Linked lists are useful for many of different reasons. For example, they can grow indefinitely, which makes them ideal for programs where the number of objects varies. 
Another important function is that because each link in the list is an object, it can store multiple pieces of information. For example, the City object has a field called “visited”, which is a primitive boolean. This boolean can be turned off and on to ensure that a city in never visited twice.
In the case of the Adjacency list of vertices, the fact that the object can reference multiple other objects allows the depth first search method to try many different routes. 
    
	2. Recursion:

The depth first search method relies on the recursion to efficiently search each possible route from the start city to all the other cities of the set. Each Vertex object in the adjacency list is linked to every other vertex object. The search works by calling the method on each edge of the vertex in order. After the first edge of the first vertex is visited, then the method is called on the first edge of the vertex at the end of the edge from the first vertex. This process continues until all the edges have been visited, and every route has been explored.

    	3. Traversal:

Unlike arrays, in order to search linked lists, the length of the list has to be traversed in order to find the searched for object. In order to do this, a special object called a “trav” is created. The Class holds the reference to an object in a list, generally the first object as a reference generally called the “head”. The trav object first references the head, then moves from one object to the next also by reference. Once it references an object in the list, the trav has access to all the fields of that particular object. It can get those fields, or set those fields as it goes. In this way information in the Template class can be searched and manipulated. This is what takes place as the user choses cities. The trav object travels along the linked list, searching for an object with the same name as the one chosen by the user. If it doesn’t find that object, then it adds a new object to the list.

    	4. Stepwise refinement:

Stepwise refinement is the splitting of the different functions of the program into different classes and methods. In the case of this program, the View, Control and Model functions are all separated into different classes, GraphGui, Clicker and TSPBacked and Graph respectively. This ensures the program is lucid and clear. The trouble arises when methods from different classes try to manipulate private variables in other classes. It is good protocol to make variables private, however this restricts access from other classes. The way to get around this is to create accessor methods, getters and setters, that allow the information inside a class to be accessed and changed without changing the private status of that variable. For example, the move() method in TSPBacked class is a public method that moves the trav object along the linked list, even though the trav object is private.

Conclusion:

Above are outlined the game play of the TSP game, how the backend functions and the programming concepts that allow the game to work. Special thanks in the creation this game to Professor David Sullivan of Harvard and his team, as the Graph class is largely based on the depth first program from Data Structures class of Fall 2016. Also, thanks to Github and Google, that allowed the search and access of longitude and latitude information for different cities in the US. Please enjoy playing the game and learning about the Traveling Salesperson Problem.

