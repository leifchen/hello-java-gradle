package com.chen.qrcode.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 *
 * @Author LeifChen
 * @Date 2018-11-27
 */
public class QrCodeUtils {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int LENGTH = 120;

    /**
     * 使用 ZXing 创建二维码
     *
     * @param format  二维码格式
     * @param content 二维码内容
     * @param file    二维码图片导出路径
     */
    public static void createQRCodeByZXing(String format, String content, Path file) {
        Map<EncodeHintType, Object> hint = new HashMap<>(4);
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hint.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hint);
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 QRCode 创建二维码
     *
     * @param format  二维码格式
     * @param content 二维码内容
     * @param file    二维码图片
     */
    public static void createQRCode(String format, String content, File file) {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');
        qrcode.setQrcodeVersion(10);
        int width = 67 + 12 * (10 - 1);
        int height = 67 + 12 * (10 - 1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.setColor(Color.BLACK);
        graphics.clearRect(0, 0, WIDTH, HEIGHT);

        // 偏移量
        int offset = 2;
        byte[] bytes = new byte[0];

        try {
            bytes = content.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (bytes.length > 0 && bytes.length < LENGTH) {
            boolean[][] s = qrcode.calQrcode(bytes);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        graphics.fillRect(j * 3 + offset, i * 3 + offset, 3, 3);
                    }
                }
            }
        }

        graphics.dispose();
        bufferedImage.flush();
        try {
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 QR-Code
     *
     * @param file 二维码图片
     * @return
     */
    public static Result readQRCode(File file) {
        MultiFormatReader formatReader = new MultiFormatReader();
        BufferedImage image;

        try {
            image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            Map<DecodeHintType, Object> hint = new HashMap<>(2);
            hint.put(DecodeHintType.CHARACTER_SET, "utf-8");

            return formatReader.decode(binaryBitmap, hint);
        } catch (IOException | NotFoundException e) {
            throw new IllegalArgumentException("解析" + file + "失败");
        }
    }
}
