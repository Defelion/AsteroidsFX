package dk.sdu.mmmi.cbse.astroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AstroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Entity[] astroids = world.getEntities(Astroid.class).toArray(new Entity[0]);
        if(astroids.length == 0) {
            world.addEntity(createNewAstroid(gameData));
        }
        for(Entity astroid : world.getEntities(Astroid.class)) {
            astroid.setX(astroid.getX() + Math.cos(Math.toRadians(astroid.getRotation())) * astroid.getSpeed());
            astroid.setY(astroid.getY() + Math.sin(Math.toRadians(astroid.getRotation())) * astroid.getSpeed());
        }
    }

    public Entity createNewAstroid (GameData gameData) {
        Entity astroid = new Astroid();
        astroid = createShape(astroid,"big");
        Random speed = new Random(2);
        Random rotation = new Random(360);
        astroid.setSpeed(speed.nextDouble());
        astroid.setRotation(rotation.nextDouble());
        astroid.setRotationSpeed(1);
        Random side = new Random(4);
        Random xpoint = new Random(gameData.getDisplayWidth());
        Random ypoint = new Random(gameData.getDisplayHeight());
        switch (side.nextInt()){
            case 1:
                astroid.setX(xpoint.nextDouble());
                astroid.setY(gameData.getDisplayHeight());
                break;
            case 2:
                astroid.setX(0);
                astroid.setY(ypoint.nextDouble());
                break;
            case 3:
                astroid.setX(xpoint.nextDouble());
                astroid.setY(0);
                break;
            default:
                astroid.setX(gameData.getDisplayWidth());
                astroid.setY(ypoint.nextDouble());
                break;
        }
        return astroid;
    }

    private Entity createShape (Entity astroid, String size) {
        astroid.setPolygonCoordinates(
                6,-2.5,
                2.5,-6,
                -2.5,-6,
                -6,-2.5,
                -6,2.5,
                -2.5,6,
                2.5,6,
                6,2.5
        );
        return astroid;
    }
}