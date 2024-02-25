package dk.sdu.mmmi.cbse.common.enemies;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemiesControlSystem implements IEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double maxHealth = (enemy.getHealth() + gameData.getScore() / 1000);
            double LostHealth = (maxHealth - enemy.getCurrenthealth());
            enemy.setCurrenthealth(maxHealth - LostHealth);
            if(enemy.getCurrenthealth() <= 0) {
                world.removeEntity(enemy);
            }
            else {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX * enemy.getSpeed());
                enemy.setY(enemy.getY() + changeY * enemy.getSpeed());

                if(enemy.getShotTimer() == (enemy.getMaxShotTimer()/4)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                if(enemy.getShotTimer() == ((enemy.getMaxShotTimer()/4)*2)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                else if(enemy.getShotTimer() == ((enemy.getMaxShotTimer()/4)*3)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                if(enemy.getShotTimer() == enemy.getMaxShotTimer()) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                    enemy.setShotTimer(0);
                    if(!targetPlayer(enemy, world)) {
                        Random randPoint = new Random();
                        double[] target = new double[]{
                                randPoint.nextDouble(gameData.getDisplayWidth()),
                                randPoint.nextDouble(gameData.getDisplayHeight())
                        };
                        enemy.setTarget(target);
                    }
                }
                else {
                    enemy.setShotTimer((enemy.getShotTimer()+1));
                    targetPlayer(enemy, world);
                }

                double vectorAngle = ((enemy.getY() - enemy.getTarget()[1])/(enemy.getX() - enemy.getTarget()[0]));
                vectorAngle = vectorAngle + Math.PI*2;
                vectorAngle = vectorAngle * (180 / Math.PI);

                if(enemy.getRotation() > vectorAngle)
                    enemy.setRotation(enemy.getRotation() - enemy.getRotationSpeed());
                if(enemy.getRotation() < vectorAngle)
                    enemy.setRotation(enemy.getRotation() + enemy.getRotationSpeed());

                if (enemy.getX() < 0) { enemy.setX(gameData.getDisplayWidth()); }
                if (enemy.getX() > gameData.getDisplayWidth()) { enemy.setX(0); }
                if (enemy.getY() < 0) { enemy.setY(gameData.getDisplayHeight()); }
                if (enemy.getY() > gameData.getDisplayHeight()) { enemy.setY(0); }
            }
        }
    }

    private void shootBullet (Entity enemy) {

    }

    private boolean targetPlayer (Entity enemy, World world) {
        boolean playerTarget = false;
        Entity Player = world.getEntities(dk.sdu.mmmi.cbse.playersystem.Player.class).getFirst();
        double distance = (
                Math.sqrt(
                        (enemy.getX() - Player.getX()) * (enemy.getX() - Player.getX()) +
                                (enemy.getY() - Player.getY()) * (enemy.getY() - Player.getY())
                )
        );
        if(distance < (enemy.getSize()*4)) {
            double[] target = new double[]{
                    Player.getX(),
                    Player.getY()
            };
            enemy.setTarget(target);
            playerTarget = true;
        }
        return playerTarget;
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
