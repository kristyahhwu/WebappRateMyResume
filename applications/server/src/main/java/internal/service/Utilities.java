package internal.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.image.BufferedImage;
public class Utilities {
    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int) file.length();
        byte[] buffer = new byte[size];
        FileInputStream in = new FileInputStream(file);
        in.read(buffer);
        in.close();
        return buffer;
    }

    public static ObjectId upload(String filePath, String fileName, MongoDatabase imgDb) {
        ObjectId fileId = null;
        try {
            // Create a gridFSBucket
            GridFSBucket gridBucket = GridFSBuckets.create(imgDb);


            InputStream inStream = new FileInputStream(new File(filePath + "/" + fileName));

            // Create some customize options for the details that describes
            // the uploaded image
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("content_type", "image/png"));

            fileId = gridBucket.uploadFromStream(fileName, inStream, uploadOptions);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileId;
    }

    public static LocalDateTime extractTime(String time) {
        // expect the time format to be year-month-date-hour-minute
        String[] parts = time.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3]);
        int minute = Integer.parseInt(parts[4]);

        LocalDateTime currentTime = LocalDateTime.of(year, month, day, hour, minute);
        return currentTime;
    }

    public static byte[] convertPDFToByteArray(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[(int) file.length()];
        try {
            int readNum = fis.read(data);
            while (readNum != -1) {
                bos.write(data, 0, readNum);
                readNum = fis.read(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit((1));
        }
        data = bos.toByteArray();
        return data;
    }

    public static void convertByteArrayToImage(byte[] data, String filename) throws IOException {
        PDDocument document = PDDocument.load(data);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, String.format("%s-%d.%s", filename, i + 1, "jpg"), 300);
        }
        document.close();
    }

    public static void generateImages(String path) {
        byte[] byteArray = null;
        String filename = new File(path).getName();
        try {
            byteArray = convertPDFToByteArray(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            convertByteArrayToImage(byteArray, filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}