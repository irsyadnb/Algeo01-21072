import java.util.*;
import java.io.*;

public class BacaFile {
  private Scanner file;
  private String fileName;
  static Scanner in = new Scanner(System.in); // intput str
  static Scanner intint = new Scanner(System.in); // input int

  public BacaFile(String fileName) {
    this.fileName = fileName;
  }

  public void bukaFile() {
    try {
        File file = new File(this.fileName);
        this.file = new Scanner(file);
        System.out.println("Isi file : ");
        while(this.file.hasNextLine()){
            System.out.println(this.file.nextLine());
        }
    } catch (Exception e) {
      System.out.println("Tidak ada nama File tersebut.");
    }
  }

  public void closeFile() {
    file.close();
  }

  // PEMBACAAN MATRIKS DARI FILE KE PROGRAM //
  public int bacaRow() {
    int row = 0;

    bukaFile();
    while (file.hasNextLine()) {
      row++;
      file.nextLine(); //ke line selanjutnya
    }
    closeFile();
    return row;
  }

  public int bacaColumn() {
    int col = 0;

    bukaFile();
    Scanner filecol = new Scanner(file.nextLine());
    while (filecol.hasNextFloat()) {
      col++;
      filecol.nextFloat(); //ke float selanjutnya
    }
    closeFile();
    return col;
  }

  public Matrix bacaFile() {
    int i, j, row, column;
    Matrix m;

    row = bacaRow();
    column = bacaColumn();
    m = new Matrix(row, column);

    bukaFile();
    for (i = 0; i < m.getRow(); i++) {
      for (j = 0; j < m.getColumn(); j++) {
        m.setELMT(i, j, file.nextFloat());
      }
    }
    closeFile();
    return m;
  }

  public static void writeMatrix(Matrix m, String fileName) {
    int i, j;

    try {
      PrintWriter write = new PrintWriter(fileName);
      for (i = 0; i < m.row; i++) {
        for (j = 0; j < m.column; j++) {
          write.print(m.getELMT(i, j));
          if (j != m.column - 1) {
            write.print(" ");
          }
        }
        write.println();
      }
      write.close();
    } catch (Exception e) {
      System.out.println("Error");
    }
  }

  /* Menampilkan display Save */
  public static void displaySave() {
    /* ALGORITMA */
    System.out.println();
    System.out.println("=======================================================");
    System.out.println("Apakah keluaran ingin disimpan dalam folder \"output\"?");
    System.out.println("1. Iya");
    System.out.println("2. Tidak");
    System.out.println("=======================================================");
    System.out.print(">Masukan: ");
  }


  /* Melakukan save File secara umum */
  public static void saveFile(String m) {
    /* KAMUS LOKAL */
    int opsi;
    String nama;
    PrintWriter out;
    /* ALGORITMA */
    try {
      displaySave();
      opsi = in.nextInt();
      if (opsi == 1) {
        System.out.print(">Nama file (Contoh: test.txt): ");
        nama = intint.nextLine();
        out = new PrintWriter(new File("output/" + nama));
        out.write(m);
        out.close();
      }
    } catch (Exception e) {
      System.out.println("Error dalam saveFile");
    }
  }
}