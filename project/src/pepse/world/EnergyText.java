package pepse.world;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;
import pepse.util.Constants;


/**
 * Represents a text display for the energy level in the game.
 * Extends GameObject and implements ObserverAvatar.
 */
public class EnergyText extends GameObject implements ObserverAvatar {
    // Fields
    private final TextRenderable textRenderable;
    private int energy;

    /**
     * Constructs a new EnergyText object with the specified position and dimensions.
     *
     * @param topLeftCorner The top-left corner position of the text display.
     * @param dimensions    The dimensions of the text display.
     */
    public EnergyText(Vector2 topLeftCorner, Vector2 dimensions) {
        super(topLeftCorner, dimensions, null);
        this.textRenderable = new TextRenderable(String.valueOf(this.energy));
        this.renderer().setRenderable(textRenderable);
        GameObject text = new GameObject(topLeftCorner, dimensions, textRenderable);
        text.setTag(Constants.TEXT_TAG);
    }

    /**
     * Updates the energy level displayed in the text.
     *
     * @param energy The new energy level to display.
     */
    @Override
    public void update(double energy) {
        this.energy = (int) energy;
        textRenderable.setString(this.energy + Constants.PERCENT_ENERGY_TEXT);
    }

    /**
     * Placeholder method for the jump action.
     */
    @Override
    public void onJump() {
    }
}
