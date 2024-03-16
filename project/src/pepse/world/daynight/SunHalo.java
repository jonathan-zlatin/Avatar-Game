package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;

import java.awt.*;
/**

 This class represents the halo around the sun in the day-night cycle of the game world.
 It creates a GameObject that represents the halo with an oval renderer.
 The halo follows the position of the sun.
 */
public abstract class SunHalo {
    /**
     Creates a GameObject representing the halo around the sun.
     @param sun The sun GameObject.
     @return A GameObject representing the sun halo.
     */
    public static GameObject create(GameObject sun) {

        GameObject sunHalo = new GameObject(sun.getCenter(), new Vector2(Constants.DIMENSION_SUN_HALO,
                Constants.DIMENSION_SUN_HALO),
                new OvalRenderable(new Color(Constants.COLOR_MAX_RGB, Constants.COLOR_MAX_RGB,
                        0, Constants.COLOR_A_SUN_HALO)));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag(Constants.SUN_HALO_TAG);

        // callback function to the sun center
        sunHalo.addComponent((deltaTime) -> sunHalo.setCenter(sun.getCenter()));
        return sunHalo;
    }
}
