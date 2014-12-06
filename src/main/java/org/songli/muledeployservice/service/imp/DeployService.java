package org.songli.muledeployservice.service.imp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.songli.muledeployservice.context.MuleContext;
import org.songli.muledeployservice.service.DeployServiceInterface;
import org.songli.muledeployservice.util.FileTransferUtil;
import org.songli.muledeployservice.util.MuleUtil;

public class DeployService extends UnicastRemoteObject implements DeployServiceInterface {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private MuleUtil muleUtil = MuleUtil.getMuleUtil();
   private MuleContext context = MuleContext.getContext();

   public DeployService() throws RemoteException {
      super();
   }

   @Override
   public void deploy(String packageName) {
      String fileLocation = null;
      FileTransferUtil fileTransferUtil = new FileTransferUtil(packageName);
      Thread fileTransferThread = new Thread(fileTransferUtil);
      try {
         fileTransferThread.start();
         fileTransferThread.join();
         fileLocation = fileTransferUtil.getFileLocation();
         
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   @Override
   public boolean startMule() {
      if (executeCommand(muleUtil.getStartCMD()) == true) {
         context.setCurrentStatus(MuleContext.Status.RUNNING);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean stopMule() {
      if (executeCommand(muleUtil.getStopCMD()) == true) {
         context.setCurrentStatus(MuleContext.Status.STOP);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean restartMule() {
      if (executeCommand(muleUtil.getRestartCMD()) == true) {
         context.setCurrentStatus(MuleContext.Status.RUNNING);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean startListenConnectorTransfer() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean stopListenConnectorTransfer() {
      // TODO Auto-generated method stub
      return false;
   }

   private boolean executeCommand(String[] command) {
      try {
         Process process = Runtime.getRuntime().exec(command);
         process.waitFor();

         BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String line = "";
         while ((line = buf.readLine()) != null) {
            System.out.println(line);
         }
         return true;
      }
      catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

}
