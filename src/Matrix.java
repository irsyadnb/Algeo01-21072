public class Matrix {
    int row, column;
    double[][] m;

    public Matrix(int i, int j){
        matriks.m = new double[i][j];
        matriks.row = i;
        matriks.column = j;
    }

    public double getELMT(int i, int j){
        return(matriks.m[i][j]);
    }

    public int getRow(){
        return(matriks.row);
    }

    public int getColumn(){
        return(matriks.column);
    }

    public void readMatrix(){
        int i, j;
        
        for(i=0; i<matriks.row; i++){
            for(j=0; j<matriks.column; j++){
                matriks.m[i][j] = in.nextDouble();
            }
        }
    }

    public void displayMatrix(){
        int i, j;
        for(i=0; i<matriks.row; i++){
            for(j=0; j<matriks.column; j++){
                System.out.print(m[i][j]);
                if (j != matriks.column - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int countELMT(){
        return(matriks.row * matriks.column);
    }
}
