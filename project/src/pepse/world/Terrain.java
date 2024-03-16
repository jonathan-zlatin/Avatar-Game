package pepse.world;

import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.NoiseGenerator;
import pepse.util.ColorSupplier;
import pepse.util.Constants;
import pepse.world.trees.Flora;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the terrain in the game environment.
 * Generates and manages the terrain blocks.
 */
public class Terrain {

    // Fields
    private static float groundHeightAtX0;
    private final NoiseGenerator noiseGenerator;
    private final Vector2 windowDimensions;


    /**
     * Rounds the specified number to the nearest multiple of the given factor.
     * If the number is exactly halfway between two multiples of the factor, it
     * is rounded towards positive.
     *
     * @param number the number to be rounded
     * @return the rounded number
     */
    private static int round(int number) {
        // Divide the number by the factor, round it to the nearest integer,
        return Math.round((float) number / Constants.BRICK_SIZE) * Constants.BRICK_SIZE;
    }

    /**
     * Constructs a new Terrain object with the specified window dimensions and seed.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param seed             The seed for generating random terrain.
     */
    public Terrain(Vector2 windowDimensions, int seed) {
        groundHeightAtX0 = windowDimensions.y() * Constants.GROUND_HEIGHT;
        noiseGenerator = new NoiseGenerator(seed, (int) groundHeightAtX0);
        this.windowDimensions = windowDimensions;
    }

    /**
     * Calculates the ground height at a specific x-coordinate.
     *
     * @param x The x-coordinate to calculate the ground height at.
     * @return The ground height at the specified x-coordinate.
     */
    public float groundHeightAt(float x) {
        // Use NoiseGenerator to generate smooth, seemingly random ground height

        float noise = (float) noiseGenerator.noise(x,
                Constants.BRICK_SIZE * Constants.NOISE_FACTOR);
        return groundHeightAtX0 + noise;
    }

    /**
     * Creates terrain blocks within the specified x-coordinate range.
     *
     * @param minX The minimum x-coordinate of the range.
     * @param maxX The maximum x-coordinate of the range.
     * @return A list of terrain blocks within the specified range.
     */
    public List<Block> createInRange(int minX, int maxX) {
        // create the list to return
        List<Block> TerrainBlocks = new ArrayList<>();

        minX = round(minX);
        maxX = round(maxX) + Constants.ONE;

        // Calculate number of blocks horizontally in the range
        for (float x = minX; x < maxX; x += Constants.BRICK_SIZE) {
            float StartY = this.groundHeightAt(x);
            StartY = (float) Math.min(
                    Math.floor(StartY / Constants.BRICK_SIZE) * Constants.BRICK_SIZE,
                    this.windowDimensions.y() - Constants.BRICK_SIZE);

            // Add blocks for the column
            for (int j = 0; j < Constants.TERRAIN_DEPTH; j++) {

                // create Renderer
                Random random = new Random();
                Color blockColor = Flora.changeColor(Constants.BASE_TERRAIN_GROUND_COLOR, random);
                RectangleRenderable rectangleRenderable = new RectangleRenderable(
                        ColorSupplier.approximateColor(blockColor));

                Vector2 position = new Vector2(x, StartY + (j * Constants.BRICK_SIZE));
                Block block = new Block(position, rectangleRenderable);
                TerrainBlocks.add(block);
            }
        }
        return TerrainBlocks;
    }
}


