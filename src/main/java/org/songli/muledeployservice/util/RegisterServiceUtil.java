package org.songli.muledeployservice.util;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.songli.muledeployservice.service.DeployServiceInterface;
import org.songli.muledeployservice.service.imp.DeployService;

public class RegisterServiceUtil {

   private DeployServiceInterface deployService;
   private String ip;
   private String port;
   private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
   
   public RegisterServiceUtil() {
      try {
         deployService = new DeployService();
         ResourceFileReader reader = ResourceFileReader.getReader();
         ClassLoader loader = MuleUtil.class.getClassLoader();
         Properties properties = reader.getPropertiesFromFile(loader.getResource("config.properties").toString());
         ip = properties.getProperty("ip");
         port = properties.getProperty("deploy_service_port");
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void initRegister() {
      try {
         LocateRegistry.createRegistry(Integer.parseInt(port));
         Naming.rebind("rmi://" + ip + "/deployservice", deployService);
         System.out.println("Service Start!");
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public void destroyRegister() {
      try {
         Naming.unbind("rmi://" + ip + "/deployservice");
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   @SuppressWarnings("unused")
   private boolean isIp(String ip) {
      Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
      Matcher matcher = pattern.matcher(ip);
      return matcher.matches();
   }
   
   @SuppressWarnings("unused")
   private boolean isPort(String port) {
      for (int i = 0; i < port.length(); i++) {
         if (port.charAt(i) < '0' || port.charAt(i) > '9') {
            return false;
         }
      }
      
      int intValue = Integer.parseInt(port);
      if (intValue < 0 || intValue > 65535) {
         return false;
      }
      return true;
   }
}
