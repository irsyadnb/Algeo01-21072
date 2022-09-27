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

    public void setELMT(int i, int j, double k){
        this.m[i][j] = k;
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
    
    public Matrix kaliMatrix(Matrix m1, Matrix m2){
        Matrix m3 = new Matrix(m1.getRow(), m2.getColumn());
        int i, j, k = 0;
        double tmp = 0;

        for (i = 0; i < m1.getRow(); i++) {
            for (j = 0; j < m2.getColumn(); j++) {
                tmp = 0;
                for (k = 0; k < m1.getColumn(); k++) {
                    tmp += m1.getELMT(i, k) * m2.getELMT(k, j);
                }
                m3.setELMT(i, j, tmp);
            }
        }
        return m3;
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

    public static boolean isUnder0(Matrix m, int i, int j){ // cek elemen dibawah == 0
        int ctr;
        ctr =0;
        while(i+1 < m.row && ctr == 0){
            if (m.getELMT(i,j) != 0){
                ctr += 1;
            }
            i++;
        }
        return (ctr == 0);
    }

    public static boolean isRowEmpty(Matrix m, int i){ // cek baris kosong
        int j,ctr;
        ctr = 0;
        for(j=0; j<m.column; j++){
            if (m.getELMT(i, j) == 0){
                ctr += 1;
            }
        }
        return (j == (ctr-1));
    }

    public static Matrix swapEmptyRow(Matrix m){ // menukar baris kosong agar menjadi dibawah matriks
        int i = 0;
        int j;
        boolean found = false;

        while(!found){
            if(isRowEmpty(m, i)){
                found = true;
            }
            i++;
        }

        for (j = i + 1;j<m.row;j++){
            if(!isRowEmpty(m, j)){
                m.swapRow(i, j);
            }
        }

        return m;

    }

    public static int idxLeadOne(Matrix m, int i){ // mencari satu utama pada suatu baris
        int j,ctr;
        ctr = 0;
        for(j=0;j<m.column;j++){
            if(m.getELMT(i, j) == 1){
                ctr = j;
            }
        }
        return ctr;
    }


    public static boolean isDiagEQOne(Matrix m){ //cek diagonal apakah bernilai 1
        int i;
        boolean isOne;
        i = 0;
        isOne = true;
        while (i<m.getRow() && isOne){
            if (m.getELMT(i, i) != 1){
                isOne = false;
            }
            i++;
        }
        return isOne;
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
