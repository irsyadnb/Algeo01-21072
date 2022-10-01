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
        System.out.println("Apakah anda ingin sudahi program?");
        System.out.println("1. Ya");
        System.out.println("99. Kembali ke menu utama");
        System.out.println("-----------------------------------");
        System.out.print("Masukan: ");
    }

    public static void main(String[] args) {
        boolean keluar = false;

        System.out.println("SELAMAT DATANG DI TUBES 1 ALGEO!");

        while (!keluar) {
            String menu;
            int row, col;
            Matrix mspl, minv, result;
            double resultd;

            dispMenu();
            menu = in.nextLine();

            switch (menu) {
                case "1":
                    System.out.println("********Sistem Persamaan Linier********");
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode matriks balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.println("99.Kembali ke Menu Utama");
                    System.out.println();

                    System.out.print("Input menu : ");
                    menu = in.nextLine();

                if (menu.equals("99")) { //penggunaan equals karena string tidak bisa hanya memakai == biasa
                    break;
                } 
                else {
                    System.out.print("Baris: ");
                    row = inint.nextInt();
                    System.out.print("Kolom: ");
                    col = inint.nextInt();
                    System.out.println("Input Elemen Matriks: ");
                    mspl = new Matrix(row, col);
                    mspl.readMatrix();

                    System.out.println();
                    switch (menu) {
                    case "1":
                        result = SPL.ubahEselon(mspl);
                        //solusi G
                        break;
                    case "2":
                        result = SPL.ubahEselonReduksi(mspl);

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
                    menu = in.nextLine();

                    switch (menu) {
                    case "1":
                        keluar = true;
                        break;
                    case "99":
                        break;
                    default:
                        keluar = true;
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
                    
                    System.out.print("Input menu : ");
                    menu = in.nextLine();

                    if (menu.equals("99")) {
                        break;

                    } 
                    else {
                        System.out.print("Masukkan N: ");
                        col = inint.nextInt();
                        System.out.println("Input Elemen Matriks: ");
                        minv = new Matrix(col, col);
                        minv.readMatrix();
                    }

                        switch (menu) {
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
                        menu = in.nextLine();

                        switch (menu) {
                            case "1":
                                keluar = true;
                                break;
                            case "99":
                                break;
                            default:
                                keluar = true;
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
                    menu = in.nextLine();

                    if (menu.equals("99")) {
                        break;
                    } 
                    else {
                        System.out.print("Masukkan ukuran matriks (nxn): ");
                        col = inint.nextInt();
                        System.out.println("Input Elemen Matriks: ");
                        minv = new Matrix(col, col);
                        minv.readMatrix();

                        System.out.println();
                        switch (menu) {
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
                    menu = in.nextLine();

                    switch (menu) {
                    case "1":
                        keluar = true;
                        break;
                    case "99":
                        break;
                    default:
                        keluar = true;
                        break;
                    }
                    break;

                case "4"://interpolasi polinom
                    dispExit();
                    menu = in.nextLine();

                    switch (menu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                        default:
                            keluar = true;
                            break;
                    }
                    break;

                case "5"://interpolasi bicubic
                    dispExit();
                    menu = in.nextLine();

                    switch (menu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                        default:
                            keluar = true;
                            break;
                    }
                    break;
                
                case "6"://regresi linier berganda
                    dispExit();
                    menu = in.nextLine();

                    switch (menu) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                        default:
                            keluar = true;
                            break;
                    }
                break;
                    
                case "7"://keluar
                    keluar = true;
                    break;
                default:
                    keluar = true;
                    break;
            }
        }
            System.out.println();
            System.out.println("TERIMA KASIH!");
    }
}