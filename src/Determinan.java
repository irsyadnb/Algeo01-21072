public class Determinan {
    public static double detRedBar(Matrix m){
        Matrix m1 = new Matrix(m.getRow(), m.getColumn());
        m.copyMatrix(m1);
        double result = 0;
        int ctr = 0;
        int i;
        for(i = 0; i<m1.getRow()-1;i++){
            if(m1.getELMT(i, i) == 0){
                boolean done = false;
                int k = i+1;
                while(k<m1.getRow() && !done){  
                    if(m1.getELMT(k, i) != 0){ // jika elemen bawah != 0, tukar.
                        m1.swapRow(i, k); 
                        done = true;
                        ctr++;
                    }
                }
            }
            for(int j=i+1; j<m1.getRow(); j++){ //OBE
                if(m1.getELMT(j, i) != 0){ //cek elemen bawah 0 atau bukan
                    double x = m1.getELMT(j, i) / m1.getELMT(i, i);
                    m1.otherKRow(j, i, x); 
                }
            }
        }
        // sudah terbentuk matrix segitiga bawah

        // melakukan perkalian diagonal untuk determinan\
        double hasil = 1;
        
        for(i=0;i<m.getRow();i++){
            hasil *= m1.getELMT(i, i);
        }

        // mengalikan (-1^ctr), banyak pertukaran garis
        for(i=ctr;i>0;i--){
            hasil *= -1;
        }
        if(hasil == -0.0){
            result = Math.abs(hasil);
        }
        return result;
    }
    public static double detKofaktor(Matrix m){
        int i, j, k, a, b;
        double det, kof;
        Matrix mHasilKof = new Matrix(m.getRow()-1, m.getColumn()-1);

        if (m.getRow() == 1 && m.getColumn() == 1){
            det = m.getELMT(0, 0);
        } else if (m.getRow() == 2 && m.getColumn() == 2){
            det = m.getELMT(1, 1)*m.getELMT(0, 0) - m.getELMT(0, 1)*m.getELMT(1, 0);
        } else {
            det = 0;
            for (k = 0; k < m.getColumn(); k++){
                a = 0; //menunjuk index baris submatrix
                for (i = 1; i < m.getRow(); i++){
                    b = 0; //menunjuk index kolom submatrix
                    for (j = 0; j < m.getColumn(); j++){
                        if (k != j){
                            // set submatrix
                            mHasilKof.setELMT(a, b, m.getELMT(i, j));
                            b++;
                        }
                    } 
                    a++;
                }
                //cek apakah kofaktor seharusnya negatif atau positif
                kof = Math.pow(-1, k) * detKofaktor((mHasilKof)) * m.getELMT(0, k);
                det += kof;
            }
        }
        return det;
    }
}
