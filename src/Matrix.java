import java.util.*;

public class Matrix {

    static Scanner in = new Scanner(System.in);
    int row, column;
    double[][] m;

    // Konstruktor //
    public Matrix(int i, int j){
        this.m = new double[i][j];
        this.row = i;
        this.column = j;
    }

    // Get //
    public double getELMT(int i, int j){
        return(this.m[i][j]);
    }

    public int getRow(){
        return(this.row);
    }

    public int getColumn(){
        return(this.column);
    }

    // Set //
    public void readMatrix(){
        int i, j;
        
        for(i=0; i<this.row; i++){
            for(j=0; j<this.column; j++){
                this.m[i][j] = in.nextDouble();
            }
        }
    }

    public void displayMatrix(){
        int i, j;
        for(i=0; i<this.row; i++){
            for(j=0; j<this.column; j++){
                System.out.print(m[i][j]);
                if (j != this.column - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int countELMT(){
        return(this.row * this.column);
    }

    public void transMatrix(){ //tranpose matrix
        int i, j;
        double tmp;
        for (i = 0; i<this.row; i++){
            for (j=0; j<this.column; j++){
                tmp = this.m[i][j];
                this.m[i][j] = this.m[j][i];
                this.m[j][i] = tmp;
            }
        }
    }
    // Operasi Baris Elementer // 
    public void divRow(int i, double k){ // membagi row dengan konstanta k
        int j;

        for (j = 0; j<column; j++){
            this.m[i][j] /= k;
        }
    }

    public void swapRow(int i1, int i2){ // menukar 2 baris
        int j;
        double tmp;
        for (j=0;j<column;j++){
            tmp = m[i1][j];
            this.m[i1][j] = this.m[i2][j];
            this.m[i2][j] = tmp;
        }
    }

    public void otherKRow(int i1, int i2, double k){ // mengurangi baris dengan kelipatan baris lainnya
        int j;
        for(j=0;j<column;j++){
            this.m[i1][j] -= k * this.m[i2][j];
        }
    }

}
