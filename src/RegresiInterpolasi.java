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

        mHasil = SPL.Cramer(mIntPol);

        //menghitung polinom interpolasi yang melalui i titik
        hasilfungsi = 0;
        for (i=0; i<m.getRow(); i++){
            hasilfungsi += mHasil.getELMT(i, 0) * Math.pow(x, i);
            // set 4 angka di belakang koma
            hasilfungsi = new BigDecimal(hasilfungsi).setScale(4, RoundingMode.HALF_UP).doubleValue();
        }


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

        System.out.println("Hasil taksir dari persamaan polinom " + persamaan + " = " + hasilfungsi);
    }
}
