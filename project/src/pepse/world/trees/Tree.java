package pepse.world.trees;

import java.util.List;

/**
 * Represents a tree consisting of a trunk, leaves, and fruits.
 */
public class Tree {
    private final Trunk trunk;
    private final List<Leaf> leafs;
    private final List<Fruit> fruits;

    /**
     * Constructs a tree with the specified trunk, leaves, and fruits.
     *
     * @param trunk  the trunk of the tree
     * @param leafs  the list of leaves on the tree
     * @param fruits the list of fruits on the tree
     */
    public Tree(Trunk trunk, List<Leaf> leafs, List<Fruit> fruits) {
        this.trunk = trunk;
        this.leafs = leafs;
        this.fruits = fruits;
    }

    /**
     * Gets the list of fruits on the tree.
     *
     * @return the list of fruits on the tree
     */
    public List<Fruit> getFruits() {
        return fruits;
    }

    /**
     * Gets the trunk of the tree.
     *
     * @return the trunk of the tree
     */
    public Trunk getTrunk() {
        return trunk;
    }

    /**
     * Gets the list of leaves on the tree.
     *
     * @return the list of leaves on the tree
     */
    public List<Leaf> getLeafs() {
        return leafs;
    }
}