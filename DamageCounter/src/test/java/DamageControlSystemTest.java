import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.damagecounter.damageControlSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DamageControlSystemTest {

    @Test
    public void testCollisionHit() {
        damageControlSystem damageControlSystem = new damageControlSystem();

        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setSize(5);

        Entity entity2 = new Entity();
        entity2.setX(3);
        entity2.setY(4);
        entity2.setSize(5);
        boolean result = damageControlSystem.collision(entity1, entity2);
        assertTrue(result, "Expected entities to collide");
    }

    @Test
    public void testCollisionNoHit() {
        damageControlSystem damageControlSystem = new damageControlSystem();

        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setSize(5);

        Entity entity2 = new Entity();
        entity2.setX(30);
        entity2.setY(40);
        entity2.setSize(5);
        boolean result = damageControlSystem.collision(entity1, entity2);
        assertFalse(result, "Expected entities to not collide");
    }
}