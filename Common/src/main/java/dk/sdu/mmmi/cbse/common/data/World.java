package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * the java class there is used to save entity into a map
 *
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    /**
     * Add entity string.
     *
     * @param entity the entity
     * @return the string
     */
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    /**
     * Remove entity.
     *
     * @param entityID the entity id
     */
    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    /**
     * Remove entity.
     *
     * @param entity the entity
     */
    public void removeEntity(Entity entity) { entityMap.remove(entity.getID()); }

    /**
     * Gets entities.
     *
     * @return the entities
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Gets entities.
     *
     * @param <E>         the type parameter
     * @param entityTypes the entity types
     * @return the entities
     */
    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    /**
     * Gets entity.
     *
     * @param ID the id
     * @return the entity
     */
    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
