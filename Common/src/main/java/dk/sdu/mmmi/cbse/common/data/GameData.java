package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.layout.Pane;

/**
 * The type Game data.
 */
public class GameData {

    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private int destroyedAsteroids = 0;
    private int destroyedEnemies = 0;
    private double score = 0;
    private  String log = "";
    private int immortalTime = 100;
    private Pane Menu = new Pane();
    private boolean GameOver = false;

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public Pane getMenu() {
        return Menu;
    }

    /**
     * Sets menu.
     *
     * @param Menu the menu
     */
    public void setMenu (Pane Menu) { this.Menu = Menu; }

    /**
     * Gets immortal time.
     *
     * @return the immortal time
     */
    public int getImmortalTime() { return immortalTime; }

    /**
     * Sets immortal time.
     *
     * @param immortalTime the immortal time
     */
    public void setImmortalTime(int immortalTime) { this.immortalTime = immortalTime; }

    /**
     * Gets keys.
     *
     * @return the keys
     */
    public GameKeys getKeys() {
        return keys;
    }

    /**
     * Sets display width.
     *
     * @param width the width
     */
    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    /**
     * Gets display width.
     *
     * @return the display width
     */
    public int getDisplayWidth() {
        return displayWidth;
    }

    /**
     * Sets display height.
     *
     * @param height the height
     */
    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    /**
     * Gets display height.
     *
     * @return the display height
     */
    public int getDisplayHeight() {
        return displayHeight;
    }

    /**
     * Gets destroyed asteroids.
     *
     * @return the destroyed asteroids
     */
    public int getDestroyedAsteroids() { return destroyedAsteroids; }

    /**
     * Sets destroyed asteroids.
     *
     * @param destroyedAsteroids the destroyed asteroids
     */
    public void setDestroyedAsteroids(int destroyedAsteroids) { this.destroyedAsteroids = destroyedAsteroids; }

    /**
     * Gets destroyed enemies.
     *
     * @return the destroyed enemies
     */
    public int getDestroyedEnemies() { return destroyedEnemies; }

    /**
     * Sets destroyed enemies.
     *
     * @param destroyedEnemies the destroyed enemies
     */
    public void setDestroyedEnemies(int destroyedEnemies) { this.destroyedEnemies = destroyedEnemies; }

    /**
     * Gets score.
     *
     * @return the score
     */
    public double getScore() { return score; }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(double score) { this.score = score; }

    /**
     * Gets log.
     *
     * @return the log
     */
    public String getLog() { return log; }

    /**
     * Sets log.
     *
     * @param log the log
     */
    public void setLog(String log) { this.log = log; }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return GameOver;
    }

    /**
     * Sets game over.
     *
     * @param gameOver the game over
     */
    public void setGameOver(boolean gameOver) {
        this.GameOver = gameOver;
    }
}
