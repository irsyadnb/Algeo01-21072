import java.util.*;
import java.io.*;

public class IOFile {

  int rows;
  int cols;
  double[][] matrix;
  private Scanner file;
  private String fileName;
  static Scanner in = new Scanner(System.in); // intput str
  static Scanner intint = new Scanner(System.in); // input int

  public IOFile(String fileName) {
    this.fileName = fileName;
  }

  public IOFile (int rows, int cols) { //KONSTRUKTOR
    this.rows = rows;
    this.cols = cols;
    this.matrix = new double[rows][cols];
}

  public static int readBaris(String s) {
    int count = 0;
    try{
      FileReader reader = new FileReader(String.format("../tubes1/%s", s));
      BufferedReader bufferReader = new BufferedReader(reader);
  
      while(bufferReader.readLine() != null){
        count++;
      }
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    }
    return count;
  }

  public static int readKol(String s){
    int count = 0;
    try{
      FileReader reader = new FileReader(String.format("../tubes1/%s", s));
      BufferedReader bufferReader = new BufferedReader(reader);
      
      String line = bufferReader.readLine();
      String[] lines = line.split(" ");
      count = lines.length;
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    }
    return count;
  }

  public static Matrix bacaM(String s){
    Matrix a = new Matrix(readBaris(s), readKol(s));

    try {
      FileReader reader = new FileReader(String.format("../tubes1/%s", s));
      BufferedReader bufferReader = new BufferedReader(reader);

      String line;
      int count = 0;
      while((line = bufferReader.readLine()) != null){
        String[] lines = line.split(" ");
        for(int i = 0; i < lines.length; i++){
          double temp = Matrix.eval(lines[i]);
          a.setELMT(count, i, temp);
        }
        count++;
      }
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    }
    return a;
  }
  /* 
  public Matrix bacaM(String pathname) {
    int i, j, row, column;
    Matrix m;

    row = readBaris(pathname);
    column = readKol(pathname);
    m = new Matrix(row, column);

    for (i = 0; i < m.getRow(); i++) {
      for (j = 0; j < m.getColumn(); j++) {
        m.setELMT(i, j, file.nextFloat());
      }
    }
    return m;
  }
  */

  //Save File
  public static void simpanMatrix(String namaFile, Matrix m){
    try{
      FileWriter writer = new FileWriter(String.format("../tubes1/%s.txt", namaFile));
      for(int i = 0; i < m.getRow(); i++){
        for(int j = 0; j < m.getColumn(); j++){
          String temp = Double.toString(m.getELMT(i, j));
          writer.write(temp);
          writer.write(" ");
        }
        writer.write("\n");
      }
      writer.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  public static void simpanFile(String namaFile, String s) {
    try {
      FileWriter writer = new FileWriter(String.format("../tubes1/%s.txt", namaFile));
      writer.write(s);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
