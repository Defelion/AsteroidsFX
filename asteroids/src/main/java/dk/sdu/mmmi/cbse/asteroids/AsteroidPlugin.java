package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        //AstroidControlSystem ACS = new AstroidControlSystem();
        Entity asteroid = createAsteroid(null, gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Asteroid.class) {
                world.removeEntity(e);
            }
        }
    }

    public Entity createAsteroid(Entity e, GameData gameData) {
        Entity Astroid = new Asteroid();
        Boolean isNew = Boolean.TRUE;
        /*if(e != null) {
            isNew = Boolean.FALSE;
            Astroid = createShape(Astroid,(e.getSize()-1));
        }*/
        Astroid = setSpawn(Astroid, e, gameData, isNew);
        Random speed = new Random(2);
        Astroid.setSpeed(speed.nextDouble());
        Astroid.setRotationSpeed(1);
        return Astroid;
    }

    private Entity setSpawn(Entity Astroid, Entity e, GameData gameData, Boolean isNew) {
        Random side = new Random(4);
        Random xpoint = new Random(gameData.getDisplayWidth());
        Random ypoint = new Random(gameData.getDisplayHeight());
        if(isNew) {
            switch (side.nextInt()){
                case 1:
                    Astroid.setX(xpoint.nextDouble());
                    Astroid.setY(gameData.getDisplayHeight());
                    Astroid.setRotation(((Math.random() * (180 - 0)) + 0));
                    break;
                case 2:
                    Astroid.setX(0);
                    Astroid.setY(ypoint.nextDouble());
                    Astroid.setRotation(((Math.random() * (270 - 90)) + 90));
                    break;
                case 3:
                    Astroid.setX(xpoint.nextDouble());
                    Astroid.setRotation(((Math.random() * (360 - 180)) + 180));
                    Astroid.setY(0);
                    break;
                default:
                    Astroid.setX(gameData.getDisplayWidth());
                    Astroid.setY(ypoint.nextDouble());
                    Random height = new Random(2);
                    if(height.nextInt() == 2) Astroid.setRotation(((Math.random() * (360 - 270)) + 270));
                    else Astroid.setRotation(((Math.random() * (90 - 0)) + 0));
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


    private Entity createShape (Entity Astroid, int size) {
        Astroid.setPolygonCoordinates(
                6,-2.5,
                2.5,-6,
                -2.5,-6,
                -6,-2.5,
                -6,2.5,
                -2.5,6,
                2.5,6,
                6,2.5
        );
        return Astroid;
    }
}
