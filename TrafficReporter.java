/**
 * 
 */
package vehicle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author gayeb
 *
 */
public interface TrafficReporter {
  public static int MIN_WHEELBASE = 186; // centimeters

  // This method takes a string containing the log of a car meter 
  // and returns a log of vehicles that passed the car meter.
  //
  // Parameters:
  //   Scanner input: is a Scanner object that is a stream of text
  //                  containing the log extracted from a traffic meter.
  //                  Each entry in the log (one per line) denotes
  //                  a vehicle axel triggering the meter.  Each
  //                  entry consists of 2 integers: time (in ms) 
  //                  and speed (in km/h)
  //                  e.g.  97205795 51
  //
  // Returns: an ArrayList of Strings.  Each string is of the form:
  //           "T: Light car or truck"
  //          or
  //           "T: Heavy truck, F R"
  //          where T is the time (in seconds) where the front axle was sensed
  //                F is the number of axles in the front of the truck (>=1)
  //                R is the number of axles in the rear of the truck (>= 1)
  //          Examples:
  //            "36736: Light car or truck"
  //            "74231: Heavy truck, 1 2"
  //          Semantics:
  //            A light car or truck has only one axle in th back and one in
  //            the front, separated by at least 186cm (MIN_WHEELBASE).
  //            A heavy truck has 3 or more axles such that 3 <= F + R
  //            where F >= 1 is the number of front axles, such that 
  //            consecutive axles are less than 186cm apart.  
  //            There are also R >= 1 rear axles, such that consecutive
  //            axles are less than 186cm apart.
  public ArrayList computeTraffic( Scanner log );
}
