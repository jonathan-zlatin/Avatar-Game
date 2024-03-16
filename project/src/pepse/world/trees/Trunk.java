package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Constants;
import pepse.world.ObserverAvatar;

import java.awt.*;

/**
 * Represents a trunk object in the game environment.
 */
public class Trunk extends GameObject implements ObserverAvatar {

    // Fields
    private final Vector2 top;
    private final Color trunkBaseColor;

    /**
     * Constructs a new trunk object.
     *
     * @param position       Position of the trunk, in window coordinates (pixels).
     * @param dimensions     Width and height of the trunk in window coordinates.
     * @param renderer       The renderer representing the trunk.
     * @param trunkBaseColor The color of the trunk.
     */
    public Trunk(Vector2 position, Vector2 dimensions,
                 Renderable renderer, Color trunkBaseColor) {
        super(position, dimensions, renderer);
        this.top = new Vector2(position.x(), position.y());
        this.trunkBaseColor = trunkBaseColor;
        this.setTag(Constants.TRUNK_TAG);

    }

    /**
     * Handles collision events with other game objects.
     * Prevents intersections and sets the trunk to immovable.
     *
     * @param other     The game object collided with.
     * @param collision Details about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }

    /**
     * Retrieves the top position of the trunk.
     *
     * @return The top position of the trunk.
     */
    public Vector2 getTop() {
        return top;
    }

    /**
     * Updates the trunk object.
     *
     * @param energy The energy level of the trunk.
     */
    @Override
    public void update(double energy) {
    }

    /**
     * Called when an Avatar jumps.
     * Changes the color of the trunk randomly.
     */
    @Override
    public void onJump() {
        Color trunkColor = ColorSupplier.approximateColor(trunkBaseColor, Constants.COLOR_DELTA);
        this.renderer().setRenderable(new RectangleRenderable(trunkColor));
    }
}
