INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk) VALUES (1,10,12,'2022-04-10',30,1,4,1);
INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk) VALUES (2,11,33,'2020-03-20',20,3,43,3);
INSERT INTO meal(id,calories,carbs,date,fats,meal_category,proteins, user_fk) VALUES (3,409,40,'2019-04-10',34,2,12,4);

INSERT INTO food(id, name, unit_size, meal_fk) VALUES (1,'Apfel', 0, 2);
INSERT INTO food(id, name, unit_size, meal_fk) VALUES (2,'Cola', 1, 2);
INSERT INTO food(id, name, unit_size) VALUES (3,'Spaghetti', 1);

INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc) VALUES (1,2,3,6,5,9,10,12,33);
INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc) VALUES (2,-1,-1,9,15,29,310,212,33);
INSERT INTO minerals(food_id, chloride, iron, magnesium, phosphorus, potassium, selenium, sodium, zinc) VALUES (3,23,33,63,53,92,12,121,31);

INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar) VALUES (1,9,39,-1,40,12,23,1,10);
INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar) VALUES (2,9,39,20,40,12,23,-1,-1);
INSERT INTO nutritional_values(food_id, alcohol, calories, carbs, fats, fats_saturated, proteins, salt, sugar) VALUES (3,0,329,120,4,121,231,1,10);

INSERT INTO vitamins(food_id,a,b1,b11,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol) VALUES (1,12,123,12,14,12,13,1,5,6,7,45,45,23);
INSERT INTO vitamins(food_id,a,b1,b11,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol) VALUES (2,112,25,13,12,-1,13,5,25,6,34,22,42,23);
INSERT INTO vitamins(food_id,a,b1,b11,b12,b2,betacarotin,c,d,e,fol,k,niacin,retinol) VALUES (3,2,19,24,14,12,14,1,5,6,7,45,5,23);





