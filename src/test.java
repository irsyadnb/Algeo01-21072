import java.util.*;

public class test {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int inputrow, inputcol;
        inputrow = in.nextInt();
        inputcol = in.nextInt();
        Matrix m;
        m = new Matrix(inputrow, inputcol);
        m.readMatrix();
        m.displayMatrix(SPL.ubahEselon(m));
        m.displayMatrix(SPL.ubahEselonReduksi(m));
        System.out.printf("%f\n", Determinan.detRedBar(m));
    }   
}