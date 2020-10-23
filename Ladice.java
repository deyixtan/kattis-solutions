import java.io.*;
import java.util.*;

public class Ladice {

    static class UnionFind {                                              
        public int[] p;
        public int[] rank;
        public int[] size;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        public int findSet(int i) { 
            if (p[i] == i)
                return i;
            else {
                p[i] = findSet(p[i]);
                return p[i]; 
            }
        }

        public Boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) { 
            if (!isSameSet(i, j)) { 
                int x = findSet(i), y = findSet(j);
                // rank is used to keep the tree short
                if (rank[x] > rank[y])  {
                    p[y] = x;
                    size[x] += size[y];
                }
                else { 
                    p[x] = y;
                    size[y] += size[x];
                    if (rank[x] == rank[y]) 
                        rank[y] = rank[y]+1;
                }
            } 
        }

        public int getSize(int i) {
            return size[findSet(i)];
        }

        public void decrementSize(int i) {
            size[findSet(i)]--;
        }
    }

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int n = io.nextInt();
        int l = io.nextInt();

        // Logic
        UnionFind uf = new UnionFind(l);
        for (int i = 0; i < n; i++) {
            // 0-indexed p[] in UFDS
            int a = io.nextInt() - 1;
            int b = io.nextInt() - 1;
            
            // Attempt to merge two disjoint sets
            // Keep track size of set under size[]
            uf.unionSet(a, b);

            // Check available "storage" from root's size
            if (uf.getSize(a) > 0) {
                uf.decrementSize(a);
                io.print("LADICA");
            } else {
                io.print("SMECE");
            }
            io.print('\n');
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