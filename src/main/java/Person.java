import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Person extends Thread {
    private final int number;
    private final int counter;
    private final int time;
    private final int[][] environment;
    private final Map<Integer, Integer> exits;
    private final Map<Integer, Integer> alreadyUsed;
    private int randomX;
    private int randomY;
    private final Random random = new Random();

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
        int x;
        startsOn();
        while (timer < time) {
            for (int i = randomX; i < environment.length; i++) {
                for (int j = randomY; j < environment.length; j++) {
                    try {
                        x = random.nextInt(4);
                        choosePath(i, j, x);
                        Thread.sleep(500);
                        timer += 1000;
                        if (timer >= time) {
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        gettingOut();
    }

    private void startsOn() {
        randomX = (int) (Math.random() * environment.length);
        randomY = (int) (Math.random() * environment.length);
        System.out.println("Pessoa " + number + " começa em " + randomX + ", " + randomY);
    }

    private void choosePath(int i, int j, int x) {
        switch (x) {
            case 0:
                toLeft(i, j);
                break;
            case 1:
                toRight(i, j);
                break;
            case 2:
                toUp(i, j);
                break;
            case 3:
                toDown(i, j);
                break;
            default:
                break;
        }
    }

    private void toLeft(int i, int j) {
        if (j - 1 >= 0) {
            if (environment[i][j - 1] == 0) {
                pathFound(i, j - 1);
            } else if (environment[i][j - 1] == 1) {
                exitFound(i, j - 1);
            }
        }
    }

    private void toRight(int i, int j) {
        if (j + 1 < environment.length) {
            if (environment[i][j + 1] == 0) {
                pathFound(i, j + 1);
            } else if (environment[i][j + 1] == 1) {
                exitFound(i, j + 1);
            }
        }
    }

    private void toUp(int i, int j) {
        if (i - 1 >= 0) {
            if (environment[i - 1][j] == 0) {
                pathFound(i - 1, j);
            } else if (environment[i - 1][j] == 1) {
                exitFound(i - 1, j);
            }
        }
    }

    private void toDown(int i, int j) {
        if (i + 1 < environment.length) {
            if (environment[i + 1][j] == 0) {
                pathFound(i + 1, j);
            } else if (environment[i + 1][j] == 1) {
                exitFound(i + 1, j);
            }
        }
    }

    private void pathFound(int x, int y) {
        System.out.println("Pessoa " + number + " - " + "encontrou um caminho em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void exitFound(int x, int y) {
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