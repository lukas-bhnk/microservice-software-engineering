# NutritionApp

##Post Excel

There a two Example Excel files in the Root Directory for posting it to the Food Controller
-> one for the test environment and one for production.
The Prod excel (Schweizer-Nahrwertdatenbank.xlsx) file was posted before the screencast.


## Microservice Data Input

This Microservice is responsible for the Data Input of the user.
The user can track his meals, the nutritional Values are tracked by adding food into the meal.
The food database is configured and is read only for the user.
It is needed to track and calculate the Nutritional Values for a meal and that other microservice can use other information like vitamins or minerals.
