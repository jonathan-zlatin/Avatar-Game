package pepse.world.trees;

import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;
import pepse.world.ObserverAvatar;

import java.util.Random;

/**
 * Represents a leaf object in the game environment.
 */
public class Leaf extends GameObject implements ObserverAvatar {

    // Fields
    private final float angle;
    private boolean defaultRotate = true;
    private final Vector2 leafDimensions;

    /**
     * Constructs a new leaf object.
     *
     * @param leafPosition   Position of the leaf, in window coordinates (pixels).
     * @param leafDimensions Width and height of the leaf in window coordinates.
     * @param leafRenderer   The renderer representing the leaf.
     */
    public Leaf(Vector2 leafPosition, Vector2 leafDimensions,
                RectangleRenderable leafRenderer) {
        super(leafPosition, leafDimensions, leafRenderer);
        this.leafDimensions = leafDimensions;
        this.angle = Constants.ANGLE_LEAF;
        this.setTag(Constants.LEAF_TAG);
        // Start transition for leaf shaking
        scheduledTaskLeafShaking();
    }

    /**
     * Starts a scheduled task for leaf shaking transition.
     * The leaf will shake at random intervals.
     */
    private void scheduledTaskLeafShaking() {
        Random random = new Random();
        float waitTime = random.nextFloat(Constants.WAIT_TIME_LEAF);
        new ScheduledTask(
                this,
                waitTime,
                true,
                this::startLeafShakingTransition
        );
    }

    /**
     * Starts the leaf shaking transition.
     * The leaf rotates and changes dimensions during shaking.
     */
    private void startLeafShakingTransition() {
        if (defaultRotate) {
            // Transition for changing leaf angle
            new Transition<>(
                    this,
                    angle -> this.renderer().setRenderableAngle(angle),
                    -angle, angle,
                    Transition.CUBIC_INTERPOLATOR_FLOAT,
                    Constants.LEAF_SHACK_INTERVAL,
                    Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                    null
            );
        } else {
            // Transition for rotating leaf 360 degrees
            new Transition<>(
                    this,
                    (Float angle) -> this.renderer().setRenderableAngle(angle),
                    Constants.ZERO_FLOAT,
                    Constants.TRANSITION_FULL_CIRCLE,
                    Transition.LINEAR_INTERPOLATOR_FLOAT,
                    Constants.LEAF_SHACK_INTERVAL,
                    Transition.TransitionType.TRANSITION_ONCE,
                    null);

            defaultRotate = true;
        }
        // Transition for changing leaf dimensions
        new Transition<>(
                this,
                this::setDimensions,
                this.leafDimensions,
                calculateNewDimensions(),
                Transition.CUBIC_INTERPOLATOR_VECTOR,
                Constants.ONE,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null
        );
    }

    /**
     * Calculates new dimensions for the leaf during shaking.
     * The leaf's dimensions change based on its rotation angle.
     *
     * @return The new dimensions of the leaf.
     */
    private Vector2 calculateNewDimensions() {
        // Modify leaf dimensions
        float scaleFactor = Constants.LEAF_SCALE + Math.abs(angle) / Constants.LEAF_SCALE_DIVIDE;
        return new Vector2(leafDimensions.x() * scaleFactor, leafDimensions.y());
    }

    /**
     * implementation of update from observer interface
     */
    @Override
    public void update(double energy) {
    }

    /**
     * implementation of onJump from observer interface
     * it end with rotating the leaf in 90 degrees.
     */
    @Override
    public void onJump() {
        defaultRotate = false;
    }
}
