package test;
import junit.framework.TestCase;
import lib.ConfigurableOption;
import lib.GameManager;
import logic.PlayerStatus;


public class TestplayerStatus extends TestCase {

	

	protected void setUp() throws Exception {
		super.setUp();
	}
	public void test_constructor(){
		PlayerStatus p = new PlayerStatus();
		assertEquals(ConfigurableOption.timelimit*GameManager.TICK_PER_SECONDS, p.getRemainingTime() );
		
	}
	public void testTime(){
		PlayerStatus p = new PlayerStatus();
		int init_time = ConfigurableOption.timelimit*GameManager.TICK_PER_SECONDS;
		p.decreaseRemainingTime(20);
		assertEquals(init_time-20, p.getRemainingTime());
		p.decreaseRemainingTime(0);
		assertEquals(init_time-20, p.getRemainingTime());
		p.decreaseRemainingTime(12);
		assertEquals(init_time-32, p.getRemainingTime());
		p.decreaseRemainingTime(Integer.MAX_VALUE);
		assertEquals(0, p.getRemainingTime());
	}
	public void testScore(){
		PlayerStatus p = new PlayerStatus();
		assertEquals(0, p.getScore());
		p.increaseScore(30);
		assertEquals(30, p.getScore());
		p.increaseScore(0);
		assertEquals(30, p.getScore());
	}
	public void testDisplay(){
		PlayerStatus p = new PlayerStatus();
		assertTrue( p.isDisplayingArea(30, 20));
		assertTrue( p.isDisplayingArea(30, 0));
		assertFalse( p.isDisplayingArea(30,50));
		assertFalse( p.isDisplayingArea(30,41));
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
