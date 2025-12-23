# Student Management App - Ứng Dụng Quản Lý Sinh Viên

Ứng dụng Android quản lý sinh viên được xây dựng với kiến trúc hiện đại sử dụng Fragment, Navigation Component, ViewModel, LiveData, và Data Binding.

## 🎯 Tính Năng

- ✅ Hiển thị danh sách sinh viên
- ✅ Thêm sinh viên mới
- ✅ Cập nhật thông tin sinh viên
- ✅ Xóa sinh viên
- ✅ Navigation giữa các màn hình

## 🏗️ Kiến Trúc

### Single Activity Architecture
- **MainActivity**: Host duy nhất chứa NavHostFragment
- **3 Fragments**: StudentListFragment, AddStudentFragment, EditStudentFragment

### Modern Android Components
- **Fragment**: Thay thế Activity cho từng màn hình
- **Navigation Component**: Điều hướng type-safe với Safe Args
- **ViewModel + LiveData**: Quản lý và chia sẻ dữ liệu
- **Data Binding**: Binding dữ liệu trực tiếp vào layout
- **Material Design**: UI components hiện đại

## 📋 Thông Tin Sinh Viên

Mỗi sinh viên bao gồm:
- MSSV (Mã số sinh viên)
- Họ tên
- Số điện thoại
- Địa chỉ

## 🛠️ Công Nghệ Sử Dụng

- **Language**: Kotlin
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Build Tool**: Gradle 8.5

### Dependencies
- Navigation Component 2.7.6
- ViewModel & LiveData 2.7.0
- Fragment KTX 1.6.2
- Material Components 1.11.0
- RecyclerView 1.3.2

## 📱 Cài Đặt

### Yêu Cầu
- Android Studio (latest version)
- Android SDK
- JDK 8 hoặc cao hơn

### Build & Run

```bash
# Clone repository
git clone <repository-url>
cd StudentManagement

# Build APK
./gradlew assembleDebug

# Install on device
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Hoặc sử dụng script tiện lợi

```bash
./build-and-install.sh
```

## 📂 Cấu Trúc Project

```
app/src/main/
├── java/com/example/studentmanagement/
│   ├── Student.kt                    # Data model
│   ├── StudentViewModel.kt           # ViewModel
│   ├── MainActivity.kt               # Host activity
│   ├── StudentListFragment.kt        # Danh sách
│   ├── AddStudentFragment.kt         # Thêm mới
│   ├── EditStudentFragment.kt        # Cập nhật
│   └── StudentAdapter.kt             # RecyclerView adapter
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── fragment_student_list.xml
│   │   ├── fragment_add_student.xml  # Data Binding
│   │   ├── fragment_edit_student.xml # Data Binding
│   │   └── item_student.xml
│   └── navigation/
│       └── nav_graph.xml             # Navigation graph
└── AndroidManifest.xml
```

## 🎨 Screenshots

### Danh Sách Sinh Viên
Hiển thị tất cả sinh viên với MSSV và họ tên

### Thêm Sinh Viên
Form nhập liệu với validation đầy đủ

### Cập Nhật Thông Tin
Chế độ View/Edit với khả năng cập nhật và xóa

## 🔄 Navigation Flow

```
StudentListFragment
    ↓
    ├─→ AddStudentFragment (FAB click)
    │       └─→ Back to List (after save)
    │
    └─→ EditStudentFragment (item click)
            └─→ Back to List (after update/delete)
```

## 💡 Điểm Nổi Bật

### Architecture
- ✅ Single Activity pattern
- ✅ ViewModel survives configuration changes
- ✅ LiveData reactive updates
- ✅ Type-safe navigation with Safe Args
- ✅ Data Binding reduces boilerplate

### Code Quality
- ✅ Separation of Concerns
- ✅ MVVM pattern
- ✅ ViewBinding for type-safe view access
- ✅ Parcelable for efficient data passing

## 📝 Phiên Bản

### Version 3.0 - Fragment Architecture (Current)
- Chuyển sang Single Activity với Fragments
- Thêm Navigation Component
- Implement ViewModel + LiveData
- Sử dụng Data Binding

### Version 2.0 - Multi-Activity
- 3 Activities riêng biệt
- Intent-based communication
- Option Menu

### Version 1.0 - Single Activity
- Tất cả chức năng trong 1 Activity
- RecyclerView adapter

## 🤝 Đóng Góp

Mọi đóng góp đều được chào đón! Hãy tạo Pull Request hoặc mở Issue.

## 📄 License

MIT License

## 👨‍💻 Tác Giả

[Tên của bạn]

## 📞 Liên Hệ

- Email: [email của bạn]
- GitHub: [github profile của bạn]

---

**Lưu ý**: Đây là project học tập về Android Development với kiến trúc hiện đại.
