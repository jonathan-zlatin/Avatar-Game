package pepse.world;

/**
 * Interface for objects observing the state of an avatar in the game.
 */
public interface ObserverAvatar {
    /**
     * Notifies the observer about changes in the avatar's energy level.
     *
     * @param energy The updated energy level of the avatar.
     */
    void update(double energy);

    /**
     * Notifies the observer when the avatar performs a jump action.
     */
    void onJump();
}
