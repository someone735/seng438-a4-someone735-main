package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
/*	constrain 
 * 	- values is less than lower, equals lower, within range, equals upper, 
 * greater than upper, and negative 
	combine 
	- ranges are separated, overlapping, equal
	- check null cases 
	getCentralValue
	- tests 
		range is positive, mixed, negative, even length, odd length
		
*/    
	
//	constrain testing 
	@Test
	public void valueLessThanRange() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = -5.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(-2.0, result, .000000001d);
	}
	@Test
	public void valueEqualsLowerBound() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = -2.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(-2.0, result, .000000001d);
	}
	@Test
	public void valueWithinRangeExcludingBounds() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 3.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(3.0, result, .000000001d);
	}
	@Test

	public void valueEqualsUpperBound() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 6.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(6.0, result, .000000001d);
	}
	@Test
	public void valueGreaterThanRange() {
		Range exampleRange = new Range(-2.0,6.0);
		double testValue = 11.0;
		double result = exampleRange.constrain(testValue);
		assertEquals(6.0, result, .000000001d);
	}
	
// 	combine testing
	@Test
	public void oneNullRange() {
		Range exampleRangeOne = new Range(1,4);
		Range exampleRangeTwo = null;
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		assertEquals(exampleRangeOne, resultRange);
	}
	
	@Test
	public void bothNullRange() {
		Range exampleRangeOne = null;
		Range exampleRangeTwo = null;
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNull(resultRange);
	}
	
	@Test
	public void combineSeperateRanges() {
		Range exampleRangeOne = new Range(6, 8);
		Range exampleRangeTwo = new Range(-7, -3);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(-7,8);
		assertEquals(finalRange, resultRange);
	}
	
	@Test 
	public void combineSimilarRanges() {
		Range exampleRangeOne = new Range(7, 10);
		Range exampleRangeTwo = new Range(5, 8);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(5,10);
		assertEquals(finalRange, resultRange);
	}
	
	@Test 
	public void combineEqualRanges() {
		Range exampleRangeOne = new Range(-2,9);
		Range exampleRangeTwo = new Range(-2,9);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		Range finalRange = new Range(-2,9);
		assertEquals(finalRange, resultRange);
	}
	
	@Test 
	public void oneNullRangeNullA() {
		Range exampleRangeOne = null;
		Range exampleRangeTwo = new Range(1,4);
		Range resultRange = Range.combine(exampleRangeOne, exampleRangeTwo);
		assertNotNull(resultRange);
		assertEquals(exampleRangeTwo, resultRange);
	}
	
	
