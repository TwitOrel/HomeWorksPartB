import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 *
 */
public class TestShapesPartA {

	private String circle1Str;
	private String circle2Str;
	private String rectangle1Str;
	private String rectangle2Str;
	private Shape circle1, circle2, rectangle1, rectangle2;

	@Before
	public void setUp() {
		circle1Str = "1,1,3.2";
		circle2Str = "-0.5,0.5,1";

		rectangle1Str = "0,0,5.5,1";
		rectangle2Str = "-0.5,0.5,1.5,0.5";
		try {
			circle1Str = "1,1,3.2";
			circle2Str = "-0.5,0.5,1";

			rectangle1Str = "0,0,5.5,1";
			rectangle2Str = "-0.5,0.5,1.5,0.5";

			circle1 = new Circle(circle1Str);
			circle2 = new Circle(circle2Str);
			rectangle1 = new Rectangle(rectangle1Str);
			rectangle2 = new Rectangle(rectangle2Str);

		} catch (Exception e) {
			System.err.println(e);
			fail("An exception in Polygon constructor");
		}
	}

	@Test
	public void testConstructorsAndEquals() throws Exception{
		Shape circle11, circle22, rectangle11, rectangle22;
		circle1Str = "1,1,3.2";
		circle2Str = "-0.5,0.5,1";

		rectangle1Str = "0,0,5.5,1";
		rectangle2Str = "-0.5,0.5,1.5,0.5";

		circle11 = new Circle(new Point(1, 1), 3.2);
		circle22 = new Circle(new Point(-0.5, 0.5), 1);
		rectangle11 = new Rectangle(new Point(0, 0), 5.5, 1);
		rectangle22 = new Rectangle(new Point(-0.5, 0.5), 1.5, 0.5);


		assertEquals(circle1, circle11);
		assertEquals(circle2, circle22);
		assertEquals(rectangle1, rectangle11);
		assertEquals(rectangle2, rectangle22);

		assertNotEquals(circle1, circle2);
		assertNotEquals(rectangle1, rectangle2);
		assertNotEquals(circle1, rectangle1);
		assertNotEquals(rectangle2, circle2);

		Shape circle3, circle4, rectangle3, rectangle4,rectangle33;
		
		circle3 = new Circle();
		rectangle3 = new Rectangle();
		circle4 = new Circle(new Point(), 1);
		rectangle4 = new Rectangle(new Point(), 1, 1);
		assertEquals(circle3, circle4);
		assertEquals(rectangle3, rectangle4);
		
		
		assertNotEquals(rectangle3, rectangle11);
		rectangle33 = new Rectangle(new Point(-0.5, 0.5), 0.5, 1.5);
		assertNotEquals(rectangle22, rectangle33);
	}

	@Test
	public void testGetCenter() {
		assertEquals(circle1.getCenter(), new Point(1,1));
		assertEquals(circle2.getCenter(), new Point(-0.5, 0.5));
		assertEquals(rectangle1.getCenter(), new Point(0,0));
		assertEquals(rectangle2.getCenter(), new Point(-0.5, 0.5));
	}

	@Test
	public void testArea() {
		assertTrue(HW3Utils.areEqual(circle1.area(), 3.2*3.2*Math.PI));
		assertTrue(HW3Utils.areEqual(circle2.area(), Math.PI));
		assertTrue(HW3Utils.areEqual(rectangle1.area(), 5.5));
		assertTrue(HW3Utils.areEqual(rectangle2.area(), 0.75));
	}

	@Test
	public void testPerimeter() {
		assertTrue(HW3Utils.areEqual(circle1.perimeter(), 3.2*2*Math.PI));
		assertTrue(HW3Utils.areEqual(circle2.perimeter(), 2*Math.PI));
		assertTrue(HW3Utils.areEqual(rectangle1.perimeter(), 13));
		assertTrue(HW3Utils.areEqual(rectangle2.perimeter(), 4));
	}
}
