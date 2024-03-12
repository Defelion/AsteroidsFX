package dk.sdu.mmmi.cbse.damage;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class damageControlSystem implements IPostEntityProcessingService {
    /**
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity1 : world.getEntities()) {
            for(Entity entity2 : world.getEntities()) {
                if(entity1 == entity2 ||
                        entity1.getPaint() == entity2.getPaint() ||
                        entity1.getImmortal() ||
                        entity2.getImmortal())
                    continue;
                if(collision(entity1,entity2,gameData)) {
                    entity1.setHealth((entity1.getHealth()-entity2.getDamage()));
                    entity2.setHealth((entity2.getHealth()-entity1.getDamage()));
                }
            }
        }
    }

    public boolean collision (Entity e1, Entity e2, GameData gameData) {
        boolean touching = false;
        double distance = (
            Math.sqrt(
                    (e1.getX() - e2.getX()) * (e1.getX() - e2.getX()) +
                    (e1.getY() - e2.getY()) * (e1.getY() - e2.getY())
            )
        );
        if(distance <= e1.getSize() || distance <= e2.getSize()){
            touching = true;
        }
        return touching;
    }
}
