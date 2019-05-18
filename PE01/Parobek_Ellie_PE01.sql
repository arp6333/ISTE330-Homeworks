SELECT 'ISTE-330-01, PE 01' as 'Ellie Parobek';

-- ISTE-330/722 Database Connectivity and Access
-- PE 01 - SQL review
-- Students: Replace the entire line "-- {Replace this entire line with your answer}.
-- 		Make sure you remove the double dashes.
--      Replace {put your name here} with your first and last name.
--      Do NOT remove the SELECT "QUESTION #"
--      Before sending this file to the dropbox, 
--      Start a new MySQL session, 'source' the travel.sql then run this 
--      script and check it against the expected output and NO errors.
--      You may receive a warning on a date query, that is OK.
--      Replace the { } comment with the database you are using for this exercise
--      then run this script to see what it does. It should execute cleanly.
--

-- Remove the comment marks on the next line, then add the database name
use travel;
-- 

-- 1.
SELECT '1. What are the names and complete addresses of all passengers listed in order by last name?' AS 'QUESTION 1';

SELECT fname, lname, street, city, state, zip
	FROM passenger JOIN zips USING (ZIP)
    ORDER BY lname ASC
;

-- 2.	
SELECT '2. What are the trip numbers, departure times, and departure locations code for all bus trips?' AS 'QUESTION 2';

SELECT t.tripnum, t.departuretime, t.departureloccode 
	FROM trip AS t JOIN trip_directory AS td USING(TripNum)
    JOIN tripcodes AS tc USING(TripType)
	WHERE tc.TypeName = 'Bus'
;

-- 3.	
SELECT '3. What are the names of the passengers who are traveling in October?' AS 'QUESTION 3';

SELECT CONCAT(p.FName,' ', p.LName) AS Passenger
	FROM passenger AS p JOIN trip_people AS tp USING (PassengerID)
    WHERE SUBSTRING(tp.Date, 6, 2) = '10' 
;

-- 4.	
SELECT '4. How many trips in the trip directory leave from each city?' AS 'QUESTION 4';

SELECT l.location AS Location, COUNT(td.DepartureLocCode) AS "Number of Departures"
	FROM locations AS l JOIN trip_directory AS td ON l.LocationCode = td.DepartureLocCode
    GROUP BY td.DepartureLocCode
    ORDER BY l.location ASC
;

-- 5.	
SELECT '5. Who, if anyone, is working the Rochester trip to Buffalo during September 2017?' AS 'QUESTION 5';

SELECT s.name
	FROM trip AS t JOIN staff AS s USING(TripNum)
    JOIN locations AS ld ON ld.LocationCode = t.DepartureLocCode
    JOIN locations AS la ON la.LocationCode = t.ArrivalLocCode
	WHERE SUBSTRING(t.Date, 1, 7) = '2017-09' AND ld.Location = "Rochester" AND la.Location = "Buffalo"
;

-- 6.	
SELECT '6. Dan Gnagy who works for Rides ‘R’ Us is from Framingham. Who, if anyone, will he meet from his town when he works on a trip, and during what trip number?' AS 'QUESTION 6';

SELECT DISTINCT t.tripnum, CONCAT(p.FName,' ', p.LName) AS "People from Framingham"
	FROM trip_people AS tp JOIN trip AS t USING(TripNum)
    JOIN passenger AS p USING(PassengerID)
    JOIN zips AS z USING(Zip)
    WHERE CONCAT(p.FName,' ', p.LName) != "Dan Gnagy" AND z.City = "Framingham"
;

-- 7.
SELECT '7.	What people from Rochester, travel by bus?' AS 'QUESTION 7';

SELECT p.fname, p.lname
	FROM passenger AS p JOIN trip_people AS tp USING(PassengerID)
    JOIN trip_directory AS td USING(TripNum)
    JOIN tripcodes AS tc USING(TripType)
    JOIN zips AS z USING(zip)
	WHERE tc.TypeName = 'Bus' AND z.City = 'Rochester'
;
    
-- 8.
SELECT '8. What is the description of the equipment on which Rich Gleason travels?' AS 'QUESTION 8';

SELECT e.equipmentdescription
	FROM equipment AS e JOIN trip AS t USING(EquipID)
    JOIN trip_people AS tp USING(TripNum) JOIN passenger AS p USING(PassengerID)
    WHERE CONCAT(p.FName,' ', p.LName) = "Rich Gleason"
;

    
-- 9.	
SELECT '9. How many passengers travel by plane?' AS 'QUESTION 9';

SELECT COUNT(tp.PassengerID) AS "Plane People"
	FROM trip_people AS tp JOIN trip_directory AS td USING(TripNum)
    JOIN tripcodes AS tc USING(TripType)
	WHERE tc.TypeName = 'Plane'
;
    
-- 10.	
SELECT '10. On which scheduled flights might there be people with cell phones?' AS 'QUESTION 10';

SELECT DISTINCT t.tripnum, t.date
	FROM trip AS t JOIN trip_people AS tp USING(TripNum)
    JOIN phones AS p USING(PassengerID)
    JOIN trip_directory AS td USING(TripNum)
    JOIN tripcodes AS tc USING(TripType)
	WHERE p.PhoneType = 'Cell' AND t.DepartureTime IS NOT NULL AND tc.TypeName = 'Plane'
;
    
-- 11.	
SELECT '11. On how many trips has each piece of equipment been used?' AS 'QUESTION 11';

SELECT e.equipid, e.equipmentname, COUNT(t.equipid) AS "NumTrips"
	FROM equipment AS e LEFT JOIN trip AS t USING(EquipID)
    GROUP BY e.equipid
;

-- 12.	
SELECT '12. What equipment has never been used on a trip?' AS 'QUESTION 12';

SELECT e.equipid, e.equipmentname, COUNT(t.equipid) AS "NumTrips"
	FROM equipment AS e LEFT JOIN trip AS t USING(EquipID)
    GROUP BY e.equipid
    HAVING COUNT(t.equipid) = 0
;
   
-- 13.	
SELECT '13. During the period of 9/1/2017 through 10/31/2017, what types of transportation had more than 1 trip?' AS 'QUESTION 13';

SELECT tc.typename, COUNT(t.tripnum) AS "NumTrips"
	FROM tripcodes AS tc JOIN trip_directory AS td USING(TripType)
    JOIN trip AS t USING(TripNum)
    WHERE SUBSTRING(t.Date, 1, 7) = '2017-09' OR SUBSTRING(t.Date, 1, 7) = '2017-10'
    GROUP BY tc.TypeName
    HAVING COUNT(t.tripnum) >= 2
;

-- 14. Using only one statement, display the first 3 dates and last 3 dates from trip_people. Place a --- between the sets of 3 as shown. Note the order of the 2nd set of 3 is reversed. Extra-bonus for getting the last 3 dates ascending. An answer receiving full points MUST use ONE statement and no temporary tables. Half points for anything else.
SELECT '14. First 3 and last 3 of trip_people by date.' as 'Question 14';

-- {Replace this entire line with your answer}

-- 15.	
SELECT '15. On the trip number 3030 on October 10, 2017, display how many staff are there, \n  where is the travel from and to and what equipment is being used?' AS 'QUESTION 15';

-- {Replace this entire line with your answer}
