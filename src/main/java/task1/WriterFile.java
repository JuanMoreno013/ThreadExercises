package task1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class WriterFile implements Runnable {

    private final RandomAccessFile rdmFile;
    private final byte[] chunkData;
    private final int lengthData;
    private final int positionBuffer;

    public WriterFile(byte[] chunkData, int lengthData, RandomAccessFile rdmFile, int positionBuffer) {
        this.chunkData = chunkData;
        this.lengthData = lengthData;
        this.rdmFile = rdmFile;
        this.positionBuffer = positionBuffer;
    }

    @Override

    public void run() {
        try {

            synchronized (rdmFile) {
                try {
                    rdmFile.seek(positionBuffer);
                    rdmFile.write(chunkData, 0, lengthData);

                    System.out.println(Thread.currentThread().getName());

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
