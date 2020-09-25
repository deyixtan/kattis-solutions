import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class JoinStrings {

    // Fast IO found online
    static class FastIO {
        InputStream dis;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(String fileName) throws Exception {
            dis = new FileInputStream(fileName);
        }

        public FastIO(InputStream is) throws Exception {
            dis = is;
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
    }

    static class ListNode {
        public int item;
        public ListNode next;
        
        public ListNode(int val) {
            this(val, null);
        }

        public ListNode(int val, ListNode n) {
            item = val;
            next = n;
        }
        
        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode n) {
            next = n;
        }
        
        public int getItem() {
            return item;
        }

        public void setItem(int val) {
            item = val;
        }
    }

    static class TailedLinkedList {
        public ListNode head;
        public ListNode tail;
        public int num_nodes;

        public ListNode getHead() {
            return head;
        }

        public ListNode getTail() {
            return tail;
        }

        public int size() { return num_nodes; }

        public void insert(ListNode cur, ListNode n) {
            if (cur == null) {
                n.setNext(head);
                head = n;
                if (tail == null)
                    tail = head;
            }
            else {
                n.setNext(cur.getNext());
                cur.setNext(n);
                if (cur == tail)
                    tail = tail.getNext();
            }
            num_nodes++;
        }

        public void addBack(int item) {
            insert(tail, new ListNode(item));
        }

        public int remove(ListNode cur) {
            int value = head.getItem();
            head = head.getNext();
            if (num_nodes == 1)
                tail = null;
            num_nodes--;
            return value;
        }

        public int removeFront() {
            return remove(null);
        }

        public void addLinkedList(TailedLinkedList tll) {
            tail.setNext(tll.getHead());
            tail = tll.getTail();
            num_nodes += tll.size();
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO sc = new FastIO(System.in);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        // Read inputs
        int n = sc.nextInt();

        // Initialise data
        HashMap<Integer, TailedLinkedList> hmap = new HashMap<Integer, TailedLinkedList>();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            // String input map
            strings[i] = sc.next();

            // Hashmap with Linkedlist containing string's index
            TailedLinkedList ll = new TailedLinkedList();
            ll.addBack(i + 1);
            hmap.put(i + 1, ll);
        }

        // N-1 concats
        int a = 1;
        int b = 1;
        for (int i = 0; i < n - 1; i++) {
            a = sc.nextInt(); 
            b = sc.nextInt();

            hmap.get(a).addLinkedList(hmap.get(b));
        }

        // Output
        TailedLinkedList ll_a = hmap.get(a);
        int a_size = ll_a.size();
        for (int i = 0; i < a_size; i++) {
            pw.write(strings[ll_a.removeFront() - 1]);
        }
        pw.write('\n');
        pw.close();
    }   
}