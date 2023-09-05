import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 *
 */
public class TestPrismPyramidPartB {

	private String circle1Str;
	private String circle2Str;
	private String rectangle1Str;
	private String rectangle2Str;
	private Shape circle1, circle2, rectangle1, rectangle2;
	private PrismPyramid prismC1, prismC2, prismC3, prismR1, prismR2, prismR3;
	private PrismPyramid pyramidC1, pyramidC2, pyramidC3, pyramidR1, pyramidR2, pyramidR3;

	@Before
	public void setUp() {
		try {
		circle1Str = "1,1,3.2";
		circle2Str = "-0.5,0.5,1";

		rectangle1Str = "0,0,5.5,1";
		rectangle2Str = "-0.5,0.5,1.5,0.5";

		circle1 = new Circle(circle1Str);
		circle2 = new Circle(circle2Str);
		rectangle1 = new Rectangle(rectangle1Str);
		rectangle2 = new Rectangle(rectangle2Str);

		prismC1 = new Prism(circle1, 0, 1);
		prismC2 = new Prism(circle2, -1.0, 1);
		prismC3 = new Prism(circle1, 1, -2);
		
		prismR1 = new Prism(rectangle1, 0, 1);
		prismR2 = new Prism(rectangle2, -1.0, 1);
		prismR3 = new Prism(rectangle1, 1, -2);

		pyramidC1 = new Pyramid(circle1, 0, 1, 1, 1);
		pyramidC2 = new Pyramid(circle2, -1.0, 1, 1, 0);
		pyramidC3 = new Pyramid(circle1, 1, 0, 1.5, -2);
		
		pyramidR1 = new Pyramid(rectangle1, 0, 1, 1, 1);
		pyramidR2 = new Pyramid(rectangle2, -1.0, 1, 1, 0);
		pyramidR3 = new Pyramid(rectangle1, 1, 0, 1.5, -2);
		} catch (Exception e) {
			System.err.print(e);
			fail("An exception in constructors");
		}

	}

	@Test
	public void testConstructorsAndEquals() {
		PrismPyramid prism1C = null;
		PrismPyramid prism2C = null;
		PrismPyramid prism1R = null;
		PrismPyramid prism2R = null;
		PrismPyramid pyramid1C = null;
		PrismPyramid pyramid2C = null;
		PrismPyramid pyramid1R = null;
		PrismPyramid pyramid2R = null;

		try {
			prism1C = new Prism("C", circle1Str, 0, 1);
			prism2C = new Prism("C", circle2Str, -1.0, 1);
			prism1R = new Prism("R", rectangle1Str, 0, 1);
			prism2R = new Prism("R", rectangle2Str, -1.0, 1);

			pyramid1C = new Pyramid("C", circle1Str, 0, 1, 1, 1);
			pyramid2C = new Pyramid("C", circle2Str, -1.0, 1, 1, 0);
			pyramid1R = new Pyramid("R", rectangle1Str, 0, 1, 1, 1);
			pyramid2R = new Pyramid("R", rectangle2Str, -1.0, 1, 1, 0);

		} catch (Exception e) {
			fail("An exception in constructor");
		}

		assertTrue(prism1C.equals(prismC1));
		assertTrue(prism2C.equals(prismC2));
		assertTrue(prism1R.equals(prismR1));
		assertTrue(prism2R.equals(prismR2));

		assertTrue(pyramid1C.equals(pyramidC1));
		assertTrue(pyramid2C.equals(pyramidC2));
		assertTrue(pyramid1R.equals(pyramidR1));
		assertTrue(pyramid2R.equals(pyramidR2));

	}

	@Test
	public void testGetBase() {
		assertEquals(prismC1.getBaseShape().toString(), "Circle [radius=3.20, center=(x=1.00, y=1.00)]");
		assertEquals(prismC2.getBaseShape().toString(), "Circle [radius=1.00, center=(x=-0.50, y=0.50)]");
		assertEquals(prismC3.getBaseShape().toString(), "Circle [radius=3.20, center=(x=1.00, y=1.00)]");

		assertEquals(prismR1.getBaseShape().toString(),
				"Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]");
		assertEquals(prismR2.getBaseShape().toString(),
				"Rectangle [length vertical edge=1.50, length horizontal edge=0.50, center=(x=-0.50, y=0.50)]");
		assertEquals(prismR3.getBaseShape().toString(),
				"Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]");

		assertEquals(pyramidC1.getBaseShape().toString(), "Circle [radius=3.20, center=(x=1.00, y=1.00)]");
		assertEquals(pyramidC2.getBaseShape().toString(), "Circle [radius=1.00, center=(x=-0.50, y=0.50)]");
		assertEquals(pyramidC3.getBaseShape().toString(), "Circle [radius=3.20, center=(x=1.00, y=1.00)]");

		assertEquals(pyramidR1.getBaseShape().toString(),
				"Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]");
		assertEquals(pyramidR2.getBaseShape().toString(),
				"Rectangle [length vertical edge=1.50, length horizontal edge=0.50, center=(x=-0.50, y=0.50)]");
		assertEquals(pyramidR3.getBaseShape().toString(),
				"Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]");
	}

