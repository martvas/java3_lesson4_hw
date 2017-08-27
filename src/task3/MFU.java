package task3;

public class MFU {
    private static final Object printMon = new Object();
    private static final Object scanMon = new Object();

    public static void main(String[] args) {
        print(8);
        print(10);
        print(5);
        print(15);

        scan(5);
        scan(7);
        scan(10);
    }

    public static void print(int pages) {
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (printMon) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int cnt = 0;
                    System.out.print("Отпечатано: ");
                    for (int i = 0; i < pages; i++) {
                        System.out.print(pages - cnt + " ");
                        cnt++;
                    }
                    System.out.println();
                }
            }
        });
        printThread.start();
    }

    public static void scan(int pages) {
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (scanMon) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int cnt = 0;
                    System.out.print("Отсканировано: ");
                    for (int i = 0; i < pages; i++) {
                        System.out.print(pages - cnt + " ");
                        cnt++;
                    }
                    System.out.println();
                }
            }
        });
        printThread.start();
    }
}
