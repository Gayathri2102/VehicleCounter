import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class VehicleCounterTest {
  private static Scanner mkTest( long [] input ) {
    String s = "";

    for( int i = 0; i < input.length; i += 2 ) {
      s += String.format( "%d %d\n", input[i], (int)input[i+1] );
    }
    return new Scanner( s );
  }
     
  private static ArrayList mkOutput( int [] output ) {
    ArrayList al = new ArrayList();

    for( int i = 0; i < output.length; i += 3 ) {
      if( output[i+1] == 0 ) {
        al.add( String.format( "%d: Car or light truck", output[i] ) );
      } else {
        al.add( String.format( "%d: Heavy truck, %d %d", output[i], output[i+1],
                               output[i+2] ) );
      }
    }
    return al;
  }
     
  private static boolean doTest( long [] input, int [] output ) {
    ArrayList expected = mkOutput( output );
    VehicleCounter vc = new VehicleCounter();
    ArrayList al = vc.computeTraffic( mkTest( input ) );
    return al != null && al.equals( mkOutput( output ) );
  }

  @Test
  void testEmpty() {
    VehicleCounter vc = new VehicleCounter();
    ArrayList al = vc.computeTraffic( new Scanner( "" ) );
    assertTrue( al != null && al.size() == 0, "Empty Test" );
  }

  @Test
  void test1Car() {
    long [] input = { 595629, 41,
                      596044, 42 };
    int [] output = { 595, 0, 0 };
    assertTrue( doTest( input, output), "1 Car Test" );
  }

  @Test
  void test1Truck() {
    long [] input = { 502788, 32,
                      503612, 33,
                      503775, 34 };
    int [] output = { 502, 1, 2 };
    assertTrue( doTest( input, output), "1 Truck Test" );
  }

  @Test
  void test1Car1Truck() {
    long [] input = { 595629, 41,
                      596044, 42,
                      650172, 56,
                      650780, 55,
                      650878, 54,
                      650978, 53 };
    int [] output = { 595, 0, 0,
                      650, 1, 3 };
    assertTrue( doTest( input, output), "1 Car 1 Truck Test" );
  }

  @Test
  void test1Truck1Car() {
    long [] input = { 502788, 32,
                      503612, 33,
                      503775, 34,
                      560933, 37,
                      561479, 36 };
    int [] output = { 502, 1, 2,
                      560, 0, 0 };
    assertTrue( doTest( input, output), "1 Car 1 Truck Test" );
  }

  @Test
  void testExample() {
    long [] input = { 502788, 32,
                      503612, 33,
                      503775, 34,
                      560933, 37,
                      561479, 36,
                      595629, 41,
                      596044, 42,
                      650172, 56,
                      650780, 55,
                      650878, 54,
                      650978, 53 };
    int [] output = { 502, 1, 2,
                      560, 0, 0,
                      595, 0, 0,
                      650, 1, 3 };
    assertTrue( doTest( input, output), "Example Test" );
  }
}
