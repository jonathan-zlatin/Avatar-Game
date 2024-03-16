package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;

import java.awt.*;

/**
 * This class represents the sun in the day-night cycle of the game world.
 * It creates a GameObject that represents the sun with an oval renderer.
 * The sun moves in a circular path across the top of the screen.
 */
public abstract class Sun {
    /**
     * Creates a GameObject representing the sun.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The length of the day-night cycle.
     * @return A GameObject representing the sun.
     */
    public static GameObject create(Vector2 windowDimensions,
                                    float cycleLength) {
        // create Sun
        GameObject sun = new GameObject(
                windowDimensions.mult(Constants.HALF_FLOAT),
                new Vector2(Constants.DIMENSION_SUN, Constants.DIMENSION_SUN),
                new OvalRenderable(Color.yellow));
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag(Constants.SUN_TAG);

        // initial position and cycle path
        Vector2 initialSunCenter = new Vector2(
                windowDimensions.mult(Constants.HALF_FLOAT).x(),
                windowDimensions.y() * Constants.CENTER_SUN);
        Vector2 cycleCenter = new Vector2(
                windowDimensions.mult(Constants.HALF_FLOAT).x(),
                (windowDimensions.x() * Constants.GROUND_HEIGHT));

        // initial cycle transition
        startSunCycle(cycleLength, sun, initialSunCenter, cycleCenter);
        return sun;
    }

    /**
     * Starts the sun's movement in a circular path.
     *
     * @param cycleLength      The length of the day-night cycle.
     * @param sun              The sun GameObject.
     * @param initialSunCenter The initial center position of the sun.
     * @param cycleCenter      The center position of the circular path.
     */
    private static void startSunCycle(float cycleLength,
                                      GameObject sun,
                                      Vector2 initialSunCenter,
                                      Vector2 cycleCenter) {
        new Transition<>(sun,
                (Float angle) -> sun.setCenter(initialSunCenter.subtract(cycleCenter)
                        .rotated(angle)
                        .add(cycleCenter)),
                Constants.ZERO_FLOAT,
                Constants.TRANSITION_FULL_CIRCLE,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength,
                Transition.TransitionType.TRANSITION_LOOP,
                null
        );
    }
}
