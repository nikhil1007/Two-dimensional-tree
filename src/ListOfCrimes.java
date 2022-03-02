/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

import java.io.FileWriter;
import java.io.IOException;

/**
 * Purpose: To keep track of list of crimes returned by "Range Search" method in TwoDTree class
 * This class is an implementation of LinkedList data structure. Each node is of type ListOfCrimeNode.
 */

public class ListOfCrimes {
    /**
     * head - Keeps track of the first element of the crime list
     * count - Keeps track of the number of nodes in the list of type ListOfCrime
     */
    private ListOfCrimesNode head;
    private ListOfCrimesNode iterator;
    StringBuilder sb = new StringBuilder();

    public int count = 0;

    public ListOfCrimes(){
        head = null;
        iterator = null;
    }

    /**
     * @return
     *  Returns a boolean value if the ListOfCrime list is empty or not
     */
    private boolean isEmpty(){
        return head == null;
    }

    private boolean hasNext(){
        return this.iterator.getLink() != null;
    }

    private ListOfCrimesNode next(){
        ListOfCrimesNode temp = iterator.getLink();
        iterator = iterator.getLink();
        return temp;
    }

    private void reset(){
        iterator = head;
    }

    /**
     * Theta(1), constant time complexity
     * @param crime
     *  A valid node of type TwoDTreeNode, to be added to the ListOfCrime linked list
     * @precondition
     *  head, count instance variables are defined and initialized appropriately
     * @postcondition
     *  A new node of type TwoDTreeNode will be added to ListOfCrime linked list
     */
    public void Add(TwoDTreeNode crime){
        ListOfCrimesNode node = new ListOfCrimesNode(crime);

        if (!this.isEmpty()) {
            node.setLink(head);
        }
        head = node;
        iterator = head;
        count++;
    }

    /**
     * @return
     *  A string representation of all data of ListOfCrimeNode class
     */
    public String toString(){
        reset();

        while (iterator!=null){
            sb.append(iterator.getCrime().toString());
            sb.append("\n");
            iterator = iterator.getLink();
        }
    return sb.toString();
    }

    /**
     * Generates a KML document for all the crimes retrieved for the range
     * @return
     *  A valid KML document which can be used to view in GIS tools like Google Earth
     */
    public String toKML(){
        //we need to generate a KML document with the co-ordinates obtained
        //reference : https://renenyffenegger.ch/notes/tools/Google-Earth/kml/index
        StringBuilder kmldoc = new StringBuilder();
        kmldoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
        kmldoc.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">" + "\n");
        kmldoc.append("<Document>" +"\n");

        reset();

        while (this.hasNext()){
            kmldoc.append("\n\t<Placemark>" + "\n");
            kmldoc.append("\t\t<Point>" + "\n");
            kmldoc.append("\t\t\t<coordinates>");
            TwoDTreeNode node = next().getCrime();
            String latitude = String.valueOf(node.getLatitude());
            String longitude = String.valueOf(node.getLongitude());
            kmldoc.append(longitude).append(",").append(latitude).append(",").append("0");
            kmldoc.append("</coordinates>");
            kmldoc.append("\t\t</Point>").append("\n");
            kmldoc.append("\t</Placemark>").append("\n");
        }

        kmldoc.append("</Document>").append("\n");
        kmldoc.append("</kml>");
    return kmldoc.toString();
    }

    /**
     * Create a KMLdoc viewable in GIS tools like Google Earth
     * Ref : https://www.w3schools.com/java/java_files_create.asp
     * @param kmlContent
     */
    public void createKMLDoc(String kmlContent){
        try {
            FileWriter myWriter = new FileWriter("PGHCrimes.kml");
            myWriter.write(kmlContent);
            myWriter.close();
            System.out.println("The crime data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
