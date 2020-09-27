import java.io.*;
import java.util.*;

public class KattisTemplate {

    public static void main(String args[]) throws Exception {
        // Fast IO
        FastIO io = new FastIO(System.in, System.out);

        // Input


        // Logic


        // Output


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
