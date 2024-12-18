import java.util.Scanner;

public class UAS1C15 { 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int jumlahTim = (68 % 3) + 4;
        String[] namaTim15 = new String[jumlahTim];
        int[][] skorTim15 = new int[jumlahTim][2];
        int[] totalSkor = new int[jumlahTim];

        while (true) {
            System.out.println("\n================================");
            System.out.println("           MENU UTAMA           ");
            System.out.println("================================");
            System.out.println("1. Input Data Skor Tim");
            System.out.println("2. Tampilkan Tabel Skor");
            System.out.println("3. Tentukan Juara");
            System.out.println("4. Keluar");

            String pilihan;

            while (true) {
                System.out.print("\nPilih menu (1-4): ");
                pilihan = input.nextLine();

                if (pilihan.equals("1") || pilihan.equals("2") || pilihan.equals("3") || pilihan.equals("4")) {
                    break;
                } else {
                    System.out.println("Pilihan menu tidak valid. Coba lagi!");
                }
            }

            if (pilihan.equals("1")) {
                inputDataSkor(input, namaTim15, skorTim15, totalSkor, jumlahTim);
            } else if (pilihan.equals("2")) {
                tampilkanTabelSkor(namaTim15, skorTim15, totalSkor, jumlahTim);
            } else if (pilihan.equals("3")) {
                tentukanJuara(namaTim15, totalSkor, skorTim15, jumlahTim);
            } else if (pilihan.equals("4")) {
                System.out.println("\nTerima kasih telah menggunakan sistem turnamen!");
                break;
            }
        }
    }

    public static void inputDataSkor(Scanner input, String[] namaTim15, int[][] skorTim15, int[] totalSkor, int jumlahTim) {
        for (int i = 0; i < jumlahTim; i++) {
            System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
            namaTim15[i] = input.nextLine();

            for (int j = 0; j < 2; j++) { 
                while (true) {
                    System.out.print("Masukkan skor Level " + (j + 1) + " untuk " + namaTim15[i] + ": ");
                    int skor = input.nextInt();
                    if (skor < 0) {
                        System.out.println("Skor tidak valid. Skor harus bilangan positif!");
                    } else if (j == 0 && skor < 35) {
                        System.out.println("Skor Level 1 kurang dari 35, dianggap 0.");
                        skor = 0;
                        skorTim15[i][j] = skor;
                        break;
                    } else {
                        skorTim15[i][j] = skor;
                        break;
                    }
                }
            }
            input.nextLine(); 
            totalSkor[i] = skorTim15[i][0] + skorTim15[i][1];
        }
    }

    public static void tampilkanTabelSkor(String[] namaTim15, int[][] skorTim15, int[] totalSkor, int jumlahTim) {
        System.out.println("\nTabel Skor Turnamen");
        System.out.printf("%-10s%-10s%-10s%-10s\n", "Nama Tim", "Level 1", "Level 2", "Total Skor");
        for (int i = 0; i < jumlahTim; i++) {
            if (totalSkor[i] % 2 == 0) totalSkor[i] -= 15;
            if (skorTim15[i][0] > 50 && skorTim15[i][1] > 50) totalSkor[i] += 13;
            System.out.printf("%-10s%-10d%-10d%-10d\n", namaTim15[i], skorTim15[i][0], skorTim15[i][1], totalSkor[i]);
        }
    }

    public static void tentukanJuara(String[] namaTim15, int[] totalSkor, int[][] skorTim15, int jumlahTim) {
        int maxSkor = -1;
        int juaraIndex = -1;
        boolean seri = false;

        for (int i = 0; i < jumlahTim; i++) {
            if (totalSkor[i] > maxSkor) {
                maxSkor = totalSkor[i];
                juaraIndex = i;
                seri = false;
            } else if (totalSkor[i] == maxSkor) {
                if (skorTim15[i][1] > skorTim15[juaraIndex][1]) {
                    juaraIndex = i;
                    seri = false;
                } else if (skorTim15[i][1] == skorTim15[juaraIndex][1]) {
                    seri = true;
                }
            }
        }

        if (seri) {
            System.out.println("\nTurnamen berakhir seri. Tim terbaik adalah [Nama Anda]");
        } else {
            System.out.println("\nSelamat kepada Tim " + namaTim15[juaraIndex] + " yang telah memenangkan kompetisi!");
        }
    }
}
