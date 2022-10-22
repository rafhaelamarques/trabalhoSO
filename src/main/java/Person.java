public class Person extends Thread {
    private final int number;
    private final int[][] environment;
    private int x;
    private int y;

    public Person(int number, int[][] environment) {
        this.number = number;
        this.environment = environment;
        getStartPoint();
    }

    public void getStartPoint() {
        int randomX = (int) (Math.random() * environment.length);
        int randomY = (int) (Math.random() * environment.length);
        if (environment[randomX][randomY] == 0) {
            this.x = randomX;
            this.y = randomY;
        } else {
            getStartPoint();
        }
    }

    public void run() {
        System.out.println("Pessoa " + number + " - " + "começa em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pathFound() {
        System.out.println("Pessoa " + number + " - " + "encontrou um caminho em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exitFound() {
        System.out.println("Pessoa " + number + " - " + "encontrou uma porta em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}