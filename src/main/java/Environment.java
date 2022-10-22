import java.util.Timer;
import java.util.TimerTask;

public class Environment {
    private int size;
    private int time;
    private int numberOfPeople;
    private int[][] environment;
    private int[][] doorsLocation;

    public Environment(int size, int doors, int time, int numberOfPeople) {
        this.size = size;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.environment = new int[size][size];
        this.doorsLocation = new int[size][size];
        createDoors(doors);
    }

    private void createDoors(int qtd) {
        for (int i = 0; i < qtd; i++) {
            int randomX = (int) (Math.random() * size);
            int randomY = (int) (Math.random() * size);
            if (doorsLocation[randomX][randomY] == 0) {
                doorsLocation[randomX][randomY] = 1;
            }
        }
    }

    public void generateEnvironment() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (doorsLocation[i][j] == 1) {
                    environment[i][j] = 1;
                } else {
                    environment[i][j] = 0;
                }
            }
        }
        printEnvironment();
        findPath();
    }

    private void printEnvironment() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(environment[i][j] + " ");
            }
            System.out.println();
        }
    }


    private void findPath() {
        int timer = 0;
        while (timer < time) {
            for (int i = 0; i < environment.length; i++) {
                for (int j = 0; j < environment.length; j++) {
                    for (int k = 1; k <= numberOfPeople; k++) {
                        Person person = new Person(k, environment);
                        try {
                            if (environment[i][j] == 0) {
                                person.pathFound();
                            } else if (environment[i][j] == 1) {
                                person.exitFound();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        timer += 1000;
                        if (timer >= time) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
