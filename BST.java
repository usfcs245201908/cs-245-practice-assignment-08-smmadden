import java.lang.*;
import java.util.*;

public class BST <Comparable>{

    protected Node root;
    public Comparator<Comparable> comparator = new Comparator<Comparable>() { // COMPARING PROBLEM - HASHCODE DOES NOT WORK
        // try figuring out implementing comparable or try testing to see what object item is, casting it, then comparing
        @Override
        public int compare(Comparable o1, Comparable o2) {
            if(o1 == o2){
                return 0;
            } else if(o1 instanceof String) {
                return ((String)o1).compareTo((String)o2);
            } else if(o1 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2);
            } else if(o1 instanceof Float) {
                return ((Float)o1).compareTo((Float)o2);
            } else if(o1 instanceof Double) {
                return ((Double)o1).compareTo((Double)o2);
            } else if(o1 instanceof Short) {
                return ((Short)o1).compareTo((Short)o2);
            } else if(o1 instanceof Long) {
                return ((Long)o1).compareTo((Long)o2);
            } else if(o1 instanceof Character) {
                return ((Character)o1).compareTo((Character) o2);
            } else if(o1 instanceof Boolean) {
                return ((Boolean)o1).compareTo((Boolean) o2);
            } else {
                return 0;
            }

            // keep adding more until all wrapper classes covered??
        }
    };


    public BST(){
        root = null;
    }

    class Node{
        public Comparable data;
        public Node left, right;
        public int instanceCounter;

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
            instanceCounter = 1;
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

        public void setInstanceCounter(int count) {
            instanceCounter = count;
        }
    }


    public boolean find(Comparable item){
        return find(item, root);
        // Return true if item is found in the BST; false otherwise.
    }

    private boolean find(Comparable item, Node node){
        if(node == null){
            return false; // item does not exist in our tree
        }
        if(item.equals(node.data)){
            return true;
        }
        if(comparator.compare(node.data, item) > 0){
            return find(item, node.left);
        } else {
            return find(item, node.right);
        }
    }

    public void insert(Comparable item){
        root = insert(item, root);
        // Insert item into BST, keeping duplicates in their own nodes.
    }

    private Node insert(Comparable item, Node node){        // problems with insert
        if(node == null ){  //|| node.data == null){
            return new Node(item);
        }
        if(item.equals(node.data)){
            node.instanceCounter++;
            return node;
        }
        //System.out.println(node.data);
        //System.out.println(item);
        if(comparator.compare(item, node.data) < 0){
            node.left = insert(item, node.left); // do we return node.left and right here?
            return node;
        } else {
            node.right = insert(item, node.right);
            return node;
        }
        // Insert item into BST, keeping duplicates in their own nodes.
    }

    public void print(){
        print(root);// should just be print root? then inside print go left and right
//        print(root.left);
//        for(int i = 1; i<=root.instanceCounter; i++){
//            System.out.println(root.data);
//        }
//        print(root.right);

        //  Using println, output each item in the BST, ​in order​.
    }

    private void print(Node node){
        if(node != null){
            print(node.left);
            for(int i=1; i<= node.instanceCounter; i++){
                System.out.println(node.data);
            }
            print(node.right);
        }
    }

    public void delete(Comparable item){
        // delete first instance of the item from the BST
        root = delete(root, item);
    }

    public Node delete(Node node, Comparable item){
        if(node==null){
            return null;
        }
        if(node.data.equals(item)) {             // some way to account for the deletion of duplicates - if node.data && instance == 1 else just return node?
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
        }
        if(comparator.compare(node.data, item) < 0){
            node.right = delete(node.right, item);
            return node;
        } else {
            node.left = delete(node.left, item);
            return node;
        }
    }

    private Comparable removesmallest(Node node){
        if(node.left.left == null){
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        return removesmallest(node.left);
    }
}
