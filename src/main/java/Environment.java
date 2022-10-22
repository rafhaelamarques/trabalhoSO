import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Environment {
    private final int size;
    private final int time;
    private final int numberOfPeople;
    private final int[][] environment;
    private final int[][] doorsLocation;
    private final List<Person> people;

    public Environment(int size, int doors, int time, int numberOfPeople) {
        this.size = size;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.environment = new int[size][size];
        this.doorsLocation = new int[size][size];
        this.people = new ArrayList<Person>();
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

    private void generatePeople() {
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person(i, environment);
            people.add(person);
        }
    }


    private void findPath() {
        int timer = 0;
        while (timer < time) {
            for (int[] ints : environment) {
                for (int j = 0; j < environment.length; j++) {
                    for (int k = 0; k < numberOfPeople; k++) {
                        Person person = new Person(k, environment);
                        try {
                            if (ints[j] == 0) {
                                person.pathFound();
                            } else if (ints[j] == 1) {
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
