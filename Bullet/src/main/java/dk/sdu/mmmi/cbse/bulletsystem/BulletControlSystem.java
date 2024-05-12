package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 * The type Bullet control system.
 */
public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            if(bullet.getHealth() <= 0) {
                bullet.setDead(true);
                continue;
            }
            bullet.setImmortal(false);
            double rot = Math.toRadians(bullet.getRotation());
            bullet.setX(bullet.getX() + Math.cos(rot) * bullet.getSpeed());
            bullet.setY(bullet.getY() + Math.sin(rot) * bullet.getSpeed());


            if ( bullet.getX() < 0 ){
                bullet.setHealth(0);
            }else if ( bullet.getX() > gameData.getDisplayWidth() ) {
                bullet.setHealth(0);
            }

            if ( bullet.getY() < 0 ) {
                bullet.setHealth(0);
            } else if ( bullet.getY() > gameData.getDisplayHeight() )
            {
                bullet.setHealth(0);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        double fullSize = shooter.getSize()/10;
        if(fullSize < 2) fullSize = 2;
        double oneQuarter = fullSize/4;
        double treeQuarter = oneQuarter*3;
        bullet.setPolygonCoordinates(
                fullSize,-oneQuarter,
                treeQuarter,-treeQuarter,
                oneQuarter,-fullSize,
                -oneQuarter,-fullSize,
                -treeQuarter,-treeQuarter,
                -fullSize,-oneQuarter,
                -treeQuarter,treeQuarter,
                -oneQuarter,fullSize,
                oneQuarter,fullSize,
                treeQuarter,treeQuarter,
                fullSize,oneQuarter);
        bullet.setDamage(shooter.getDamage() * (1+gameData.getScore()/10000));
        bullet.setSpeed(3);
        bullet.setHealth(1);
        bullet.setRotationSpeed(1);
        bullet.setImmortal(false);
        bullet.setPaint(shooter.getPaint());
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }
}
