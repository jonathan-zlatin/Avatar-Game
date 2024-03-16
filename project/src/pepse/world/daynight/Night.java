package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;
/**

 This class represents the night in the day-night cycle of the game world.
 It creates a GameObject that represents the night with a black rectangle renderer.
 The opacity of the night transitions from 0 to MIDNIGHT_OPACITY and back over half of the cycle length.
 */
public abstract class Night {
    /**
     Creates a GameObject representing the night.
     @param windowDimensions The dimensions of the game window.
     @param cycleLength The length of the day-night cycle.
     @return A GameObject representing the night.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Constants.BLACK_RECTANGLE));
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag(Constants.NIGHT_TAG);
        new Transition<>(night,
                night.renderer()::setOpaqueness,
                Constants.ZERO_FLOAT,
                Constants.MIDNIGHT_OPACITY,
                Transition.CUBIC_INTERPOLATOR_FLOAT,
                cycleLength / Constants.DIVIDE,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null
        );
        return night;
    }
}
