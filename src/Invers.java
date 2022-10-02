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
  public static Matrix getKofMatrix (){

  int a = 0, b = 0; //index m1
  int tanda = 1;
  Matrix kofaktor = new Matrix(m.getRow(), m.getColumn());
  Determinan det = new Determinan();
  double determinan;
  for (int j = 0; j < m.getColumn(); j++){
      Matrix m1 = new Matrix(m.getRow() - 1, m.getColumn() - 1);
  for (int i = 0; i < m.getRow(); i++){
      if (i % 2 == 0){
          tanda = 1;
      }
      else {
          tanda = -1;
      }
          for (int c = 0 ; c < m.getRow() ; c++){
              for (int d = 0 ; d < m.getColumn() ; d++){
                  if (c != i && d != j){
                      m1.Mat[a][b] = m.getELMT(c, d);
                      if (b + 1 < m1.getColumn()){
                          b++;
                      }
                      else if (a + 1 < m1.getRow()){
                          a++; b = 0;
                      }
                  }
              }
          }
              a = 0; b = 0;
              kofaktor.Mat[i][j] = det.detKofaktor(m1) * tanda;
              tanda *= -1;
          }
      }
      return kofaktor;
}
  
  // Mencari adj matriks
  public static Matrix getAdj(){
    if (m.getRow() == 1 && m.getColumn() == 1){
        m.Mat[0][0] = 1; 
        return m;
      }
    else {
    Matrix matadj;
    matadj = m.getKofMatrix();
    return matadj.transMatrix();
  }
}
  
    // Mencari inv matriks dengan matriks adj
    public static Matrix adjInv(){
      Determinan det = new Determinan();
      double determinan = det.detKofaktor(m);
      Matrix matInv = m.adj();
      for (int i = 0 ; i < matInv.getRow() ; i++){
          for (int j = 0 ; j < matInv.getColumn() ; j++){
              matInv.Mat[i][j] *= (1 / determinan);
          }
      }
      return matInv;
  }
}
