package task1;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@MultipartConfig
@WebServlet("/dataFile")
public class DataFile extends HttpServlet {

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String typeFile = getFileExtension(fileName);


        try {
            InputStream is = filePart.getInputStream();
            byte[] buffer = new byte[1048];
            int n;
            int countBufferPosition = 0;
            int positionBuffer;

            ExecutorService pool = Executors.newCachedThreadPool();
            RandomAccessFile newFile = new RandomAccessFile(fileName, "rw");

            while ((n = is.read(buffer)) != -1) {

                positionBuffer = countBufferPosition * buffer.length;
                byte[] bufferClone = buffer.clone();
                WriterFile processFile = new WriterFile(bufferClone, n, newFile, positionBuffer);

                pool.execute(processFile);
                countBufferPosition++;

            }

            resp.getWriter().print(" File uploaded successfully!! ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
