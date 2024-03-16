package pepse.util;
import java.awt.*;
/**
 * A utility constants class containing constant values used throughout the application.
 */
public class Constants {

    /**
     * The limit we approve to change the current color.
     */
    public static final int COLOR_DELTA = 20;
    /**
     * The minimum space between trees.
     */
    public static final int TREES_SPACE = 15;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    public Constants() {
    }

    /**
     * Default sky color.
     */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");
    /**
     * Default NOISE_FACTOR.
     */
    public static final int NOISE_FACTOR = 6;

    /**
     * Default color for the base terrain ground.
     */
    public static final Color BASE_TERRAIN_GROUND_COLOR = new Color(212, 123, 74);

    /**
     * Default size of a brick.
     */
    public static final int BRICK_SIZE = 30;

    /**
     * Default height of the ground.
     */
    public static final float GROUND_HEIGHT = (2 / 3.f);

    /**
     * Default color for a black rectangle.
     */
    public static final Color BLACK_RECTANGLE = Color.BLACK;

    /**
     * Length of a day cycle.
     */
    public static final float DayCycleLength = 30;

    /**
     * Opacity value for midnight.
     */
    public static final float MIDNIGHT_OPACITY = 0.5f;

    /**
     * Default opacity value.
     */
    public static final float NO_OPACITY = 1f;

    /**
     * Default half value as a float.
     */
    public static final float HALF_FLOAT = 0.5f;

    /**
     * Default half value as a double.
     */
    public static final double HALF = 0.5;

    /**
     * Energy consumed for a jump action.
     */
    public static final int JUMP_ENERGY = 10;

    /**
     * Default depth of the terrain.
     */
    public static final int TERRAIN_DEPTH = 20;

    /**
     * Maximum energy value.
     */
    public static final int MAX_ENERGY = 100;

    /**
     * Energy gained from consuming a fruit.
     */
    public static final int FRUIT_ENERGY = 10;

    /**
     * Angle of leaf movement.
     */
    public static final float ANGLE_LEAF = 30f;

    /**
     * Time for which a leaf shakes.
     */
    public static final float WAIT_TIME_LEAF = 3f;

    /**
     * Interval for leaf shaking.
     */
    public static final int LEAF_SHACK_INTERVAL = 2;

    /**
     * Scale factor for leaf size.
     */
    public static final float LEAF_SCALE = 1.1f;

    /**
     * Divide factor for leaf scale.
     */
    public static final float LEAF_SCALE_DIVIDE = 90.0f;

    // Constants for physics simulation

    /**
     * Velocity in the x direction.
     */
    public static final float VELOCITY_X = 400;

    /**
     * Velocity in the y direction.
     */
    public static final float VELOCITY_Y = -650;

    /**

     Default size of the avatar.
     */
    public static final int AVATAR_SIZE= 50;
    /**

     Default Color of the avatar.
     */
    public static final Color AVATAR_COLOR= Color.DARK_GRAY;
    /**
     * Gravity constant.
     */
    public static final float GRAVITY = 600;

    // Tag constants for different game objects

    /**
     * Tag for text objects.
     */
    public static final String TEXT_TAG = "Text";

    /**
     * Tag for avatar objects.
     */
    public static final String AVATAR_TAG = "Avatar";

    /**
     * Tag for night objects.
     */
    public static final String NIGHT_TAG = "Night";

    /**
     * Tag for sun objects.
     */
    public static final String SUN_TAG = "Sun";

    /**
     * Tag for sun halo objects.
     */
    public static final String SUN_HALO_TAG = "SunHalo";

    /**
     * Tag for fruit objects.
     */
    public static final String FRUIT_TAG = "Fruit";

    /**
     * Tag for leaf objects.
     */
    public static final String LEAF_TAG = "Leaf";

    /**
     * Tag for trunk objects.
     */
    public static final String TRUNK_TAG = "Trunk";

    /**
     * Tag for block objects.
     */
    public static final String BLOCK_TAG = "Block";

    /**
     * Tag for sky objects.
     */
    public static final String SKY_TAG = "Sky";

    // Constants for sun and sun halo dimensions and colors

