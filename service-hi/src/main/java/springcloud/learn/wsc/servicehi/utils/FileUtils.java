package springcloud.learn.wsc.servicehi.utils;

import java.io.*;

/**
 * @author wangshuaichao
 * @ClassName: FileUtils
 * @Decription TOO
 * @Date 2019/7/22 11:14
 **/
public class FileUtils {

    /**
     * @Author wangshuaichao
     * @Description 文件的读取（节点流FileInputStream读取字节流）
     **/
    public static void readFileByBytes(String fileName) {
        // 一般先创建file对象
        FileInputStream fileInput = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] buffer = new byte[1024];
            fileInput = new FileInputStream(file);
            int byteread = 0;
            // byteread表示一次读取到buffers中的数量。
            while ((byteread = fileInput.read(buffer)) != -1) {
                System.out.write(buffer, 0, byteread);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {

            try {
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /**
     * @Author wangshuaichao
     * @Description 文件读取（节点流FileReader读取字符流）
     **/
    public static void readFileByChars(String fileName) {
        FileReader reader = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new FileReader(file);
            char[] buffer = new char[1024];
            int charread = 0;
            while ((charread = reader.read(buffer)) != -1) {
                System.out.print(buffer);
            }
        } catch (IOException e) {
            // TODO: handle exception

        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * @Author wangshuaichao
     * @Description 文件的读取通过BufferedReader读取数据
     **/
    public static void readByBufferedReader(String fileName) {
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                System.out.println(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @Author wangshuaichao
     * @Description 向文件中写入数据（字节流FileOutputStream）
     **/
    public void writeByFileOutputStream() {

        FileOutputStream fop = null;
        File file;
        String content = "This is the text content";
        try {
            file = new File("c:/newfile.txt");
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author wangshuaichao
     * @Description 向文件中写数据（FileReader）
     * 【注意使用FileReader（“path”，true）可以往文件后面追加内容，否则就直接覆盖了】
     **/
    public static void writeByFileReader() {
        try {
            String data = " This content will append to the end of the file";

            File file = new File("javaio-appendfile.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            FileWriter fileWritter = new FileWriter(file.getName(), true);
            fileWritter.write(data);
            fileWritter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author wangshuaichao
     * @Description 采用BufferedWriter向文件中写数据
     **/
    public static void writeByBufferedReader() {
        try {

            String content = "This is the content to write into file";
            File file = new File("/users/mkyong/filename.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
