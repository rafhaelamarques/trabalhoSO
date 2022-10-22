import java.util.*;

public class Environment {
    private final int size;
    private final int time;
    private final int numberOfPeople;
    private final int[][] environment;
    private final int[][] doorsLocation;
    private final List<Person> people;
    private final Map<Integer, Integer> exits;
    private final Map<Integer, Integer> alredyUsed;

    public Environment(int size, int doors, int time, int numberOfPeople) {
        this.size = size;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.environment = new int[size][size];
        this.doorsLocation = new int[size][size];
        this.people = new ArrayList<>();
        this.exits = new HashMap<>();
        this.alredyUsed = new HashMap<>();

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
        generatePeople();
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
        for (int i = 1; i <= numberOfPeople; i++) {
            Person person = new Person(i);
            people.add(person);
            person.start();
        }
        findPath(people);
    }


    private void findPath(List<Person> people) {
        int timer = 0;
        while (timer < time) {
            for (int i = 0; i < environment.length; i++) {
                for (int j = 0; j < environment.length; j++) {
                    try {
                        for (Person person : people) {
                            int randomX = (int) (Math.random() * environment.length);
                            int randomY = (int) (Math.random() * environment.length);
                            if (environment[randomX][randomY] == 0) {
                                person.pathFound(randomX, randomY);
                            } else if (environment[randomX][randomY] == 1) {
                                person.exitFound(randomX, randomY);
                            }
                            timer += 1000;
                            if (timer >= time) {
                                getOut();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void getOut() {
        int x = 0;
        while (x < numberOfPeople) {
            Map.Entry<Integer, Integer> entry = exits.entrySet().iterator().next();
            for (Person p : people) {
                p.gettingOut(entry.getKey(), entry.getValue());
                entry = getNextExit(entry);
                x++;
            }
        }
        System.exit(0);
    }

    private Map.Entry<Integer, Integer> getNextExit(Map.Entry<Integer, Integer> exit) {
        alredyUsed.put(exit.getKey(), exit.getValue());
        Map.Entry<Integer, Integer> entry = exits.entrySet().iterator().next();
        for (Map.Entry<Integer, Integer> e : exits.entrySet()) {
            if (!Objects.equals(e.getKey(), exit.getKey()) && !Objects.equals(e.getValue(), exit.getValue())) {
                if (!alredyUsed.containsKey(e.getKey()) && !alredyUsed.containsValue(e.getValue())) {
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
