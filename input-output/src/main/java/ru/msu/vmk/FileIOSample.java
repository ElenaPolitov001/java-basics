package ru.msu.vmk;

import java.io.FileInputStream;
import java.io.IOException;

public class FileIOSample {

    public static void main(String[] args) {
/*
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/projects/maven-sample-project/adams.txt");
            int b;
            while((b = inputStream.read()) != -1) {
                System.out.println((char)b);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
 */

        try (FileInputStream inputStream = new FileInputStream("/projects/maven-sample-project/adams.txt")) {
//            int b;
//            while ((b = inputStream.read()) != -1) {
//                System.out.println((byte)b);
//            }
            byte[] bytes = new byte[1024];
            while(true) {
                int n = inputStream.read(bytes);
                for (int i = 0; i<n; i++) {
                    System.out.println(bytes[i]);
                }
                if (n < 1024) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
