package handlers;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.GameWorld;
import org.jbox2d.common.Vec2;

/**
 * Impromptu timer using the step listener; spawns items periodically based on
 * the spawn location, randomises item spawns (and locations) based on the
 * level, and determines whether the location has already spawned an item.
 *
 * @author jly09
 */
public class ItemSpawn implements StepListener {

    GameWorld world;
    Player1 p1;
    Player2 p2;
    //boolean itemSpawned;
    private long startTime;
    private long delay;

    private List<Items> itemList = new ArrayList<>();
    private List<Items> spawnableItemsList = new ArrayList<>();
    private Map<Vec2, Integer> spawnLocationList = new HashMap<>();
    private List<Vec2> itemLocationList = new ArrayList<>();
    private List<Vec2> spawnableItemLocations = new ArrayList<>();

    /**
     *
     * @param world
     */
    public ItemSpawn(GameWorld world) {
        this.world = world;
        //itemSpawned = false;
        startTime = 0;
        delay = 0;

        this.p1 = world.getPlayer1();
        this.p2 = world.getPlayer2();

        spawnableItemsList = world.getItemList();
        itemLocationList.add(world.getItemSpawnLocation());
        spawnLocationList.put(world.getItemSpawnLocation(), 0);

    }

    /**
     *
     * @param se
     */
    @Override
    public void preStep(StepEvent se) {

    }

    /**
     *
     * @param se
     */
    @Override
    public void postStep(StepEvent se) {
        //Timer
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        //System.out.println(elapsed);
        if (elapsed >= delay) {

            if (calculateItemSpawn()) {
                //System.out.println("Spawned");
                randomizeSpawn();

                resetTimer();
            } else {
                resetTimer();
            }
        }

    }

    //Calculates whether a item has spawned in current location
    private boolean calculateItemSpawn() {
        //System.out.println("Cal");
        for (int i = 0; i < itemLocationList.size(); i++) {
            if (spawnLocationList.containsKey(itemLocationList.get(i))) {
                if (spawnLocationList.get(itemLocationList.get(i)) == 0) {
                    spawnableItemLocations.add(itemLocationList.get(i));
                }
            }
        }

        //System.out.println(spawnLocationList);
        if (spawnableItemLocations.isEmpty()) {
            return false;
        }

        return true;
    }

    //Resets Timer
    public void resetTimer() {
        delay = 15000;
        startTime = System.nanoTime();
    }

    private void randomizeSpawn() {
        /*Shield ashield = new Shield(world);
        ashield.createItem(new Vec2(0, 10f));
        ashield.addCollisionListener(new Pickup(p1, p2));*/

        if (spawnableItemsList != null) {
            int amountOfItems = spawnableItemsList.size();
            int randomNumber = (int) ((Math.random() * amountOfItems));

            Items tempItem = spawnableItemsList.get(randomNumber);

            if (tempItem instanceof Shield) {
                Shield shield = new Shield(world);
                shield.addCollisionListener(new Pickup(world, this));
                shield.createItem(spawnableItemLocations.get(0));
                itemList.add(shield);

                if (spawnLocationList.containsKey(spawnableItemLocations.get(0))) {
                    spawnLocationList.put(spawnableItemLocations.get(0), 1);
                    spawnableItemLocations.remove(0);
                }
            }

            if (tempItem instanceof WingedBoots) {
                WingedBoots wingBoots = new WingedBoots(world);
                wingBoots.addCollisionListener(new Pickup(world, this));
                wingBoots.createItem(spawnableItemLocations.get(0));
                itemList.add(wingBoots);

                if (spawnLocationList.containsKey(spawnableItemLocations.get(0))) {
                    spawnLocationList.put(spawnableItemLocations.get(0), 1);
                    spawnableItemLocations.remove(0);
                }
            }

            if (tempItem instanceof RunningBoots) {
                RunningBoots runBoots = new RunningBoots(world);
                runBoots.addCollisionListener(new Pickup(world, this));
                runBoots.createItem(spawnableItemLocations.get(0));
                itemList.add(runBoots);

                if (spawnLocationList.containsKey(spawnableItemLocations.get(0))) {
                    spawnLocationList.put(spawnableItemLocations.get(0), 1);
                    spawnableItemLocations.remove(0);

                }
            }

            if (tempItem instanceof DefaultArrow) {
                DefaultArrow defaultArrow = new DefaultArrow(world);
                defaultArrow.addCollisionListener(new Pickup(world, this));
                defaultArrow.createItem(spawnableItemLocations.get(0));
                itemList.add(defaultArrow);

                if (spawnLocationList.containsKey(spawnableItemLocations.get(0))) {
                    spawnLocationList.put(spawnableItemLocations.get(0), 1);
                    spawnableItemLocations.remove(0);
                }
            }

            if (tempItem instanceof LaserArrow) {
                LaserArrow laserArrow = new LaserArrow(world);
                laserArrow.addCollisionListener(new Pickup(world, this));
                laserArrow.createItem(spawnableItemLocations.get(0));
                itemList.add(laserArrow);

                if (spawnLocationList.containsKey(spawnableItemLocations.get(0))) {
                    spawnLocationList.put(spawnableItemLocations.get(0), 1);
                    spawnableItemLocations.remove(0);
                }
            }
        }

        //itemList.add(spawn1);
        //spawn1.addCollisionListener(new Pickup(p1, p2));
    }

    /**
     *
     * @return
     */
    public Map<Vec2, Integer> getSpawnLocationList() {
        return spawnLocationList;
    }

    /**
     *
     * @return
     */
    public List<Items> getItemList() {
        return itemList;
    }
}
