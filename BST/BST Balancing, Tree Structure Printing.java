 



/**
 * BST
 * Binary Search Tree class inteded to store data in a Binary Search Tree. The object in the generic
 * class must be comparable (implements Comparable). For the prints to work, the object must be
 * printable as well (implements toString()).
 * 
 * Seperate, recursive methods are used to implement recursive algorithms for operations.
 * This is because most recursive methods require the parent to be a parameter, but
 * generally when you call a method (like insert for example) the parent is assumed to
 * be the root.
 */ 
/**
 * This program also balances and prints the tree
 * @author Nikhil Kanamarla
 * @version 2.3
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;
 

/**
 * BST
 * Binary Search Tree class inteded to store data in a Binary Search Tree. The object in the generic
 * class must be comparable (implements Comparable). For the prints to work, the object must be
 * printable as well (implements toString()).
 * 
 * Seperate, recursive methods are used to implement recursive algorithms for operations.
 * This is because most recursive methods require the parent to be a parameter, but
 * generally when you call a method (like insert for example) the parent is assumed to
 * be the root.
 */
public class BST <T> {
 

    BSTNode <T> root  = new BSTNode<T>();
    BSTNode <T> root2  = new BSTNode<T>();
    BSTNode <T> lower = new BSTNode<T>();
    BSTNode <T> upper = new BSTNode<T>();
    BSTNode <T> lower2 = new BSTNode<T>();
    BSTNode <T> upper2 = new BSTNode<T>();
 

    ArrayList <T> balanced = new ArrayList <T>();
    List <T> uppervalues = new LinkedList <T>();
    List <T> lowervalues = new LinkedList <T>();
 

    /**
     * insert a single element into the tree. It is potentially possible to mess up the tree by modifying an element after insertion.
     * @param in the element to insert (the actual instance is inserted, NOT A CLONE)
     */
    public void insert(T in) {
        if (in == null) return;
        BSTNode <T> bin = new BSTNode<T>();
        bin.set(in);
        if (root == null) {
            root = bin;
        } else {
            insert_re(root, bin);
        }
    }
 

    public void insert_re(BSTNode parent, BSTNode insertMe) {
        if (insertMe != null) return;
        if (parent.getc().compareTo(insertMe.getc()) > 0) {
            if (parent.getLeft() == null) {
                parent.setLeft(insertMe);
            } else {
                insert_re(parent.getLeft(), insertMe);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(insertMe);
            } else {
                insert_re(parent.getRight(), insertMe);
            }
        }
    }
 

    /**
     * in order traversal of the tree, using println to output the elements
     */
    public void inOrderPrint() {
        if (root != null) {
            inOrderPrint_re(root);
        }
    }
 

    private void inOrderPrint_re(BSTNode<T> parent) {
        if (parent == null) return;
 

        inOrderPrint_re(parent.getLeft());
        System.out.println(parent.get());
        inOrderPrint_re(parent.getRight());
 

    }
 

    /**
     * See if an element exists in the tree
     * @param q element to search for in the tree
     * @return true if an element in the tree is equal to the q, false otherwise
     */
    public boolean exists(Integer q) {
        return exists_re(q,root);
    }
 

    private boolean exists_re(Integer q, BSTNode<T> parent) {
        if (parent == null) return false;
        if (((Comparable)q).compareTo(parent.getc()) == 0) { //Typecasting shenanigans necessary for compareTo to work
            return true;
        } else if (((Comparable)q).compareTo(parent.getc()) < 0) {
            return exists_re(q, parent.getLeft());
        } else {
            return exists_re(q, parent.getRight());
        }
    }
 

    //@ param balanced is the new balanced arraylist
    // prints out tree based on level and following indexes
    public void print(ArrayList <T> balanced) {
 

        int x = 1;
        for(int i = 0; i < balanced.size(); i++) {
 

            if(x ==1 ) {
                System.out.println("level" + " " + x + " " + balanced.subList(0,1));
            }
            if(x == 2) {
                System.out.println("level" + " " + x + " " + balanced.subList(2,4));
 

            }
            if(x == 3) {
                System.out.println("level" + " " + x + " " + balanced.subList(3,7));
 

            }
            if(x == 4) {
                System.out.println("level" + " " + x + " " + balanced.subList(8,16));
 

            }
            if(x == 5) {
                System.out.println("level" + " " + x + " " + balanced.subList(17,49));
 

            }
            if(x == 6) {
                System.out.println("level" + " " + x + " " + balanced.subList(50,114));
 

            }
            if(x == 7) {
                System.out.println("level" + " " + x + " " + balanced.subList(115,243));
 

            }
            if(x == 8) {
                System.out.println("level" + " " + x + " " + balanced.subList(116,372));
 

            }
            if(x == 9) {
                System.out.println("level" + " " + x + " " + balanced.subList(372,884));
 

            }
            x++;
        }
    }
 

    //@ param values is the unbalanced arraylist and depth is the number of layers
    public void balance(ArrayList <T> values, int depth)
    {
        // tells when to activate the print method
        if ((values.isEmpty()))   {
            print(balanced);
            return;
        }
 

        // sets the main node based on median
        int middleindex = (values.size()/2);
        T middle = (values.get(middleindex));
 

        root.set(middle);
 

        balanced.add(values.get(middleindex)); 
 

        // creates upper and lower ranges
 

        lowervalues = values.subList(0,middleindex-1);
        uppervalues = values.subList(middleindex+1, values.size());
 

        // sets the left and right node based on median of upper and lower ranges
 

        int middleindexlower = (lowervalues.size()/2);
        int middleindexupper = (uppervalues.size()/2);  
 

        T middle2 = (values.get(middleindexlower));
 

        T middle3 = (values.get(middleindex+middleindexupper));
 

        balanced.add(values.get(middleindexlower));
        balanced.add(values.get(middleindexupper));
 

        lower.set(middle2);
        upper.set(middle3);
 

        // create new left and right node ;
 

        root.setLeft((lower)); //first half
        root.setRight((upper)); // second half
        
        //removes old median indexes
 

        values.remove(middleindex);
        values.remove(middleindexlower);
 

        if(middleindexupper > 0) {
            values.remove(middleindexupper);
        }
 

        //increases depth
        
        depth++;
 

        BSTTest.setdepth(depth);
        
        //recursively calls the method 
 

        balance(values,depth);
    }
 

}

