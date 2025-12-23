# 🚀 HƯỚNG DẪN SỬ DỤNG NHANH

## ✅ Đã hoàn thành setup!

File APK đã được build thành công tại:
```
app/build/outputs/apk/debug/app-debug.apk (5.5MB)
```

---

## 📱 CÁCH 1: Sử dụng Script Tự Động (KHUYẾN NGHỊ)

### Chạy script tiện lợi:
```bash
cd /home/oc/Downloads/android/StudentManagement
./build-and-install.sh
```

Script sẽ hiển thị menu với các tùy chọn:
1. **Build APK** - Tạo file cài đặt mới
2. **Cài đặt lên thiết bị** - Cài APK đã build
3. **Build và cài đặt luôn** - Làm cả 2 bước trên
4. **Xem log** - Debug ứng dụng
5. **Gỡ cài đặt** - Xóa app khỏi thiết bị

---

## 📱 CÁCH 2: Cài Đặt Thủ Công

### Bước 1: Kết nối điện thoại Android
1. Bật **Developer Options** (Tùy chọn nhà phát triển)
   - Vào Settings → About Phone
   - Nhấn 7 lần vào "Build Number"
2. Bật **USB Debugging**
   - Settings → Developer Options → USB Debugging
3. Kết nối điện thoại với máy tính qua USB
4. Chấp nhận kết nối debugging trên điện thoại

### Bước 2: Kiểm tra kết nối
```bash
export ANDROID_HOME=~/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
adb devices
```

Bạn sẽ thấy:
```
List of devices attached
XXXXXXXX    device
```

### Bước 3: Cài đặt APK
```bash
cd /home/oc/Downloads/android/StudentManagement
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Bước 4: Mở ứng dụng
Tìm app **"Quản Lý Sinh Viên"** trên điện thoại và mở

---

## 📱 CÁCH 3: Copy APK Thủ Công

### Bước 1: Copy file APK
```bash
# Copy APK vào thư mục Downloads của điện thoại
adb push app/build/outputs/apk/debug/app-debug.apk /sdcard/Download/
```

### Bước 2: Cài đặt trên điện thoại
1. Mở **Files** hoặc **My Files** trên điện thoại
2. Vào thư mục **Downloads**
3. Nhấn vào file **app-debug.apk**
4. Cho phép cài đặt từ nguồn không xác định (nếu được hỏi)
5. Nhấn **Install**

---

## 🔧 Build Lại APK (Khi Có Thay Đổi Code)

```bash
cd /home/oc/Downloads/android/StudentManagement
export ANDROID_HOME=~/Android/Sdk
./gradlew assembleDebug
```

APK mới sẽ được tạo tại: `app/build/outputs/apk/debug/app-debug.apk`

---

## 🎯 Các Lệnh Hữu Ích

### Build APK
```bash
./gradlew assembleDebug
```

### Build và cài đặt
```bash
./gradlew installDebug
```

### Xem danh sách thiết bị
```bash
adb devices
```

### Cài đặt APK
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Gỡ cài đặt
```bash
adb uninstall com.example.studentmanagement
```

### Xem log
```bash
adb logcat | grep StudentManagement
```

### Chụp màn hình từ điện thoại
```bash
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png .
```

---

## ❓ Khắc Phục Sự Cố

### Lỗi: "adb: command not found"
```bash
export ANDROID_HOME=~/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

### Lỗi: "no devices/emulators found"
- Kiểm tra USB Debugging đã bật
- Thử cáp USB khác
- Chạy: `adb kill-server && adb start-server`

### Lỗi: "INSTALL_FAILED_UPDATE_INCOMPATIBLE"
```bash
# Gỡ phiên bản cũ trước
adb uninstall com.example.studentmanagement
# Rồi cài lại
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Build lại từ đầu
```bash
./gradlew clean
./gradlew assembleDebug
```

---

## 📚 Cấu Trúc Project

```
StudentManagement/
├── app/
│   ├── build/outputs/apk/debug/
│   │   └── app-debug.apk          ← File APK để cài đặt
│   ├── src/main/
│   │   ├── java/                  ← Code Kotlin
│   │   ├── res/                   ← Resources (layouts, strings, etc)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build-and-install.sh           ← Script tiện lợi
├── gradlew                        ← Gradle wrapper
└── README.md
```

---

## 🎉 Hoàn Tất!

Bạn đã sẵn sàng sử dụng ứng dụng Quản Lý Sinh Viên!

**Cách nhanh nhất:**
```bash
./build-and-install.sh
# Chọn option 3 (Build và cài đặt luôn)
```
