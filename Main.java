import java.util.ArrayList;
import java.util.Scanner;

// Parent Class
class Manusia {
    protected String nama;
    protected String nim;

    public Manusia(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }
}

// Child Class menggunakan Inheritance
class Mahasiswa extends Manusia {
    private int nilai;
    private String grade;
    private String status;

    public Mahasiswa(String nim, String nama, int nilai) {
        super(nim, nama);
        this.nilai = nilai;
        hitungGrade();
    }

    private void hitungGrade() {
        if (nilai >= 80 && nilai <= 100) {
            grade = "A";
            status = "Lulus";
        } else if (nilai >= 70) {
            grade = "B";
            status = "Lulus";
        } else if (nilai >= 60) {
            grade = "C";
            status = "Lulus";
        } else if (nilai >= 50) {
            grade = "D";
            status = "Tidak Lulus";
        } else if (nilai >= 0 && nilai < 50) {
            grade = "E";
            status = "Tidak Lulus";
        } else {
            grade = "Error";
            status = "Input nilai anda salah";
        }
    }

    public void tampilkanData() {
        System.out.println("NIM : " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade: " + grade);
        System.out.println("---------------------------------------");
    }

    public int getNilai() { return nilai; }
    public String getNama() { return nama; }
    public String getGrade() { return grade; }
    public String getStatus() { return status; }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
        
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < jumlah; i++) {
            System.out.println("Data ke-" + (i + 1));
            System.out.print("Masukkan NIM : ");
            String nim = scanner.nextLine();
            System.out.print("Masukkan NAMA: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan NILAI: ");
            int nilai = scanner.nextInt();
            scanner.nextLine(); 

            if (nilai < 0 || nilai > 100) {
                System.out.println("Input nilai anda salah");
            } else {
                listMahasiswa.add(new Mahasiswa(nim, nama, nilai));
            }
            System.out.println();
        }

        // Menampilkan Output
        double totalNilai = 0;
        int lulusCount = 0, tidakLulusCount = 0;
        int countA = 0, countB = 0, countD = 0;
        StringBuilder namaLulus = new StringBuilder();
        StringBuilder namaTidakLulus = new StringBuilder();
        StringBuilder namaA = new StringBuilder();
        StringBuilder namaB = new StringBuilder();
        StringBuilder namaD = new StringBuilder();

        for (Mahasiswa m : listMahasiswa) {
            m.tampilkanData();
            totalNilai += m.getNilai();
            
            if (m.getStatus().equals("Lulus")) {
                lulusCount++;
                namaLulus.append(m.getNama()).append(", ");
            } else {
                tidakLulusCount++;
                namaTidakLulus.append(m.getNama()).append(", ");
            }

            if (m.getGrade().equals("A")) {
                countA++;
                namaA.append(m.getNama()).append(", ");
            } else if (m.getGrade().equals("B")) {
                countB++;
                namaB.append(m.getNama()).append(", ");
            } else if (m.getGrade().equals("D")) {
                countD++;
                namaD.append(m.getNama()).append(", ");
            }
        }

        // Ringkasan Statistik
        System.out.println("Jumlah Mahasiswa : " + listMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulusCount + " yaitu " + cleanComma(namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulusCount + " yaitu " + cleanComma(namaTidakLulus));
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + countA + " yaitu " + cleanComma(namaA));
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + countB + " yaitu " + cleanComma(namaB));
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + countD + " yaitu " + cleanComma(namaD));
        
        if (listMahasiswa.size() > 0) {
            System.out.println("Rata-rata nilai mahasiswa adalah : " + (totalNilai / listMahasiswa.size()));
        }
    }

    // Fungsi pembantu untuk merapikan koma di akhir nama
    private static String cleanComma(StringBuilder sb) {
        String s = sb.toString().trim();
        return s.endsWith(",") ? s.substring(0, s.length() - 1) : s;
    }
}