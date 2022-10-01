import java.util.*;

class MenuUI {
    static Scanner in = new Scanner(System.in); // scan string
    static Scanner inint = new Scanner(System.in); // scan int 

    public static void dispMenu(){ //menu utama
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
    }

    public static void dispExit(){ //menu exit
        System.out.println();
        System.out.println("---------------(^_^)---------------");
        System.out.println("Keluar program?");
        System.out.println("1. Ya");
        System.out.println("99. Kembali ke menu utama");
        System.out.print("Input : ");
    }

    public static void main(String[] args) throws Exception {
        boolean keluar = false;
        System.out.println("SELAMAT DATANG DI TUBES 1 ALGEO!");
        while (!keluar) {
            String inputmenu, submenu;
            int row, col, size;
            Matrix mspl, minv, result;
            double resultd;

            dispMenu();
            inputmenu = in.nextLine();

            switch (inputmenu) {
                case "1":
                    System.out.println("********Sistem Persamaan Linier********");
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode matriks balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.println("99.Kembali ke Menu Utama");
                    System.out.println();

                    System.out.print("Input menu : ");
                    inputmenu = in.nextLine();

                    if (inputmenu.equals("99")) { //penggunaan equals karena pada if else, string tidak bisa hanya memakai ==
                        break;
                    } 
                    else {
                        System.out.print("Baris: ");
                        row = inint.nextInt();
                        System.out.print("Kolom: ");
                        col = inint.nextInt();
                        mspl = new Matrix(row, col);
                        System.out.println("Input elemen matriks: ");
                        mspl.readMatrix();

                        switch (inputmenu) {
                        case "1":
                            result = SPL.ubahEselon(mspl);
                            //solusi G
                            break;
                        case "2":
                            result = SPL.ubahEselonReduksi(mspl);
                            //solusi GJ
                            break;
                        case "3":
                            //matriks balikan
                            //belum
                            break;

                        case "4":
                            //cramer
                            //belum
                            break;
                        }
                        dispExit();
                        inputmenu = in.nextLine();

                        switch (inputmenu) {
                            case "1":
                                keluar = true;
                                break;
                            case "99":
                                break;
                            }
                            break;
                    }

                case "2":
                    System.out.println("***************Determinan**************");
                    System.out.println("1. Metode Ekspansi Kofaktor");
                    System.out.println("2. Metode Reduksi Baris");
                    System.out.println("99.Kembali ke Menu Utama");
                    System.out.println();

                    System.out.print("Input sub menu : ");
                    submenu = in.nextLine();

                    if (submenu.equals("99")) {
                        break;
                    } 
                    else {
                        System.out.print("Masukkan ukuran matriks (nxn): ");
                        col = inint.nextInt();
                        minv = new Matrix(col, col);
                        System.out.println("Input Elemen Matriks: ");
                        minv.readMatrix();
                    }
                    switch (submenu) {
                        case "1":
                            resultd = Determinan.detKofaktor(minv);
                            System.out.println("Determinan Kofaktor = " + resultd);
                            break;
                        case "2":
                            resultd = Determinan.detRedBar(minv);
                            System.out.println("Determinan Reduksi = " + resultd);
                            break;
                    }  
                    dispExit();
                    inputmenu = in.nextLine();
                    switch (inputmenu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                    }
                    break;

                case "3":
                    System.out.println("************Matriks Balikan************");
                    System.out.println("1. Metode eliminasi Gauss-Jordan");
                    System.out.println("2. Metode Adjoint");
                    System.out.println("99.Kembali ke Menu Utama");
                    System.out.println();
                    
                    System.out.print("Input menu : ");
                    inputmenu = in.nextLine();

                    if (inputmenu.equals("99")) {
                        break;
                    } 
                    else {
                        System.out.print("Masukkan ukuran matriks (nxn): ");
                        size = inint.nextInt();
                        minv = new Matrix(size, size);
                        System.out.println("Input Elemen Matriks: ");
                        minv.readMatrix();

                        System.out.println();
                        switch (inputmenu) {
                            case "1":
                                resultd = Determinan.detKofaktor(minv);
                                if (resultd == 0) {
                                    System.out.println("Determinan = 0, tidak ada matriks balikan");
                                } 
                                else {
                                    result = Invers.gaussInv(minv);
                                    System.out.println("Hasil matriks balikan (gauss-jordan) : ");
                                    result.displayMatrix(result);
                                }
                                break;
                            case "2":
                                resultd = Determinan.detKofaktor(minv);
                                if (resultd == 0) {
                                    System.out.println("Determinan = 0, tidak ada matriks balikan");
                                } 
                                else {
                                    result = Invers.adjInv(minv);
                                    System.out.println("Hasil matriks balikan (adjoint) : ");
                                    result.displayMatrix(result);
                                }
                                break;
                        }
                    }
                    dispExit();
                    inputmenu = in.nextLine();

                    switch (inputmenu) {
                    case "1":
                        keluar = true;
                        break;
                    case "99":
                        break;
                    }

                case "4"://interpolasi polinom
                    System.out.println("************Interpolasi Polinom************");

                    dispExit();
                    inputmenu = in.nextLine();

                    switch (inputmenu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                    }
                    break;

                case "5"://interpolasi bicubic
                    System.out.println("************Interpolasi Bicubic************");

                    dispExit();
                    inputmenu = in.nextLine();

                    switch (inputmenu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                    }
                    break;
                
                case "6"://regresi linier berganda
                    System.out.println("************Regresi Linier Berganda************");
                    dispExit();
                    inputmenu = in.nextLine();

                    switch (inputmenu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                    }
                break;
                    
                case "7"://keluar
                    keluar = true;
                    break;
            }
        }
            System.out.println("TERIMA KASIH!");
    }
}