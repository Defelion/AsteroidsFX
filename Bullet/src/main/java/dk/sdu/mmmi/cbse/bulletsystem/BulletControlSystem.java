package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            bullet.setX(bullet.getX() + Math.cos(Math.toRadians(bullet.getRotation())) * bullet.getSpeed());
            bullet.setY(bullet.getY() + Math.sin(Math.toRadians(bullet.getRotation())) * bullet.getSpeed());
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(
                1,-0.25,
                0.75,-0.75,
                0.25,-1,
                -0.25,-1,
                -0.75,-0.75,
                -1,-0.25,
                -0.75,0.75,
                -0.25,1,
                0.25,1,
                0.75,0.75,
                1,0.25);
        bullet.setSpeed(3);
        bullet.setRotationSpeed(1);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {
    }

}
