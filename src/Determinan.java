public class Determinan {
    public static double detRedBar(Matrix m){
        Matrix m1 = new Matrix(m.getRow(), m.getColumn());
        m.copyMatrix(m1);
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
        return Math.abs(hasil);
    }
}
