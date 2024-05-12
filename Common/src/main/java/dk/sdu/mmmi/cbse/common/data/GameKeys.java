package dk.sdu.mmmi.cbse.common.data;

/**
 * The type Game keys.
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 6;
    /**
     * The constant UP.
     */
    public static final int UP = 0;
    /**
     * The constant LEFT.
     */
    public static final int LEFT = 1;
    /**
     * The constant RIGHT.
     */
    public static final int RIGHT = 2;
    /**
     * The constant SPACE.
     */
    public static final int SPACE = 3;
    /**
     * The constant E.
     */
    public static final int E = 4;
    /**
     * The constant Q.
     */
    public static final int Q = 5;

    /**
     * Instantiates a new Game keys.
     */
    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    /**
     * Update.
     */
    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    /**
     * Sets key.
     *
     * @param k the k
     * @param b the b
     */
    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    /**
     * Is down boolean.
     *
     * @param k the k
     * @return the boolean
     */
    public boolean isDown(int k) {
        return keys[k];
    }

    /**
     * Is pressed boolean.
     *
     * @param k the k
     * @return the boolean
     */
    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}
