package org.songli.muledeployservice.util;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class MuleUtil {
   private volatile static MuleUtil muleUtil;
   private static String[] startCMD = null;
   private static String[] restartCMD;
   private static String[] stopCMD;
   private static String mulePath;
   
   private MuleUtil() {

   }

   public static MuleUtil getMuleUtil() {
      if (muleUtil == null) {
         synchronized (MuleUtil.class) {
            if (muleUtil == null) {
               muleUtil = new MuleUtil();
               initMuleUtil();
            }
         }
      }
      return muleUtil;
   }

   private static void initMuleUtil() {
      String currentOS = System.getProperty("os.name");
      ResourceFileReader reader = ResourceFileReader.getReader();
      ClassLoader loader = MuleUtil.class.getClassLoader();

      Properties properties = reader.getPropertiesFromFile(loader.getResource("config.properties")
            .toString());

      mulePath = properties.getProperty("mule_path");

      if (currentOS.toLowerCase().contains("linux") || currentOS.toLowerCase().contains("mac")) {
         startCMD = new String[] { "/bin/bash", "-c", mulePath + "/bin/mule" };
         stopCMD = new String[] { "/bin/bash", "-c", mulePath + "/bin/mule stop" };
         restartCMD = new String[] { "/bin/bash", "-c", mulePath + "/bin/mule restart" };
      } else {
         startCMD = new String[] { "cmd /c " + mulePath + "/bin/launcher.bat" };
         stopCMD = new String[] { "cmd /c " + mulePath + "/bin/launcher.bat" };
         restartCMD = new String[] { "cmd /c " + mulePath + "/bin/launcher.bat" };
      }
   }
   
   public boolean deployProjectFile(String fromFile) {
      try {
         // TODO
         FileUtils.copyDirectory(new File(fromFile), new File(mulePath + "/apps/"));
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   public String[] getStartCMD() {
      return startCMD;
   }

   public String[] getRestartCMD() {
      return restartCMD;
   }

   public String[] getStopCMD() {
      return stopCMD;
   }

}
