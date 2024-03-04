package dk.sdu.mmmi.cbse.common.enemies;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class EnemiesPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

        int destroyedEnemies = gameData.getDestroyedEnemies();
        int diffculty = 1;
        if(destroyedEnemies >= 10) diffculty = gameData.getDestroyedEnemies()/10;
        for(int i = 0; i < diffculty; i++) {
            Entity Enemy = createEnemy(gameData, diffculty);
            gameData.setLog(gameData.getLog()+"\nEnemy"+i+" Created");
            world.addEntity(Enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Enemy.class) {
                world.removeEntity(e);
            }
        }
    }

    private Entity createEnemy (GameData gameData, int Difficulty) {
        Entity Enemy = new Enemy();
        Enemy = setSpawn(gameData);
        Enemy.setPaint(Color.RED);
        Random size = new Random();
        Enemy.setSize(size.nextInt(5,25));
        Enemy.setPolyCoordinatesArray(createShape(Enemy.getSize()));
        Enemy.setHealth((Enemy.getHealth()+Difficulty));
        Enemy.setRotationSpeed(1+(Difficulty/50));
        Enemy.setDamage((1+Difficulty)-(gameData.getScore()/1000));
        Enemy.setSpeed((1+(Difficulty/20)));
        Enemy.setCurrenthealth(Enemy.getHealth());
        Enemy.setMaxShotTimer(((Enemy.getSize()*(10+gameData.getScore()/1000))-(Difficulty/5)));
        Enemy.setShotTimer(Enemy.getMaxShotTimer());
        Random randPoint = new Random();
        double[] target = new double[]{
                randPoint.nextDouble(gameData.getDisplayWidth()),
                randPoint.nextDouble(gameData.getDisplayHeight())
        };
        Enemy.setTarget(target);
        double shotTimer = ((10 + gameData.getScore()/1000)-(Difficulty/5));
        if(shotTimer <= 1) Enemy.setShotTimer(1);
        else Enemy.setShotTimer((int) shotTimer);
        return Enemy;
    }

    private double[] createShape (int size) {
        return new double[] {
               -size,-size,
                (size*2),0,
                -size,size
        };
    }
    private Entity setSpawn(GameData gameData) {
        Entity Enemy = new Enemy();
        Random side = new Random(4);
        Random xpoint = new Random(gameData.getDisplayWidth());
        Random ypoint = new Random(gameData.getDisplayHeight());
        Random point = new Random();
        switch (side.nextInt(4)){
            case 1:
                Enemy.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                Enemy.setY(gameData.getDisplayHeight());
                Enemy.setRotation(point.nextDouble(0,180));
                break;
            case 2:
                Enemy.setX(0);
                Enemy.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                Enemy.setRotation(point.nextDouble(90,270));
                break;
            case 3:
                Enemy.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                Enemy.setRotation(point.nextDouble(180,360));
                Enemy.setY(0);
                break;
            default:
                Enemy.setX(gameData.getDisplayWidth());
                Enemy.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                Random height = new Random(2);
                if(height.nextInt(1,2) == 2) Enemy.setRotation(point.nextDouble(270,360));
                else Enemy.setRotation(point.nextDouble(0,90));
                break;
        }
        return Enemy;
    }
}