//	getCentralValue testing
    @Test
    public void centralValueShouldBeZero() {
        Range exampleRange = new Range(-1,1);
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void positiveRangeEvenLengthCentralValue() {
    	// calculate the central value of a range that has an even length and positive upper and lower
    	double upper = 5.0;
    	double lower = 3.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void positiveRangeOddLengthCentralValue() {
    	double upper = 7.0;
    	double lower = 4.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void mixedRangeEvenLengthCentralValue() {
    	double upper = 5.0;
    	double lower = -3.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void mixedRangeOddLengthCentralValue() {
    	double upper = 4.0;
    	double lower = -7.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void negativeRangeEvenLengthCentralValue() {
    	double upper = -3.0;
    	double lower = -11.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 == 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    @Test
    public void negativeRangeOddLengthCentralValue() {
    	double upper = -6.0;
    	double lower = -13.0;
    	Range exampleRange = new Range(lower, upper);
    	assertTrue(exampleRange.getLength()%2 != 0); 
    	double result = exampleRange.getCentralValue();
    	assertEquals((upper - lower)/2 + lower, result, .000000001d);
    }
    
    
 // Intersects Test :: Mark Jimenez (30193166)
    // Intersects Test :: Mark Jimenez (30193166
    // Intersects Test :: Mark Jimenez (30193166

 	@Test
 	public void testIntersects_ExactMatch() {
 	    // This test checks if two ranges, 3.0-9.0 and 3.0-9.0, are an exact match and thus intersect
 	    Range range = new Range(3.0, 9.0);
 	    assertTrue(range.intersects(3.0, 9.0));
 	}

 	@Test
 	public void testIntersects_PartialOverlapFromLeft() {
 	    // This test checks if the range 4.0-10.0 partially overlaps the range 2.0-5.0 from the left
 	    Range range = new Range(4.0, 10.0);
 	    assertTrue(range.intersects(2.0, 5.0)); // Overlaps on the left
 	}

 	@Test
 	public void testIntersects_PartialOverlapFromRight() {
 	    // This test checks if the range 4.0-10.0 partially overlaps the range 8.0-11.0 from the right
 	    Range range = new Range(4.0, 10.0);
 	    assertTrue(range.intersects(8.0, 11.0)); // Overlaps on the right
 	}

 	@Test
 	public void testIntersects_ContainedWithin() {
 	    // This test checks if the range 4.0-7.0 is fully contained within the range 3.0-9.0
 	    Range range = new Range(3.0, 9.0);
 	    assertTrue(range.intersects(4.0, 7.0)); // Fully inside the range
 	}

 	@Test
 	public void testIntersects_OverlappingBothSides() {
 	    // This test checks if the range 2.0-12.0 completely overlaps the range 4.0-10.0
 	    Range range = new Range(4.0, 10.0);
 	    assertTrue(range.intersects(2.0, 12.0)); // Completely covers the range
 	}

 	@Test
 	public void testIntersects_JustTouchingLowerBound() {
 	    // This test checks if the range 1.0-4.0 just touches the lower bound of the range 4.0-10.0 without overlapping
 	    Range range = new Range(4.0, 10.0);
 	    assertFalse(range.intersects(1.0, 4.0)); // `b1` is exactly `lower`
 	}

 	@Test
 	public void testIntersects_BothBelowRange() {
 	    // This test checks if the range 2.0-5.0 is completely below the range 6.0-12.0
 	    Range range = new Range(6.0, 12.0);
 	    assertFalse(range.intersects(2.0, 5.0)); // Completely below range
 	}

 	@Test
 	public void testIntersects_BothAboveRange() {
 	    // This test checks if the range 13.0-17.0 is completely above the range 6.0-12.0
 	    Range range = new Range(6.0, 12.0);
 	    assertFalse(range.intersects(13.0, 17.0)); // Completely above range
 	}

 	@Test
 	public void testIntersects_SinglePointWithinRange() {
 	    // This test checks if a single point (8.0) inside the range 6.0-12.0 is considered an intersection
 	    Range range = new Range(6.0, 12.0);
 	    assertTrue(range.intersects(8.0, 8.0)); // Single point inside the range
 	}

 	@Test
 	public void testIntersects_SinglePointOutsideRange() {
 	    // This test checks if a single point (13.0) outside the range 6.0-12.0 is considered an intersection
 	    Range range = new Range(6.0, 12.0);
 	    assertFalse(range.intersects(13.0, 13.0)); // Single point outside the range
 	}

 	@Test
 	public void testIntersects_ReverseInputs() {
 	    // This test checks if invalid reversed inputs (10.0-9.0) are correctly handled
 	    Range range = new Range(6.0, 12.0);
 	    assertFalse(range.intersects(10.0, 9.0)); // `b1 < b0`, should be invalid
 	}

 // Intersects Range testing

 	@Test
 	public void testIntersectsWithExactMatch() {
 	    // Test where two ranges are exactly the same
 	    Range range1 = new Range(3.0, 7.0);
 	    Range range2 = new Range(3.0, 7.0);
 	    assertTrue(range1.intersects(range2)); // Exact match, should return true
 	}

 	@Test
 	public void testIntersectsWithPartialOverlapFromLeft() {
 	    // Test where the second range overlaps partially on the left side
 	    Range range1 = new Range(4.0, 8.0);
 	    Range range2 = new Range(2.0, 5.0);
 	    assertTrue(range1.intersects(range2)); // Overlapping on the left side, should return true
 	}

 	@Test
 	public void testIntersectsWithPartialOverlapFromRight() {
 	    // Test where the second range overlaps partially on the right side
 	    Range range1 = new Range(4.0, 8.0);
 	    Range range2 = new Range(6.0, 9.0);
 	    assertTrue(range1.intersects(range2)); // Overlapping on the right side, should return true
 	}

 	@Test
 	public void testIntersectsWithCompleteContainment() {
 	    // Test where one range is fully contained within the other
 	    Range range1 = new Range(3.0, 7.0);
 	    Range range2 = new Range(4.0, 6.0);
 	    assertTrue(range1.intersects(range2)); // range2 is completely inside range1, should return true
 	}

 	@Test
 	public void testIntersectsWithNoOverlap() {
 	    // Test where two ranges do not overlap
 	    Range range1 = new Range(3.0, 6.0);
 	    Range range2 = new Range(7.0, 10.0);
 	    assertFalse(range1.intersects(range2)); // No overlap, should return false
 	}
//?
 	@Test
 	public void testIntersectsWithSinglePointTouchingLowerBound() {
 	    // Test where one range is touching the lower bound of another range
 	    Range range1 = new Range(3.0, 7.0);
 	    Range range2 = new Range(7.0, 10.0);
 	    try { 	    	
 	    	assertFalse(range1.intersects(range2)); // The upper bound of range1 and lower bound of range2 touch, should return true
 	    } catch (Exception e){
 	    	fail("Failed test");
 	    }
 	}

 	@Test
 	public void testIntersectsWithSinglePointTouchingUpperBound() {
 	    // Test where one range is touching the upper bound of another range
 	    Range range1 = new Range(3.0, 7.0);
 	    Range range2 = new Range(0.0, 3.0);
 	    assertFalse(range1.intersects(range2)); // The lower bound of range1 and upper bound of range2 touch, should return true
 	}

 	@Test
 	public void testIntersectsWithReverseOrder() {
 	    // Test where the ranges are given in reverse order, ensuring the method still handles this correctly
 	    Range range1 = new Range(3.0, 7.0);
 	    try { 	    	
 	    	Range range2 = new Range(7.0, 3.0);
 	    } catch (Exception e){
 	    	return;
 	    }
// 	    assertTrue(range1.intersects(range2)); // The order of the bounds doesn't affect the intersection
 	}

 	@Test
 	public void testIntersectsWithDisjointRanges() {
 	    // Test where one range is entirely below the other, with no overlap
 	    Range range1 = new Range(3.0, 5.0);
 	    Range range2 = new Range(6.0, 8.0);
 	    assertFalse(range1.intersects(range2)); // No overlap, should return false
 	}

 	@Test
 	public void testIntersectsWithNullRange() {
 	    // Test where the intersecting range is null
 	    Range range1 = new Range(3.0, 7.0);
 	    Range range2 = null;
 	    try {
 	        range1.intersects(range2); 
 	        fail("Should throw NullPointerException");
 	    } catch (NullPointerException e) {
 	        // Expected exception
 	    }
 	}
 	
 // toString Method Testing 
 	//Returns a string of the range "Range[lower,upper]"
 	
 	// Tests if toString() correctly formats positive ranges
 	 @Test
 	 public void PositiveRange() {
 		 
 		 Range range = new Range(7, 12);
 		 
 		// Expected output: "Range[7.0,12.0]"
 		 
 	     assertEquals("toString range check (positive).",
 	     "Range[7.0,12.0]", range.toString());
 	 }

 	// Tests if toString() correctly formats negative ranges
 	 @Test
 	 public void NegativeRange() {
 		 
 	     Range range = new Range(-10, -3);
 	     
 	     // Expected output: "Range[-10.0,-3.0]" 
 		 
 	     assertEquals("toString range check (negative).",
 	     "Range[-10.0,-3.0]", range.toString());
 	 }
 	 
 	// Tests if toString() correctly formats negative-positive ranges
 	 @Test
 	 public void NegativePositiveRange() {
 		 
 	     Range range = new Range(-7, 10);    
 	     
 	     // Expected output: "Range[-7.0,10.0]"
 	     
 	     assertEquals("toString range check (negative & positive).",
 	     "Range[-7.0,10.0]", range.toString());
 	 }
  
 	// Tests if toString() correctly formats zero ranges
 	 @Test
 	 public void ZeroRange() {

 	     Range range = new Range(0, 0);
 	     
 	     // Expected output: "Range[0.0,0.0]"
 	     
 	     assertEquals("toString range check (both zero).",
 	     "Range[0.0,0.0]", range.toString());
 	 }
    
 	// scale(Range, double) 
 
  	@Test
  	public void scalePositiveFactor() {
  		Range range = new Range(3, 9);
  		Range scaledRange = Range.scale(range, 3);
  		assertEquals("Expected value after scaling.", 27, scaledRange.getUpperBound(), 0.000000001d);
  	}
  	 
 	 
 	@Test
 	public void scaleNegativeFactor() {

 	    Range range = new Range(1, 8);

 	    // Expected: IllegalArgumentException should be thrown

 	    try {
 	        Range scaledRange = Range.scale(range, -2);
 	    } catch (IllegalArgumentException e) {
 	    	assertEquals("Expected IllegalArgumentException when scaling by a negative factor.", 
                     IllegalArgumentException.class, e.getClass());
 	    }
 	}
 	
 // shift(Range, double, boolean)
 
 	@Test
 	public void testShiftByPositiveWithZeroCrossingAllowed() {
 	    Range range = new Range(5, 10);
 	    Range shiftedRange = Range.shift(range, 120, true);
 	    assertEquals("The upper bound after shifting should be 130", 130, shiftedRange.getUpperBound(), .000000001d);
 	}

 	@Test
 	public void testShiftWithoutZeroCrossingWhenValueIsNegative() {
 	    Range range = new Range(-3, 7);
 	    Range shiftedRange = Range.shift(range, 50, false);
 	    assertEquals("The upper bound after shifting should be 57", 57, shiftedRange.getUpperBound(), .000000001d);
 	}

 	@Test
 	public void testShiftWithoutZeroCrossingWhenValueIsZero() {
 	    Range range = new Range(0, 8);
 	    Range shiftedRange = Range.shift(range, 75, false);
 	    assertEquals("The upper bound after shifting should be 83", 83, shiftedRange.getUpperBound(), .000000001d);
 	}

 // shift(Range, double) 
 	
 	@Test
 	public void testShiftLowerBoundWithPositiveDouble() {
 	    Range range = new Range(3, 8);
 	    Range shiftedRange = Range.shift(range, 5.4);
 	    assertEquals("The lower bound after shifting should be 8.4", 8.4, shiftedRange.getLowerBound(), .000000001d);
 	}

 	@Test
 	public void testShiftUpperBoundWithPositiveDouble() {
 	    Range range = new Range(4, 9);
 	    Range shiftedRange = Range.shift(range, 7.5);
 	    assertEquals("The upper bound after shifting should be 16.5", 16.5, shiftedRange.getUpperBound(), .000000001d);
 	}

 	@Test
 	public void testShiftLowerBoundWithNegativeDouble() {
 	    Range range = new Range(5, 10);
 	    Range shiftedRange = Range.shift(range, -2.5);
 	    assertEquals("The lower bound after shifting should be 2.5", 2.5, shiftedRange.getLowerBound(), .000000001d);
 	}


 	@Test
 	public void testShiftUpperBoundWithNegativeDouble() {
 	    Range range = new Range(6, 12);
 	    Range shiftedRange = Range.shift(range, -3.7);
 	    assertEquals("The upper bound after shifting should be 8.3", 8.3, shiftedRange.getUpperBound(), .000000001d);
 	}

 // equals testing
    @Test
    public void testRangesAreEqual() {
            Range exampleRangeOne = new Range(-1.0,2.0);
            Range exampleRangeTwo = exampleRangeOne;
            boolean result = exampleRangeOne.equals(exampleRangeTwo);
            assertTrue(result);
    }
    @Test
    public void testRangesAreNotEqualUnequalNumbers() {
            Range exampleRangeOne = new Range(-1.0,2.0);
            Range exampleRangeTwo = new Range(4.0,5.0);
            boolean result = exampleRangeOne.equals(exampleRangeTwo);
            assertTrue(!result);
    }
    @Test
    public void testRangesAreNotEqualInvalidCompare() {
            Range exampleRangeOne = new Range(-1.0,2.0);
            double exampleValue = 5.0;
            boolean result = exampleRangeOne.equals(exampleValue);
            assertTrue(!result);
    }
   
    @Test
    public void testRangesAreNotEqualDifferentLower() {
            Range exampleRangeOne = new Range(-1.0,2.0);
            Range exampleRangeTwo = new Range(1.0,2.0);
            boolean result = exampleRangeOne.equals(exampleRangeTwo);
            assertTrue(!result);
    }
   
    @Test
    public void testRangesAreNotEqualDifferentUpper() {
            Range exampleRangeOne = new Range(-1.0,2.0);
            Range exampleRangeTwo = new Range(-1.0,10.0);
            boolean result = exampleRangeOne.equals(exampleRangeTwo);
            assertTrue(!result);
    }
   
    // expand testing
    @Test
    public void testSuccessfulExpand() {
            Range baseRange = new Range(-2.0, 1.0);
            Range newRange = Range.expand(baseRange, 1,1);
            Range resultRange = new Range(-5.0,4.0);
            assertEquals(resultRange, newRange);
    }
   
    @Test
    public void testLowerBoundBecomesGreaterThanUpperBoundAfterExpand() {
           Range baseRange = new Range(-50.0, 50.0);
           Range newRange = Range.expand(baseRange, 0, -2);
           Range resultRange = new Range(-100,-100);
           assertEquals(resultRange, newRange);
    }
    
 // combiningIgnoringNaN testing
   @Test
   public void oneNullARangeCombineNan() {
           double NaN = Double.NaN;
           Range exampleRangeNaN = new Range(NaN,NaN);
           Range resultRange = Range.combineIgnoringNaN(null, exampleRangeNaN);
           assertNull(resultRange);
   }
    
    @Test
    public void oneNullAValidBRangeCombineNan() {
           Range exampleRangeOne = new Range(1,4);
           Range resultRange = Range.combineIgnoringNaN(null, exampleRangeOne);
           assertNotNull(resultRange);
           assertEquals(exampleRangeOne, resultRange);
   }
    @Test
   public void oneNullBRangeCombineNan() {
           double NaN = Double.NaN;
           Range exampleRangeNaN = new Range(NaN,NaN);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeNaN, null);
           assertNull(resultRange);
   }
    
    @Test
    public void oneNullBValidARangeCombineNan() {
           Range exampleRangeOne = new Range(1,4);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, null);
           assertNotNull(resultRange);
           assertEquals(exampleRangeOne, resultRange);
    }
   
    @Test
    public void bothNaNRangeCombineNan() {
           double NaN = Double.NaN;
           Range exampleRangeNaN = new Range(NaN,NaN);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeNaN, exampleRangeNaN);
           assertNull(resultRange);
    }
    @Test
    public void bothNaNLowerNanRangeCombineNan() {
           Range bothNaN = new Range(Double.NaN, Double.NaN);
           Range exampleRangeNaN = new Range(Double.NaN,7.0);
           Range resultRange = Range.combineIgnoringNaN(bothNaN, exampleRangeNaN);
           assertNotNull(resultRange);
    }
    
    @Test
    public void bothNaNUpperNanRangeCombineNan() {
           double NaN = Double.NaN;
           Range exampleRangeNaN = new Range(1.0,NaN);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeNaN, exampleRangeNaN);
           assertNotNull(resultRange);
   }
    
   @Test
   public void bothNullRangeCombineNan() {
           Range exampleRangeOne = null;
           Range exampleRangeTwo = null;
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, exampleRangeTwo);
           assertNull(resultRange);
   }
   
   @Test
   public void combineSeperateRangesCombineNan() {
           Range exampleRangeOne = new Range(6, 8);
           Range exampleRangeTwo = new Range(-7, -3);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, exampleRangeTwo);
           assertNotNull(resultRange);
           Range finalRange = new Range(-7,8);
           assertEquals(finalRange, resultRange);
   }
   
   @Test
   public void combineSimilarRangesCombineNan() {
           Range exampleRangeOne = new Range(7, 10);
           Range exampleRangeTwo = new Range(5, 8);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, exampleRangeTwo);
           assertNotNull(resultRange);
           Range finalRange = new Range(5,10);
           assertEquals(finalRange, resultRange);
   }
   
   @Test
   public void combineEqualRangesCombineNan() {
           Range exampleRangeOne = new Range(-2,9);
           Range exampleRangeTwo = new Range(-2,9);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, exampleRangeTwo);
           assertNotNull(resultRange);
           Range finalRange = new Range(-2,9);
           assertEquals(finalRange, resultRange);
   }
    
   
     	// getLowerBound Test :: Mark Jimenez (30193166)
	 	 // getLowerBound Test :: Mark Jimenez (30193166)
	 	 // getLowerBound Test :: Mark Jimenez (30193166)
	 	@Test
	    public void testGetLowerBoundWithPositiveRange() {
	        // This test checks if the lower bound of the range from 3.0 to 9.0 is correctly returned as 3.0
	        Range range = new Range(3.0, 9.0);
	        assertEquals(3.0, range.getLowerBound(), 0.0001);
	    }

	    @Test
	    public void testGetLowerBoundWithNegativeRange() {
	        // This test checks if the lower bound of the range from -8.0 to -3.0 is correctly returned as -8.0
	        Range range = new Range(-8.0, -3.0);
	        assertEquals(-8.0, range.getLowerBound(), 0.0001);
	    }

	    @Test
	    public void testGetLowerBoundWithMixedSignRange() {
	        // This test checks if the lower bound of the range from -4.0 to 6.0 is correctly returned as -4.0
	        Range range = new Range(-4.0, 6.0);
	        assertEquals(-4.0, range.getLowerBound(), 0.0001);
	    }

	    @Test
	    public void testGetLowerBoundWithZeroRange() {
	        // This test checks if the lower bound of the range from 0.0 to 0.0 is correctly returned as 0.0
	        Range range = new Range(0.0, 0.0);
	        assertEquals(0.0, range.getLowerBound(), 0.0001);
	    }

	    @Test
	    public void testGetLowerBoundWithVeryLargeNumbers() {
	        // This test checks if the lower bound of the range from 2.0e9 to 2.0e9 + 7.0 is correctly returned as 2.0e9
	        Range range = new Range(2.0e9, 2.0e9 + 7.0);
	        assertEquals(2.0e9, range.getLowerBound(), 0.0001);
	    }

	    @Test
	    public void testConstructorThrowsExceptionForInvalidRangeforLowerBound() {
	        // This test checks if an exception is thrown when the lower bound is greater than the upper bound
	        try {
	            Range range = new Range(7.0, 4.0); 
	            range.getLowerBound();  
	        } catch (IllegalArgumentException e) {
	            assertEquals("Range(double, double): require lower (7.0) <= upper (4.0).", e.getMessage());
	        }
	    }
	    
	 // getLength Test :: Mark Jimenez (30193166)
	 	// getLength Test :: Mark Jimenez (30193166)
	 	// getLength Test :: Mark Jimenez (30193166)

	 	@Test
	 	public void testGetLengthWithPositiveRange() {
	 	    // This test checks if the length of the range from 3.0 to 9.0 is correctly calculated as 6.0
	 	    Range range = new Range(3.0, 9.0);
	 	    assertEquals(6.0, range.getLength(), 0.0001);
	 	}

	 	@Test
	 	public void testGetLengthWithNegativeRange() {
	 	    // This test checks if the length of the range from -8.0 to -3.0 is correctly calculated as 5.0
	 	    Range range = new Range(-8.0, -3.0);
	 	    assertEquals(5.0, range.getLength(), 0.0001);
	 	}

	 	@Test
	 	public void testGetLengthWithMixedSignRange() {
	 	    // This test checks if the length of the range from -4.0 to 6.0 is correctly calculated as 10.0
	 	    Range range = new Range(-4.0, 6.0);
	 	    assertEquals(10.0, range.getLength(), 0.0001);
	 	}

	 	@Test
	 	public void testGetLengthWithZeroRange() {
	 	    // This test checks if the length of the range from 0.0 to 0.0 is correctly calculated as 0.0
	 	    Range range = new Range(0.0, 0.0);
	 	    assertEquals(0.0, range.getLength(), 0.0001);
	 	}

	 	@Test
	 	public void testGetLengthWithVeryLargeNumbers() {
	 	    // This test checks if the length of the range from 2.0e9 to 2.0e9 + 7.0 is correctly calculated as 7.0
	 	    Range range = new Range(2.0e9, 2.0e9 + 7.0);
	 	    assertEquals(7.0, range.getLength(), 0.0001);
	 	}

	 	@Test
	 	public void InvalidRangeTest() {
	 	    // This test ensures that an IllegalArgumentException is thrown when the lower bound is greater than the upper bound
	 	    try {
	 	        Range range = new Range(7.0, 4.0);
	 	    } catch (IllegalArgumentException e) {
	 	        assertEquals("Range(double, double): require lower (7.0) <= upper (4.0).", e.getMessage());
	 	    }
	 	}
	 	
	 	
	 	// getUpperBound Test :: Mark Jimenez (30193166)
	 	// getUpperBound Test :: Mark Jimenez (30193166)
	 	// getUpperBound Test :: Mark Jimenez (30193166)
	 	
	 	@Test
	 	public void testGetUpperBoundWithPositiveRange() {
	 	    // This test checks if the upper bound of the range from 3.0 to 9.0 is correctly returned as 9.0
	 	    Range range = new Range(3.0, 9.0);
	 	    assertEquals(9.0, range.getUpperBound(), 0.0001);
	 	}

	 	@Test
	 	public void testGetUpperBoundWithNegativeRange() {
	 	    // This test checks if the upper bound of the range from -8.0 to -3.0 is correctly returned as -3.0
	 	    Range range = new Range(-8.0, -3.0);
	 	    assertEquals(-3.0, range.getUpperBound(), 0.0001);
	 	}

	 	@Test
	 	public void testGetUpperBoundWithMixedSignRange() {
	 	    // This test checks if the upper bound of the range from -4.0 to 6.0 is correctly returned as 6.0
	 	    Range range = new Range(-4.0, 6.0);
	 	    assertEquals(6.0, range.getUpperBound(), 0.0001);
	 	}

	 	@Test
	 	public void testGetUpperBoundWithZeroRange() {
	 	    // This test checks if the upper bound of the range from 0.0 to 0.0 is correctly returned as 0.0
	 	    Range range = new Range(0.0, 0.0);
	 	    assertEquals(0.0, range.getUpperBound(), 0.0001);
	 	}

	 	@Test
	 	public void testGetUpperBoundWithVeryLargeNumbers() {
	 	    // This test checks if the upper bound of the range from 2.0e9 to 2.0e9 + 7.0 is correctly returned as 2.0e9 + 7.0
	 	    Range range = new Range(2.0e9, 2.0e9 + 7.0);
	 	    assertEquals(2.0e9 + 7.0, range.getUpperBound(), 0.0001);
	 	}

	 	@Test
	 	public void testConstructorThrowsExceptionForInvalidRange() {
	 	    // This test checks if an exception is thrown when the lower bound is greater than the upper bound
	 	    try {
	 	        Range range = new Range(7.0, 4.0); 
	 	        range.getUpperBound();  
	 	    } catch (IllegalArgumentException e) {
	 	        assertEquals("Range(double, double): require lower (7.0) <= upper (4.0).", e.getMessage());
	 	    }
	 	}

	 // isNaNRange Test :: LANA HAWA (30187079)
		// isNaNRange Test :: LANA HAWA (30187079)

		@Test
	    public void testNormalRangeIsNotNaN() {
	        Range r = new Range(1.0, 2.0);
	        assertFalse("A normal range should return false for isNaNRange()", r.isNaNRange());
	    }

	    @Test
	    public void testLowerBoundNaNOnly() {
	        Range r = new Range(Double.NaN, 2.0);
	        assertFalse("A range with only a NaN lower bound should return false for isNaNRange()", r.isNaNRange());
	    }

	    @Test
	    public void testUpperBoundNaNOnly() {
	        Range r = new Range(1.0, Double.NaN);
	        assertFalse("A range with only a NaN upper bound should return false for isNaNRange()", r.isNaNRange());
	    }

	    @Test
	    public void testBothBoundsNaN() {
	        Range r = new Range(Double.NaN, Double.NaN);
	        assertTrue("A range with both bounds as NaN should return true for isNaNRange()", r.isNaNRange());
	    }
	    
	 // expandToInclude Test :: LANA HAWA (30187079)
	    // expandToInclude Test :: LANA HAWA (30187079)
	    @Test
	    public void testExpandToIncludeWithNullRange() {
	        double value = 5.0;
	        Range result = Range.expandToInclude(null, value);
	        Range expected = new Range(value, value);
	        assertEquals("Expanding a null range should create a range with equal bounds to the value.",
	                expected, result);
	    }

	    
	    @Test
	    public void testExpandToIncludeValueWithinRange() {
	        Range range = new Range(2.0, 8.0);
	        double value = 5.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("Expanding a range with a value inside the range should return the same range.",
	                range, result);
	    }

	   
	    @Test
	    public void testExpandToIncludeValueEqualsLower() {
	        Range range = new Range(2.0, 8.0);
	        double value = 2.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("Expanding a range with a value equal to the lower bound should return the same range.",
	                range, result);
	    }

	   
	    @Test
	    public void testExpandToIncludeValueEqualsUpper() {
	        Range range = new Range(2.0, 8.0);
	        double value = 8.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("Expanding a range with a value equal to the upper bound should return the same range.",
	                range, result);
	    }

	    @Test
	    public void testExpandToIncludeValueLessThanLower() {
	        Range range = new Range(2.0, 8.0);
	        double value = 1.0;
	        Range result = Range.expandToInclude(range, value);
	        Range expected = new Range(value, 8.0);
	        assertEquals("Expanding a range with a value less than the lower bound should update the lower bound.",
	                expected, result);
	    }

	    @Test
	    public void testExpandToIncludeValueGreaterThanUpper() {
	        Range range = new Range(2.0, 8.0);
	        double value = 10.0;
	        Range result = Range.expandToInclude(range, value);
	        Range expected = new Range(2.0, value);
	        assertEquals("Expanding a range with a value greater than the upper bound should update the upper bound.",
	                expected, result);
	    }

	    @Test
	    public void testExpandToIncludeEqualBoundsValueLessThan() {
	        Range range = new Range(5.0, 5.0);
	        double value = 3.0;
	        Range result = Range.expandToInclude(range, value);
	        Range expected = new Range(value, 5.0);
	        assertEquals("Expanding a range with equal bounds when the value is less should update the lower bound.",
	                expected, result);
	    }


	    @Test
	    public void testExpandToIncludeEqualBoundsValueGreaterThan() {
	        Range range = new Range(5.0, 5.0);
	        double value = 7.0;
	        Range result = Range.expandToInclude(range, value);
	        Range expected = new Range(5.0, value);
	        assertEquals("Expanding a range with equal bounds when the value is greater should update the upper bound.",
	                expected, result);
	    }


	    @Test
	    public void testExpandToIncludeEqualBoundsValueEqual() {
	        Range range = new Range(5.0, 5.0);
	        double value = 5.0;
	        Range result = Range.expandToInclude(range, value);
	        assertEquals("Expanding a range with equal bounds and a value equal to them should return the same range.",
	                range, result);
	    }
	    
	 // hashCode Test :: LANA HAWA (30187079)
	    // hashCode Test :: LANA HAWA (30187079)
	    @Test
	    public void testHashCodeConsistency() {
	        Range range = new Range(1.0, 2.0);
	        int hash1 = range.hashCode();
	        int hash2 = range.hashCode();
	        int hash3 = range.hashCode();
	        assertEquals("hashCode should be consistent across multiple calls", hash1, hash2);
	        assertEquals("hashCode should be consistent across multiple calls", hash2, hash3);
	    }
	    
	    @Test
	    public void testEqualRangesHaveSameHashCode() {
	        Range r1 = new Range(1.0, 2.0);
	        Range r2 = new Range(1.0, 2.0);
	        assertTrue("Ranges with identical bounds should be equal", r1.equals(r2));
	        assertEquals("Equal ranges should have the same hash code", r1.hashCode(), r2.hashCode());
	    }
	    

	    @Test
	    public void testDifferentRangesHaveDifferentHashCodes() {
	        Range r1 = new Range(1.0, 2.0);
	        Range r2 = new Range(1.0, 3.0);
	        assertFalse("Ranges with different bounds should not be equal", r1.equals(r2));
	        assertNotEquals("Different ranges should ideally have different hash codes", r1.hashCode(), r2.hashCode());
	    }
	    

	    @Test
	    public void testHashCodeWithNegativeRange() {
	        Range range = new Range(-5.0, -1.0);
	        int expected = range.hashCode();
	        assertEquals("Hash code for a negative range should be consistent", expected, range.hashCode());
	    }
	    

	    @Test
	    public void testHashCodeWithInfiniteBounds() {
	        Range r1 = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	        Range r2 = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	 
	        assertEquals("Ranges with infinite bounds should have equal hash codes if they are equal", 
	                r1.hashCode(), r2.hashCode());
	    }
	    
	    @Test
	    public void testHashCodeWithNaNBounds() {
	        Range r1 = new Range(Double.NaN, Double.NaN);
	        Range r2 = new Range(Double.NaN, Double.NaN);

	        int hash1 = r1.hashCode();
	        assertEquals("Hash code should be consistent for NaN range", hash1, r1.hashCode());
	        
	        if (r1.equals(r2)) {
	            assertEquals("Equal NaN ranges should have the same hash code", r1.hashCode(), r2.hashCode());
	        }
	    }
	    
	    // contain testing
	    @Test
	    public void rangeContainsValueWithinBounds() {
	    	Range r1 = new Range(-5.0,5.0);
	    	double targetValue = 1.0;
	    	assertTrue(r1.contains(targetValue));
	    }
	    @Test
	    public void rangeDoesNotContainValueWithinBoundsLower() {
	    	Range r1 = new Range(-5.0,5.0);
	    	double targetValue = -6.0;
	    	assertFalse(r1.contains(targetValue));
	    }
	    @Test
	    public void rangeDoesNotContainValueWithinBoundsUpper() {
	    	Range r1 = new Range(-5.0,5.0);
	    	double targetValue = 6.0;
	    	assertFalse(r1.contains(targetValue));
	    }
	    @Test
	    public void rangeContainsValueEqualLower() {
	    	Range r1 = new Range(-5.0,5.0);
	    	double targetValue = -5.0;
	    	assertTrue(r1.contains(targetValue));
	    }
	    @Test
	    public void rangeContainsValueEqualUpper() {
	    	Range r1 = new Range(-5.0,5.0);
	    	double targetValue = 5.0;
	    	assertTrue(r1.contains(targetValue));
	    }
	    // shiftWithNoZeroCrossing testing 
	    @Test
	    public void shiftNoZeroCrossRangePositivePositive() {
	    	Range r1 = new Range(5,10);
	    	double targetValue = 5.0;
	    	Range resultRange = Range.shift(r1, targetValue, false);
	    	Range expectedRange = new Range(10,15);
	    	assertEquals(expectedRange, resultRange);		
	    }
	    @Test
	    public void shiftNoZeroCrossLowerBoundNegativePostive() {
	    	Range r1 = new Range(-5,10);
	    	double targetValue = 5.0;
	    	Range resultRange = Range.shift(r1, targetValue, false);
	    	Range expectedRange = new Range(0,15);
	    	assertEquals(expectedRange, resultRange);		
	    }
	    
	    @Test
	    public void shiftNoZeroCrossLowerBoundNegativeNegative() {
	    	Range r1 = new Range(-10,-5);
	    	double targetValue = 5.0;
	    	Range resultRange = Range.shift(r1, targetValue, false);
	    	Range expectedRange = new Range(-5,0);
	    	assertEquals(expectedRange, resultRange);	
	    }
	    @Test
	    public void shiftNoZeroCrossRangeNegativeZero() {
	    	Range r1 = new Range(-5,0);
	    	double targetValue = 5.0;
	    	Range resultRange = Range.shift(r1, targetValue, false);
	    	Range expectedRange = new Range(0,5);
	    	assertEquals(expectedRange, resultRange);		
	    }
	    @Test
	    public void shiftNoZeroCrossLowerBoundZeroPostive() {
	    	Range r1 = new Range(0,10);
	    	double targetValue = 5.0;
	    	Range resultRange = Range.shift(r1, targetValue, false);
	    	Range expectedRange = new Range(5,15);
	    	assertEquals(expectedRange, resultRange);		
	    }
	    
	    @Test
	    public void minimumValueBCombineIgnoringNaN() {
	    	Range testRangeNan = new Range(Double.NaN,10.0);
	    	Range testRangeHigh = new Range(1.0,10.0);
	    	Range result = Range.combineIgnoringNaN(testRangeHigh, testRangeNan);
	    	Range targetRange = new Range(1.0,10.0);
	    	assertEquals(targetRange, result);
	    }
	    @Test
	    public void maximumValueBCombineIgnoringNaN() {
	    	Range testRangeNan = new Range(-10.0,Double.NaN);
	    	Range testRangeHigh = new Range(1.0,10.0);
	    	Range result = Range.combineIgnoringNaN(testRangeHigh, testRangeNan);
	    	Range targetRange = new Range(-10.0,10.0);
	    	assertEquals(targetRange, result);
	    }
	    @Test 
		public void valuePlusOneUpperBoundConstrain() {
			Range r1= new Range(5, 10);
			assertEquals(10, r1.constrain(11), 0.00001d);
		}
	    @Test 
		public void valueMinusOneLowerBoundConstrain() {
			Range r1= new Range(5, 10);
			assertEquals(5, r1.constrain(4), 0.00001d);
		}
	    @Test 
		public void usingZeroLowerConstrain() {
			Range r1= new Range(1, 10);
			assertEquals(1, r1.constrain(0), 0.00001d);
		}
	    @Test 
		public void usingZeroUpperConstrain() {
			Range r1= new Range(-10, -1);
			assertEquals(-1, r1.constrain(0), 0.00001d);
		}
	    @Test
	    public void combineExactResult() {
			Range r1 = new Range(5, 10);
			Range r2 = new Range(15, 20);
			Range resultRange = new Range(5, 20);
	    	assertEquals(resultRange, Range.combine(r1, r2));

	    }
	    @Test
	    public void oneNullAValidBRangeCombineNanExact() {
           Range exampleRangeOne = new Range(1,4);
           Range resultRange = Range.combineIgnoringNaN(null, exampleRangeOne);
           assertEquals(resultRange, exampleRangeOne);
	    }
	    @Test
	    public void oneNullBValidBRangeCombineNanExact() {
           Range exampleRangeOne = new Range(1,4);
           Range resultRange = Range.combineIgnoringNaN(exampleRangeOne, null);
           assertEquals(resultRange, exampleRangeOne);
	    }
	    
	    
}
