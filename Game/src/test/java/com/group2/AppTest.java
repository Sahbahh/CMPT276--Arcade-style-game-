package com.group2;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Unit test for the app class.
 */
public class AppTest 
{
	
	private App app;
	
	@Before
	public void setup() {
		app = new App();
	}
	
	@Test
    public void constructorTest()
    {
        assertNotNull(app);
    }
    
	@Test
    public void stateTest() {
		assertEquals("State is not initially set to STATE.MAINMENU", App.STATE.MAINMENU, App.state);
	}
}
