package task1;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


import static org.junit.jupiter.api.Assertions.*;


class DataFileTest {

    static private HttpServletRequest request;
    static private HttpServletResponse response;

    static private final String nameFileImage = "ballFot.png";

    static private final String messageInside = " File uploaded successfully!! ";


    @BeforeAll
    public static void setUp() throws ServletException, IOException {

        File dataFile = new File(nameFileImage);


        File messageFile = new File("MessageFile.txt");

        FileOutputStream is = new FileOutputStream(messageFile);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer writerMessage = new BufferedWriter(osw);

        writerMessage.write(messageInside);
        writerMessage.close();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        Part filePart = mock(Part.class);

        when(request.getPart("file")).thenReturn(filePart);
        when(filePart.getSubmittedFileName()).thenReturn(nameFileImage);
        when(filePart.getInputStream()).thenReturn(new FileInputStream(dataFile));

        when(response.getWriter()).thenReturn(new PrintWriter(writerMessage));

        when(response.getStatus()).thenReturn(200);

        new DataFile().doPost(request, response);

    }


    @Disabled
    @Test
    @DisplayName("UploadFile, shows the current time of execution, when you mock")
    void uploadFile() throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        System.out.println("Initial time: " + startTime);

        Part filePart = mock(Part.class);
        File dataFile = new File(nameFileImage);

        when(request.getPart("file")).thenReturn(filePart);
        when(filePart.getSubmittedFileName()).thenReturn(nameFileImage);
        when(filePart.getInputStream()).thenReturn(new FileInputStream(dataFile));

        long finalTime = System.currentTimeMillis();
        System.out.println("Final time: " + finalTime);
        System.out.println("Time passed: " + (finalTime - startTime) / 1000d + "s");
    }

    @Test
    @DisplayName("File exists, shows true , when the new file does exists")
    void existFile() {
        File file = new File("new" + nameFileImage);
        assertTrue(file.exists());
        System.out.println("True, file: " + file.getName() + " exists ");
    }

    @Test
    @DisplayName("File does not exists , shows false , when the new file does not exists")
    void existFile2() {
        File file = new File("new234321" + nameFileImage);
        assertFalse(file.exists());

        System.out.println("False, file: " + file.getName() + " does NOT exists ");
    }

    @Test
    @DisplayName("Status , shows code of response , when upload success")
    void statusResponse() {
        int status = response.getStatus();
        System.out.println(status);

        assertEquals(200, status);
    }

    @Test
    @DisplayName("File Size, assert TRUE, when both files are equals")
    void checkSizeFiles() {

        boolean sizeFileEquals;

        try {

            File dataFile = new File(nameFileImage);
            File dataFile2 = new File("new" + nameFileImage);

            RandomAccessFile randomAccessFile1 = new RandomAccessFile(dataFile, "r");
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(dataFile2, "r");

            FileChannel ch1 = randomAccessFile1.getChannel();
            FileChannel ch2 = randomAccessFile2.getChannel();

            long size = ch1.size();
            MappedByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            MappedByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);

            sizeFileEquals = m1.equals(m2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(sizeFileEquals);
    }

    @Test
    @DisplayName("File Size 2, assert FALSE ,when both files are equals")
    void checkSizeFiles2() {

        boolean sizeFileEquals;

        try {

            File dataFile = new File(nameFileImage);
            File dataFile2 = new File("Balon.jpeg");

            RandomAccessFile randomAccessFile1 = new RandomAccessFile(dataFile, "r");
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(dataFile2, "r");

            FileChannel ch1 = randomAccessFile1.getChannel();
            FileChannel ch2 = randomAccessFile2.getChannel();

            long size = ch1.size();
            MappedByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
            MappedByteBuffer m2 = ch2.map(FileChannel.MapMode.READ_ONLY, 0L, size);

            sizeFileEquals = m1.equals(m2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertFalse(sizeFileEquals);
    }

    @Disabled
    @Test
    @DisplayName("Response , assert true, when the response its correct")
    void checkResponse() {

        try {
            File messageFile = new File("MessageFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(messageFile));
            String line;
            String message = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                message = line;
            }
//            when(response.getWriter()).thenReturn(message);
            System.out.println(response.getWriter().toString());


            assertEquals(response.getWriter().toString(), message);

//            response.getWriter().


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}