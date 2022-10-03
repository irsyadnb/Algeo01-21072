import java.util.*;

public class SPL {
    static Scanner in = new Scanner(System.in); // Input String
    static Scanner sc = new Scanner(System.in); // Input integer/double
    // Eliminasi Gauss

    public static Matrix ubahEselon(Matrix m){
        int i, j, a, aa, c;
        boolean keep;
        double bagi, nilai, k;
        Matrix mHasil = new Matrix(m.getRow(), m.getColumn());
        m.copyMatrix(mHasil);
        mHasil.displayMatrix(m);

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
            }
            
            // bagi baris  agar yang pertama jadi 1
            if (mHasil.getELMT(i, i) != 0){
                bagi = 1 / mHasil.getELMT(i, i);
            } else{
                break;
            }

            for (j=i; j<mHasil.getColumn(); j++){
                mHasil.setELMT(i, j, (mHasil.getELMT(i, j)*bagi));
            }

            //membuat elemen di bawah 1 utama menjadi 0
            for(a=i+1; a<mHasil.getRow(); a++){
                k = mHasil.getELMT(a, i) / mHasil.getELMT(i, i);
                for (aa = i; aa < mHasil.getColumn(); aa++){
                    nilai = mHasil.getELMT(a, aa) - k * mHasil.getELMT(i, aa);
                    mHasil.setELMT(a, aa, nilai);
                }
            }
            
        }
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
                    m.displayMatrix(m);

                    while (otherRow >= 0){
                        factor = m.getELMT(otherRow, j);
                        m.otherKRow(otherRow, i, factor);
                        otherRow--;
                        m.displayMatrix(m);
                    }
                }
            }
        }
        return m;
    }

    // Kaidah Cramer
    public static Matrix Cramer(Matrix m){
        int i,j;
        double detkoef, detCramer;
        Matrix koef, kons, cramer;
        koef = Matrix.coeffMat(m);
        kons = Matrix.constMat(m);
        cramer = new Matrix(kons.getRow(), 1);
        detkoef = Determinan.detKofaktor(koef);

        //loop kolomnya buat ganti per kolom sama konstanta matriksnya
        if (detkoef != 0){
            for (j=0; j<koef.getColumn(); j++){
                //biar balik lagi ke matriks koef yang awal
                koef = Matrix.coeffMat(m);
                for (i=0; i<koef.getRow(); i++){
                    // ganti konstanta per barisnya
                    koef.setELMT(i, j, kons.getELMT(i, 0));
                }
                //hitung determinan cramernya
                detCramer = Determinan.detKofaktor(koef);
                cramer.setELMT(j, 0, detCramer/detkoef);
            }
            return cramer;
        } else{
            System.out.println("Matriks merupakan matriks singular. Tidak dapat melakukan Kaidah Cramer.");
            return m;
        }

    }
    
    //SPL dengan metode matriks balikan
    public static Matrix inverse(Matrix m){
        Matrix mKoef, mKons, mHasil;
        mKoef = new Matrix(m.getRow(), m.getColumn()-1);
        mKons = new Matrix(m.getRow(), 1);
        mHasil = new Matrix(mKoef.getRow(), mKoef.getColumn());

        //pisah matrix augmented menjadi dua
        mKoef = Matrix.coeffMat(m);
        mKons = Matrix.constMat(m);
        
        //inverse matrix spl
        mKoef = Invers.InversGaussJordan(mKoef);
        // kalikan inverse dengan matriks konstanta A^-1b = x
        mHasil = m.kaliMatrix(mKoef, mKons);
        
        return mHasil;
    }
    //Solusi untuk Gauss
    public static void solusiGauss(Matrix m){
        //prekondisi matrix augmented
        int i = m.getRow()-1;
        int a, j, k, sumnol, counter;
        double [] temp = new double [26];
        boolean solusi;
        solusi = true;
        String hasil = "";
        while (solusi && i >= 0){
            sumnol = 0;
            for (j=0; j<m.getColumn()-1;j++){
                if(m.getELMT(i, j) == 0){
                    sumnol++;
                }
                if (sumnol == m.getColumn()-1 && m.getELMT(i, m.getColumn()-1) != 0){
                    solusi = false;
                }
            }
            i--;
        }

        if (solusi == false){
            hasil ="Tidak ada solusi untuk SPL ini.";
        } else{
            if((m.getRow()==m.getColumn()-1)&&(Matrix.isDiagonalOne(m))){
                i=0;
                a = i+1;
                hasil = "x" + a + " = " + m.getELMT(i, m.getColumn()-1);
                for(i=1; i<m.getRow(); i++){
                    a = i+1;
                    hasil += ", x" + a + " = " + m.getELMT(i, m.getColumn()-1);
                }
            } else {
                k = 0;
                for (i=m.getRow()-1; i>=0; i++){
                    counter=0;
                    for (j=0; j<m.getColumn()-1;j++){
                        if (m.getELMT(i, j) != 0){
                            counter++;
                            temp[k] = j;  
                        }
                    }
                    if (counter == 1){
                        hasil += "x" + (temp[k]+1) + " = " + (m.getELMT(i, m.getColumn()-1)/m.getELMT(i, j));
                        k++;
                    } else {
                        hasil += ("Program parametrik belum tersedia");
                    }
                }
            }
        }
        System.out.println(hasil);
    }  
}
