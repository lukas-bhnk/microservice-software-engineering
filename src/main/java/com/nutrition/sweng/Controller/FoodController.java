package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.FoodDto;
import com.nutrition.sweng.Exceptions.ProcessException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodService;
import com.nutrition.sweng.security.JwtValidator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("rest/food")
public class FoodController {
    private FoodService foodService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private JwtValidator jwtTokenValidator;

    @Autowired
    public FoodController(FoodService foodService, JwtValidator jwtTokenValidator){
        this.foodService = foodService;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    /**
     * Get the Food values by its id
     * @param id of the food
     * @return food
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public FoodDto getFood(@PathVariable Long id){
        LOG.info("Received GET-Request /rest/food/{id}).", id);
        Food food = this.foodService.getFood(id);
        return new FoodDto(food);
    }

    /**
     * search for all foods that have included a part of the search string
     * @param name of the food
     * @return foodlist, a foodlist of all foods that have included the string
     */
    @GetMapping("/name={name}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public List<Food> getFood (@PathVariable String name){
        LOG.info("Received GET-Request /rest/food/name={name}).", name);
        List<Food> foodList = this.foodService.getFood(name);
        return foodList;
    }


    /**
     * Saves all Food Values incl. NutritionalValues, Vitamins and Minerals automatically form a Xlsx file.
     * Post food is only be possible for Admins.
     * An example of the excel is in the Repo (SchweizerNahrwertdatenbank.xlsx and SchweizerNahrwertdatenbank-test.xlsx)
     * @param file a excel, that has the following columns
     * Name	Bezugseinheit	Energie, Kalorien (kcal)	Fett, total (g)	Fetts??uren, ges??ttigt (g)	Kohlenhydrate, verf??gbar (g)	Zucker (g)	Protein (g)	Salz (NaCl) (g)	Alkohol (g)	Vitamin A-Aktivit??t, RE (??g-RE)	Retinol (??g)	Betacarotin (??g)	Vitamin B1 (Thiamin) (mg)	Vitamin B2 (Riboflavin) (mg)	Vitamin B6 (Pyridoxin) (mg)	Vitamin B12 (Cobalamin) (??g)	Niacin (mg)	Folat (??g)	Vitamin C (Ascorbins??ure) (mg)	Vitamin D (Calciferol) (??g)	Vitamin E-Aktivit??t (mg-ATE)	Kalium (K) (mg)	Natrium (Na) (mg)	Chlorid (Cl) (mg)	Magnesium (Mg) (mg)	Phosphor (P) (mg)	Eisen (Fe) (mg)	Zink (Zn)  (mg)	Selen (Se) (??g)
     * @return a Meal
     */
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> handlePost(@RequestParam(name = "file") MultipartFile file) {
        LOG.info("Received POST-Request /rest/food/");
        // validate that the file has the .xlsx ending
        String fileName = file.getOriginalFilename();
        //count the rows that have been processed
        int rowCounter = 0;
        if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
            try (InputStream excelIs = file.getInputStream()) {
                // create the Workbook using the InputStream returned by
                // MultipartFile#getInputStream()
                Workbook wb = WorkbookFactory.create(excelIs);
                // get the first sheet of the Workbook
                Sheet sheet = wb.getSheetAt(0);
                Iterator<Row> rowIt = sheet.rowIterator();

                //skip header
                rowIt.next();
                // iterating rows
                while (rowIt.hasNext()) {

                    Row currentRow = rowIt.next();
                    //save all values of the row
                    String name = currentRow.getCell(0).getStringCellValue();
                    String unitSizeString = currentRow.getCell(1).getStringCellValue();
                    FoodUnitSize unitSize;
                    if (unitSizeString.contains("ml")) unitSize = FoodUnitSize.MILLILITRE;
                    else unitSize = FoodUnitSize.GRAMS;
                    int calories = (int) Math.ceil(currentRow.getCell(2).getNumericCellValue());
                    double fats = currentRow.getCell(3).getNumericCellValue();
                    double fatsSaturated = currentRow.getCell(4).getNumericCellValue();
                    double carbs = currentRow.getCell(5).getNumericCellValue();
                    double sugar = currentRow.getCell(6).getNumericCellValue();
                    double proteins = currentRow.getCell(7).getNumericCellValue();
                    double salt = currentRow.getCell(8).getNumericCellValue();
                    double alcohol = currentRow.getCell(9).getNumericCellValue();
                    double a = currentRow.getCell(10).getNumericCellValue();
                    double retinol = currentRow.getCell(11).getNumericCellValue();
                    double betacarotin = currentRow.getCell(12).getNumericCellValue();
                    double b1 = currentRow.getCell(13).getNumericCellValue();
                    double b2 = currentRow.getCell(14).getNumericCellValue();
                    double b6 = currentRow.getCell(15).getNumericCellValue();
                    double b12 = currentRow.getCell(16).getNumericCellValue();
                    double niacin = currentRow.getCell(17).getNumericCellValue();
                    double fol = currentRow.getCell(18).getNumericCellValue();
                    double c = currentRow.getCell(19).getNumericCellValue();
                    double d = currentRow.getCell(20).getNumericCellValue();
                    double e = currentRow.getCell(21).getNumericCellValue();
                    double potassium = currentRow.getCell(22).getNumericCellValue();
                    double sodium = currentRow.getCell(23).getNumericCellValue();
                    double chloride = currentRow.getCell(24).getNumericCellValue();
                    double magnesium = currentRow.getCell(25).getNumericCellValue();
                    double phosphorus = currentRow.getCell(26).getNumericCellValue();
                    double iron = currentRow.getCell(27).getNumericCellValue();
                    double zinc = currentRow.getCell(28).getNumericCellValue();
                    double selen = currentRow.getCell(29).getNumericCellValue();

                    Food food = new Food();
                    food.setUnitSize(unitSize);
                    food.setName(name);

                    NutritionalValues nutritionalValues = new NutritionalValues();
                    nutritionalValues.setCalories(calories);
                    nutritionalValues.setAlcohol(alcohol);
                    nutritionalValues.setCarbs(carbs);
                    nutritionalValues.setFats(fats);
                    nutritionalValues.setFatsSaturated(fatsSaturated);
                    nutritionalValues.setProteins(proteins);
                    nutritionalValues.setSalt(salt);
                    nutritionalValues.setSugar(sugar);

                    Minerals minerals = new Minerals();
                    minerals.setChloride(chloride);
                    minerals.setIron(iron);
                    minerals.setSelenium(selen);
                    minerals.setSodium(sodium);
                    minerals.setZinc(zinc);
                    minerals.setPotassium(potassium);
                    minerals.setMagnesium(magnesium);
                    minerals.setPhosphorus(phosphorus);

                    Vitamins vitamins = new Vitamins();
                    vitamins.setA(a);
                    vitamins.setB1(b1);
                    vitamins.setB2(b2);
                    vitamins.setB6(b6);
                    vitamins.setB12(b12);
                    vitamins.setC(c);
                    vitamins.setD(d);
                    vitamins.setE(e);
                    vitamins.setFol(fol);
                    vitamins.setBetacarotin(betacarotin);
                    vitamins.setNiacin(niacin);
                    vitamins.setRetinol(retinol);
                    foodService.saveAllFoodValues(food, minerals, vitamins, nutritionalValues);

                    rowCounter++;
                }
            } catch(IOException e) {
                LOG.error("Can not process the posted .xlsx file");
            }

        } else {
            throw new ProcessException("The file should be a .xlsx");
        }
        LOG.info("Successfully processed the posted file with {} rows", rowCounter);
        return ResponseEntity.ok("Successfully processed the posted file");
    }

}
