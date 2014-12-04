package org.songli.muledeployservice.context;

public class MuleContext {
   enum Status {
      RUNNING, STOP, STARTING, ERROR
   }

   private static volatile MuleContext context;
   private Status currentStatus;

   private MuleContext() {

   }

   public static MuleContext getContext() {
      if (context == null) {
         synchronized (MuleContext.class) {
            if (context == null) {
               context = new MuleContext();
            }
         }
      }
      return context;
   }

   public Status getCurrentStatus() {
      return currentStatus;
   }

   public void setCurrentStatus(Status currentStatus) {
      this.currentStatus = currentStatus;
   }

}
