create me a class ParkingManager, which has 3 parking lots: 
1. The Plaza Park (9 parking capacity) 
2. City Mall Garage (12 parking capacity)
3. Office Tower Parking (9 parking capacity)  

And have 3 parking boys:
1. Standard parking strategy
2. Smart parking strategy
3. Super Smart parking strategy
--------------------------------------------------------
parking manager need a park function, which accepts a parking strategy nad a car to be park, and it will assign the correponding parking boy to park the cars, then will return the Ticket returned from parking boy

--------------------------------------------------------

create me a rest controller ParkingLot controller, with @requestmapping("parkinglot"). Then with a get request which returns all the parking lots. It will call ParkingLotService to get the parking lots

--------------------------------------------------------

Create me a rest controller ParkingLot controller, with @requestmapping("parkinglot"). Then with a get request which returns all the parking lots. It will call ParkingLotService to get the parking lots

-----------------------------------------------------

generate me a ParkingLotControllerTest, for integration test the ParkingLotController, using the given when then format in strict manner

-----------------------------------------------------

create a new post mapping park cars, which accept and parking strategy and Car object as request body and parks car using parkingLotService

-----------------------------------------------------

write a integration test in ParkingLotCOntroller for the parkCar method on postmapping "/park"

-----------------------------------------------------

group the post to car park test with @parameter with different strategy

-----------------------------------------------------

generate me all unit test cases for ParkingManager in ParkingManagerTest with stirct order and full coverage

-----------------------------------------------------

add a new function in ParkingManager call fetch, which fetches the Car given a carplates from all of its parkinglots and return the car