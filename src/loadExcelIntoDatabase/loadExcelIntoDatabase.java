public class ExcelIntoDatabase {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/Lebensmittel";
        String username = "admin";
        String password = "password";

        String excelFilePath = "Schweizer-Nahrwertdatenbank.xlsx";
        Connection connection = null;

        try{
            long start = System.currentMillis();
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO  (bezeichnung, fats, proteins, carbs) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            rowIterator.next(); // skip the header row

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            String name = nextCell.getStringCellValue();
                            statement.setString(1, name);
                            break;
                        case 1:
                            String catagory = nextCell.getStringCellValue();
                            statement.setString(2, category);
                        case 2:
                            String unitSize = nextCell.getStringCellValue();
                            statement.setString(3, unitSize);
                        case 3:
                            int calories = (int) nextCell.getNumericCellValue();
                            statement.setInt (4, calories);
                        case 4:
                            double fats = (double) nextCell.getNumericCellValue();
                            statement.setDouble (5, fats);
                        case 5:
                            double carbs = (double) nextCell.getNumericCellValue();
                            statement.setDouble (6, carbs);
                        case 6:
                            double sugar = (double) nextCell.getNumericCellValue();
                            statement.setDouble (7, sugar);
                        case 7:
                            double protein = (double) nextCell.getNumericCellValue();
                            statement.setDouble(8, protein);
                        case 8:
                            double
                    }

                }

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }

            }

            workbook.close();

            // execute the remaining queries
            statement.executeBatch();

            connection.commit();
            connection.close();

            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));

        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
        }

    }
}