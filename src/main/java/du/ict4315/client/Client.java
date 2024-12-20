/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du.ict4315.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author student
 */
public class Client {

    public static final String[][] COMMANDS = new String[][]{
        {"Register Customer", "CUSTOMER", "First Name", "Last Name", "Phone number", "Address 1", "Address 2", "City", "State", "Zipcode"},
        {"Register Vehicle", "CAR", "COMPACT/SUV", "License", "Customer Id"},
//        {"Start Parking", "PARK", "Permit Id", "Time"},
        {"Finish Parking", "PARK", "Parking lot Id", "Permit Id", "Time in", "Time out"},
        {"Get Charges", "CHARGES", "Customer Id", "Permit Id"},};

    private static final int PORT = 7777;
    private static final String SERVER = "localhost";

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @SuppressWarnings("finally")
	public static String runCommand(JSONObject data)
            throws IOException {
        final long startTime = System.currentTimeMillis();
    
        InetAddress host = InetAddress.getByName(SERVER);
        String jsonResult = "";
        try (Socket link = new Socket(host, PORT)) {

            // Sending requestData object to server
           // RequestData requestData = new RequestData(data.getString("command"), data);
           JSONObject requestData = data;
            ObjectOutputStream out = new ObjectOutputStream(link.getOutputStream());
            //System.out.println("data being transmitted: " + requestData);
            out.writeObject(new JSONArray().put(requestData).toString());
            out.flush();

            // connect to server
            System.out.println("You are now connected to: " + host.getHostAddress());

            // Getting responseData object via server
            ObjectInputStream is = new ObjectInputStream(link.getInputStream());
            ResponseData responseData = (ResponseData) is.readObject();
            jsonResult = gson.toJson(responseData);
            System.out.println(jsonResult);
            
            // Close socket
            link.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        } finally {
            final long endTime = System.currentTimeMillis();

            System.out.println("Total Client execution time: " + (endTime - startTime));
            return jsonResult;
        }
    }

    public static Map<String, Command> commands() {
        Map<String, Command> commands = new HashMap<>();
        for (String[] description : COMMANDS) {
            commands.put(description[1], new Command(description[0], description[1],
                    Arrays.asList(description).subList(2, description.length)));
        }
        return commands;
    }

    /**
     * Run this as: $ java ict4300.week8.client.Client COMMAND label1=value1
     * label2=value2 ... Use LIST to get the list of commands and their labels.
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0 || args[0].equals("LIST")) {
            System.out.println("Here are the commands we know about.");
            System.out.println(
                    "Usage: $ java ict4300.week8.client.Client COMMAND label1=value1 label2=value2 ...");
            System.out.println();
            for (String[] description : COMMANDS) {
                System.out.format("%s: %s ", description[0], description[1]);
                for (int i = 2; i < description.length; ++i) {
                    System.out.format("%s=value ", description[i].replaceAll(" ", "").toLowerCase());
                }
                System.out.println();
            }
            return;
        }

        Command command = commands().get(args[0]);
        if (command == null) {
            System.out.println("Unrecognised command: " + args[0]);
            System.out.print("Known commands: ");
            String comma = "";
            for (String[] description : COMMANDS) {
                System.out.print(comma + description[1]);
                comma = ", ";
            }
            System.out.println();
            return;
        }
        Map<String, String> values = new LinkedHashMap<>();
        //Map<String,String> jsonMap = new HashMap<String,String>();
        values.put("command", command.command());
        //JSONObject jsonObject = new JSONObject();
        for (String label : command.fieldNames()) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i].startsWith(label.replaceAll(" ", "").toLowerCase())) {
                    values.put(label, args[i].replaceAll(".*=", ""));
                    break;
                }
            }
        }
        System.out.println("values: " + values);
        //this seems to actually create a json array, need to check more into it.
        JSONObject json = new JSONObject(values);
        System.out.println("JSON Object " + json);
       
        String result = runCommand(json);
        //jsonMap.put(values, new JSONObject(values).);
    }
}
