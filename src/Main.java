import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
            boolean keluar = true;

    System.out.println("Selamat Datang di Tubes Algeo!!");

    while (!keluar){
        String inputmenu;

        System.out.println("---------------MENU(^_^)---------------");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
        System.out.println();

        System.out.print("Input menu : ");
        inputmenu = in.nextLine();

        switch (inputmenu){
            case "1" :
                System.out.println("********Sistem Persamaan Linier********");
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                
                System.out.println();

                System.out.print("Input menu : ");
                inputmenu = in.nextLine();
            case "2" :
                System.out.println("***************Determinan**************");
                System.out.println("1. Metode Ekspansi Kofaktor");
                System.out.println("2. Metode Reduksi Baris");
                System.out.println();
                
                System.out.print("Input menu : ");
                inputmenu = in.nextLine();
            case "3" :
                System.out.println("************Matriks Balikan************");
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println();
                
                System.out.print("Input menu : ");
                inputmenu = in.nextLine();
            case "4" :
            case "5" :
            case "6" :
        }
    }
    }
}
