package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private int destroydAsteroids = 0;
    private int destroyedEnemies = 0;
    private double score = 0;
    private  String log = "";
    private int immortalTime = 50;

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
