/*
    Author - Ankit Sablok
    Email-id - ankitsab@buffalo.edu
    Institution - University at Buffalo, The State University of New York
*/

package com.algorithms.week1;

import java.util.Scanner;

// this class provides a second solution to the Dynamic Connectivity problem using lazy approach to algorithm design through a quick union algorithm
class UF_QuickUnion {

    // id array has the following interpretation - it acts a forest of trees where each tree is a connected component and id[i] = parent of object i in the tree
    private int[] id;

    // this is the constructor for the QuickUnion class
    public UF_QuickUnion(int N) throws Exception {
        if(N <= 0) {
            throw new ArrayIndexOutOfBoundsException("Number of objects is either negative or 0");
        }

        // initialize the id array and assign each array entry to itself as all the objects are independent when they start out
        this.id = new int[N];
        for(int i = 0; i < N; ++i) {
            this.id[i] = i;
        }
    }

    // this private method is used to find the root of an object in the id array, root is basically the representative of the connected component containing the object p
    private int root(int p) throws Exception {
        if(p < 0) {
            throw new ArrayIndexOutOfBoundsException("Object p has an index that is out of bounds, please try again.");
        }

        // we recursively try to move our way upwards in the tree
        while(p != id[p]) {
            p = id[p];
        }
        return p;
    }

    // this is the connected operation which returns true or false depending on if p and q are connected to each other or not
    public boolean connected(int p, int q) throws Exception {
        if(p < 0 || q < 0) {
            throw new ArrayIndexOutOfBoundsException("Array indices passed to the method are out of bounds, please try again.");
        }

        return root(p) == root(q);
    }

    // this is the union operation in the Quick Union algorithm which just performs a single step given the roots of the two objects passed to it
    public void union(int p, int q) throws Exception {
        if(p < 0 || q < 0) {
            throw new ArrayIndexOutOfBoundsException("Array indices passed to the method are out of bounds, please try again.");
        }

        if(connected(p,q)) {
            return;
        }

        // set the id of p's root to id of q's root or just qRoot would also work fine
        int pRoot = root(p);
        int qRoot = root(q);
        this.id[pRoot] = this.id[qRoot];
    }

    // this method displays the state of the id array at any point in time
    public void display() {
        for(int i = 0; i < this.id.length; ++i) {
            System.out.print(this.id[i] + " - ");
        }
        System.out.println();
    }
}

public class QuickUnion {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of objects in the system : ");
        int N = sc.nextInt();
        System.out.println("Please enter the number of union operations you want to perform : ");
        int union = sc.nextInt();
        System.out.println("Please enter the number of connected operations you want to perform : ");
        int connected = sc.nextInt();

        // we make a quick find object to perform several operations
        UF_QuickUnion qu = new UF_QuickUnion(N);
        for(int i = 0; i < union; ++i) {
            System.out.println("Union #" + (i+1));
            System.out.print("p: ");
            int p = sc.nextInt();
            System.out.print("q: ");
            int q = sc.nextInt();
            qu.union(p,q);
        }

        System.out.println("The state of the array after performing union operations is as follows -: ");
        qu.display();

        for(int i = 0; i < connected; ++i) {
            System.out.println("Connected #" + (i+1));
            System.out.print("p: ");
            int p = sc.nextInt();
            System.out.print("q: ");
            int q = sc.nextInt();
            System.out.println("The two objects entered are connected? : " + qu.connected(p,q));
        }
    }
}
