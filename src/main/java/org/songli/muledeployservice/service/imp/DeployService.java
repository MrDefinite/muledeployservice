package org.songli.muledeployservice.service.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.songli.muledeployservice.service.DeployServiceInterface;
import org.songli.muledeployservice.util.MuleUtil;

public class DeployService extends UnicastRemoteObject implements DeployServiceInterface {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private MuleUtil muleUtil = MuleUtil.getMuleUtil();

   public DeployService() throws RemoteException {
      super();
   }

   @Override
   public boolean deploy() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean startMule() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean stopMule() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean restartMule() {
      // TODO Auto-generated method stub
      return false;
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

}
