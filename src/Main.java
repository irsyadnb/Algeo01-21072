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
            String inputmenu, submenu, inputexit, resultds;
            int row, col, size;
            Matrix m, mspl, minv, mdet, result;
            double resultd, x, y;
            IOFile f;

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

                    if (inputmenu.equals("99")) { //soalnya kalo pake == gajelas, audah knapa
                        break;
                    } 
                    else {
                        System.out.println("Pilih cara baca : ");
                        System.out.println("1. Baca file : ");
                        System.out.println("2. Baca input keyboard : ");
                        int type = inint.nextInt();
                        if(type == 1){
                            System.out.println("Nama File : ");
                            String pathfile = in.nextLine();
                            f = new IOFile(pathfile);
                            mspl = IOFile.bacaM(pathfile);
                        }
                        else{
                            System.out.print("Baris: ");
                            row = inint.nextInt();
                            System.out.print("Kolom: ");
                            col = inint.nextInt();
                            mspl = new Matrix(row, col);
                            System.out.println("Input elemen matriks: ");
                            mspl.readMatrix();
                        }
                        switch (inputmenu) {
                            case "1":
                                result = SPL.ubahEselon(mspl);
                                resultds = SPL.solusiSPL(result);
                                System.out.println(resultds);
                                IOFile.simpanFile("hasil", resultds);
                                break;
                            case "2":
                                result = SPL.ubahEselonReduksi(mspl);
                                resultds = SPL.solusiSPL(result);
                                System.out.println(resultds);
                                IOFile.simpanFile("hasil", resultds);
                                break;
                            case "3":
                                result = new Matrix(mspl.getRow(),1);
                                result = SPL.inverse(mspl);
                                resultds = SPL.SolusiInversCramer(result);
                                System.out.println(resultds);
                                IOFile.simpanFile("hasil", resultds);
                                break;

                            case "4":
                                result = SPL.Cramer(mspl);
                                resultds = SPL.SolusiInversCramer(result);
                                System.out.println(resultds);
                                IOFile.simpanFile("hasil", resultds);
                                break;
                        }
                        dispExit();
                        inputexit = in.nextLine();

                        switch (inputexit) {
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
                        System.out.println("Pilih cara baca : ");
                        System.out.println("1. Baca file : ");
                        System.out.println("2. Baca input keyboard : ");
                        int type = inint.nextInt();
                        if(type == 1){
                            System.out.println("Nama File : ");
                            String pathfile = in.nextLine();
                            f = new IOFile(pathfile);
                            mdet = IOFile.bacaM(pathfile);
                        }
                        else{
                        System.out.print("Masukkan ukuran matriks (nxn): ");
                        col = inint.nextInt();
                        mdet = new Matrix(col, col);
                        System.out.println("Input Elemen Matriks: ");
                        mdet.readMatrix();
                        }
                    
                    switch (submenu) {
                        case "1":
                            resultd = Determinan.detKofaktor(mdet);
                            System.out.println("Determinan Kofaktor = " + resultd);
                            resultds = Double.toString(resultd);
                            IOFile.simpanFile("hasil", "Determinan Kofaktor = " + resultds);
                            break;
                        case "2":
                            resultd = Determinan.detRedBar(mdet);
                            System.out.println("Determinan Reduksi = " + resultd);
                            resultds = Double.toString(resultd);
                            IOFile.simpanFile("hasil", "Determinan Reduksi = " + resultds);
                            break;
                        }  
                    }
                    dispExit();
                    inputexit = in.nextLine();
                    switch (inputexit) {
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
                        System.out.println("Pilih cara baca : ");
                        System.out.println("1. Baca file : ");
                        System.out.println("2. Baca input keyboard : ");
                        int type = inint.nextInt();
                        if(type == 1){
                            System.out.println("Nama File : ");
                            String pathfile = in.nextLine();
                            f = new IOFile(pathfile);
                            minv = IOFile.bacaM(pathfile);
                        }
                        else{
                        System.out.print("Masukkan ukuran matriks (nxn): ");
                        size = inint.nextInt();
                        minv = new Matrix(size, size);
                        System.out.println("Input Elemen Matriks: ");
                        minv.readMatrix();
                        }
                        System.out.println();
                        switch (inputmenu) {
                            case "1":
                                resultd = Determinan.detKofaktor(minv);
                                if (resultd == 0) {
                                    System.out.println("Determinan = 0, tidak ada matriks balikan");
                                    IOFile.simpanFile("hasil", "Determinan = 0, tidak ada matriks balikan");
                                } 
                                else {
                                    result = Invers.InversGaussJordan(minv);
                                    System.out.println("Hasil matriks balikan (gauss-jordan) : ");
                                    result.displayMatrix();
                                    IOFile.simpanMatrix("hasil", result);
                                }
                                break;
                            case "2":
                                resultd = Determinan.detKofaktor(minv);
                                if (resultd == 0) {
                                    System.out.println("Determinan = 0, tidak ada matriks balikan");
                                    IOFile.simpanFile("hasil", "Determinan = 0, tidak ada matriks balikan");
                                } 
                                else {
                                    result = Invers.adjInv(minv);
                                    System.out.println("Hasil matriks balikan (adjoint) : ");
                                    result.displayMatrix();
                                    IOFile.simpanMatrix("hasil", result);
                                }
                                break;
                        }
                    }
                    dispExit();
                    inputexit = in.nextLine();

                    switch (inputexit) {
                        case "1":
                            keluar = true;
                            break;
                        case "99":
                            break;
                    }
                    break;

                case "4"://interpolasi polinom
                    System.out.println("************Interpolasi Polinom************");
                    System.out.println("Pilih cara baca : ");
                    System.out.println("1. Baca file : ");
                    System.out.println("2. Baca input keyboard : ");
                    int type = inint.nextInt();
                    if(type == 1){
                        System.out.println("Nama File : ");
                        String pathfile = in.nextLine();
                        f = new IOFile(pathfile);
                        m = new Matrix(IOFile.readKol(pathfile),2);
                        m = IOFile.bacaM(pathfile);
                        System.out.println("Masukkan x :");
                        x = inint.nextDouble();
                    }
                    else{
                        System.out.print("Jumlah data : ");
                        col = inint.nextInt();
                        m = new Matrix(2, col);
                        System.out.println("Input data :  ");
                        m.readMatrix();

                        System.out.println("Masukkan x : ");
                        x = inint.nextDouble();
                    }
                    System.out.println(RegresiInterpolasi.InterpolasiPolinom(m, x));
                    IOFile.simpanFile("hasil", RegresiInterpolasi.InterpolasiPolinom(m, x));
                    
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
                    System.out.println("Pilih cara baca : ");
                    System.out.println("1. Baca file : ");
                    System.out.println("2. Baca input keyboard : ");
                    type = inint.nextInt();
                    if(type == 1){
                        System.out.println("Nama File : ");
                        String pathfile = in.nextLine();
                        f = new IOFile(pathfile);
                        m = IOFile.bacaM(pathfile);

                        System.out.println("Masukkan titik x :");
                        x = inint.nextDouble();
                        System.out.println("Masukkan titik y :");
                        y = inint.nextDouble();
                    }
                    else{
                    System.out.print("Baris: ");
                    row = inint.nextInt();
                    System.out.print("Kolom: ");
                    col = inint.nextInt();
                    m = new Matrix(row, col);
                    System.out.println("Input elemen matriks: ");
                    m.readMatrix();

                    System.out.println("Masukkan titik x : ");
                    x = inint.nextDouble();
                    System.out.println("Masukkan titik y : ");
                    y = inint.nextDouble();
                    }
                    System.out.println(RegresiInterpolasi.InterpolasiBikubik(m, x, y));
                    IOFile.simpanFile("hasil", RegresiInterpolasi.InterpolasiBikubik(m, x, y));

                    dispExit();
                    inputexit = in.nextLine();

                    switch (inputexit) {
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
                    inputexit = in.nextLine();

                    switch (inputexit) {
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
