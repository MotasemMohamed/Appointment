/*
 * Copyright (c) INTELLISC 2020.
 * All Rights Reserved.
 *
 * ver          Developer          Date        Comments
 * ----- ---------------------  ----------  ----------------------------------------
 * 1.00  Eng. Mohamed Abden 23/06/2020 - Script created.
 */

package Utilities;

import base.TestBase;

public class SetURL extends TestBase {
	
	public static String  DLSUrlStg = "http://192.168.0.176:8282/login";
	
	public static String  IExamUrlStg = "http://192.168.0.176:8480/";
	
	 public static void lunchDLS() {
	        driver.navigate().to(DLSUrlStg);

	    }
	    public static void lunchIExam() {

	 	driver.navigate().to(IExamUrlStg);
	    }
	
	public static void refreshPage(){
	 	driver.navigate().refresh();
	}
	
}
