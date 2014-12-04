package org.songli.muledeployservice.service;

import java.rmi.Remote;

public interface DeployServiceInterface extends Remote {
   
   public boolean deploy();
   
   public boolean startMule();
   
   public boolean stopMule();
   
   public boolean restartMule();
   
   public boolean startListenConnectorTransfer();
   
   public boolean stopListenConnectorTransfer();
}
