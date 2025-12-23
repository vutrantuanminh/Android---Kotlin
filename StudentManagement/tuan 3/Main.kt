import kotlin.math.abs
// Hàm tính UCLN (GCD) thủ công
fun gcd(a: Int, b: Int): Int {
    return if (b == 0) kotlin.math.abs(a) else gcd(b, a % b)
}

class Fraction(var tuso: Int, var mauso: Int) {
    init {
        if (mauso == 0) {
            throw IllegalArgumentException("Mau so khong duoc bang 0")
        }
        // Chuẩn hóa dấu: nếu mauso âm, chuyển dấu sang tuso
        if (mauso < 0) {
            tuso = -tuso
            mauso = -mauso
        }
    }

    // Phương thức nhập phân số từ bàn phím
    fun nhap() {
        do {
            print("Nhap tu so (khong bang 0): ")
            tuso = readln().toInt()
            print("Nhap mau so (khong bang 0): ")
            mauso = readln().toInt()
            if (tuso == 0 || mauso == 0) {
                println("Tu so hoac mau so bang 0, vui long nhap lai!")
            }
        } while (tuso == 0 || mauso == 0)
        
        // Chuẩn hóa dấu
        if (mauso < 0) {
            tuso = -tuso
            mauso = -mauso
        }
    }

    // Phương thức in phân số
    fun inPhanSo() {
        print("$tuso/$mauso")
    }

    // Phương thức tối giản phân số
    fun toigian(): Fraction {
        val ucln = gcd(abs(tuso), abs(mauso))
        tuso /= ucln
        mauso /= ucln
        return this
    }

    // Phương thức so sánh với phân số khác
    fun sosanh(other: Fraction): Int {
        val giaTri1 = tuso.toDouble() / mauso
        val giaTri2 = other.tuso.toDouble() / other.mauso
        return when {
            giaTri1 < giaTri2 -> -1
            giaTri1 > giaTri2 -> 1
            else -> 0
        }
    }

    // Phương thức tính tổng với một phân số khác
    fun tong(other: Fraction): Fraction {
        val newTuso = tuso * other.mauso + other.tuso * mauso
        val newMauso = mauso * other.mauso
        return Fraction(newTuso, newMauso).toigian()
    }
}

fun main() {
    // Nhập mảng phân số
    print("Nhap so luong phan so trong mang: ")
    val n = readln().toInt()
    val mangPhanSo = Array(n) { Fraction(1, 1) }  // Khởi tạo tạm

    for (i in 0 until n) {
        println("Nhap phan so thu ${i + 1}:")
        mangPhanSo[i] = Fraction(0, 1)  // Tạm
        mangPhanSo[i].nhap()
    }

    // In mảng vừa nhập
    println("Mang phan so vua nhap:")
    for (ps in mangPhanSo) {
        ps.inPhanSo()
        print(" ")
    }
    println()

    // Tối giản các phần tử và in kết quả
    println("Mang sau khi toi gian:")
    for (ps in mangPhanSo) {
        ps.toigian().inPhanSo()
        print(" ")
    }
    println()

    // Tính tổng các phân số
    var tong = Fraction(0, 1)
    for (ps in mangPhanSo) {
        tong = tong.tong(ps)
    }
    print("Tong cac phan so: ")
    tong.inPhanSo()
    println()

    // Tìm phân số lớn nhất
    var max = mangPhanSo[0]
    for (i in 1 until n) {
        if (mangPhanSo[i].sosanh(max) > 0) {
            max = mangPhanSo[i]
        }
    }
    print("Phan so lon nhat: ")
    max.inPhanSo()
    println()

    // Sắp xếp mảng theo thứ tự giảm dần và in
    val mangSapXep = mangPhanSo.sortedWith { a, b -> b.sosanh(a) }  // Giảm dần: so sánh b với a
    println("Mang sap xep giam dan:")
    for (ps in mangSapXep) {
        ps.inPhanSo()
        print(" ")
    }
    println()
}