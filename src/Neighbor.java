/**
 * Name: Nikhil Kashyap (nikhilka)
 * Course: Data Structures and Algorithms 95-771, CMU
 * Assignment: Project 2 - 2d Trees
 * Date of Submission: 15th Feb 2022
 */

/**
 * This class holds the details of the node identified by the nearest neighbor search method.
 */
public class Neighbor {
    /**
     * distanceFromTarget - distance of this node from the target node given by the user
     * myNearestNode - a TwoDTreeNode object of the current node
     */
    double distanceFromTarget;
    TwoDTreeNode myNearestNode;

    Neighbor(){
        this.distanceFromTarget = 0;
        this.myNearestNode = null;
    }

    public String toString(){
        return "Found the nearest crime at: " + myNearestNode;
    }

    //--------------------------------- Getters and Setters for instance variables ---------------------------------
    public double getDistanceFromTarget() {
        return distanceFromTarget;
    }

    public void setDistanceFromTarget(double distanceFromTarget) {
        this.distanceFromTarget = distanceFromTarget;
    }

    public TwoDTreeNode getMyNearestNode() {
        return myNearestNode;
    }

    public void setMyNearestNode(TwoDTreeNode myNearestNode) {
        this.myNearestNode = myNearestNode;
    }
}
