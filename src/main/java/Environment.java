import java.util.Timer;
import java.util.TimerTask;

public class Environment {
    private int width;
    private int height;
    private int time;
    private int numberOfPeople;
    private int[][] environment;
    private int[][] doorsLocation;

    public Environment(int width, int height, int doors, int time, int numberOfPeople) {
        this.width = width;
        this.height = height;
        this.time = time;
        this.numberOfPeople = numberOfPeople;
        this.environment = new int[width][height];
        this.doorsLocation = new int[width][height];
        createDoors(doors);
    }

    private void createDoors(int qtd) {
        for (int i = 0; i < qtd; i++) {
            int randomX = (int) (Math.random() * width);
            int randomY = (int) (Math.random() * width);
            if (doorsLocation[randomX][randomY] == 0) {
                doorsLocation[randomX][randomY] = 1;
            } else {
                createDoors(qtd);
            }
        }
    }

    public void generateEnvironment() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(environment[i][j] + " ");
            }
            System.out.println();
        }
    }


    private void findPath() {
        Timer timer = new Timer();
        for (int i = 1; i <= numberOfPeople; i++) {
            Person person = new Person(i, environment);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    person.start();
                }
            }, 0, time);
            person.interrupt();
        }
    }
}
