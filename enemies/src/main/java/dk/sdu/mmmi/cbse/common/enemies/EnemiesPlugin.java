package dk.sdu.mmmi.cbse.common.enemies;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * The type Enemies plugin.
 */
public class EnemiesPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        int destroyedEnemies = gameData.getDestroyedEnemies();
        int diffculty = 4;
        if(destroyedEnemies >= 10) diffculty += gameData.getDestroyedEnemies()/10;
        if(diffculty > (gameData.getDisplayHeight()/5)) diffculty = (gameData.getDisplayHeight()/5);
        for(int i = 0; i < diffculty; i++) {
            Entity Enemy = createEnemy(gameData, diffculty);
            //gameData.setLog(gameData.getLog()+"\nEnemy"+i+" Created");
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
        Random size = new Random();
        Enemy = setSpawn(gameData);
        int choice = size.nextInt(0,100);
        if(choice <= 50) Enemy.setPaint(Color.RED);
        else Enemy.setPaint(Color.GREEN);
        Enemy.setSize(size.nextInt(5,25));
        Enemy.setPolyCoordinatesArray(createShape(Enemy.getSize()));
        Enemy.setHealth((Enemy.getSize()/4)+Difficulty);
        Enemy.setRotationSpeed(1+(Difficulty/50));
        if(gameData.getScore() > 1000)
            Enemy.setDamage(((Enemy.getSize()/2)+Difficulty)-(gameData.getScore()/1000));
        else
            Enemy.setDamage(((Enemy.getSize()/2)+Difficulty));
        Enemy.setSpeed((1+(Difficulty/20)));
        Enemy.setCurrenthealth(Enemy.getHealth());
        Enemy.setType("Enemy");
        double shot = ((Enemy.getSize()*(10+gameData.getScore()/1000))-(Difficulty/5));
        if(shot < 20) shot = 20;
        //gameData.setLog(gameData.getLog()+"\nShot: "+shot);
        Enemy.setMaxShotTimer(shot);
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
        Random side = new Random();
        Random xpoint = new Random();
        Random ypoint = new Random();
        Random point = new Random();
        switch (side.nextInt(4)){
            case 1:
                Enemy.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                Enemy.setY(gameData.getDisplayHeight());
                break;
            case 2:
                Enemy.setX(0);
                Enemy.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                break;
            case 3:
                Enemy.setX(xpoint.nextDouble(gameData.getDisplayWidth()));
                Enemy.setY(0);
                break;
            default:
                Enemy.setX(gameData.getDisplayWidth());
                Enemy.setY(ypoint.nextDouble(gameData.getDisplayHeight()));
                break;
        }

        double[] target = new double[]{
                point.nextDouble(gameData.getDisplayWidth()),
                point.nextDouble(gameData.getDisplayHeight())
        };
        double vectorAngle = ((Enemy.getY() - target[1])/(Enemy.getX() - target[0]));

        Enemy.setRotation(Math.toDegrees(vectorAngle));
        return Enemy;
    }
}
