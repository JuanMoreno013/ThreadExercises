package task1;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriterFile implements Runnable {

    private final RandomAccessFile rdmFile;
    private final byte[] chunkData;
    private final int lengthData;

    public WriterFile(byte[] chunkData, int lengthData, RandomAccessFile rdmFile){
        this.chunkData = chunkData;
        this.lengthData = lengthData;

        this.rdmFile = rdmFile;
    }

    @Override

    public void run() {
        try {

            synchronized (rdmFile) {
                try {
                    rdmFile.write(chunkData, 0, lengthData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
