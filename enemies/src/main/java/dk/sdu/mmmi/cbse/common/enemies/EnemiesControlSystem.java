package dk.sdu.mmmi.cbse.common.enemies;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;
import javafx.geometry.Point2D;

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
            if(enemy.getHealth() <= 0) {
                gameData.setDestroyedEnemies(gameData.getDestroyedEnemies() + 1);
                if(!gameData.isGameOver())
                    gameData.setScore(gameData.getScore() + enemy.getSize());
                enemy.setDead(true);
                break;
            }
            else {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX * enemy.getSpeed());
                enemy.setY(enemy.getY() + changeY * enemy.getSpeed());
                double shotTimer = enemy.getShotTimer();
                double maxShotTimer = enemy.getMaxShotTimer();
                if(maxShotTimer > 300) {
                    maxShotTimer = maxShotTimer/2;
                    enemy.setMaxShotTimer(maxShotTimer);
                }
                if(shotTimer == (maxShotTimer/4)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                else if(shotTimer == ((maxShotTimer/4)*2)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                else if(shotTimer == ((maxShotTimer/4)*3)) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                }
                if(enemy.getShotTimer() >= gameData.getImmortalTime()) enemy.setImmortal(false);
                Entity Player = null;
                if(world.getEntities(Player.class).size() > 0) Player = world.getEntities(dk.sdu.mmmi.cbse.playersystem.Player.class).getFirst();
                if(shotTimer >= maxShotTimer) {
                    /*gameData.setLog("");
                    gameData.setLog(gameData.getLog()+"\nPlayer: "+gameData.getDisplayWidth());*/
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy,gameData));}
                    );
                    enemy.setShotTimer(0);
                    if(targetPlayer(enemy,Player)) {
                        double[] target = new double[]{
                                Player.getX(),
                                Player.getY()
                        };
                        enemy.setTarget(target);
                    }
                    else {
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
                    if(targetPlayer(enemy,Player)) {
                        double[] target = new double[]{
                                Player.getX(),
                                Player.getY()
                        };
                        enemy.setTarget(target);
                    }
                }

                double vectorAngle = ((enemy.getY() - enemy.getTarget()[1])/(enemy.getX() - enemy.getTarget()[0]));

                if(enemy.getRotation() > Math.toDegrees(vectorAngle))
                    enemy.setRotation(enemy.getRotation() - enemy.getRotationSpeed());
                if(enemy.getRotation() < Math.toDegrees(vectorAngle))
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

    private boolean targetPlayer (Entity enemy, Entity player) {
        boolean playerTarget = false;
        if(player != null) {

            /*Point2D enemyPoint = new Point2D(enemy.getX(), enemy.getY());
            Point2D playerPoint = new Point2D(player.getX(), player.getY());
            double distance = enemyPoint.distance(playerPoint);*/
            double distance = (
                    Math.sqrt(
                            (enemy.getX() - player.getX()) * (enemy.getX() - player.getX()) +
                                    (enemy.getY() - player.getY()) * (enemy.getY() - player.getY())
                    )
            );
            if (distance <= (enemy.getSize() * 10)) {
                playerTarget = true;
            }
        }
        return playerTarget;
    }
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
