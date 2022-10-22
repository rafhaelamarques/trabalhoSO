public class Person extends Thread {
    private final int number;

    public Person(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("Pessoa " + number + " - " + "começa");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public void gettingOut(int x, int y) {
        System.out.println("Pessoa " + number + " - " + "saiu em: " + x + ", " + y);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}