    /**
     * Dimension of the sun.
     */
    public static final int DIMENSION_SUN = 80;

    /**
     * Center position of the sun.
     */
    public static final float CENTER_SUN = 0.3f;

    /**
     * Full circle transition value.
     */
    public static final float TRANSITION_FULL_CIRCLE = 360f;

    /**
     * Dimension of the sun halo.
     */
    public static final int DIMENSION_SUN_HALO = 120;

    /**
     * Maximum value for RGB color.
     */
    public static final int COLOR_MAX_RGB = 255;

    /**
     * Alpha value for the sun halo color.
     */
    public static final int COLOR_A_SUN_HALO = 20;

    /**
     * Divide constant.
     */
    public static final int DIVIDE = 2;

    /**
     * Zero value as a float.
     */
    public static final float ZERO_FLOAT = 0F;

    // Constants for colors of tree components

    /**
     * Red color component for tree trunk.
     */
    public static final int COLOR_R_TRUNK = 100;

    /**
     * Green color component for tree trunk.
     */
    public static final int COLOR_G_TRUNK = 50;

    /**
     * Blue color component for tree trunk.
     */
    public static final int COLOR_B_TRUNK = 20;

    /**
     * Red color component for tree leaf.
     */
    public static final int COLOR_R_LEAF = 50;

    /**
     * Green color component for tree leaf.
     */
    public static final int COLOR_G_LEAF = 200;

    /**
     * Blue color component for tree leaf.
     */
    public static final int COLOR_B_LEAF = 30;

    /**
     * Red color component for fruit.
     */
    public static final int COLOR_R_FRUIT = 120;

    /**
     * Green color component for fruit.
     */
    public static final int COLOR_G_FRUIT = 30;

    /**
     * Blue color component for fruit.
     */
    public static final int COLOR_B_FRUIT = 20;

    // Other constants...

    /**
     * Maximum number of leaves for a tree.
     */
    public static final int MAX_LEAVES = 50;

    /**
     * Minimum number of leaves for a tree.
     */
    public static final int MIN_LEAVES = 30;

    /**
     * Offset for changing colors.
     */
    public static final int COLOR_OFFSET = 30;

    /**
     * Maximum value for random number generation.
     */
    public static final int RANDOM_MAX = 500;

    /**
     * Chance value for biased coin toss.
     */
    public static final int CHANCE = 499;

    /**
     * Minimum range for trunk height.
     */
    public static final int TRUNK_HEIGHT_MIN_RANGE = Constants.BRICK_SIZE * 5;

    /**
     * Maximum range for trunk height.
     */
    public static final int TRUNK_HEIGHT_MAX_RANGE = Constants.BRICK_SIZE * 10;
    /**
     * Minimum range for trunk radius.
     */
    public static final int RADIUS_MIN_RANGE = Constants.BRICK_SIZE * 2;

    /**
     * Maximum range for trunk radius.
     */
    public static final int RADIUS_MAX_RANGE = Constants.BRICK_SIZE * 3;

    /**
     * Constant value for one.
     */
    public static final int ONE = 1;

    /**
     * Constant value for negative one.
     */
    public static final int N_ONE = -1;

    /**
     * Maximum number of fruits for a tree.
     */
    public static final int MAX_FRUITS = 22;

    /**
     * Minimum number of fruits for a tree.
     */
    public static final int MIN_FRUITS = 12;

    /**
     * Default size of a leaf.
     */
    public static final float LEAF_SIZE = (float) 2 / 3 * Constants.BRICK_SIZE;

    /**
     * Default size of a fruit.
     */
    public static final float FRUIT_SIZE = (float) 4 / 7 * Constants.BRICK_SIZE;

    /**
     * Default spot for the avatar.
     */
    public static final int AVATAR_SPOT = 70;

    /**
     * Default x-coordinate for the energy spot.
     */
    public static final int ENERGY_SPOT_X = 40;

    /**
     * Default y-coordinate for the energy spot.
     */
    public static final int ENERGY_SPOT_Y = 60;

    /**
     * Default percent
     */
    public static final String PERCENT_ENERGY_TEXT = "%";
}
