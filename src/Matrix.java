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

    // Pembuatan Matrix Augmented //

    public static Matrix coeffMat(Matrix m){ // membuat Coefficient Matrix
        Matrix koefMat = new Matrix(m.row, m.column - 1);
        int i,j;
        for(i=0;i<koefMat.row;i++){
            for(j=0;j<koefMat.column;j++){
                koefMat.m[i][j] = m.m[i][j];
            }
        }
        return koefMat;
    }

    public static Matrix constMat(Matrix m){ // membuat Constant Matrix
        Matrix konsMatrix = new Matrix(m.row,1);
        int i;
        for (i=0;i<konsMatrix.row;i++){
            konsMatrix.m[i][0] = m.m[i][m.column-1];
        }
        return konsMatrix;
    }

    // Matriks Augmented (Penggabungan Coefficient Matrix dan Constant Matrix)
    public static Matrix createMatAug(Matrix koef, Matrix konst){
        Matrix hasil = new Matrix(koef.row, koef.column);
        int i,j,k;
        for(i=0;i<koef.row;i++){
            for (j=0;i<koef.column;j++){
                hasil.m[i][j] = koef.m[i][j];
            }
        }
        for(k=0;k<koef.column;k++){
            hasil.m[k][koef.column-1] = konst.m[k][0];
        }
        return hasil;
    }   
}
