package org.songli.muledeployservice;

import org.songli.muledeployservice.util.RegisterServiceUtil;

/**
 * Entry of this service
 *
 */
public class App {
   public static void main(String[] args) {
      
      RegisterServiceUtil registerUtil = new RegisterServiceUtil();
      registerUtil.initRegister();
      
      
   }
}
