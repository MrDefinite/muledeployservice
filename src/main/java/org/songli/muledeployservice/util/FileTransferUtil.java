package org.songli.muledeployservice.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.UUID;

public class FileTransferUtil implements Runnable {

   private String platformIP;
   private String fileTransferPort;
   private String fileOutPut;

   public FileTransferUtil(String originName) {
      ResourceFileReader reader = ResourceFileReader.getReader();
      ClassLoader loader = FileTransferUtil.class.getClassLoader();

      Properties properties = reader.getPropertiesFromFile(loader.getResource(
            "config.properties").toString());

      platformIP = properties.getProperty("platform_ip");
      fileTransferPort = properties.getProperty("platform_file_transfer_port");
      fileOutPut = loader.getResource("connectors/").toString()
            + packageNameGener(originName);
   }
   
   public String getFileLocation() {
      return fileOutPut;
   }

   @Override
   public void run() {
      byte[] aByte = new byte[1];
      int byteReader;
      Socket socket = null;
      InputStream inputStream = null;
      
      try {
         socket = new Socket(platformIP, Integer.parseInt(fileTransferPort));
         inputStream = socket.getInputStream();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      
      if (inputStream != null) {
         FileOutputStream fileOutputStream = null;
         BufferedOutputStream bufferedOutputStream = null;
         
         try {
            fileOutputStream = new FileOutputStream(fileOutPut);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byteReader = inputStream.read(aByte, 0, aByte.length);
            
            do {
               byteArrayOutputStream.write(aByte);
               byteReader = inputStream.read(aByte);
            } while (byteReader != -1);
            
            bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            socket.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   private String packageNameGener(String originName) {
      return originName + "_" + UUID.randomUUID();
   }

}
