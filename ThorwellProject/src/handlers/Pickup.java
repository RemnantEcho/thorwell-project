package handlers;

import city.cs.engine.*;
import entities.*;
import java.util.List;
import main.GameWorld;

/**
 * Collision Listener for items pickup by players;
 *
 */
public class Pickup implements CollisionListener, SensorListener {

    private GameWorld world;
    private ItemSpawn itemSpawn;
    private Player1 p1;
    private Player2 p2;

    /**
     *
     * @param world
     * @param itemSpawn
     */
    public Pickup(GameWorld world, ItemSpawn itemSpawn) {
        this.world = world;
        this.itemSpawn = itemSpawn;
    }

    /**
     *
     * @param e
     */
    @Override
    public void collide(CollisionEvent e) {
        /*System.out.println("Other Body: " + e.getOtherBody());
        System.out.println("Reporting Body: " + e.getReportingBody());*/
        p1 = world.getPlayer1();
        p2 = world.getPlayer2();

        for (Items item : itemSpawn.getItemList()) {
            if (e.getReportingBody() == item) {
                //Player 1
                if (e.getOtherBody() == p1 && item instanceof Shield) {
                    if (!p1.getShielded()) {
                        p1.setShielded(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }

                }

                if (e.getOtherBody() == p1 && e.getReportingBody() instanceof RunningBoots) {
                    if (!p1.getHasRunningBoots()) {
                        p1.setHasRunningBoots(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p1 && e.getReportingBody() instanceof WingedBoots) {
                    if (!p1.getHasFlyingBoots()) {
                        p1.setHasFlyingBoots(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p1 && e.getReportingBody() instanceof DefaultArrow) {
                    if (p1.getAmmo() < 6) {
                        p1.incrementAmmo(new DefaultArrow(world));

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p1 && e.getReportingBody() instanceof LaserArrow) {
                    if (p1.getAmmo() < 6) {
                        p1.incrementAmmo(new LaserArrow(world));

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                //Player 2
                if (e.getOtherBody() == p2 && e.getReportingBody() instanceof Shield) {
                    if (!p2.getShielded()) {
                        p2.setShielded(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p2 && e.getReportingBody() instanceof RunningBoots) {
                    if (!p2.getHasRunningBoots()) {
                        p2.setHasRunningBoots(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p2 && e.getReportingBody() instanceof WingedBoots) {
                    if (!p2.getHasFlyingBoots()) {
                        p2.setHasFlyingBoots(true);

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p2 && e.getReportingBody() instanceof DefaultArrow) {
                    if (p2.getAmmo() < 6) {
                        p2.incrementAmmo(new DefaultArrow(world));

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }

                if (e.getOtherBody() == p2 && e.getReportingBody() instanceof LaserArrow) {
                    if (p2.getAmmo() < 6) {
                        p2.incrementAmmo(new LaserArrow(world));

                        if (itemSpawn.getSpawnLocationList().containsKey(item.getSpawnLocation())) {
                            itemSpawn.getSpawnLocationList().put(item.getSpawnLocation(), 0);
                        }

                        item.destroy();
                    }
                }
            }
        }

    }

    /**
     *
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        /*if (e.getSensor().getBody() instanceof Shield) {

        }
        if (e.getSensor().getBody() instanceof Shield && e.getContactBody() == p1) {
            e.getSensor().getBody().destroy();
            p1.setShielded(true);
        }

        if (e.getSensor().getBody() instanceof RunningBoots && e.getContactBody() == p1) {
            e.getSensor().getBody().destroy();
            p1.setHasRunningBoots(true);
        }

        if (e.getSensor().getBody() instanceof WingedBoots && e.getContactBody() == p1) {
            e.getSensor().getBody().destroy();
            p1.setHasFlyingBoots(true);
        }

        if (e.getSensor().getBody() instanceof Shield && e.getContactBody() == p2) {
            e.getSensor().getBody().destroy();
            p2.setShielded(true);
        }

        if (e.getSensor().getBody() instanceof RunningBoots && e.getContactBody() == p2) {
            e.getSensor().getBody().destroy();
            p2.setHasRunningBoots(true);
        }

        if (e.getSensor().getBody() instanceof WingedBoots && e.getContactBody() == p2) {
            e.getSensor().getBody().destroy();
            p2.setHasFlyingBoots(true);
        }*/
    }

    /**
     *
     * @param e
     */
    @Override
    public void endContact(SensorEvent e) {

    }

}
