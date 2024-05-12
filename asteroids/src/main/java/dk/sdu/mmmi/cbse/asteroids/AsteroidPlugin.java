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
        int destroyedAsteroids = gameData.getDestroyedAsteroids();
        int diffculty = 4;
        if(destroyedAsteroids >= 100) diffculty = diffculty + gameData.getDestroyedAsteroids()/100;
        if(diffculty > (gameData.getDisplayHeight()/5)) diffculty = (gameData.getDisplayHeight()/5);
        if(diffculty < 4) diffculty = 4;
        for(int i = 0; i < diffculty; i++) {
            Entity asteroid = createAsteroid(null, gameData);
            //gameData.setLog(gameData.getLog()+"\nAsteroid"+i+" Created");
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
        double size = 0;
        if (e != null) size = (e.getSize()/2);
        Entity Astroid = null;
        if(size > 5 || e == null) {
            Astroid = new Asteroids();
            Boolean isNew = Boolean.TRUE;
            Random randSize = new Random();
            if (e != null) {
                isNew = Boolean.FALSE;
                Astroid.setSize(randSize.nextInt(5,(int)size));
            }
            else Astroid.setSize(randSize.nextInt(10, 50));
            Astroid.setPolyCoordinatesArray(createShape(Astroid.getSize()));
            Astroid = setSpawn(Astroid, e, gameData, isNew);
            Astroid.setPaint(Color.GREY);
            Astroid.setHealth(Astroid.getSize());
            Astroid.setDamage(Astroid.getSize());
            Astroid.setCurrenthealth(Astroid.getHealth());
            Random speed = new Random();
            Astroid.setSpeed(speed.nextDouble(1, 2));
            Astroid.setRotationSpeed(1);
        }
        return Astroid;
    }

    private Entity setSpawn(Entity Astroid, Entity e, GameData gameData, Boolean isNew) {
        Random side = new Random();
        Random xpoint = new Random();
        Random ypoint = new Random();
        Random point = new Random();
        if(isNew) {
                switch (side.nextInt(1,4)){
                    case 1:
                        Astroid.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                        Astroid.setY(gameData.getDisplayHeight());
                        break;
                    case 2:
                        Astroid.setX(0);
                        Astroid.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                        break;
                    case 3:
                        Astroid.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                        Astroid.setY(0);
                        break;
                    default:
                        Astroid.setX(gameData.getDisplayWidth());
                        Astroid.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                        break;
                }

                double[] target = new double[]{
                        point.nextDouble(gameData.getDisplayWidth()),
                        point.nextDouble(gameData.getDisplayHeight())
                };
                double vectorAngle = ((Astroid.getY() - target[1])/(Astroid.getX() - target[0]));

                Astroid.setRotation(Math.toDegrees(vectorAngle));
                //System.out.println("X: "+Astroid.getX()+" Y: "+Astroid.getY());

        }
        else {
            Astroid.setX(point.nextDouble((e.getX()-(e.getSize())),(e.getX()+(e.getSize()))));
            Astroid.setY(point.nextDouble((e.getY()-(e.getSize())),(e.getY()+(e.getSize()))));
            double vectorAngle = ((Astroid.getY() - e.getY())/(Astroid.getX() - e.getX()));
            Astroid.setRotation(Math.toDegrees(vectorAngle));
            Astroid.setSpeed((e.getSpeed()*2));
        }
        return Astroid;
    }

    private double[] createShape (double size) {
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
