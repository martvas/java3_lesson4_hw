package task1;

public class AbcPrinter {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        AbcPrinter abc = new AbcPrinter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                abc.print('A');
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                abc.print('B');
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                abc.print('C');
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public void print(char letter) {
        synchronized (mon) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter != letter) {
                        mon.wait();
                    }

                    System.out.print(letter);
                    switch (currentLetter) {
                        case 'A':
                            currentLetter = 'B';
                            break;
                        case 'B':
                            currentLetter = 'C';
                            break;
                        case 'C':
                            currentLetter = 'A';
                            break;
                    }
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


