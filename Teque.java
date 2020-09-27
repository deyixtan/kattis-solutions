import java.io.*;
import java.util.*;

public class Teque {

    // Deque using circular array approach
    static class DequeDS {
        private int[] arr;
        private int front, back;
        private int count;
        private int capacity;

        public DequeDS(int max_size) {
            arr = new int[max_size];
            front = 0;
            back = 0;
            count = 0;
            capacity = max_size;
        }

        public int size() {
            return count;
        }

        public int get(int index) { 
            return arr[(front + index) % capacity];
        }

        public void addFront(int value) {
            if (count > 0)
                front--;
            front = Math.floorMod(front, capacity);
            arr[front] = value;
            count++;
        }

        public int removeFront() {
            int value = arr[front];
            front++;
            front = Math.floorMod(front, capacity);
            count--;
            return value;
        }

        public void addBack(int value) {
            if (count > 0)
                back++;
            back = Math.floorMod(back, capacity);
            arr[back] = value;
            count++;
        }

        public int removeBack() {
            int value = arr[back];
            back--;
            back = Math.floorMod(back, capacity);
            count--;
            return value;
        }
    }

    // Teque (consists of 2 Deque)
    static class TequeDS {
        private DequeDS front;
        private DequeDS back;

        public TequeDS(int size) {
            front = new DequeDS(size);
            back = new DequeDS(size);
        }

        public int size() {
            return front.size() + back.size();
        }

        public void addFront(int value) {
            front.addFront(value);
            if (size() > 0)
                balance();
        }

        public void addMiddle(int value) {
            // No balance needed
            // Pick a side to add and balance Teque
            int front_size = front.size();
            int back_size = back.size();
            if (front_size > back_size)
                back.addFront(value);
            else
                front.addBack(value);
        }

        public void addBack(int value) {
            if (size() > 0) {
                back.addBack(value);
                balance();
            } else
                front.addFront(value);
        }

        public int get(int index) {
            int front_size = front.size();
            if (index < front_size)
                return front.get(index);
            // offset index for back Deque
            index -= front_size;
            return back.get(index);
        }

        public void balance() {
            int front_size = front.size();
            int back_size = back.size();
            if (front_size < back_size) {
                int diff = back_size - front_size;
                for (int i = 0; i < diff; i++)
                    front.addBack(back.removeFront());
            }
            else if (front_size > back_size) {
                int diff = front_size - back_size - 1;
                for (int i = 0; i < diff; i++)
                    back.addFront(front.removeBack());
            }
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Process inputs
        // Produce outputs
        int n = io.nextInt();
        TequeDS teque = new TequeDS(n - 1); // -1 to exclude first input
        for (int i = 0; i < n; i++) {
            String s = io.next();
            int x = io.nextInt();
            switch (s) {
                case "push_front":
                    teque.addFront(x);
                    break;
                case "push_middle":
                    teque.addMiddle(x);
                    break;
                case "push_back":
                    teque.addBack(x);
                    break;
                case "get":
                    io.print(teque.get(x));
                    io.print('\n');
                    break;
            }
        }
        io.flushOutput();
    }

    // ############################################################################
    // ##################### Fast IO found online (modified) ######################
    // https://github.com/MrinallU/USACO-java-fastIO/blob/master/NewIOtemplate.java
    // ############################################################################
    static class FastIO {
        InputStream dis;
        PrintWriter pw;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(InputStream input, OutputStream output) throws Exception {
            dis = input;
            pw = new PrintWriter(new OutputStreamWriter(output));
        }

        int nextInt() throws Exception {
            int ret = 0;
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }
            return (negative) ? -ret : ret;
        }
        
        long nextLong() throws Exception {
            long ret = 0;
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }
            return (negative) ? -ret : ret;
        }
        
        byte nextByte() throws Exception {
            if (pointer == buffer.length) {
                dis.read(buffer, 0, buffer.length);
                pointer = 0;
            }
            return buffer[pointer++];
        }
        
        String next() throws Exception {
            StringBuffer ret = new StringBuffer();
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            while (b > ' ') {
                ret.appendCodePoint(b);
                b = nextByte();
            }
            return ret.toString();
        }

        void print(char text) {
            pw.write(text);
        }

        void print(String text) {
            pw.write(text);
        }

        void print(int value) {
            pw.print(value);
        }

        void flushOutput() {
            pw.flush();
        }
    }
}
