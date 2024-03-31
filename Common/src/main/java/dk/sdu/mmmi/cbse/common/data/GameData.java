package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.layout.Pane;

public class GameData {

    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private int destroydAsteroids = 0;
    private int destroyedEnemies = 0;
    private double score = 0;
    private  String log = "";
    private int immortalTime = 100;
    private Pane Menu = new Pane();

    public Pane getMenu() {
        return Menu;
    }

    public void setMenu (Pane Menu) { this.Menu = Menu; }

    public int getImmortalTime() { return immortalTime; }

    public void setImmortalTime(int immortalTime) { this.immortalTime = immortalTime; }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getDestroydAsteroids() { return destroydAsteroids; }

    public void setDestroydAsteroids(int destroydAsteroids) { this.destroydAsteroids = destroydAsteroids; }

    public int getDestroyedEnemies() { return destroyedEnemies; }

    public void setDestroyedEnemies(int destroyedEnemies) { this.destroyedEnemies = destroyedEnemies; }

    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }

    public String getLog() { return log; }

    public void setLog(String log) { this.log = log; }
}
