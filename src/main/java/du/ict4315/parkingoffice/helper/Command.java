/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package du.ict4315.parkingoffice.helper;

/**
 *
 * @author Bobby Vocque
 */
interface  Command {
  
  public String getCommandName();
  public String getDisplayName();
  public Object perform(String parameters);
  
}
