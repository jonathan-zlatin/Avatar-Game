package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;

/**
 * Represents the sky in the world.
 */
public abstract class Sky {
    /**
     * Creates a new sky GameObject.
     *
     * @param windowDimensions The dimensions of the window.
     * @return The created sky GameObject.
     */
    public static GameObject create(Vector2 windowDimensions) {
        // initiate sky GameObject
        GameObject sky = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Constants.BASIC_SKY_COLOR));

        // set the sky to move along the camera
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);

        // set sky tag to "sky"
        sky.setTag(Constants.SKY_TAG);
        return sky;
    }
}
