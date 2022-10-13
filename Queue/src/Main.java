import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Queue q = new QueueClass();
        Queue p = new QueueClass();
        System.out.println(q);
        System.out.println(q.isEmpty());
        q.add(1);
        p.add(1);
        q.add(2);
        p.add(2);
        q.add(3);
        p.add(30);
        q.print();
        q.printReverse();
        System.out.println(q.size());
        System.out.println(q.equals(p));
        System.out.println(q.peek());
        q.add(2);
        System.out.println(q.indexOf(2));
        System.out.println(q.lastIndexOf(2));
        System.out.println(q.remove());
        System.out.println(q);
        q.jumpTheQueue(1,4000);
        System.out.println(q);
        q.clear(2);
        System.out.println(q);
        q.clear();
        System.out.println(q);
    }
}

interface Queue {
    // voeg een item toe aan de FIFO queue
    void add(int value);

    // verwijder een item uit de FIFO queue
    int remove();

    // geef het eerste item in de FIFO queue terug, maar haal het er niet uit
    int peek();

    // geef aan of de FIFO queue leeg is
    boolean isEmpty();

    // geef de lengte van de FIFO queue terug
    int size();

    // Print de inhoud van de FIFO queue
    void print();

    // verwijder alle items uit de FIFO queue
    void clear();

    // verwijder de eerste n items uit de FIFO queue
    void clear(int n);

    // print de inhoud van de FIFO queue in omgekeerde volgorde
    void printReverse();

    // plaats een element op een bepaalde positie in de FIFO queue
    void jumpTheQueue(int n, int value);

    // Zet de FIFO queue om naar een String
    String toString();

    // Kijk of de FIFO queue gelijk is aan een andere FIFO queue
    boolean equals(Queue q);

    // Bepaal de index van een bepaalde waarde in de FIFO queue
    int indexOf(int value);

    // bepaal de laatste index van een bepaalde waarde in de FIFO queue
    int lastIndexOf(int value);
}

class QueueClass implements Queue {

    private final ArrayList<Integer> queue = new ArrayList<>();

    @Override
    public void add(int value) {
        queue.add(value);
    }

    @Override
    public int remove() {
        int result = peek();
        queue.remove(0);
        return result;
    }

    @Override
    public int peek() {
        if (!isEmpty()) {
            return queue.get(0);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void print() {
        queue.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public void clear(int n) {
        for (int i = 0; i < n; i++) {
            remove();
        }
    }

    @Override
    public void printReverse() {
        for (int i = size() - 1; i >= 0; i--) {
            System.out.print(queue.get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public void jumpTheQueue(int n, int value) {
        queue.add(n, value);
    }

    @Override
    public boolean equals(Queue q) {
        return this.toString().equals(q.toString());
    }

    @Override
    public int indexOf(int value) {
        return queue.indexOf(value);
    }

    @Override
    public int lastIndexOf(int value) {
        return queue.lastIndexOf(value);
    }

    public String toString() {
        return "QueueClass " + queue;
    }
}