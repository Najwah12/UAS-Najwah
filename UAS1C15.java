import java.util.Scanner;
public class UAS1C15 {
    static Scanner input = new Scanner(System.in);
    static int jumlahTim15 = (68 % 3) + 4; 
    static String[] namaTim15 = new String[jumlahTim15];
    static int[][] skorTim15 = new int[jumlahTim15][2];
    static int[] totalSkor15 = new int[jumlahTim15];

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Input Data Skor Tim");
            System.out.println("2. Tampilkan Tabel Skor");
            System.out.println("3. Tentukan Juara");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            String pilihan = input.nextLine();

            if (pilihan.equals("1")) {
                inputDataSkor15();
            } else if (pilihan.equals("2")) {
                tampilkanTabelSkor15();
            } else if (pilihan.equals("3")) {
                if (dataBelumAda()) {
                    System.out.println("\nTidak ada data yang bisa ditampilkan.");
                } else {
                    tentukanJuara15();
                }
            } else if (pilihan.equals("4")) {
                System.out.println("\nTerima kasih telah menggunakan sistem turnamen!");
                break;
            } else {
                System.out.println("Pilihan tidak valid. Coba lagi!");
            }
        }
    }

    static boolean dataBelumAda() {
        return namaTim15[0] == null;
    }

    static void inputDataSkor15() {
        for (int i = 0; i < jumlahTim15; i++) {
            System.out.print("\nMasukkan nama tim ke-" + (i + 1) + ": ");
            namaTim15[i] = input.nextLine();
            for (int j = 0; j < 2; j++) {
                skorTim15[i][j] = masukkanSkor(namaTim15[i], j + 1);
            }
            totalSkor15[i] = skorTim15[i][0] + skorTim15[i][1];
        }
    }

    static int masukkanSkor(String namaTim15, int level) {
        while (true) {
            System.out.print("Masukkan skor " + namaTim15 + " untuk Level " + level + ": ");
            if (input.hasNextInt()) {
                int skor = input.nextInt();
                input.nextLine();
                if (skor < 0) {
                    System.out.println("Skor tidak valid. Skor harus positif!");
                } else if (level == 1 && skor < 35) {
                    System.out.println("Skor Level 1 kurang dari 35, dianggap 0.");
                    return 0;
                } else {
                    return skor;
                }
            } else {
                System.out.println("Input tidak valid. Masukkan angka saja.");
                input.nextLine(); 
            }
        }
    }

    static void tampilkanTabelSkor15() {
        if (dataBelumAda()) {
            System.out.println("\nTidak ada data yang bisa ditampilkan.");
            return;
        }

        System.out.println("\nTabel Skor Turnamen");
        System.out.println("Nama Tim   Level 1   Level 2   Total Skor");
        System.out.println("------------------------------------------");
    
        for (int i = 0; i < jumlahTim15; i++) {
            String baris = formatKolom(namaTim15[i], 10) + 
                           formatKolom(String.valueOf(skorTim15[i][0]), 8) + 
                           formatKolom(String.valueOf(skorTim15[i][1]), 8) + 
                           formatKolom(String.valueOf(totalSkor15[i]), 12);
            System.out.println(baris);
        }
    }

    static String formatKolom(String teks, int lebar) {
        if (teks.length() > lebar) {
            return teks.substring(0, lebar - 1) + " ";
        }
        while (teks.length() < lebar) {
            teks += " "; 
        }
        return teks;
    }

    static void tentukanJuara15() {
        int maxSkor = -1;
        int juara = -1;
        boolean seri = false;

        for (int i = 0; i < jumlahTim15; i++) {
            if (totalSkor15[i] > maxSkor) {
                maxSkor = totalSkor15[i];
                juara = i;
                seri = false;
            } else if (totalSkor15[i] == maxSkor) {
                seri = true;
            }
        }

        if (seri) {
            System.out.println("\nTurnamen berakhir seri!");
        } else {
            System.out.println("\nSelamat kepada Tim " + namaTim15[juara] + " yang telah memenangkan kompetisi!");
        }
    }
}
