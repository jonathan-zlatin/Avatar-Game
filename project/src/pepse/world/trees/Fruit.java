package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Constants;
import pepse.world.Avatar;
import pepse.world.ObserverAvatar;

import java.awt.*;
import java.util.Objects;


/**
 * Represents a fruit object in the game environment.
 */
public class Fruit extends GameObject implements ObserverAvatar {
    private final Color fruitBaseColor;
    private boolean fruitCollide;

    /**
     * Constructs a new fruit object.
     *
     * @param topLeftCorner  Position of the object, in window coordinates (pixels).
     * @param dimensions     Width and height in window coordinates.
     * @param renderer       The renderer representing the object.
     * @param fruitBaseColor The color of the fruit.
     */
    public Fruit(Vector2 topLeftCorner, Vector2 dimensions,
                 Renderable renderer, Color fruitBaseColor) {
        super(topLeftCorner, dimensions, renderer);
        this.fruitBaseColor = fruitBaseColor;
        this.setTag(Constants.FRUIT_TAG);
        this.fruitCollide = false;
    }

    /**
     * Initializes transitions for the fruit object.
     * Transitions are used for animations/effects.
     */
    private void initFruitTransitions() {
        new Transition<>(
                this,
                this.renderer()::setOpaqueness,
                Constants.ZERO_FLOAT, Constants.ZERO_FLOAT,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                Constants.DayCycleLength,
                Transition.TransitionType.TRANSITION_ONCE,
                () -> this.renderer().setOpaqueness(Constants.NO_OPACITY));

        new ScheduledTask(this, Constants.DayCycleLength,
                false, this::isCollide);
    }

    /**
     * Callback func to change state of fruit after the interval
     */
    private void isCollide() {
        fruitCollide = false;
    }

    /**
     * Called when a collision occurs with another game object.
     * If the colliding object is an Avatar, increases
     * its energy and initiates fruit transitions.
     *
     * @param other     The game object collided with.
     * @param collision Details about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (!this.fruitCollide && Objects.equals(other.getTag(), Constants.AVATAR_TAG)) {
            ((Avatar) other).addEnergy(Constants.FRUIT_ENERGY);
            initFruitTransitions();
            this.fruitCollide = true;
        }
    }

    /**
     * Updates the fruit object.
     *
     * @param energy The energy level of the fruit.
     */
    @Override
    public void update(double energy) {
    }

    /**
     * Called when the Avatar is jumping.
     * Changes the fruit's color randomly
     * and updates its rendering.
     */
    @Override
    public void onJump() {
        Color fruitColor = ColorSupplier.approximateColor(fruitBaseColor, Constants.COLOR_DELTA);
        this.renderer().setRenderable(new OvalRenderable(fruitColor));
    }
}
