package API;

import Utilities.DBconnection;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class Get_Appointment_API {

    private Integer VehicleClassid = 0;
    private Integer serviceId = 0;
    private String startTime="";
    private String endTime="";
    private Integer AddappTrsId = 0;
    Faker fakerdata= new Faker();
    String plateNumber = fakerdata.number().digits(4);
    String PhoneNumberFaker = fakerdata.number().digits(11);
    private Integer IdAppointmentTransaction = 0;
    private Integer AppTrsId = 0;
    DBconnection db = new DBconnection();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat dateFormatReverse = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String date1= dateFormat.format(date);
    String date2= dateFormatReverse.format(date);
    public static RequestSpecification BaseUrl = new RequestSpecBuilder().setBaseUri("https://172.16.25.200/api/inspectionAppFlowRest").build();
    Map<String,Object> map = db.getReferenceNumberForCanceling();

    public Get_Appointment_API() throws SQLException, ClassNotFoundException {
    }

    @Test (priority = 1)
        public void GetVehicleClasses ()
        {
            Response res = given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId", "000000")
                    .when().get("/getVehicleClasses").then().assertThat().statusCode(200).extract().response();
            VehicleClassid = JsonPath.from(res.asString()).getInt("data[0].id");
        }
    @Test (priority = 2, dependsOnMethods = "GetVehicleClasses")
        public void GetServices ()
        {
            Response response = given().relaxedHTTPSValidation().spec(BaseUrl).queryParam("vclClassId", VehicleClassid)
                    .when().get("/getServices").then().assertThat().statusCode(200).extract().response();
            serviceId = JsonPath.from(response.asString()).getInt("data[0].id");
        }
    @Test (priority = 3, dependsOnMethods = "GetServices")
    public void getVTCZoneList ()
        {
            given().relaxedHTTPSValidation().spec(BaseUrl).queryParams("vclClassId", VehicleClassid, "serviceId", serviceId)
                    .when().get("/getVtcZoneList").then().assertThat().statusCode(200);
        }
    @Test (priority = 4,dependsOnMethods = "getVTCZoneList")
    public String Get_Available_Appointment_Time_Slots()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("vehicleClassId",VehicleClassid);
        jsonObject.put("serviceId",serviceId);
        jsonObject.put("centerId","110");
        jsonObject.put("apointmentDayDate",date1);
       Response res= given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
               .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/getAvailableAppointmentTimeSlots").then().assertThat().statusCode(200).extract().response();

        startTime= JsonPath.from(res.asString()).getString("data[0].startTime");
        endTime= JsonPath.from(res.asString()).getString("data[0].endTime");
        System.out.println(startTime);
        System.out.println(endTime);
        return startTime;
    }

    @Test (priority = 5, dependsOnMethods = "Get_Available_Appointment_Time_Slots")
    public void BOOK_WITH_DELEGATION()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","null");
        jsonObject.put("customerName","IsoftName");
        jsonObject.put("customerEmail","IsoftName@Test.com");
        jsonObject.put("customerMobileNo","51279894578");
        jsonObject.put("registeredVehicle",true);
        jsonObject.put("registrationCountryId",51);
        jsonObject.put("vehicleClassId",VehicleClassid);
        jsonObject.put("serviceId",serviceId);
        jsonObject.put("centerId",101);
        jsonObject.put("plateNum",plateNumber+"-"+"ف ف ل");
        jsonObject.put("plateTypeId",31);
        jsonObject.put("apointmentDayDate",date1);
        jsonObject.put("laneId",184);
        jsonObject.put("apointmentStartTime",startTime);
        jsonObject.put("apointmentEndTime",endTime);
        jsonObject.put("recaptcha","");
        jsonObject.put("operationMode",1);
        JSONObject aptDelegationObject = new JSONObject();
        aptDelegationObject.put("name","Mona Abdallah");
        aptDelegationObject.put("phone","01011111111111");
        aptDelegationObject.put("aptDelegationType","GULF_CITIZEN");
        aptDelegationObject.put("residencyNo","22222222222");
        aptDelegationObject.put("dob","13-12-2006");
        aptDelegationObject.put("identityNo","1111111111111");
        aptDelegationObject.put("acceptConditions",true);
        JSONObject nationalityObject = new JSONObject();
        nationalityObject.put("id",25);
        aptDelegationObject.put("nationality",nationalityObject);
        jsonObject.put("aptDelegation",aptDelegationObject);
        //System.out.println(jsonObject.toString());
        Response res= given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/sendSMSVerifyCode").then().assertThat().statusCode(200).extract().response();

        AddappTrsId= JsonPath.from(res.asString()).getInt("data[0].appTrsId");
        System.out.println(AddappTrsId);
    }
    @Test (priority = 6, dependsOnMethods = "BOOK_WITH_DELEGATION")
    public void  validate_SMS_Verify_Code_Add()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerMobileNo",PhoneNumberFaker);
        jsonObject.put("passCode","123453");
        jsonObject.put("operationMode",1);
        jsonObject.put("appTrsId",AddappTrsId);

        System.out.println(jsonObject.toString());

        Response res= given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/validateSMSVerifyCode").then().assertThat().statusCode(200).extract().response();

        IdAppointmentTransaction= JsonPath.from(res.asString()).getInt("data[0].id");
        System.out.println(IdAppointmentTransaction);
    }

    @Test
    public void  send_SMS_Verify_Code(int operationMode )  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",map.get("AppointmentNumber"));
        jsonObject.put("customerMobileNo",map.get("PhoneNumber"));
        jsonObject.put("operationMode",operationMode);

        System.out.println(jsonObject.toString());

        Response res= given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/sendSMSVerifyCode").then().assertThat().statusCode(200).extract().response();

        AppTrsId= JsonPath.from(res.asString()).getInt("data[0].appTrsId");
        System.out.println(AppTrsId);
    }
    @Test
    public void  validate_SMS_Verify_Code(int operationMode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerMobileNo",map.get("PhoneNumber"));
        jsonObject.put("passCode",123456);
        jsonObject.put("operationMode",operationMode);
        jsonObject.put("appTrsId",AppTrsId);
        System.out.println(jsonObject.toString());
        Response res= given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/validateSMSVerifyCode").then().assertThat().statusCode(200).extract().response();
    }
    @Test
    public void Edit_BOOKing_WITH_DELEGATION()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",map.get("aptId"));
        jsonObject.put("customerName","IsoftName");
        jsonObject.put("customerEmail","IsoftName@Test.com");
        jsonObject.put("customerMobileNo",PhoneNumberFaker);
        jsonObject.put("registeredVehicle",true);
        jsonObject.put("registrationCountryId",51);
        jsonObject.put("vehicleClassId",VehicleClassid);
        jsonObject.put("serviceId",106);
        jsonObject.put("centerId",101);
        jsonObject.put("plateNum",plateNumber+"-"+"ف ف ل");
        jsonObject.put("plateTypeId",1);
        jsonObject.put("apointmentDayDate",date2);
        jsonObject.put("laneId",184);
        jsonObject.put("apointmentStartTime",startTime);
        jsonObject.put("apointmentEndTime",endTime);
        jsonObject.put("recaptcha","");
        jsonObject.put("operationMode",2);
        jsonObject.put("appTrsId",AppTrsId);
        JSONObject aptDelegationObject = new JSONObject();
        aptDelegationObject.put("name","volamagolus");
        aptDelegationObject.put("phone","01011111111111");
        aptDelegationObject.put("aptDelegationType","GULF_CITIZEN");
        aptDelegationObject.put("residencyNo","2222222223");
        aptDelegationObject.put("dob","13-12-2006");
        aptDelegationObject.put("identityNo","1111111112");
        aptDelegationObject.put("acceptConditions",true);
        JSONObject nationalityObject = new JSONObject();
        nationalityObject.put("id",25);
        aptDelegationObject.put("nationality",nationalityObject);
        jsonObject.put("aptDelegation",aptDelegationObject);
        System.out.println(jsonObject.toString());
       // AddappTrsId= JsonPath.from(res.asString()).getInt("data[0].appTrsId");
        System.out.println(AddappTrsId);
        given().relaxedHTTPSValidation().spec(BaseUrl).header("x-clientId","000000")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post("/bookedAppointment").then().assertThat().statusCode(200);
    }
}