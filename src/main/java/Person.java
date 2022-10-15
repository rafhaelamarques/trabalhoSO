public class Person extends Thread {
    private int number;
    private int[][] environment;
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
        for (int i = 0; i < environment.length; i++) {
            for (int j = 0; j < environment.length; j++) {
                if (i == x && j == y) {
                    for (int k = x; k < environment.length; k++) {
                        for (int l = y; l < environment.length; l++) {
                            if (environment[k][l] == 0) {
                                System.out.println("Pessoa " + number + " - " + "Caminho encontrada em: " + k + ", " + l);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else if (environment[k][l] == 1) {
                                System.out.println("Pessoa " + number + " - " + "Porta encontrada em: " + k + ", " + l);
                            }
                        }
                    }
                }
            }
        }
    }
}
