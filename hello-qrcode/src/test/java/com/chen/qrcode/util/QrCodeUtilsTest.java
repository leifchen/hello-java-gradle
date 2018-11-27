package com.chen.qrcode.util;

import com.google.zxing.Result;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

/**
 * 二维码工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-11-27
 */
public class QrCodeUtilsTest {

    private static final String format = "png";
    private static final String content = "https://github.com/leifchen";

    @Test
    public void createQRCodeByZXing() {
        Path file = new File("E:/zxing.png").toPath();
        QrCodeUtils.createQRCodeByZXing(format, content, file);
    }

    @Test
    public void createQRCode() {
        File file = new File("E:/qrcode.png");
        QrCodeUtils.createQRCode(format, content, file);
    }

    @Test
    public void readQRCode() {
        File file1 = new File("E:/zxing.png");
        Result result1 = QrCodeUtils.readQRCode(file1);
        System.out.println("----- ZXing二维码 -----");
        System.out.println("解析结果：" + result1.toString());
        System.out.println("二维码格式：" + result1.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result1.getText());

        File file2 = new File("E:/qrcode.png");
        Result result2 = QrCodeUtils.readQRCode(file2);
        System.out.println("----- QRCode二维码 -----");
        System.out.println("解析结果：" + result2.toString());
        System.out.println("二维码格式：" + result2.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result2.getText());

        File file3 = new File("E:/jquery-qrcode.png");
        Result result3 = QrCodeUtils.readQRCode(file3);
        System.out.println("----- JQuery-QRCode二维码 -----");
        System.out.println("解析结果：" + result3.toString());
        System.out.println("二维码格式：" + result3.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result3.getText());
    }
}