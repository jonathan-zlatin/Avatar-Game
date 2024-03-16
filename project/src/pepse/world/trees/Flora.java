package pepse.world.trees;

import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.Terrain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents the flora in the game world, including trees, leaves, and fruits.
 * It provides methods to generate trees and their components.
 */
public class Flora {
    private final Terrain terrain;
    private final Color trunkColor = new Color(
            Constants.COLOR_R_TRUNK,
            Constants.COLOR_G_TRUNK,
            Constants.COLOR_B_TRUNK);
    private final Color leafColor = new Color(
            Constants.COLOR_R_LEAF,
            Constants.COLOR_G_LEAF,
            Constants.COLOR_B_LEAF);
    private final Color fruitColor = new Color(
            Constants.COLOR_R_FRUIT,
            Constants.COLOR_G_FRUIT,
            Constants.COLOR_B_FRUIT);
    private final List<Tree> treeList = new ArrayList<>();

    /**
     * Creates a new Flora instance with the specified terrain.
     *
     * @param terrain The terrain for the flora.
     */
    public Flora(Terrain terrain) {
        this.terrain = terrain;
    }

    /**
     * Changes the color of an object randomly by adding or
     * subtracting a random offset to each color component.
     *
     * @param objColor The original color.
     * @param random   The random number generator.
     * @return A new color with the changed RGB values.
     */
    public static Color changeColor(Color objColor, Random random) {
        int offset = random.nextInt(Constants.COLOR_OFFSET);

        // Randomly choose between going up or down
        int side = random.nextBoolean() ? Constants.ONE : Constants.N_ONE;

        // Ensure within [0, 255] range
        int r = Math.min(Math.max(objColor.getRed() + offset * side, 0), Constants.COLOR_MAX_RGB);
        int b = Math.min(Math.max(objColor.getBlue() + offset * side, 0), Constants.COLOR_MAX_RGB);
        int g = Math.min(Math.max(objColor.getGreen() + offset * side, 0), Constants.COLOR_MAX_RGB);
        return new Color(r, g, b);
    }

    /**
     * Simulates a biased coin toss with a specific chance.
     *
     * @return True if the coin toss resulted in the specified chance, false otherwise.
     */
    private static boolean biasedCoinToss() {
        Random random = new Random();
        int chance = random.nextInt(Constants.RANDOM_MAX);
        return chance == Constants.CHANCE;
    }

    /**
     * Gets the list of trees in the flora.
     *
     * @return The list of trees.
     */
    public List<Tree> getTreeList() {
        return treeList;
    }

    /**
     * Generates trees within the specified range of x coordinates.
     *
     * @param minX The minimum x coordinate.
     * @param maxX The maximum x coordinate.
     */
    public void generateTrees(int minX, int maxX) {
        Random random = new Random();
        for (int x = minX; x < maxX; x++) {
            if (biasedCoinToss()) { // decide weather to plant or not in this position
                // create trunk
                Color trunkC = changeColor(trunkColor, random);
                int trunkHeight = random.nextInt(
                        Constants.TRUNK_HEIGHT_MIN_RANGE,
                        Constants.TRUNK_HEIGHT_MAX_RANGE);
                Vector2 trunkPosition = new Vector2(x, terrain.groundHeightAt(x) - trunkHeight);
                Vector2 trunkDimensions = new Vector2(Constants.BRICK_SIZE, trunkHeight);
                RectangleRenderable trunkRenderer = new RectangleRenderable(trunkC);

                // create tree properties
                int radius = random.nextInt(
                        Constants.RADIUS_MIN_RANGE,
                        Constants.RADIUS_MAX_RANGE);
                Trunk trunk = new Trunk(trunkPosition, trunkDimensions, trunkRenderer, trunkColor);
                List<Leaf> leafs = this.generateLeafs(trunk.getTop(), radius);
                List<Fruit> fruits = this.generateFruits(trunk.getTop(), radius);

                // create tree
                Tree tree = new Tree(trunk, leafs, fruits);
                treeList.add(tree);

                // space the trees
                x += Constants.TREES_SPACE;
            }
        }
    }

    /**
     * Generates a list of leaves around the specified position with the given radius.
     *
     * @param leafPos The position around which to generate leaves.
     * @param radius  The radius within which to generate leaves.
     * @return The list of generated leaves.
     */
    public List<Leaf> generateLeafs(Vector2 leafPos, int radius) {
        List<Leaf> leafs = new ArrayList<>();

        // Generate a random number
        Random random = new Random();

        // Determine leaf size and amount
        float leafSize = Constants.LEAF_SIZE;
        int numLeaves = random.nextInt(Constants.MIN_LEAVES, Constants.MAX_LEAVES);

        // Create leafs
        for (int i = 0; i < numLeaves; i++) {
            // Generate random offsets for leaf position
            int offsetX = random.nextInt(-radius, radius);
            int offsetY = random.nextInt(-radius, radius);

            // Generate unique color
            Color leafC = changeColor(leafColor, random);

            // Create leaf block at a random position around the central position
            Vector2 leafPosition = new Vector2(
                    leafPos.x() + offsetX,
                    leafPos.y() + offsetY);
            RectangleRenderable leafRenderer = new RectangleRenderable(leafC);
            Vector2 leafDimensions = new Vector2(leafSize, leafSize);
            Leaf leaf = new Leaf(leafPosition, leafDimensions, leafRenderer);
            leafs.add(leaf);
        }
        return leafs;
    }

    /**
     * Generates a list of fruits around the specified position with the given radius.
     *
     * @param fruitsPos The position around which to generate fruits.
     * @param radius    The radius within which to generate fruits.
     * @return The list of generated fruits.
     */
    public List<Fruit> generateFruits(Vector2 fruitsPos, int radius) {
        List<Fruit> fruits = new ArrayList<>();

        // Generate a random number
        Random random = new Random();

        // Determine leaf size and amount
        int numFruit = random.nextInt(Constants.MIN_FRUITS, Constants.MAX_FRUITS);
        float fruitSize = Constants.FRUIT_SIZE;

        // Create fruits
        for (int i = 0; i < numFruit; i++) {
            // Generate random offsets for leaf position
            int offsetX = random.nextInt(-radius, radius);
            int offsetY = random.nextInt(-radius, radius);

            // Generate unique color
            Color fruitC = changeColor(fruitColor, random);

            // Create fruit block at a random position around the central position
            Vector2 fruitPosition = new Vector2(
                    fruitsPos.x() + offsetX,
                    fruitsPos.y() + offsetY);
            OvalRenderable fruitRenderer = new OvalRenderable(fruitC);
            Vector2 fruitDimensions = new Vector2(fruitSize, fruitSize);
            Fruit fruit = new Fruit(fruitPosition, fruitDimensions, fruitRenderer, fruitC);
            fruits.add(fruit);
        }
        return fruits;
    }
}