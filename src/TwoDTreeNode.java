/**
 * @author Nikhil Kashyap
 */

/**
 * This class represents single node in 2d tree of type TwoDTree. It keeps track of its left, right child and its parent.
 */

public class TwoDTreeNode {
    /**
     *  X, Y - x and y coordinate to construct a 2 dimensional spatial Tree
     *  latitude and longitude - are coordinates to track the crime location on GIS tools like google earth
     *  Line - it is a string with all information concatenated
     *  leftChild, rightChild, parent - are left,right child and parent of the current node in a 2d tree
     */
    private double X;
    private double Y;
    private double latitude;
    private double longitude;
    private final String Line;
    private TwoDTreeNode leftChild;
    private TwoDTreeNode rightChild;
    private TwoDTreeNode parent;

    /**
     * Extracts individual fields from "line" and populates appropriate instance variables. Also initializes the instance
     * variables with valid default values.
     * @param line
     *  A line from the CrimeLatLonXY.csv file.
     */
    public TwoDTreeNode(String line){
        Line = line;
        String[] fields = line.split(",");
        setX(Double.parseDouble(fields[0]));
        setY(Double.parseDouble(fields[1]));
        setLatitude(Double.parseDouble(fields[7]));
        setLongitude(Double.parseDouble(fields[8]));
        setLeftChild(null);
        setRightChild(null);
        setParent(null);
    }

    public String toString(){
        return Line;
    }

    // ------------------------------ Getters and Setters for the above instance variables -----------------------
    public TwoDTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TwoDTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TwoDTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TwoDTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public TwoDTreeNode getParent() {
        return parent;
    }

    public void setParent(TwoDTreeNode parent) {
        this.parent = parent;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    //-----------------------------------------------------------------------------------------------------------------

}
