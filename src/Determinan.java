public class Determinan {
    public static double detRedBar(Matrix m){
        int x, i, j, k, row;
        double num1, num2, num3, temp;
        double det, total;
    
        row = m.getRow();
        det = 1;
        total = 1;

        for (i = 0; i<row; i++) {
            x = i;
            while (x < row && m.getELMT(x, i) == 0) {
                x++;
            }
            if (x == row) {
                return 0;
            }
            if (x != i) {
                for (j=0; j<row; j++) {
                    temp = m.getELMT(x, j);
                    m.setELMT(x, j, m.getELMT(i, j));
                    m.setELMT(i, j, temp);
                }
                det *= -1;
            }
            // OBE
            for (j = i+1; j<row; j++) {
                num1 = m.getELMT(i, i);
                num2 = m.getELMT(j, i);
                for (k=0; k<row; k++) {
                    num3 = (num1*m.getELMT(j, k)) - (num2*m.getELMT(i, k));
                    m.setELMT(j, k, num3);
                }
                total *= num1;
            }
        }
        // Determinan
        for (i = 0; i<row;i++) {
            det *= m.getELMT(i, i);
        }
        return (det/total);
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
