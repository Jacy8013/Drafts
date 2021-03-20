package io.jacy.drafts.io;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Jacy
 */
public class FileIO {
    private static byte[] data = "qwertyuiop".getBytes();
    private static String filePath = "/tmp/jacy.io.out";

    private static void basicFileIO() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            while (true) {
                fos.write(data);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedFileIO() {
        try {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            while (true) {
                fos.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void randomAccessIO() {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "");
            raf.write("hello random access file.\n".getBytes());
            raf.write("hello jacy.".getBytes());
            System.out.println("========= hello ===========");
            System.in.read();

            raf.seek(4);
            raf.write("Insert Text".getBytes());
            System.out.println("========= seek ============");
            System.in.read();

            FileChannel fc = raf.getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 3, data.length);
            mbb.put(data);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
