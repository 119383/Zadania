/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author 119383
 */
public class BaseTest{
    

    
    @BeforeClass
    public static void setUpBeforeClass(){
        GoogleTools.initializeBrowser();
        GoogleTools.visitGoogle();
        GoogleTools.searchFor("samochód");
        GoogleTools.waitForResultsPage();
    }
    
    @AfterClass
    public static void tearDownAfterClass(){
        GoogleTools.turnOffWindow();
    }
}
