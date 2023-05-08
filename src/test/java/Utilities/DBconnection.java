package Utilities;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBconnection {

    private String DBclassname = "oracle.jdbc.driver.OracleDriver";
    private String DBurl = "jdbc:oracle:thin:@172.16.25.23:1521/DEVDB02";
    private String username = "SASO_MVPI2";
    private String password = "samvpinew";

    public Map<String,Object> getReferenceNumberForAppointment() throws ClassNotFoundException, SQLException {
        Map<String,Object> map = new HashMap<>();
        Integer apt_id = 0;

        Class.forName(DBclassname);
        Connection con = DriverManager.getConnection(DBurl,username,password);
        Statement st = con.createStatement();
        //ResultSet queryresult2 = st.executeQuery("SELECT c.ID, a.CUSTOMER_MOBILE_NO,c.APT_ID FROM  CI_APT_APPOITMENTS a ,CI_APT_SLOTS s ,CI_APT_STATUS c WHERE a.ID =s.APT_ID AND a.ID =c.APT_ID AND a.APPOINTMENT_CHANGE_NO =1 AND s.STATUS !=3 AND c.STATUS !=2 AND  TRUNC( s.APPOINTMENT_TIME_FROM )>= TRUNC(SYSDATE)ORDER BY a.ID  DESC   FETCH FIRST 1 ROWS ONLY\n");
        ResultSet queryresult2 = st.executeQuery("SELECT c.ID, a.CUSTOMER_MOBILE_NO,c.APT_ID FROM  CI_APT_APPOITMENTS a ,CI_APT_SLOTS s ,CI_APT_STATUS c WHERE a.ID =s.APT_ID AND a.ID =c.APT_ID AND a.APPOINTMENT_CHANGE_NO =1 GROUP BY c.ID,a.CUSTOMER_MOBILE_NO,c.APT_ID ORDER BY c.ID DESC  FETCH FIRST 1 ROWS ONLY\n");  //mashy 7alek

        while (queryresult2.next()){
            String appointmentreference = queryresult2.getString(1);
            apt_id = Integer.parseInt(appointmentreference);
            map.put("aptId", apt_id);
            String Phone_Number = queryresult2.getString(2);
            map.put("PhoneNumber", Phone_Number);
            String Appointment_Number = queryresult2.getString(3);
            map.put("AppointmentNumber", Appointment_Number);
        }
        con.close();
        return map;
    }
    public Map<String,Object> getReferenceNumberForCancelingAppointment() throws ClassNotFoundException, SQLException {
        Map<String,Object> map = new HashMap<>();
        Integer apt_id = 0;

        Class.forName(DBclassname);
        Connection con = DriverManager.getConnection(DBurl,username,password);
        Statement st = con.createStatement();
        //ResultSet queryresult2 = st.executeQuery("SELECT c.ID, a.CUSTOMER_MOBILE_NO,c.APT_ID FROM  CI_APT_APPOITMENTS a ,CI_APT_SLOTS s ,CI_APT_STATUS c WHERE a.ID =s.APT_ID AND a.ID =c.APT_ID  AND s.STATUS !=3 AND c.STATUS !=2 AND  TRUNC( s.APPOINTMENT_TIME_FROM )>= TRUNC(SYSDATE)ORDER BY a.ID  DESC   FETCH FIRST 1 ROWS ONLY\n");
        ResultSet queryresult2 = st.executeQuery("SELECT c.ID, a.CUSTOMER_MOBILE_NO,c.APT_ID FROM  CI_APT_APPOITMENTS a ,CI_APT_SLOTS s ,CI_APT_STATUS c WHERE a.ID =s.APT_ID AND a.ID =c.APT_ID GROUP BY c.ID,a.CUSTOMER_MOBILE_NO,c.APT_ID ORDER BY c.ID DESC  FETCH FIRST 1 ROWS ONLY");  //mashy 7alek

        while (queryresult2.next()){
            String appointmentreference = queryresult2.getString(1);
            apt_id = Integer.parseInt(appointmentreference);
            map.put("aptId", apt_id);
            String Phone_Number = queryresult2.getString(2);
            map.put("PhoneNumber", Phone_Number);
            String Appointment_Number = queryresult2.getString(3);
            map.put("AppointmentNumber", Appointment_Number);
        }
        con.close();
        return map;
    }
    public Map<String,Object> getReferenceNumberForCanceling() throws ClassNotFoundException, SQLException {
        Map<String,Object> map = new HashMap<>();
        Integer apt_id = 0;

        Class.forName(DBclassname);
        Connection con = DriverManager.getConnection(DBurl,username,password);
        Statement st = con.createStatement();
        ResultSet queryresult2 = st.executeQuery("SELECT c.ID, a.CUSTOMER_MOBILE_NO,c.APT_ID " +
                " FROM CI_APT_STATUS c, CI_APT_APPOITMENTS a WHERE c.APT_ID =a.ID AND c.STATUS !=3 ORDER BY c.ID  DESC   FETCH FIRST 1 ROWS ONLY");

        while (queryresult2.next()){
            String appointmentreference = queryresult2.getString(1);
            apt_id = Integer.parseInt(appointmentreference);
            map.put("aptId", apt_id);
            String Phone_Number = queryresult2.getString(2);
            map.put("PhoneNumber", Phone_Number);
            String Appointment_Number = queryresult2.getString(3);
            map.put("AppointmentNumber", Appointment_Number);
        }

        con.close();
        return map;
    }
    public Map<String,Object> AssertThetransactionexistinDB(String Transactionnumber) throws ClassNotFoundException, SQLException {
        Map<String,Object> map = new HashMap<>();
        Integer apt_id = 0;

        Class.forName(DBclassname);
        Connection con = DriverManager.getConnection(DBurl,username,password);
        Statement st = con.createStatement();
        ResultSet queryresult2 = st.executeQuery("SELECT a.ID ,a.STATUS , CASE WHEN  a.ID ="+Transactionnumber+" THEN 'exist' ELSE 'Not exist' END Appointment_existance FROM CI_APT_APPOITMENTS a WHERE a.ID ="+Transactionnumber+"");

        while (queryresult2.next()){
            String Status = queryresult2.getString(2);
            map.put("Status", Status);
            String Appointment_Existance = queryresult2.getString(3);
            map.put("AppointmentExistance", Appointment_Existance);
        }
        con.close();
        return map;
    }
}