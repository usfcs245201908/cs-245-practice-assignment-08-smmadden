import java.lang.*;

public class BST <T>{

    protected Node root;

    public BST(){
        root = null;
    }

    class Node{
        public Comparable data;
        public Node left, right;
        public int instanceCounter; // counts number of times an item appears in the tree

        // constructor with no parameters, sets the data and next to null
        public Node(){
            data = null;
            right = null;
            left = null;
            instanceCounter = 1;
        }

        // constructor with parameters for class Node, sets the data of the node to item and the next to null
        public Node(Comparable item){
            data = item;
            right = null;
            left = null;
            instanceCounter = 1; // when the node is created, there is one instance of the data
        }

        // getters:
        public Comparable getData(){
            return data;
        }

        public Node getRight(){
            return right;
        }

        public Node getLeft(){
            return left;
        }

        public int getInstanceCounter() {
            return instanceCounter;
        }

        // setters:
        public void setData(Comparable item){
            data = item;
        }

        public void setRight(Node n){
            right = n;
        }

        public void setLeft(Node n){
            left = n;
        }

        public void setInstanceCounter() {
            instanceCounter++;
        }
    }

    // Returns true if item is found in the tree and false otherwise.
    public boolean find(Comparable item){
        return find(item, root);
    }

    // recursive find function
    private boolean find(Comparable item, Node node){
        if(node == null){
            return false; // item does not exist in our tree
        }
        if(item.equals(node.data)){
            return true; // we have found the node
        }
        if(node.data.compareTo(item) > 0){ // if the item < node, search to the left
            return find(item, node.left);
        } else {
            return find(item, node.right); // if item > node, search to the right
        }
    }

    // Inserts the item into the tree
    public void insert(Comparable item){
        root = insert(item, root);
    }

    private Node insert(Comparable item, Node node){
        if(node == null ){ // reached the end of the tree so time to add the node and return it to reestablish all of the links
            return new Node(item);
        }
        if(item.equals(node.data)){ // if the new item is the same value as a node then increase the instance counter
            node.instanceCounter++;
            return node;
        }
        if(item.compareTo(node.data) < 0){ // if the item is less than the current node, add to the left
            node.left = insert(item, node.left);
            return node; // return the current node after adding to the left, reestablishes the links between nodes
        } else { // if the item > node, add to the right
            node.right = insert(item, node.right);
            return node;
        }
    }

    // prints all of the data in the tree in order
    public void print(){
        print(root);
    }

    // prints all of the nodes to the left, the current node, then all of the nodes to the right
    private void print(Node node){
        if(node != null){ // if the node is null then don't print anything
            print(node.left); // prints nodes to the left
            for(int i=1; i <= node.instanceCounter; i++){ // prints out all of the duplicates
                System.out.println(node.data);
            }
            print(node.right); // prints nodes to the right
        }
    }

    // delete first instance of the item from the BST
    public void delete(Comparable item){
        root = delete(root, item);
    }

    public Node delete(Node node, Comparable item){
        if(node==null){ // if the node does not exist in the tree
            return null;
        }
        if(node.data.equals(item) && node.instanceCounter == 1) { // if it is the correct node and there is only one instance
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.right.left == null) {
                node.data = node.right.data;
                node.right = node.right.right;
                return node;
            } else {
                node.data = removesmallest(node.right);
                return node;
            }
        } else if(node.instanceCounter > 1){
            // if the instance counter is more than one, then do not need to delete entire node, just one instance
            node.instanceCounter--;
            return node;
        }
        if(node.data.compareTo(item) < 0){ // if the item is greater than the current node, then recursive call
            node.right = delete(node.right, item);
            return node;
        } else { // if item < current node, then recursive call
            node.left = delete(node.left, item);
            return node;
        }
    }

    // used for the delete function, returns the data of the smallest in-order successor
    private Comparable removesmallest(Node node){
        if(node.left.left == null){ // if the left's node's left node is null, then the left node must be the smallest
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        return removesmallest(node.left); // if the left node is not the smallest
    }
}
