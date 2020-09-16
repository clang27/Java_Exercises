/*
 * Write a function that tells if two nodes (inputs) in a network are connected. Each node object has a property that is a list of adjacent nodes. Here is a sample network:

      d
     / \
a-b-c   f
     \ /
      e

g-h-i

a and b are connected. a and f are connected. a and g are not.

a.adjacent would contain b.
c.adjacent would contain b,d,e

*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');

        a.addNeighbor(b);
        b.addNeighbor(c);
        c.addNeighbor(d);
        c.addNeighbor(e);
        d.addNeighbor(f);
        e.addNeighbor(f);

        // These should all return true
        System.out.println(a.isConnected(b));
        System.out.println(a.isConnected(c));
        System.out.println(a.isConnected(d));
        System.out.println(a.isConnected(e));
        System.out.println(a.isConnected(f));
        System.out.println(b.isConnected(a));
        System.out.println(c.isConnected(a));
        System.out.println(d.isConnected(a));
        System.out.println(e.isConnected(a));
        System.out.println(f.isConnected(a));
        System.out.println(d.isConnected(e));
        System.out.println();

        Node g = new Node('g');
        Node h = new Node('h');
        Node i = new Node('i');

        g.addNeighbor(h);
        h.addNeighbor(i);

        // These should all return true
        System.out.println(g.isConnected(h));
        System.out.println(g.isConnected(i));
        System.out.println(h.isConnected(g));
        System.out.println(i.isConnected(h));
        System.out.println(i.isConnected(g));
        System.out.println();

        // Edge cases that should all return false
        System.out.println(d.isConnected(g));
    }
}

class Node {
    char id;
    List<Node> nextNodes = new ArrayList<>();
    List<Node> prevNodes = new ArrayList<>();
    // Not needed, but just in case for future implementation
    private static List<Node> memoizedNodes = new ArrayList<>();

    public Node(char id) {
        this.id = id;
    }

    public void addNeighbor(Node neighbor) {
        // Add the node as a connected node to this node BOTH ways
        this.nextNodes.add(neighbor);
        neighbor.prevNodes.add(this);
    }


    public boolean isUpAhead(Node neighbor) {
        this.addToMemo();

        for(Node n1: this.nextNodes) {
            if (n1.equals(neighbor) || n1.isUpAhead(neighbor)) {
                return true;
            }
        }

        Node.clearMemo();
        return false;
    }

    public boolean isDownBehind(Node neighbor) {
        this.addToMemo();

        for(Node n1: this.prevNodes) {
            if (n1.equals(neighbor) || n1.isDownBehind(neighbor)) {
                return true;
            }
        }

        Node.clearMemo();
        return false;
    }

    public boolean isSibling(Node neighbor) {
        this.addToMemo();

        for(Node n1: this.prevNodes) {
            if (n1.nextNodes.contains(neighbor)) {
                return true;
            }
        }

        for(Node n2: this.nextNodes) {
            if (n2.prevNodes.contains(neighbor)) {
                return true;
            }
        }

        Node.clearMemo();
        return false;
    }

    public boolean isConnected(Node neighbor) {
        return this.isUpAhead(neighbor) || this.isDownBehind(neighbor) || this.isSibling(neighbor);
    }

    public void addToMemo() {
        Node.memoizedNodes.add(this);
    }

    public static void clearMemo() {
        Node.memoizedNodes = new ArrayList<>();
    }

    public boolean beenVisited() {
        return Node.memoizedNodes.contains(this);
    }
}
