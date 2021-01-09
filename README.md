# SmartClean REST API Asessment

## Tech Used
Spring Boot
Java 
ThymeLeaf
HSQLDB 

   *A call to _create?start=, step= should result in creation of a new go routine in the program that starts counting every steptime seconds. The response should return a unique identifier for that routine.*

Usage example:
curl http://localhost:8080/stepTimer/_create/startval=1;step=500

Here, Timer and TimerTask utility classes are used internally to achieve this functionality.

   *A call to _check?id= should return the current counter value, creation time and step time for that service.*

Usage example:
curl http://localhost:8080/stepTimer/_check/21

Here, the requirement was to show only three fields. But I've exposed all the fields purposefully for demo purpose. This can easily be achieved by using  **@JsonIgnore**.


   *A call to _check should return same as (2) above but for all the unique identifiers present in the system.*

Usage example: 
curl http://localhost:8080/stepTimer/_check/


   *A call to _render should return an HTML page stating in tabular form, the unique identifiers and their current counts.*

Usage example: 
http://localhost:8080/stepTimer/_render/

ThymeLeaf Engine was used to render the view. This request is handled by **@Controller** instead of **@RestController**

*A call to _clear/:id or _clear?id= should clear the timer and cleanly exit setting the service status to stopped.*

Usage example: 
curl http://localhost:8080/stepTimer/_clear/16

This functionality is achieved by cancelling the Timer instance as well as removing our custom StepTimer instance from both DB and HashMap.


   *A call to _pause?id= should pause a given service if it exists, else return error. If it exists, and is a valid target, change the modifiedAt time to reflect this call’s response.*

Usage example: 
curl http://localhost:8080/stepTimer/_pause/17

Here also, we are discarding the Timer instance. But in this case, we are preserving our custom StepTimer instance.

  *Use dependency injection patterns to implement the above and discuss about the same.*

We didn't have to do much here because most of heavylifting is done by Spring. We have autowired the DAO  and one of the controllerBean. 

*Discuss the use of interfaces in the above program if relevant.*

We have extended JpaRepository Interface to create our DAO interface.
Spring internally uses SimpleJpaRepository Bean to implement it.

*You can use any database backend if required by the above program for persistence and state retrieval on abrupt kills.*

We have used  Spring Data JPA to persist the Custom StepTimer instances in HSQLDB .
In case of abrupt kills, JVM shutdown hooks should be employed for last minute persistence. Since, I'm using in-memory db, I've not registered any shutdown hooks.