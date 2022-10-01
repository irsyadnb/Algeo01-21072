public class Invers {
  //Mencari inv matriks dengan Gauss-Jordan
   //Mencari inv matriks dengan Gauss-Jordan
     public static Matrix InversGaussJordan(Matrix m) {
        int i, j, a, aa, c, x, y;
        boolean keep;
        double bagi, k, nilai, nilai2;
        // buat dua matriks kanan kiri untuk dilakukan gauss-jordan
        Matrix m1 = new Matrix (m.getRow(), m.getColumn());
        Matrix m2 = new Matrix (m.getRow(), m.getColumn());
        m.copyMatrix(m1);
        m.copyMatrix(m2);

        m2.MatrixIdentitas();

        for (i=0; i<m1.getRow(); i++){
            if (m1.getELMT(i, i) != 1 || m1.getELMT(i, i) == 0){
                c  = i + 1;
                keep = true;
                while (c < m1.getRow() && keep){
                    if (m1.getELMT(c, i) == 1){
                        m1.swapRow(i, c);
                        m2.swapRow(i, c);
                        keep = false;
                    }
                    c++;
                }   
            }

            // bagi baris  agar yang pertama jadi 1
            if (m1.getELMT(i, i) != 0){
                bagi = 1 / m1.getELMT(i, i);
            } else{
                break;
            }

            for (j=i; j<m1.getColumn(); j++){
                m1.setELMT(i, j, (m1.getELMT(i, j)*bagi));
            }

            for (y=0; y<m2.getColumn(); y++){
                m2.setELMT(i, y, (m2.getELMT(i, y)*bagi));
            }

            //membuat elemen di bawah 1 utama menjadi 0
            for(a=i+1; a<m1.getRow(); a++){
                k = m1.getELMT(a, i) / m1.getELMT(i, i);
                for (aa = i; aa < m1.getColumn(); aa++){
                    nilai = m1.getELMT(a, aa) - k * m1.getELMT(i, aa);
                    m1.setELMT(a, aa, nilai);
                }
                for (x=0; x<m2.getColumn(); x++){
                    nilai2 = m2.getELMT(a, x) - k * m2.getELMT(i, x);
                    m2.setELMT(a, x, nilai2);
                }
            }
        }
        for (i = m1.getRow()-1 ; i >= 0; i--){ 
            for(j = m1.getColumn()-1 ; j >= 0; j--){
                if (m1.getELMT(i, j) == 1){
                    double factor;
                    int otherRow = i -1;

                    while (otherRow >= 0){
                        factor = m1.getELMT(otherRow, j);
                        m1.otherKRow(otherRow, i, factor);
                        m2.otherKRow(otherRow, i, factor);
                        otherRow--;
                    }
                }
            }
        }
        return m2;
     }
    
  // Mencari kofaktor matriks
    public static Matrix getKofMatrix (Matrix m, int pivotrow, int pivotcol) {
        int i, j;
        int ikof = 0, jkof = 0;
        Matrix kof = new Matrix((m.getRow() - 1), (m.getColumn() - 1));
        // Ukuran matriks lebih dari 2x2
        if (m.countELMT() != 4) {
            for (i = 0; i < m.getRow(); i++) {
                for (j = 0; j < m.getColumn(); j++) {
                    if ((i != pivotrow) && (j != pivotcol)) { //Memindahkan matriks m ke matriks kofaktor
                        kof.setELMT(ikof, jkof, m.getELMT(i, j));
                        jkof++;
                    if (jkof == (m.getColumn() - 1)) { // Iterasi indeks baris matriks kofaktor
                        jkof = 0;
                        ikof++;
                    }
                }
            }
        }   
    }
      return kof;
}
  
  // Mencari adj matriks
  public static Matrix getAdj(Matrix m) {
      int i, j;
      int tanda = 1;
      double kofaktor;
      Matrix kof = new Matrix(m.getRow(), m.getColumn());
      Matrix tempkof = new Matrix(m.getRow(), m.getColumn());
      // Ukuran matriks 3x3
      for (i = 0; i < m.getRow(); i++) {
        for (j = 0; j < m.getColumn(); j++) {
          if ((i + j) % 2 == 0) { //Ubah tanda + dan -
            tanda = 1;
          } 
          else {
            tanda = -1;
          }
          tempkof = getKofMatrix(m, i, j);
          kofaktor = Determinan.detKofaktor(tempkof) * tanda; // Mencari determinan matriks kofaktor
          kof.setELMT(i, j, kofaktor);
        }
      }
      return (kof.transMatrix(m));
    }
  
    // Mencari inv matriks dengan matriks adj
    public static Matrix adjInv(Matrix m) {
      int i, j;
      Matrix invbiasa = new Matrix(m.getRow(), m.getColumn());
      Matrix adj = new Matrix(m.getRow(), m.getColumn());
      Matrix inv = new Matrix(m.getRow(), m.getColumn());
      double matInv, det;
      adj = getAdj(m);
      det = Determinan.detKofaktor(m);
  
      // Ukuran matriks 2x2
      if (m.countELMT() == 4) {
        invbiasa.setELMT(0, 0, m.getELMT(1, 1));
        invbiasa.setELMT(1, 1, m.getELMT(0, 0));
        invbiasa.setELMT(0, 1, (-1) * m.getELMT(0, 1));
        invbiasa.setELMT(1, 0, (-1) * m.getELMT(1, 0));
        for (i = 0; i < m.getRow(); i++) {
          for (j = 0; j < m.getColumn(); j++) {
            matInv = (invbiasa.getELMT(i, j) / det);
            inv.setELMT(i, j, matInv);
          }
        }
      } 
      else {
        for (i = 0; i < m.getRow(); i++) {
          for (j = 0; j < m.getColumn(); j++) {
            matInv = (adj.getELMT(i, j) / det);
            inv.setELMT(i, j, matInv);
          }
        }
      }
      return inv;
    }
  }
