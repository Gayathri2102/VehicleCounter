FUNCTIONAL FEATURES
-------------------
Analyzes how much traffic is on the Street.
Analyzes the type of vehicle contributing to the traffic on the Street.
Differentiates the number of cars and Heavy vehicles using the Street.
Converts the input log from the traffic meter that records the time and 
the speed of the axle was travelling at, in to a log of cars and heavy vehicles
crossing the traffic meter.

INPUT FORMAT
------------
time(ms) speed(km/h)
97205795 51
97205995 52
97207123 45
97207347 44

OUTPUT FORMAT
-------------
T: Type, F R
502: Heavy truck, 1 2
560: Car or light truck
595: Car or light truck
650: Heavy truck, 1 3

where T - is the time, rounded down to the nearest second, of the vehicle's first axle crossing the
trac meter.
F is the number of axles at the front of a heavy truck
R is the number of axles at the rear of a heavy truck

NOTE:
-----
1. This Code was done as part of Assignment 1 in CS3 course(MACS, Dalhousie University)
2. VehicleCounterTest.java, TrafficReporter.java - Files rovided by Dr. Alexander Brodskey, Dalhousie University 
   for the purpose of testing.



