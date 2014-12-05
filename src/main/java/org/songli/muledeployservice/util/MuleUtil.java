package org.songli.muledeployservice.util;

import java.util.Properties;

public class MuleUtil {
   private volatile static MuleUtil muleUtil;
   private static String[] startCMD = null;
   private static String[] restartCMD;
   private static String[] stopCMD;

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

      String mulePath = properties.getProperty("mule_path");

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
