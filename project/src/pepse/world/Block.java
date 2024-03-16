package pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;

/**
 * Represents a block GameObject in the world.
 */
public class Block extends GameObject {
    /**
     * Constructs a new Block instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     * @param renderer      The renderer representing the object.
     */
    // initiate new Block
    public Block(Vector2 topLeftCorner, Renderable renderer) {
        super(topLeftCorner, Vector2.ONES.mult(Constants.BRICK_SIZE), renderer);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        this.setTag(Constants.BLOCK_TAG);
    }

}
