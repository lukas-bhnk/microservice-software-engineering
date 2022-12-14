INSERT INTO user(id, email, version) VALUES (1,'ab@domain.com',1);

INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk, version) VALUES (1,10,12,'2022-04-10',30,1,4,1,1);
INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk, version) VALUES (2,11,33,'2020-03-20',20,3,43,1,1);
INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk, version) VALUES (3,409,40,'2019-04-10',34,2,12,1,1);

INSERT INTO food(id, name, unit_size, version) VALUES (1,'Apfel', 0,1);
INSERT INTO food(id, name, unit_size, version) VALUES (2,'Cola', 1,1);
INSERT INTO food(id, name, unit_size, version) VALUES (3,'Spaghetti', 1,1);
INSERT INTO food_entry(id, calories, carbs, fats, proteins, quantity, food_id, meal_id, version) VALUES (1,100,12,1,21,150,1,1,1);
INSERT INTO food_entry(id, calories, carbs, fats, proteins, quantity, food_id, meal_id, version) VALUES (2,100,12,1,21,150,2,1,1);

INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc, version) VALUES (1,2,3,6,5,9,10,12,33,1);
INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc, version) VALUES (2,1,1,9,15,29,310,212,33,1);
INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc, version) VALUES (3,23,33,63,53,92,12,121,31,1);

INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar,version) VALUES (1,9,39,1,40,12,23,1,10,1);
INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar,version) VALUES (2,9,39,20,40,12,23,1,1,1);
INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar, version) VALUES (3,0,329,120,4,121,231,1,10,1);

INSERT INTO vitamins(food_id,a,b1,b6,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol,version) VALUES (1,12,123,12,14,12,13,1,5,6,7,45,45,23,1);
INSERT INTO vitamins(food_id,a,b1,b6,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol,version) VALUES (2,112,25,13,12,1,13,5,25,6,34,22,42,23,1);
INSERT INTO vitamins(food_id,a,b1,b6,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol,version) VALUES (3,2,19,24,14,12,14,1,5,6,7,45,5,23,1);





