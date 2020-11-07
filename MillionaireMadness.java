import java.io.*;
import java.util.*;

public class MillionaireMadness {

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        int m = io.nextInt();
        int n = io.nextInt();

        // Set up vertices
        int[][] vertices = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vertices[i][j] = io.nextInt();
            }
        }

        // Prim's Algorithm
        PrimsMST prims = new PrimsMST(vertices);
        int result = prims.start();
        io.print(result);

        // Output
        io.flushOutput();
    }

    static class PrimsMST {
        private int[][] vertices;
        private boolean[][] taken;
        private PriorityQueue<Edge> pq;

        public PrimsMST(int[][] vertices) {
            int r = vertices.length;
            int c = vertices[0].length;

            this.vertices = vertices;
            this.taken = new boolean[r][c];
            this.pq = new PriorityQueue<Edge>();
        }

        public void process(IntegerPair vertex) {
            // Set current vertex as 'taken'
            int r = vertex.first();
            int c = vertex.second();
            this.taken[r][c] = true;

            int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int i = 0; i < 4; i++) {
                int next_r = r + move[i][0];
                int next_c = c + move[i][1];

                // check bounds
                if (next_r < 0 || next_r > this.vertices.length - 1)
                    continue;
                if (next_c < 0 || next_c > this.vertices[0].length - 1)
                    continue;

                // Get all neighbouring edges of current vertex
                // Add edges whose destination not 'taken' yet
                if (!this.taken[next_r][next_c]) {
                    int w = this.vertices[next_r][next_c] - this.vertices[r][c];
                    if (w < 0)
                        w = 0; // going downwards cost nothing (0 weight)
                    
                    this.pq.offer(new Edge(new IntegerPair(r, c),  new IntegerPair(next_r, next_c), w));
                }
            }
        }

        public int start() {
            int max_edge = 0;

            // Start from vertex at (0, 0)
            process(new IntegerPair(0, 0));

            while (!this.pq.isEmpty()) {
                // Get next shortest weighted edge
                Edge edge = this.pq.poll();
                int src_r = edge.getSrc().first();
                int src_c = edge.getSrc().second();
                int dest_r = edge.getDest().first();
                int dest_c = edge.getDest().second();

                // Process edge if destination not 'taken' yet
                if (!this.taken[dest_r][dest_c]) {
                     // Update max edge
                    if (this.vertices[dest_r][dest_c] - this.vertices[src_r][src_c] > max_edge)
                        max_edge = edge.getWeight();

                    // Force stop when reaches right bottom corner
                    if (dest_r == this.vertices.length - 1 && dest_c == this.vertices[0].length - 1)
                        break;
                    
                    process(new IntegerPair(dest_r, dest_c));
                }
            }

            return max_edge;
        }

        // Edge class
        static class Edge implements Comparable<Edge> {
            private IntegerPair src;
            private IntegerPair dest;
            private Integer weight;

            public Edge(IntegerPair src,  IntegerPair dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }

            public int compareTo(Edge other) {
                if (!this.getWeight().equals(other.getWeight()))
                    return this.getWeight() - other.getWeight();
                else if (!this.getSrc().equals(other.getSrc()))
                    return this.getSrc().compareTo(other.getSrc());
                else
                    return this.getDest().compareTo(other.getDest());
            }

            public IntegerPair getSrc() { 
                return this.src; 
            }

            public IntegerPair getDest() { 
                return this.dest; 
            }

            public Integer getWeight() { 
                return this.weight; 
            }
        }

    }

    static class IntegerPair implements Comparable<IntegerPair> {
        public Integer _first, _second;

        public IntegerPair(Integer f, Integer s) {
            _first = f;
            _second = s;
        }

        public int compareTo(IntegerPair o) {
            if (!this.first().equals(o.first()))
                return this.first() - o.first();
            else
                return this.second() - o.second();
        }

        Integer first() { return _first; }

        Integer second() { return _second; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof IntegerPair)) return false;

            IntegerPair that = (IntegerPair)obj; // than we can cast it to People safely
            return this.first().equals(that.first()) && this.second().equals(that.second());
        }
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

        char nextChar() throws Exception {
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            return (char)b;
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
