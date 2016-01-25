/*
    Author - Ankit Sablok
    Email-id - ankitsab@buffalo.edu
    Institution - University at Buffalo, The State University of New York
*/

package com.algorithms.week1;

import java.util.Scanner;

// this class provides a first solution to the Dynamic Connectivity problem using eager approach to algorithm design through a quick find algorithm
class UF_QuickFind {

    // id array has the following interpretation - if id[p] == id[q] then both p and q are in the same connected component
    private int[] id;

    // this is the constructor for QuickFind algorithm
    public UF_QuickFind(int N) throws Exception {
        if(N <= 0) {
            throw new ArrayIndexOutOfBoundsException("Number of objects is either negative or 0");
        }

        // initialize the id array and assign each array entry to itself as all the objects are independent when they start out
        this.id = new int[N];
        for(int i = 0; i < N; ++i) {
            this.id[i] = i;
        }
    }

    // this is the connected operation which returns true or false depending on if p and q are connected to each other or not
    public boolean connected(int p, int q) throws Exception {
        if(p < 0 || q < 0) {
            throw new ArrayIndexOutOfBoundsException("Array indices passed to the method are out of bounds, please try again.");
        }

        return this.id[p] == this.id[q];
    }

    // this is the union operation and the one that is responsible for the inefficiency of quick find algorithm
    public void union(int p, int q) throws Exception {
        if(p < 0 || q < 0) {
            throw new ArrayIndexOutOfBoundsException("Array indices passed to the method are out of bounds, please try again.");
        }

        if(connected(p,q)) {
            return;
        }

        int pid = this.id[p];
        int qid = this.id[q];

        // change all the entries in the id array that have the same entry as pid to qid
        for(int i = 0; i < this.id.length; ++i) {
            if(this.id[i] == pid) {
                this.id[i] = qid;
            }
        }
    }

    // this method displays the state of the id array at any point in time
    public void display() {
        for(int i = 0; i < this.id.length; ++i) {
            System.out.print(this.id[i] + " - ");
        }
        System.out.println();
    }
}

public class QuickFind {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of objects in the system : ");
        int N = sc.nextInt();
        System.out.println("Please enter the number of union operations you want to perform : ");
        int union = sc.nextInt();
        System.out.println("Please enter the number of connected operations you want to perform : ");
        int connected = sc.nextInt();

        // we make a quick find object to perform several operations
        UF_QuickFind qf = new UF_QuickFind(N);
        for(int i = 0; i < union; ++i) {
            System.out.println("Union #" + (i+1));
            System.out.print("p: ");
            int p = sc.nextInt();
            System.out.print("q: ");
            int q = sc.nextInt();
            qf.union(p,q);
        }

        System.out.println("The state of the array after performing union operations is as follows -: ");
        qf.display();

        for(int i = 0; i < connected; ++i) {
            System.out.println("Connected #" + (i+1));
            System.out.print("p: ");
            int p = sc.nextInt();
            System.out.print("q: ");
            int q = sc.nextInt();
            System.out.println("The two objects entered are connected? : " + qf.connected(p,q));
        }
    }
}


