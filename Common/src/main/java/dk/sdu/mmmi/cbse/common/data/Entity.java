package dk.sdu.mmmi.cbse.common.data;

import javafx.scene.paint.Paint;

import java.io.Serializable;
import java.util.UUID;

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

    public Boolean getImmortal() { return immortal; }

    public void setImmortal(Boolean immortal) { this.immortal = immortal; }

    public Boolean getDead() { return dead; }

    public void setDead(Boolean dead) { this.dead = dead; }

    public double getDamage() { return damage; }

    public void setDamage(double damage) { this.damage = damage; }

    public double[] getTarget() { return target; }

    public void setTarget(double[] Target) { this.target = Target; }

    public double getShotTimer() { return shotTimer; }

    public void setShotTimer(double ShotTimer) { this.shotTimer = ShotTimer; }

    public double getMaxShotTimer() { return maxShotTimer; }

    public void setMaxShotTimer(double MaxShotTimer) { this.maxShotTimer = MaxShotTimer; }

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public void setPolyCoordinatesArray (double[] cordinates) { this.polygonCoordinates = cordinates; }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public double getMaxRotation() { return maxRotation; }

    public void setRotationSpeed(double RotationSpeed) { this.rotationSpeed = RotationSpeed; }

    public double getRotationSpeed() {return rotationSpeed;}

    public void setSpeed(double Speed) { this.speed = Speed; }

    public double getSpeed() { return speed; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public double getHealth() { return health; }

    public void setHealth(double health) { this.health = health; }

    public double getCurrenthealth() { return currenthealth; }

    public void setCurrenthealth(double CurrentHealth) { this.currenthealth = CurrentHealth; }

    public Paint getPaint() { return paint; }

    public void setPaint(Paint paint) { this.paint = paint; }
}
