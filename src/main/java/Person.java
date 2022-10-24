import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person extends Thread {
    private final int number;
    private final int counter;
    private final int time;
    private final int[][] environment;
    private final Map<Integer, Integer> exits;
    private final Map<Integer, Integer> alreadyUsed;

    public Person(int number, int counter, int time, int[][] environment, Map<Integer, Integer> exits) {
        this.number = number;
        this.time = time;
        this.counter = counter;
        this.environment = environment;
        this.exits = exits;
        this.alreadyUsed = new HashMap<>();
    }

    public void run() {
        int timer = 0;
        while (timer < time) {
            for (int i = 0; i < environment.length; i++) {
                for (int j = 0; j < environment.length; j++) {
                    try {
                        int randomX = (int) (Math.random() * environment.length);
                        int randomY = (int) (Math.random() * environment.length);
                        if (environment[randomX][randomY] == 0) {
                            pathFound(randomX, randomY);
                        } else if (environment[randomX][randomY] == 1) {
                            exitFound(randomX, randomY);
                        }
                        Thread.sleep(500);
                        timer += 1000;
                        if (timer >= time) {
                            gettingOut();
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void pathFound(int x, int y) {
        System.out.println("Pessoa " + number + " - " + "encontrou um caminho em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exitFound(int x, int y) {
        System.out.println("Pessoa " + number + " - " + "encontrou uma porta em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gettingOut() {
        int x = 0;
        if (exits.size() > 0) {
            while (x < counter) {
                Map.Entry<Integer, Integer> entry = exits.entrySet().iterator().next();
                for (int i = 1; i <= counter; i++) {
                    System.out.println("Pessoa " + i + " - " + "saiu em: " + entry.getKey() + ", " + entry.getValue());
                    entry = getNextExit(entry);
                    x++;
                }
            }
        } else {
            System.out.println("Nenhuma porta encontrada");
        }
        System.exit(0);
    }

    private Map.Entry<Integer, Integer> getNextExit(Map.Entry<Integer, Integer> exit) {
        alreadyUsed.put(exit.getKey(), exit.getValue());
        Map.Entry<Integer, Integer> entry = exits.entrySet().iterator().next();
        for (Map.Entry<Integer, Integer> e : exits.entrySet()) {
            if (!Objects.equals(e.getKey(), exit.getKey()) && !Objects.equals(e.getValue(), exit.getValue())) {
                if (!alreadyUsed.containsKey(e.getKey()) && !alreadyUsed.containsValue(e.getValue())) {
                    entry = e;
                    break;
                } else {
                    entry = e;
                }
            }
        }
        return entry;
    }
}