public class Invers {
  //Mencari inv matriks dengan Gauss-Jordan
   public static Matrix gaussInv(Matrix m) {
    int i, j, k;
    Matrix matKiri = new Matrix(m.getRow(), m.getColumn());
    Matrix matKanan = new Matrix(m.getRow(), m.getColumn());
    m.copyMatrix(matKiri);
    m.copyMatrix(matKanan);
    matKanan.identmMatrix(matKanan);;
    for (i = 0; i < matKiri.getRow(); i++) {
      if (matKiri.getELMT(i, i) == 0) { //Menukar kalo ada elmt diagonal yg 0
        boolean flag = true;
        k = i + 1;
        while (k < matKiri.getRow() && flag) {
          if (matKiri.getELMT(k, i) != 0) {
            matKiri.swapRow(i, k);
            matKanan.swapRow(i, k);
            flag = false;
          }
        }
      }
      if (matKiri.getELMT(i, i) != 1) { //Bagi koef
        matKiri.divRow(i, matKiri.getELMT(i, i));
        matKanan.divRow(i, matKiri.getELMT(i, i));
      }
      for (j = 0; j < matKiri.getRow(); j++) { //OBE di pivot
        if (matKiri.getELMT(j, i) != 0 && i != j) {
          matKiri.otherKRow(j, i, matKiri.getELMT(j, i));
          matKanan.otherKRow(j, i, matKiri.getELMT(j, i));
        }
      }
    }
    for (i = 0; i < matKanan.getRow(); i++) {
      for (j = 0; j < matKanan.getColumn(); j++) {
        if (matKanan.m[i][j] == -0.0) {
          matKanan.m[i][j] = Math.abs(matKanan.m[i][j]);
        }
      }
    }
    return matKanan;
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