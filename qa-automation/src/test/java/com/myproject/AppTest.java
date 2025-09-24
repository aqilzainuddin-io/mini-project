package com.myproject;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    @Test
    public void testAddPositiveNumbers() {
        App app = new App();
        int result = app.add(2, 3);
        Assert.assertEquals(result, 5, "Addition of 2 + 3 should be 5");
    }

    @Test
    public void testAddWithNegativeNumber() {
        App app = new App();
        int result = app.add(-2, 3);
        Assert.assertEquals(result, 1, "Addition of -2 + 3 should be 1");
    }
}
