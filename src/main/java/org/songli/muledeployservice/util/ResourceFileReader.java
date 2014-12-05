package org.songli.muledeployservice.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ResourceFileReader {
   private static ResourceFileReader reader = new ResourceFileReader();
   private Properties properties;
   private FileInputStream inputStream;

   private ResourceFileReader() {

   }

   public static ResourceFileReader getReader() {
      return reader;
   }
   
   public Properties getPropertiesFromFile(String filePath) {
      try {
         inputStream = new FileInputStream(filePath);
         properties.load(inputStream);
         inputStream.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      return properties;
   }
}
