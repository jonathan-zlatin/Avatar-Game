package pepse.world;

import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.AnimationRenderable;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.Constants;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player's avatar in the game.
 */
public class Avatar extends GameObject {
    // Constants
    private static final double TIME_BETWEEN_CLIPS = 0.1;
    private static final double TIME_BETWEEN_CLIPS_JUMP = 0.5;
    private static final String ASSETS_JUMP_0_PNG = "assets/jump_0.png";
    private static final String ASSETS_JUMP_1_PNG = "assets/jump_1.png";
    private static final String ASSETS_JUMP_2_PNG = "assets/jump_2.png";
    private static final String ASSETS_JUMP_3_PNG = "assets/jump_3.png";
    private static final String ASSETS_RUN_0_PNG = "assets/run_0.png";
    private static final String ASSETS_RUN_1_PNG = "assets/run_1.png";
    private static final String ASSETS_RUN_2_PNG = "assets/run_2.png";
    private static final String ASSETS_RUN_3_PNG = "assets/run_3.png";
    private static final String ASSETS_RUN_4_PNG = "assets/run_4.png";
    private static final String ASSETS_RUN_5_PNG = "assets/run_5.png";
    private static final String ASSETS_IDLE_0_PNG = "assets/idle_0.png";
    private static final String ASSETS_IDLE_1_PNG = "assets/idle_1.png";
    private static final String ASSETS_IDLE_2_PNG = "assets/idle_2.png";
    private static final String ASSETS_IDLE_3_PNG = "assets/idle_3.png";

    // Fields
    private double energy;
    private final UserInputListener inputListener;
    private AnimationRenderable idleRenderer;
    private AnimationRenderable jumpRenderer;
    private AnimationRenderable runRenderer;
    private final List<ObserverAvatar> observers = new ArrayList<>();

    /**
     * Constructs a new avatar object.
     *
     * @param pos           The initial position of the avatar.
     * @param inputListener The input listener for capturing user inputs.
     * @param imageReader   The image reader for loading avatar animations.
     */
    public Avatar(Vector2 pos, UserInputListener inputListener, ImageReader imageReader) {
        super(pos, new Vector2(Constants.AVATAR_SIZE, Constants.AVATAR_SIZE),
                new OvalRenderable(Constants.AVATAR_COLOR));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        transform().setAccelerationY(Constants.GRAVITY);
        this.inputListener = inputListener;
        this.energy = Constants.MAX_ENERGY;
        this.initIdleAnimation(imageReader);
        this.initRunAnimation(imageReader);
        this.initJumpAnimation(imageReader);
        this.renderer().setRenderable(this.idleRenderer);
        this.setTag(Constants.AVATAR_TAG);
    }

    /**
     * Adds an observer to the avatar.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ObserverAvatar observer) {
        observers.add(observer);
    }

    /**
     * Notifies the observers about the avatar's energy state.
     */
    private void setState(Double energy) {
        notifyEnergyObservers();
    }

    /**
     * Notifies all energy observers.
     */
    private void notifyEnergyObservers() {
        for (ObserverAvatar observer : observers) {
            observer.update(energy);
        }
    }

    /**
     * Notifies all jump observers.
     */
    private void notifyJumpObservers() {
        for (ObserverAvatar observer : observers) {
            observer.onJump();
        }
    }

    /**
     * Increases the avatar's energy.
     *
     * @param energy The energy to add.
     */
    public void addEnergy(float energy) {
        this.energy = Math.min(Constants.MAX_ENERGY, this.energy + energy);
        setState(this.energy);
    }

    /**
     * Initializes the idle animation for the avatar.
     *
     * @param imageReader The image reader for loading animation images.
     */
    private void initIdleAnimation(ImageReader imageReader) {
        Renderable[] idleAnimationRenderable = {
                imageReader.readImage(ASSETS_IDLE_0_PNG, true),
                imageReader.readImage(ASSETS_IDLE_1_PNG, true),
                imageReader.readImage(ASSETS_IDLE_2_PNG, true),
                imageReader.readImage(ASSETS_IDLE_3_PNG, true)
        };
        this.idleRenderer = new AnimationRenderable(idleAnimationRenderable, TIME_BETWEEN_CLIPS);
    }

    /**
     * Initializes the run animation for the avatar.
     *
     * @param imageReader The image reader for loading animation images.
     */
    private void initRunAnimation(ImageReader imageReader) {
        Renderable[] runAnimationRenderable = {
                imageReader.readImage(ASSETS_RUN_0_PNG, true),
                imageReader.readImage(ASSETS_RUN_1_PNG, true),
                imageReader.readImage(ASSETS_RUN_2_PNG, true),
                imageReader.readImage(ASSETS_RUN_3_PNG, true),
                imageReader.readImage(ASSETS_RUN_4_PNG, true),
                imageReader.readImage(ASSETS_RUN_5_PNG, true)
        };
        this.runRenderer = new AnimationRenderable(runAnimationRenderable, TIME_BETWEEN_CLIPS);
    }

    /**
     * Initializes the jump animation for the avatar.
     *
     * @param imageReader The image reader for loading animation images.
     */
    private void initJumpAnimation(ImageReader imageReader) {
        Renderable[] jumpAnimationRenderable = {
                imageReader.readImage(ASSETS_JUMP_0_PNG, true),
                imageReader.readImage(ASSETS_JUMP_1_PNG, true),
                imageReader.readImage(ASSETS_JUMP_2_PNG, true),
                imageReader.readImage(ASSETS_JUMP_3_PNG, true)
        };
        this.jumpRenderer = new AnimationRenderable(jumpAnimationRenderable, TIME_BETWEEN_CLIPS_JUMP);
    }

    /**
     * Updates the avatar's state based on user input and energy level.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float xVel = 0;
        if (getVelocity().y() != 0) {
            renderer().setRenderable(jumpRenderer);
        }
        if (this.getVelocity().x() == 0 && this.getVelocity().y() == 0) {
            energy = Math.min(Constants.MAX_ENERGY, energy + Constants.ONE);
            setState(energy);
            renderer().setRenderable(idleRenderer);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT) && energy >= Constants.HALF) {
            xVel -= Constants.VELOCITY_X;
            energy -= Constants.HALF;
            setState(energy);
            renderer().setRenderable(runRenderer);
            renderer().setIsFlippedHorizontally(true);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT) && energy >= Constants.HALF) {
            xVel += Constants.VELOCITY_X;
            energy -= Constants.HALF;
            setState(energy);
            renderer().setIsFlippedHorizontally(false);
            renderer().setRenderable(runRenderer);
        }
        transform().setVelocityX(xVel);
        if (inputListener.isKeyPressed(KeyEvent.VK_SPACE) &&
                getVelocity().y() == 0 && energy >= Constants.JUMP_ENERGY) {
            transform().setVelocityY(Constants.VELOCITY_Y);
            energy -= Constants.JUMP_ENERGY;
            setState(energy);
            renderer().setRenderable(jumpRenderer);
            notifyJumpObservers();
        }
    }
}
