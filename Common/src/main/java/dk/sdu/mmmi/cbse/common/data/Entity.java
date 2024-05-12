package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Entity.
 */
public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double speed;
    private double rotation;
    private double maxRotation = 360;
    private double rotationSpeed;
    private int size;
    private double health;
    private double currenthealth;
    private Paint paint;
    private double shotTimer = 0;
    private double maxShotTimer = 1;
    private double[] target;
    private double damage;
    private Boolean dead = false;
    private Boolean immortal = true;
    private String type;

    /**
     * Gets immortal.
     *
     * @return the immortal
     */
    public Boolean getImmortal() { return immortal; }

    /**
     * Sets immortal.
     *
     * @param immortal the immortal
     */
    public void setImmortal(Boolean immortal) { this.immortal = immortal; }

    /**
     * Gets dead.
     *
     * @return the dead
     */
    public Boolean getDead() { return dead; }

    /**
     * Sets dead.
     *
     * @param dead the dead
     */
    public void setDead(Boolean dead) { this.dead = dead; }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public double getDamage() { return damage; }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(double damage) { this.damage = damage; }

    /**
     * Get target double [ ].
     *
     * @return the double [ ]
     */
    public double[] getTarget() { return target; }

    /**
     * Sets target.
     *
     * @param Target the target
     */
    public void setTarget(double[] Target) { this.target = Target; }

    /**
     * Gets shot timer.
     *
     * @return the shot timer
     */
    public double getShotTimer() { return shotTimer; }

    /**
     * Sets shot timer.
     *
     * @param ShotTimer the shot timer
     */
    public void setShotTimer(double ShotTimer) { this.shotTimer = ShotTimer; }

    /**
     * Gets max shot timer.
     *
     * @return the max shot timer
     */
    public double getMaxShotTimer() { return maxShotTimer; }

    /**
     * Sets max shot timer.
     *
     * @param MaxShotTimer the max shot timer
     */
    public void setMaxShotTimer(double MaxShotTimer) { this.maxShotTimer = MaxShotTimer; }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return ID.toString();
    }


    /**
     * Sets polygon coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    /**
     * Sets poly coordinates array.
     *
     * @param cordinates the cordinates
     */
    public void setPolyCoordinatesArray (double[] cordinates) { this.polygonCoordinates = cordinates; }

    /**
     * Get polygon coordinates double [ ].
     *
     * @return the double [ ]
     */
    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }


    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x =x;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets rotation.
     *
     * @param rotation the rotation
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Gets rotation.
     *
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Gets max rotation.
     *
     * @return the max rotation
     */
    public double getMaxRotation() { return maxRotation; }

    /**
     * Sets rotation speed.
     *
     * @param RotationSpeed the rotation speed
     */
    public void setRotationSpeed(double RotationSpeed) { this.rotationSpeed = RotationSpeed; }

    /**
     * Gets rotation speed.
     *
     * @return the rotation speed
     */
    public double getRotationSpeed() {return rotationSpeed;}

    /**
     * Sets speed.
     *
     * @param Speed the speed
     */
    public void setSpeed(double Speed) { this.speed = Speed; }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() { return speed; }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() { return size; }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) { this.size = size; }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() { return health; }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(double health) { this.health = health; }

    /**
     * Gets currenthealth.
     *
     * @return the currenthealth
     */
    public double getCurrenthealth() { return currenthealth; }

    /**
     * Sets currenthealth.
     *
     * @param CurrentHealth the current health
     */
    public void setCurrenthealth(double CurrentHealth) { this.currenthealth = CurrentHealth; }

    /**
     * Gets paint.
     *
     * @return the paint
     */
    public Paint getPaint() { return paint; }

    /**
     * Sets paint.
     *
     * @param paint the paint
     */
    public void setPaint(Paint paint) { this.paint = paint; }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() { return type; }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) { this.type = type; }
}
