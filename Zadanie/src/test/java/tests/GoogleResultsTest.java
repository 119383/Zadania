/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 119383
 */
public class GoogleResultsTest extends BaseTest{
    
    GoogleTools tools = new GoogleTools();
    
    @Test
    public void checkIfPicturesAreDisplayed(){
        Assert.assertTrue("No pictures found!", tools.checkIfPageContainsText("Obrazy dla samochód"));
    }
    
    @Test
    public void checkIfResultsNumberIsPassed(){        
        Assert.assertTrue("No search results quantity is displayed",tools.isAValidNumber(tools.getPagesFoundNumberAsString()));
        
    }
    
    @Test
    public void checkIfPageDisplaysMoreThan10Results(){
        Assert.assertEquals(10,tools.getNumberOfDisplayedResults());
    }    
}
