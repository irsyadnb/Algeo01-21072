import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RegresiInterpolasi {
    public static void InterpolasiPolinom (Matrix m, double x){
        int i, j;
        String persamaan;
        double awal,k;
        double hasilfungsi, pers;
        Matrix mIntPol = new Matrix(m.getRow(),m.getRow()+1);
        Matrix mHasil = new Matrix(mIntPol.getRow(), 1);

        for (i=0; i<m.getRow(); i++){
            awal = 1;
            k = m.getELMT(i, 0);
            for (j=0; j<m.getRow(); j++){
                mIntPol.setELMT(i, j, awal);
                awal *= k;
            }
            mIntPol.setELMT(i, j, m.getELMT(i, m.getColumn()-1));
        }
        mIntPol.displayMatrix();

        mHasil = SPL.Cramer(mIntPol);
        mHasil.displayMatrix();
        //menghitung taksiran polinom interpolasi yang melalui i titik
        hasilfungsi = 0;
        for (i=0; i<m.getRow(); i++){
            hasilfungsi += mHasil.getELMT(i, 0) * Math.pow(x, i);   
        }
        // set 4 angka di belakang koma
        hasilfungsi = new BigDecimal(hasilfungsi).setScale(4, RoundingMode.HALF_UP).doubleValue();

        // membuat persamaan
        pers = mHasil.getELMT(0, 0);
        pers = new BigDecimal(pers).setScale(4, RoundingMode.HALF_UP).doubleValue();
        persamaan = "p" + (mHasil.getRow()-1) + "(X) = " + pers;

        for (i=1; i<mHasil.getRow(); i++){
            //bila negatif diberi kurung pemisah
            pers = mHasil.getELMT(i, 0);
            pers = new BigDecimal(pers).setScale(4, RoundingMode.HALF_UP).doubleValue();
            if (mHasil.getELMT(i, 0) < 0){
                persamaan += " + (" + (pers) + "X^" + i + ")";
            } else {
                persamaan += " + " + (pers) + "X^" + i;
            }
        }

        System.out.println("Hasil taksir dengan persamaan polinom " + persamaan + " adalah " + hasilfungsi);
    }

    public static void InterpolasiBikubik(Matrix m, int x, int y){
        int i, j, k, a, b, idxi, idxj, p, q;
        double hasil;
        Matrix mBikubik = new Matrix (16, 16);
        Matrix mInverse = new Matrix (mBikubik.getRow(), mBikubik.getColumn());
        Matrix mTemp = new Matrix (16, 1);
        Matrix mHasil = new Matrix (mInverse.getRow(), mTemp.getColumn());
        p = x+1;
        q = y+1;

        //-1,0,1,2
        idxi = 0;
        for(a=-1;a<3;a++){
            for(b=-1;b<3;b++){
                idxj = 0;
                for(i=0; i<4; i++){
                    for(j=0; j<4; j++){
                        mBikubik.setELMT(idxi, idxj, Math.pow(a, i)*Math.pow(b, j));
                        idxj++;
                    }
                }
                idxi++;
            }
        }
        mInverse = Invers.InversGaussJordan(mBikubik);

        //buat matrix 4x4 jadi 16x1
        k = 0;
        for (i = 0; i<m.getRow();i++){
            for (j = 0; j<m.getColumn();j++){
                mTemp.setELMT(k, 0, m.getELMT(i, j));
                k++;
            }
        }
        // kali inverse matriks bikubik dengan matrix 4x4
        mHasil.kaliMatrix(mInverse, mTemp);
        hasil = mHasil.getELMT(p, q);

        System.out.println("Hasil dari interpolasi bikubik dengan x = " + x + " dan y = " + y + " adalah " + hasil);
    }

    public static void RegresiLinierGanda(Matrix m, Matrix n){
        int i, j, k, variabel, idx, idxj;
        double temp, hasilreg, pers;
        String persamaan;
        variabel = n.getColumn();
        Matrix mHasil;
        Matrix mRLB = new Matrix(variabel+1, variabel+2);

        for(i=0; i<mRLB.getRow(); i++){
            idx = i-1;
            for(j=0; j<m.getColumn(); j++){
                temp = 0;
                idxj = j+1;
                for(k=0; k<m.getRow(); k++){
                    if (i == 0){
                        temp += m.getELMT(k, j);
                    } else {
                        temp += m.getELMT(k, j) * m.getELMT(k, idx);
                    }
                }
                if (i==0 && j==0){
                    mRLB.setELMT(i, j, m.getRow());
                } else if (i!=0 && j==0){
                    mRLB.setELMT(i, j, mRLB.getELMT(j, i));
                }
                mRLB.setELMT(i, idxj, temp);
            }
        }
        mHasil = SPL.Cramer(mRLB);
        mHasil.displayMatrix();

        hasilreg = mHasil.getELMT(0, 0);
        for (i=0; i<variabel; i++){
            hasilreg += n.getELMT(0, i) * mHasil.getELMT(i+1, 0);
        }
        // set 4 angka di belakang koma
        hasilreg = new BigDecimal(hasilreg).setScale(4, RoundingMode.HALF_UP).doubleValue();

        // membuat persamaan
        pers = mHasil.getELMT(0, 0);
        pers = new BigDecimal(pers).setScale(4, RoundingMode.HALF_UP).doubleValue();
        persamaan = "y = " + pers;

        for (i=1; i<mHasil.getRow(); i++){
            //bila negatif diberi kurung pemisah
            pers = mHasil.getELMT(i, 0);
            pers = new BigDecimal(pers).setScale(4, RoundingMode.HALF_UP).doubleValue();
            if (mHasil.getELMT(i, 0) < 0){
                persamaan += " + (" + (pers) + "X" + i + ")";
            } else {
                persamaan += " + " + (pers) + "X" + i;
            }
        }
        mHasil.displayMatrix();

        System.out.println("Hasil taksir dengan persamaan regresi " + persamaan + " adalah " + hasilreg);
    }
}

