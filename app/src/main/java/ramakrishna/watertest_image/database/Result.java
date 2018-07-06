package ramakrishna.watertest_image.database;

public class Result {
    public static final String TABLE_NAME = "results";
 
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEST_ID = "testid";
    public static final String COLUMN_NAME = "testname";
    public static final String COLUMN_RESULT = "testresult";
    public static final String COLUMN_UNIT = "resultUnit";
    public static final String COLUMN_COLOR = "resultColor";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Result(int id, int test_id, String testName, String testResult, String unit, String color, String timeStamp) {
        this.id = id;
        this.testName = testName;
        this.testResult = testResult;
        this.unit = unit;
        this.color = color;
        this.timeStamp = timeStamp;
        this.test_id = test_id;
    }

    private int id;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    private int test_id;

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String testName;
    private String testResult;
    private String unit;
    private String color;
    private String timeStamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TEST_ID + " INTEGER,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_RESULT + " TEXT,"
                    + COLUMN_UNIT + " TEXT,"
                    + COLUMN_COLOR + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";
 
    public Result() {

    }
 

 
    public int getId() {
        return id;
    }
 

}