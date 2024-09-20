/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du.ict4315.server;


import du.ict4315.client.Command;
import du.ict4315.client.ResponseData;
import du.ict4315.customer.Address;
import du.ict4315.charges.factory.ParkingChargeFactory;
import du.ict4315.parkinglot.ParkingLot;

import du.ict4315.parkingoffice.ParkingOffice;
import du.ict4315.parkingoffice.service.ParkingService;
//import ict4315.parkingsystem.PermitManager;
//import ict4315.parkingsystem.TransactionManager;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author student
 */
public class Server {

    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
    }

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final int PORT = 7777;

    private final ParkingService service;

    public Server(ParkingService service) {
        this.service = service;
    }
    public void startServer() throws IOException, CloneNotSupportedException {
        //System.out.println(ClassName.class.getClassLoader().getResource("logging.properties"));
        logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());
        ClientHandler cHandler = new ClientHandler();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);
            FixedThreadPool pool = new FixedThreadPool(); 
            while (true) {
                Socket client = serverSocket.accept();
                ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                InputStream is = client.getInputStream();
                ClientHandler handler = cHandler.clone();
                handler.setParameters(service, os, is);
                pool.addWorker(handler);
                System.out.println("Number of threads: " + Thread.activeCount());
                //handleClient(client, os, is);
            }
        }
    }
    
    

    /**
     * Run this as: $ java ict4300.week8.server.Server
     */
    public static void main(String[] args) throws Exception {
//    ParkingOffice parkingOffice = new ParkingOffice("Office", new Address());

        Address parkingOfficeAddress = new Address("20 Joseph Street", "South Iris", "Bronx", "NY", "");
        //TransactionManager transactionManager = new TransactionManager();
        //rmitManager permitManager = new PermitManager();
        ParkingOffice parkingOffice = new ParkingOffice("Main Office", parkingOfficeAddress);//ansactionManager, permitManager);
         ParkingService service = new ParkingService(parkingOffice);

        //ParkingChargeFactory parkingChargeCalculatorFactory = new ParkingChargeFactory();

        //Create parking Lot A with discount
        Address parkingLotAddressA = new Address("214 CherryCreek", "Broomfield", "Bronx", "CO", "");
        ParkingLot parkingLotA = new ParkingLot("Lot A", parkingLotAddressA,150L, 100, true, false);
        System.out.println(String.format("Parking lot %s applies %s", parkingLotA.getLotId(), parkingLotA.getLotStrategy().getName()));

        //Create parking Lot B with standard 
        Address parkingLotAddressB = new Address("111 University Blvd", "Littelton", "Denver", "Co", "");
        ParkingLot parkingLotB = new ParkingLot("Lot B", parkingLotAddressB, 75L, 125, false, false);
        System.out.println(String.format("Parking lot %s applies %s",parkingLotB.getLotId(), parkingLotB.getLotStrategy().getName()));
        
        //Create parking Lot C with No Discount
        Address parkingLotAddressC = new Address("121 University Blvd", "Littelton", "Denver", "Co", "");
        //ParkingLot parkingLotC = new ParkingLot("3", "Lot C", parkingLotAddressC, parkingChargeCalculatorFactory);
        //System.out.println("Parking lot Lot C does not apply discount strategy");

        parkingOffice.addLot(parkingLotA);
        parkingOffice.addLot(parkingLotA);

        
        
        new Server(service).startServer();
    }
}
