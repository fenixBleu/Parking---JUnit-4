/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.server;


import du.ict4315.client.ResponseData;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import du.ict4315.parkingoffice.service.ParkingService;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vocqueb
 */
public class ClientHandler implements Runnable, Cloneable {
    
    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
    }

    private static final Logger logger = Logger.getLogger(Server.class.getName());

   // private final int PORT = 7777;
    
    private ParkingService service;
    private ObjectOutputStream oStream;
    private InputStream inStream;
    
    @Override
    public void run(){
        
        final long startTime = System.currentTimeMillis();
        try {
            
            // Using os to return output to client
            //ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ResponseData output;
            try {
                // Process requestData from client and return output as responseData
                logger.log(Level.INFO, "entered server.handleClient");
                output = service.handleInput(inStream);
                //output = 
                    //service.handleInput(inStream);
                //System.out.println("data recieved: " + output);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
               output = new ResponseData(false, ex.getLocalizedMessage(), null);
            }

            oStream.writeObject(output);
            oStream.flush();

        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to read from client.", e);
        } finally {
          
            final long endTime = System.currentTimeMillis();
            System.out.println("Total Client execution time: " + (endTime - startTime) + "ms");
        }
    
        
        
        
    }
        public ClientHandler(ParkingService service, ObjectOutputStream oStream, InputStream inStream){
                
             this.service = service;
             this.oStream = oStream;
             this.inStream = inStream;
             
        }
        public ClientHandler(){
          
        }
        public void setParameters(ParkingService service, ObjectOutputStream oStream, InputStream inStream){
          
             this.service = service;
             this.oStream = oStream;
             this.inStream = inStream;
          
        }
        @Override
        public ClientHandler clone() throws CloneNotSupportedException {
          
          return (ClientHandler) super.clone();
        }
    }
        
       


    

