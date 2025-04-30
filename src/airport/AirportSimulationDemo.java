package airport;

import java.util.Random;
import java.util.concurrent.*;

public class AirportSimulationDemo {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Aircraft[] planes = {
                new PassengerPlane("P1"),
                new CargoPlane("C1"),
                new Helicopter("H1"),
                new PassengerPlane("P2"),
                new CargoPlane("C2")
        };

        for (Aircraft a : planes) {
            if (random.nextBoolean()) {
                tower.requestLanding(a);
            } else {
                tower.requestTakeoff(a);
            }
        }

        scheduler.scheduleAtFixedRate(() -> {
            for (Aircraft a : planes) {
                if (tower.requestRunway(a)) {
                    a.receive("Clear to proceed!");
                }
            }
        }, 0, 2, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            planes[0].send("MAYDAY", tower);
        }, 5, TimeUnit.SECONDS);

        scheduler.schedule(() -> scheduler.shutdown(), 20, TimeUnit.SECONDS);
    }
}