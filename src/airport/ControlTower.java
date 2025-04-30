package airport;

import java.util.LinkedList;
import java.util.Queue;

public class ControlTower implements TowerMediator {
    private final Queue<Aircraft> landingQueue = new LinkedList<>();
    private final Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private Aircraft emergencyAircraft = null;

    @Override
    public synchronized void broadcast(String msg, Aircraft sender) {
        if (msg.equalsIgnoreCase("MAYDAY")) {
            System.out.println("!!! Emergency: " + sender.getId());
            emergencyAircraft = sender;
            for (Aircraft a : landingQueue) {
                a.receive("Hold position, emergency landing in progress!");
            }
            for (Aircraft a : takeoffQueue) {
                a.receive("Hold position, emergency landing in progress!");
            }
        } else {
            System.out.println(sender.getId() + " says: " + msg);
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft a) {
        if (emergencyAircraft != null && a != emergencyAircraft) {
            return false;
        }

        if (!landingQueue.isEmpty()) {
            if (landingQueue.peek() == a) {
                landingQueue.poll();
                System.out.println(a.getId() + " has landed.");
                emergencyAircraft = null;
                return true;
            }
        } else if (!takeoffQueue.isEmpty()) {
            if (takeoffQueue.peek() == a) {
                takeoffQueue.poll();
                System.out.println(a.getId() + " has taken off.");
                return true;
            }
        }
        return false;
    }

    public synchronized void requestLanding(Aircraft a) {
        landingQueue.offer(a);
    }

    public synchronized void requestTakeoff(Aircraft a) {
        takeoffQueue.offer(a);
    }
}

