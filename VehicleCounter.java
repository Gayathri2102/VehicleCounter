/** */
package vehicle;

import java.util.ArrayList;
import java.util.Scanner;

/*
* The TrafficReporter interface is implemented using the VehicleCounter.
* The Scanner object takes input from the log derived from the traffic
* meter. This output is processed based on the time and speed between the
* wheels and oytput is given in an array list differentiating if the vehicle 
* is a car or light truck or if its a heavy truck.
* @author  Gayathri Balasubramanian
*/

public class VehicleCounter implements TrafficReporter {
	
 /*
 * Array list reads the value and find the distance between the 
 * vechile axle distance based on that finds the object(car/heavytruck)
 * and returns the value in an arraylist
 * 
 * The Array list consists of the scanned input of arraylist(time and speed
 * in milliseconds and km/hr respectively). Based on these units, the 
 * vehicle differentiation is found by calculating the distance between 
 * the axles.
 *
 * @param  log  is a Scanner object that is a stream of text
 *        containing the log extracted from a traffic meter.
 *        Each entry in the log (one per line) denotes
 *        a vehicle axle triggering the meter.  Each
 *        entry consists of 2 integers: time (in ms) 
 *        and speed (in km/h)
 * @return an ArrayList of Strings.  Each string is of the form:
 *        "T: Light car or truck"
 *        or
 *        "T: Heavy truck, front rear"
 *        where T is the time (in seconds) where the front axle was sensed
 *        front is the number of axles in the front of the truck (>=1)
 *        rear is the number of axles in the rear of the truck (>= 1) 
 */
  @Override
  public ArrayList computeTraffic(Scanner log) {
    ArrayList list = new ArrayList();
    if (!log.hasNextLong()) {
      // If input is empty returns the list of value as empty output
      return list;
    }
    long prevTime = log.nextLong(), currTime;
    float prevSpeed = ((float) log.nextInt()) / 36, currSpeed;
    int newTime = Math.round(prevTime / 1000), front = 0, rear = 0;
    float distance_;
    char flag = 'C'; //T-truck, C-car

    /*
     * The given input time and speed are converted from milliseconds 
     * to seconds and kilometers per hour to meters per seconds
     * to bring all the components to same metric unit. The conditional
     * check for the front, rear axles of the cars and trucks are
     * done based on the distance criteria given between the 
     * axles where MIN_WHEELBASE = 186 cm. 
    */
    while (log.hasNextLong()) {
      currTime = log.nextLong();
      currSpeed = ((float) log.nextInt()) / 36;
      //Calculating distance between the 2 consecutive axles using the time and speed
      distance_ = (currTime - prevTime) * (currSpeed + prevSpeed) / 2;
      System.out.println("d:" + distance_);
      switch (flag) {
        /*calculating the no of axles in front and rear and 
         * the type of vehicle(car or truck)
         */
        case 'C':
          if (distance_ >= MIN_WHEELBASE && front == 0){
			  front = 1;
		  } else if (distance_ >= MIN_WHEELBASE && front == 1) {
            list.add(newTime + ": Car or light truck");
            newTime = Math.round(currTime / 1000);
            front = 0;
            rear = 0;
          } else if (distance_ < MIN_WHEELBASE) {
            flag = 'T';
            if (front == 0){
				front += 2;
            }else{
				rear += 2;
			}
          }
          break;
        case 'T':
          if (distance_ >= MIN_WHEELBASE && rear != 0) {
            list.add(newTime + ": Heavy truck, " + front + " " + rear);
            newTime = Math.round(currTime / 1000);
            flag = 'C';
            front = 0;
            rear = 0;
          } else if (distance_ >= MIN_WHEELBASE && rear == 0){
			  rear++;
          }else if (distance_ < MIN_WHEELBASE && rear != 0){
			  rear++;
          }else if (distance_ < MIN_WHEELBASE && rear == 0){
			  front++;
		  }
          break;
      }
      prevSpeed = currSpeed;
      prevTime = currTime;
    }
    if (flag == 'C'){
		list.add(newTime + ": Car or light truck");
    }else {
		list.add(newTime + ": Heavy truck, " + front + " " + rear);
	}
    return list;
  }
}
