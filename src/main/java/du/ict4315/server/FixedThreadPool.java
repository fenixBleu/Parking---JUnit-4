/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


/**
 *
 * @author vocqueb
 */
public class FixedThreadPool {
  
   private static ExecutorService executor = Executors.newFixedThreadPool(5);
    
    public static void main(String[] args) {
      
      int previousThreadCount = 3;
      System.out.println("Executor shutdown state = " + executor.isShutdown());
      
      
        
        while(!executor.isShutdown()){
          
          for (int i = 0; i < 51; i++) {
            
            if (i > 50){
              i = 0;
              previousThreadCount = Thread.activeCount() - 1;
              System.out.println("Number of Active Threads in Pool: " + previousThreadCount);
            }
          }
          
          
          
        }
        System.out.println("Finished all threads");
    }
    public void addWorker(Runnable worker){
      
      executor.execute(worker);
      
       
      
    }
    
}
  

