package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.world.*;
import pepse.util.Constants;

import java.util.List;
import java.util.Random;

import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.*;

/**
 * Manages the game logic for Pepse game.
 * Extends GameManager.
 */
public class PepseGameManager extends GameManager {
    // Fields
    private ImageReader imageReader;
    private UserInputListener inputListener;
    private Vector2 windowDimensions;
    private Terrain terrain;
    private Avatar avatar;

    /**
     * Initializes the game environment, including terrain,
     * sky, sun, night, avatar, and flora.
     *
     * @param imageReader      The image reader for loading images.
     * @param soundReader      The sound reader for loading sounds.
     * @param inputListener    The input listener for capturing user inputs.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowDimensions = windowController.getWindowDimensions();
        generateGame();
    }

    /**
     * Generates the game environment.
     */
    private void generateGame() {
        initializeSky();
        initializeTerrain();
        initializeSun();
        initializeNight();
        initializeAvatar();
        initializeFlora();
    }

    /**
     * Initializes the sky game object.
     */
    private void initializeSky() {
        GameObject sky = Sky.create(windowDimensions);
        gameObjects().addGameObject(sky, Layer.BACKGROUND);
    }

    /**
     * Initializes the terrain game object.
     */
    private void initializeTerrain() {
        int seed = new Random().nextInt();
        Terrain terrain = new Terrain(windowDimensions, seed);
        List<Block> blocks = terrain.createInRange(0, (int) windowDimensions.x());

        // adds all the terrain blocks to the game
        for (Block block : blocks) {
            gameObjects().addGameObject(block, Layer.STATIC_OBJECTS);
        }
        this.terrain = terrain;
    }

    /**
     * Initializes the sun game object.
     */
    private void initializeSun() {
        GameObject sun = Sun.create(windowDimensions, Constants.DayCycleLength);
        gameObjects().addGameObject(sun, Layer.BACKGROUND);
        initializeSunHaloCallback(sun);
    }

    /**
     * Initializes the night game object.
     */
    private void initializeNight() {
        GameObject night = Night.create(windowDimensions, Constants.DayCycleLength);
        gameObjects().addGameObject(night, Layer.UI);
    }

    /**
     * Initializes the sun halo game object.
     *
     * @param sun The sun game object.
     */
    private void initializeSunHaloCallback(GameObject sun) {
        GameObject sunHalo = SunHalo.create(sun);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);
    }

    /**
     * Initializes the avatar game object.
     */
    private void initializeAvatar() {
        int xPosition = new Random().nextInt((int) windowDimensions.x());
        Vector2 pos = new Vector2(xPosition,
                terrain.groundHeightAt(xPosition) - Constants.AVATAR_SPOT);
        EnergyText energy = new EnergyText(Vector2.ZERO,
                new Vector2(Constants.ENERGY_SPOT_X, Constants.ENERGY_SPOT_Y));
        this.avatar = new Avatar(pos, inputListener, imageReader);
        this.avatar.addObserver(energy);
        gameObjects().addGameObject(energy);
        gameObjects().addGameObject(avatar);
    }

    /**
     * Initializes the flora game objects.
     */
    private void initializeFlora() {
        Flora flora = new Flora(terrain);

        //Initiate the trees
        flora.generateTrees(Constants.TERRAIN_DEPTH,
                (int) (windowDimensions.x() - Constants.TERRAIN_DEPTH));
        List<Tree> trees = flora.getTreeList();

        // add all the flora to the board
        // and make all the objects to observe the Avatar
        for (Tree tree : trees) {
            // add trunk
            gameObjects().addGameObject(tree.getTrunk(), Layer.STATIC_OBJECTS);
            avatar.addObserver(tree.getTrunk());

            // add leafs
            List<Leaf> Leafs = tree.getLeafs();
            for (Leaf leaf : Leafs) {
                avatar.addObserver(leaf);
                gameObjects().addGameObject(leaf, Layer.STATIC_OBJECTS);
            }

            // add fruits
            List<Fruit> fruits = tree.getFruits();
            for (Fruit fruit : fruits) {
                avatar.addObserver(fruit);
                gameObjects().addGameObject(fruit);
            }

        }
    }


    /**
     * The main method to start the Pepse game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }
}

