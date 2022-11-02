package internal.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Utilities {
    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int)file.length();
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
}
