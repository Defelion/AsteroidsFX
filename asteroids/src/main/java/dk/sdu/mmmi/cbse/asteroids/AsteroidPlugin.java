package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        int destroyedAsteroids = gameData.getDestroydAsteroids();
        int diffculty = 10;
        if(destroyedAsteroids >= 10) diffculty = gameData.getDestroydAsteroids()/10;
        for(int i = 0; i < diffculty; i++) {
            Entity asteroid = createAsteroid(null, gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Asteroids.class) {
                world.removeEntity(e);
            }
        }
    }

    public Entity createAsteroid(Entity e, GameData gameData) {
        Entity Astroid = new Asteroids();
        Boolean isNew = Boolean.TRUE;
        if(e != null) {
            isNew = Boolean.FALSE;
            Astroid.setPolyCoordinatesArray(createShape((e.getSize()/2)));
        }
        else {
            Random randSize = new Random();
            Astroid.setSize(randSize.nextInt(10,50));
            Astroid.setPolyCoordinatesArray(createShape(Astroid.getSize()));
        }
        Astroid = setSpawn(Astroid, e, gameData, isNew);
        Astroid.setPaint(Color.DARKORANGE);
        Astroid.setHealth(Astroid.getSize());
        Astroid.setDamage(Astroid.getSize()/5);
        Astroid.setCurrenthealth(Astroid.getHealth());
        Random speed = new Random();
        Astroid.setSpeed(speed.nextDouble(1,2));
        Astroid.setRotationSpeed(1);
        return Astroid;
    }

    private Entity setSpawn(Entity Astroid, Entity e, GameData gameData, Boolean isNew) {
        Random side = new Random(4);
        Random xpoint = new Random(gameData.getDisplayWidth());
        Random ypoint = new Random(gameData.getDisplayHeight());
        Random point = new Random();
        if(isNew) {
            switch (side.nextInt(1,4)){
                case 1:
                    Astroid.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                    Astroid.setY(gameData.getDisplayHeight());
                    Astroid.setRotation(point.nextDouble(0,180));
                    break;
                case 2:
                    Astroid.setX(0);
                    Astroid.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                    Astroid.setRotation(point.nextDouble(90,270));
                    break;
                case 3:
                    Astroid.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                    Astroid.setRotation(point.nextDouble(180,360));
                    Astroid.setY(0);
                    break;
                default:
                    Astroid.setX(gameData.getDisplayWidth());
                    Astroid.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                    Random height = new Random(2);
                    if(height.nextInt(1,2) == 2)
                        Astroid.setRotation(point.nextDouble(270,360));
                    else
                        Astroid.setRotation(point.nextDouble(0,90));
                    break;
            }
        }
        else {
            Astroid.setX(e.getX());
            Astroid.setY(e.getY());
            Astroid.setRotation((Math.random() * 360));
        }
        return Astroid;
    }

    private double[] createShape (int size) {
        Random randSize = new Random();
        double big = randSize.nextDouble((size/2),size);
        double small = randSize.nextDouble(size/2);
        return new double[]{
                big,-small,
                small, -big,
                -small, -big,
                -big,-small,
                -big,small,
                -small, big,
                small, big,
                big,small
        };
    }
}
