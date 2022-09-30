import java.util.*;

public class SPL {
    static Scanner in = new Scanner(System.in); // Input String
    static Scanner sc = new Scanner(System.in); // Input integer/double
    // Eliminasi Gauss

    public static Matrix ubahEselon(Matrix m){
        int i, j, a, aa, c, carinol;
        boolean keep;
        double bagi, nilai, k;
        Matrix mHasil = new Matrix(m.getRow(), m.getColumn());
        m.copyMatrix(mHasil);
        mHasil.displayMatrix();

        for (i=0; i<mHasil.getRow(); i++){
            if (mHasil.getELMT(i, i) != 1 || mHasil.getELMT(i, i) == 0){
                c  = i + 1;
                keep = true;
                while (c < mHasil.getRow() && keep){
                    if (mHasil.getELMT(c, i) == 1){
                        mHasil.swapRow(i, c);
                        keep = false;
                    }
                    c++;
                }   
                mHasil.displayMatrix();
            }
            
            // bagi baris  agar yang pertama jadi 1
            bagi = 1 / mHasil.getELMT(i, i);

            for (j=i; j<mHasil.getColumn(); j++){
                mHasil.setELMT(i, j, (mHasil.getELMT(i, j)*bagi));
            }
            mHasil.displayMatrix();

            //membuat elemen di bawah 1 utama menjadi 0
            for(a=i+1; a<mHasil.getRow(); a++){
                k = mHasil.getELMT(a, i) / mHasil.getELMT(i, i);
                carinol = 0;
                for (aa = i; aa < mHasil.getColumn(); aa++){
                    nilai = mHasil.getELMT(a, aa) - k * mHasil.getELMT(i, aa);
                    if (nilai == 0){
                        carinol++;
                    }
                    mHasil.setELMT(a, aa, nilai);
                }
                if (carinol == 2){
                    break;
                }
            }
            
        }
        mHasil.displayMatrix();
        return mHasil;
    }

    // Eliminasi Gauss-Jordan
    public static Matrix ubahEselonReduksi(Matrix m){
        m = ubahEselon(m);

        // iterasi keatas, dari sudut kanan bawah 
        for (int i = m.getRow()-1 ; i >= 0; i--){ 
            for(int j = m.getColumn()-1 ; j >= 0; j--){
                if (m.getELMT(i, j) == 1){
                    double factor;
                    int otherRow = i -1;
                    m.displayMatrix();

                    while (otherRow >= 0){
                        factor = m.getELMT(otherRow, j);
                        m.otherKRow(otherRow, i, factor);
                        otherRow--;
                        m.displayMatrix();
                    }
                }
            }
        }
        return m;
    }

    public static Matrix Cramer(Matrix m){
        int i,j;
        double detkoef, detCramer;
        Matrix koef, kons, cramer;
        koef = Matrix.coeffMat(m);
        kons = Matrix.constMat(m);
        cramer = new Matrix(kons.getRow(), 1);
        detkoef = Determinan.detKofaktor(koef);

        for (j=0; j<koef.getColumn(); j++){
            koef = Matrix.coeffMat(m);
            for (i=0; i<koef.getRow(); i++){
                koef.setELMT(i, j, kons.getELMT(i, 0));
            }
            detCramer = Determinan.detKofaktor(koef);
            cramer.setELMT(j, 0, detCramer/detkoef);
        }
        return cramer;
    }
}
