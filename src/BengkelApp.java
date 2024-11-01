import java.util.Scanner;

// Kelas Induk Pelanggan
class Pelanggan {
    private String nama;

    public Pelanggan(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}

// Kelas Induk Kendaraan
class Kendaraan {
    protected String jenis;
    protected String platNomor;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
    }

    public void inputData(Scanner scanner) {
        this.platNomor = scanner.nextLine();
    }

    public String getJenis() {
        return jenis;
    }

    public String getPlatNomor() {
        return platNomor;
    }
}

// Kelas Anak Motor
class Motor extends Kendaraan {
    public Motor() {
        super("Motor");
        System.out.println("Motor kakaknya beat karbu itu ya.");
    }
    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Plat nomor motor kakaknya apa kak? ");
        this.platNomor = scanner.nextLine();
    }

}

// Kelas Anak Mobil
class Mobil extends Kendaraan {
    public Mobil() {
        super("Mobil");
        System.out.println("Mobil kakaknya Pajero itu ya.");
    }
    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Plat nomor mobil kakaknya apa kak? ");
        this.platNomor = scanner.nextLine();
    }
}

// Kelas Induk Layanan
class Layanan {
    private String layanan1 = "";
    private String layanan2 = "";
    private String layanan3 = "";
    private String layanan4 = "";
    private int totalHarga = 0;

    public void tambahLayanan(Scanner scanner, Kendaraan kendaraan) {
        System.out.println("Daftar layanan untuk " + kendaraan.getJenis() + " di kami cuman ada ini kak:");
        if (kendaraan instanceof Motor) {
            System.out.println("1. Ganti Oli - Rp 50,000");
            System.out.println("2. Servis Rutin - Rp 100,000");
            System.out.println("3. Ganti Ban - Rp 80,000");
        } else if (kendaraan instanceof Mobil) {
            System.out.println("1. Ganti Oli - Rp 70,000");
            System.out.println("2. Servis Rutin - Rp 150,000");
            System.out.println("3. Ganti Ban - Rp 120,000");
            System.out.println("4. Pembersihan AC - Rp 90,000");
        }

        System.out.print("Pilih layanan yang diinginkan (pisahkan dengan koma, contoh: 1,2): ");
        String pilihan = scanner.nextLine();
        String[] pilihanLayanan = pilihan.split(",");

        for (String p : pilihanLayanan) {
            switch (p.trim()) {
                case "1":
                    layanan1 = "Ganti Oli";
                    totalHarga += kendaraan instanceof Motor ? 50000 : 70000;
                    break;
                case "2":
                    layanan2 = "Servis Rutin";
                    totalHarga += kendaraan instanceof Motor ? 100000 : 150000;
                    break;
                case "3":
                    layanan3 = "Ganti Ban";
                    totalHarga += kendaraan instanceof Motor ? 80000 : 120000;
                    break;
                case "4":
                    if (kendaraan instanceof Mobil) {
                        layanan4 = "Pembersihan AC";
                        totalHarga += 90000;
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid: " + p);
            }
        }
    }

    public String getLayanan1() {
        return layanan1.isEmpty() ? "" : layanan1;
    }

    public String getLayanan2() {
        return layanan2.isEmpty() ? "" : layanan2;
    }

    public String getLayanan3() {
        return layanan3.isEmpty() ? "" : layanan3;
    }

    public String getLayanan4() {
        return layanan3.isEmpty() ? "" : layanan4;
    }

    public int getTotalHarga() {
        return totalHarga;
    }
}

// Kelas Induk Riwayat
class Riwayat {
    private String namaPelanggan;
    private Kendaraan kendaraan;
    private Layanan layanan;

    public Riwayat(String namaPelanggan, Kendaraan kendaraan, Layanan layanan) {
        this.namaPelanggan = namaPelanggan;
        this.kendaraan = kendaraan;
        this.layanan = layanan;
    }

    public void tampilkanRiwayat() {
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Jenis Kendaraan: " + kendaraan.getJenis());
        System.out.println("Plat Nomor: " + kendaraan.getPlatNomor());
        System.out.println("Layanan yang Dipilih:");
        if (!layanan.getLayanan1().isEmpty()) System.out.println("1. " + layanan.getLayanan1());
        if (!layanan.getLayanan2().isEmpty()) System.out.println("2. " + layanan.getLayanan2());
        if (!layanan.getLayanan3().isEmpty()) System.out.println("3. " + layanan.getLayanan3());
        if (!layanan.getLayanan4().isEmpty()) System.out.println("4. " + layanan.getLayanan4());
        System.out.println("Total Harga: Rp " + layanan.getTotalHarga());
    }
}
public class BengkelApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======= Selamat Datang di Bengkel Young Pablo =======");

        // Input pelanggan
        System.out.print("Namanya siapa kak? ");
        String namaPelanggan = scanner.nextLine();

        System.out.print("Mau service motor atau mobil kak? ");
        String jenisKendaraan = scanner.nextLine();

        Kendaraan kendaraan;

        if (jenisKendaraan.equals("motor") || jenisKendaraan.equals("Motor")) {
            kendaraan = new Motor();
        } else if (jenisKendaraan.equals("mobil") || jenisKendaraan.equals("Mobil")) {
            kendaraan = new Mobil();
        } else {
            System.out.println("Gabisa Servicenya kita mas");
            return;
        }

        kendaraan.inputData(scanner);

        Layanan layanan = new Layanan();
        layanan.tambahLayanan(scanner, kendaraan);

        Riwayat riwayat = new Riwayat(namaPelanggan, kendaraan, layanan);

        System.out.println("\n=== Riwayat Pesanan ===");
        riwayat.tampilkanRiwayat();

        scanner.close();
    }
}
