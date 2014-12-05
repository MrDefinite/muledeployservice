package org.songli.muledeployservice.util;

import java.util.Properties;

public class FileTransferUtil implements Runnable {

   private String platformIP;
   private String fileTransferPort;
   private String fileOutPut;

   public FileTransferUtil() {
      ResourceFileReader reader = ResourceFileReader.getReader();
      ClassLoader loader = MuleUtil.class.getClassLoader();

      Properties properties = reader.getPropertiesFromFile(loader.getResource(
            "config.properties").toString());

      platformIP = properties.getProperty("platform_ip");
      fileTransferPort = properties.getProperty("platform_file_transfer_port");

   }

   @Override
   public void run() {
      // TODO Auto-generated method stub

   }
}
