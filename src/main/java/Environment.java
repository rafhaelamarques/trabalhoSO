import java.util.*;

public class Environment {
    private final int size;
    private final int time;
    private final int numberOfPeople;
    private final int[][] environment;
    private final int[][] doorsLocation;
    private final List<Person> people;
    private final Map<Integer, Integer> exits;

    public Environment(int size, int doors, int time, int numberOfPeople) {
        this.size = size;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.environment = new int[size][size];
        this.doorsLocation = new int[size][size];
        this.people = new ArrayList<>();
        this.exits = new HashMap<>();

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
                    exits.put(i, j);
                } else {
                    environment[i][j] = 0;
                }
            }
        }
        printEnvironment();
        initPeople();
    }

    private void printEnvironment() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(environment[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void initPeople() {
        for (int i = 1; i <= numberOfPeople; i++) {
            Person person = new Person(i, numberOfPeople, time, environment, exits);
            people.add(person);
        }
        findPath();
    }

    private void findPath() {
        for (Person person : people) {
            try {
                person.start();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
