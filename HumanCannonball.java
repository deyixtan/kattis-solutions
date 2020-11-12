import java.io.*;
import java.util.*;

public class HumanCannonball {

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input
        DoublePair start = new DoublePair(io.nextDouble(), io.nextDouble());
        DoublePair end = new DoublePair(io.nextDouble(), io.nextDouble());        
        
        int n = io.nextInt();
        DoublePair[] vertices = new DoublePair[n + 2];

        vertices[0] = start;
        vertices[1] = end;
        for (int i = 2; i < vertices.length; i++) {
            double x = io.nextDouble();
            double y = io.nextDouble();
            vertices[i] = new DoublePair(x, y);
        }

        // Logic
        double[][] graph = createGraph(vertices);
        floydWarshall(graph);

        // Output
        io.print(graph[0][1]);
        io.flushOutput();
    }

    public static double[][] createGraph(DoublePair[] vertices) {
        int size = vertices.length;
        double[][] graph = new double[size][size];

        for (int i = 0; i < size; i++) {
            DoublePair vertex_i = vertices[i];
            for (int j = 0; j < size; j++) {
                DoublePair vertex_j = vertices[j];

                double distance = Math.hypot(vertex_i.first() - vertex_j.first(), vertex_i.second() - vertex_j.second());
                if (i == 0 || i == 1)
                    graph[i][j] = distance / 5;
                else
                    graph[i][j] = 2 + Math.abs(distance - 50.0) / 5;
            }
        }
        return graph;
    }

    public static void floydWarshall(double[][] graph) {
        int size = graph.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }
    }

    static class DoublePair implements Comparable<DoublePair> {
        public Double _first, _second;

        public DoublePair(Double f, Double s) {
            _first = f;
            _second = s;
        }

        public int compareTo(DoublePair o) {
            if (!this.first().equals(o.first()))
                return (int)(this.first() - o.first());
            else
                return (int)(this.second() - o.second());
        }

        Double first() { return _first; }

        Double second() { return _second; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof DoublePair)) return false;

            DoublePair that = (DoublePair)obj; // than we can cast it to People safely
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

        double nextDouble() throws Exception 
        { 
            double ret = 0, div = 1; 
            byte c = nextByte(); 
            while (c <= ' ') 
                c = nextByte(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = nextByte(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            }
            while ((c = nextByte()) >= '0' && c <= '9'); 
  
            if (c == '.') { 
                while ((c = nextByte()) >= '0' && c <= '9') 
                    ret += (c - '0') / (div *= 10);
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
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

        void print(double value) {
            pw.print(value);
        }

        void flushOutput() {
            pw.flush();
        }
    }
}