	@Test
	public void testGetHeight() {
		assertTrue(HW3Utils.areEqual(prismC1.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(prismC2.getHeight(), 2));
		assertTrue(HW3Utils.areEqual(prismC3.getHeight(), 3));

		assertTrue(HW3Utils.areEqual(prismR1.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(prismR2.getHeight(), 2));
		assertTrue(HW3Utils.areEqual(prismR3.getHeight(), 3));

		assertTrue(HW3Utils.areEqual(pyramidC1.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(pyramidC2.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(pyramidC3.getHeight(), 3));

		assertTrue(HW3Utils.areEqual(pyramidR1.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(pyramidR2.getHeight(), 1));
		assertTrue(HW3Utils.areEqual(pyramidR3.getHeight(), 3));
	}

	@Test
	public void testToString() {
		assertEquals(prismC1.toString(),
				"Prism: Base shape=Circle [radius=3.20, center=(x=1.00, y=1.00)]. z-values for bases=0.00, 1.00");
		assertEquals(prismC2.toString(),
				"Prism: Base shape=Circle [radius=1.00, center=(x=-0.50, y=0.50)]. z-values for bases=-1.00, 1.00");
		assertEquals(prismC3.toString(),
				"Prism: Base shape=Circle [radius=3.20, center=(x=1.00, y=1.00)]. z-values for bases=-2.00, 1.00");

		assertEquals(prismR1.toString(),
				"Prism: Base shape=Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]. z-values for bases=0.00, 1.00");
		assertEquals(prismR2.toString(),
				"Prism: Base shape=Rectangle [length vertical edge=1.50, length horizontal edge=0.50, center=(x=-0.50, y=0.50)]. z-values for bases=-1.00, 1.00");
		assertEquals(prismR3.toString(),
				"Prism: Base shape=Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]. z-values for bases=-2.00, 1.00");

		assertEquals(pyramidC1.toString(),
				"Pyramid: Base shape=Circle [radius=3.20, center=(x=1.00, y=1.00)]. z-base shape =0.00. Apex=(1.00,1.00,1.00)");
		assertEquals(pyramidC2.toString(),
				"Pyramid: Base shape=Circle [radius=1.00, center=(x=-0.50, y=0.50)]. z-base shape =-1.00. Apex=(1.00,1.00,0.00)");
		assertEquals(pyramidC3.toString(),
				"Pyramid: Base shape=Circle [radius=3.20, center=(x=1.00, y=1.00)]. z-base shape =1.00. Apex=(0.00,1.50,-2.00)");

		assertEquals(pyramidR1.toString(),
				"Pyramid: Base shape=Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]. z-base shape =0.00. Apex=(1.00,1.00,1.00)");
		assertEquals(pyramidR2.toString(),
				"Pyramid: Base shape=Rectangle [length vertical edge=1.50, length horizontal edge=0.50, center=(x=-0.50, y=0.50)]. z-base shape =-1.00. Apex=(1.00,1.00,0.00)");
		assertEquals(pyramidR3.toString(),
				"Pyramid: Base shape=Rectangle [length vertical edge=5.50, length horizontal edge=1.00, center=(x=0.00, y=0.00)]. z-base shape =1.00. Apex=(0.00,1.50,-2.00)");

	}

	@Test
	public void testVolume() {
		assertTrue(HW3Utils.areEqual(prismC1.volume(), 3.2 * 3.2 * Math.PI));
		assertTrue(HW3Utils.areEqual(prismC2.volume(), Math.PI * 2));
		assertTrue(HW3Utils.areEqual(prismC3.volume(), 3.2 * 3.2 * 3 * Math.PI));

		assertTrue(HW3Utils.areEqual(prismR1.volume(), 5.5));
		assertTrue(HW3Utils.areEqual(prismR2.volume(), 1.5));
		assertTrue(HW3Utils.areEqual(prismR3.volume(), 5.5 * 3));

		assertTrue(HW3Utils.areEqual(pyramidC1.volume(), 3.2 * 3.2 * Math.PI / 3));
		assertTrue(HW3Utils.areEqual(pyramidC2.volume(), Math.PI / 3));
		assertTrue(HW3Utils.areEqual(pyramidC3.volume(), 3.2 * 3.2 * 3 * Math.PI / 3));

		assertTrue(HW3Utils.areEqual(pyramidR1.volume(), 5.5 / 3));
		assertTrue(HW3Utils.areEqual(pyramidR2.volume(), 0.75 / 3));
		assertTrue(HW3Utils.areEqual(pyramidR3.volume(), 5.5 * 3 / 3));
	}

}
