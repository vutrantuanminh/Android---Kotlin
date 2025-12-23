#!/bin/bash

# Script tiện lợi để build và cài đặt ứng dụng Android

# Màu sắc cho output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}========================================${NC}"
echo -e "${BLUE}   Ứng Dụng Quản Lý Sinh Viên${NC}"
echo -e "${BLUE}========================================${NC}"
echo ""

# Set ANDROID_HOME
export ANDROID_HOME=~/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Menu
echo "Chọn thao tác:"
echo "1. Build APK (tạo file cài đặt)"
echo "2. Cài đặt lên thiết bị Android"
echo "3. Build và cài đặt luôn"
echo "4. Xem log của ứng dụng"
echo "5. Gỡ cài đặt ứng dụng"
echo ""
read -p "Nhập lựa chọn (1-5): " choice

case $choice in
    1)
        echo -e "${YELLOW}Đang build APK...${NC}"
        ./gradlew assembleDebug
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✓ Build thành công!${NC}"
            echo -e "File APK: ${BLUE}app/build/outputs/apk/debug/app-debug.apk${NC}"
            ls -lh app/build/outputs/apk/debug/app-debug.apk
        else
            echo -e "${RED}✗ Build thất bại!${NC}"
        fi
        ;;
    2)
        echo -e "${YELLOW}Đang cài đặt APK lên thiết bị...${NC}"
        adb devices
        echo ""
        adb install -r app/build/outputs/apk/debug/app-debug.apk
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✓ Cài đặt thành công!${NC}"
        else
            echo -e "${RED}✗ Cài đặt thất bại! Kiểm tra kết nối USB và USB Debugging${NC}"
        fi
        ;;
    3)
        echo -e "${YELLOW}Đang build APK...${NC}"
        ./gradlew assembleDebug
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✓ Build thành công!${NC}"
            echo -e "${YELLOW}Đang cài đặt lên thiết bị...${NC}"
            adb devices
            echo ""
            adb install -r app/build/outputs/apk/debug/app-debug.apk
            if [ $? -eq 0 ]; then
                echo -e "${GREEN}✓ Cài đặt thành công!${NC}"
                echo -e "${GREEN}Bạn có thể mở ứng dụng 'Quản Lý Sinh Viên' trên điện thoại${NC}"
            else
                echo -e "${RED}✗ Cài đặt thất bại!${NC}"
            fi
        else
            echo -e "${RED}✗ Build thất bại!${NC}"
        fi
        ;;
    4)
        echo -e "${YELLOW}Đang xem log...${NC}"
        echo "Nhấn Ctrl+C để thoát"
        adb logcat | grep -i "StudentManagement"
        ;;
    5)
        echo -e "${YELLOW}Đang gỡ cài đặt...${NC}"
        adb uninstall com.example.studentmanagement
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✓ Gỡ cài đặt thành công!${NC}"
        else
            echo -e "${RED}✗ Gỡ cài đặt thất bại!${NC}"
        fi
        ;;
    *)
        echo -e "${RED}Lựa chọn không hợp lệ!${NC}"
        ;;
esac

echo ""
echo -e "${BLUE}========================================${NC}"
