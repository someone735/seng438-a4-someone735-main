package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.*;
import org.junit.*;

import org.jmock.Expectations;
import org.jmock.Mockery;


public class DataUtilitiesTest {
	
	// calculateRowTotal Testing
	@Test
    public void noMockSimpleCalculateRowTotal() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(1.0, 0, 0);
            exampleDataUlt.addValue(3.0, 1, 0);
            
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 0);
            assertEquals(1.0, result, .000000001d);
    }
    @Test
    public void noMocknegativeValueCalculateRowTotal() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1);
            assertEquals(-7.0, result, .000000001d);
    }
    
    @Test
    public void noMockCalculateRowTotalOf2x2Table() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(5.0, 0, 1);
            exampleDataUlt.addValue(7.0, 1, 0);
            exampleDataUlt.addValue(11.0, 1, 1);
            
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1);
            assertEquals(18.0, result, .000000001d);
    }

    //calculateColumnTotal
    
    @Test
    public void testSimpleColumnTotal() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(2.0, 0, 0);
        data.addValue(4.0, 1, 0);

        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(6.0, result, .000000001d);
    }

    @Test
    public void testColumnTotalWithNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(5.0, 0, 0);
        data.addValue(-8.0, 1, 0);

        double result = DataUtilities.calculateColumnTotal(data, 0);
        assertEquals(-3.0, result, .000000001d);
    }

    @Test
    public void testColumnTotalFor2x2Matrix() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(2.0, 0, 0);
        data.addValue(6.0, 1, 0);
        data.addValue(8.0, 0, 1);
        data.addValue(10.0, 1, 1);

        double result = DataUtilities.calculateColumnTotal(data, 1);
        assertEquals(18.0, result, .000000001d);
    }

	    
    // getCumulativePercentages Method Testing
		 
    @Test
    public void testCumulativePercentagesWithSingleValue() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue("A", 5);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals("The cumulative percentage of a single value should be 1.0", 1.0, result.getValue("A").doubleValue(), .000000001d);
    }

    @Test
    public void testCumulativePercentagesWithMultipleValues() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue("A", 10);
        values.addValue("B", 20);
        values.addValue("C", 30);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.1666666667, result.getValue("A").doubleValue(), .000000001d);
        assertEquals(0.5, result.getValue("B").doubleValue(), .000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), .000000001d);
    }

    @Test
    public void testCumulativePercentagesWithZeroValues() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue("A", 0);
        values.addValue("B", 0);
        values.addValue("C", 10);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.0, result.getValue("A").doubleValue(), .000000001d);
        assertEquals(0.0, result.getValue("B").doubleValue(), .000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), .000000001d);
    }

    @Test
    public void testCumulativePercentagesWithAllZeroes() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue("A", 0);
        values.addValue("B", 0);
        values.addValue("C", 0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertTrue(Double.isNaN(result.getValue("A").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("B").doubleValue()));
        assertTrue(Double.isNaN(result.getValue("C").doubleValue()));
    }

    @Test
    public void testCumulativePercentagesWithNullValues() {
        DefaultKeyedValues values = new DefaultKeyedValues();
        values.addValue("A", 10);
        values.addValue("B", null); 
        values.addValue("C", 30);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.25, result.getValue("A").doubleValue(), .000000001d);
        assertEquals(0.25, result.getValue("B").doubleValue(), .000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), .000000001d);
    }

    @Test
    public void testCumulativePercentagesWithEmptyDataset() {
        DefaultKeyedValues values = new DefaultKeyedValues(); 

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0, result.getItemCount()); 
    }
	    
	
		/**
	 * Test for DataUtilities.createNumberArray with valid nonzero values.
	 * This test verifies that the returned array is non-null, has the expected length,
	 * and that each element matches the corresponding input value.
	 */
	@Test
	public void createNumberArrayWithValidValues() {
	    double[] input = {1.5, 2.5, 3.5};
	    Number[] result = DataUtilities.createNumberArray(input);

	    // Check that the result is not null and has the correct length.
	    assertNotNull("Result should not be null", result);
	    assertEquals("Array length should match", input.length, result.length);

	    // Compare each value numerically (with a null-check).
	    for (int i = 0; i < input.length; i++) {
	        assertNotNull("Element at index " + i + " should not be null", result[i]);
	        assertEquals("Mismatch at index " + i, input[i], result[i].doubleValue(), 0.00001);
	    }
	}

	/**
	 * Test for DataUtilities.createNumberArray with an empty input array.
	 * Verifies that an empty input array returns an empty Number array.
	 */
	@Test
	public void createNumberArrayWithEmptyArray() {
	    double[] input = {};
	    Number[] expected = {};
	    Number[] result = DataUtilities.createNumberArray(input);
	    assertArrayEquals("Empty arrays should be equal", expected, result);
	}

	/**
	 * Test for DataUtilities.createNumberArray with negative numbers.
	 * This test checks that each negative value is properly converted.
	 */
	@Test
	public void createNumberArrayWithNegativeNumbers() {
	    double[] input = {-1.2, -2.3, -3.4};
	    Number[] result = DataUtilities.createNumberArray(input);

	    // Ensure array lengths match.
	    assertEquals("Array length should match", input.length, result.length);

	    // Compare each element numerically, ensuring no element is null.
	    for (int i = 0; i < input.length; i++) {
	        assertNotNull("Element at index " + i + " should not be null", result[i]);
	        assertEquals("Mismatch at index " + i, input[i], result[i].doubleValue(), 0.00001);
	    }
	}

	/**
	 * Test for DataUtilities.createNumberArray with zero values.
	 * This test verifies that the returned array for zero values matches the expected values.
	 */
	@Test
	public void createNumberArrayWithZeroValues() {
	    double[] input = {0.0, 0.0, 0.0};
	    Number[] result = DataUtilities.createNumberArray(input);

	    assertNotNull("Result should not be null", result);

	    // Define expected output as a Double array.
	    Double[] expected = {0.0, 0.0, 0.0};
	    assertArrayEquals("Arrays with zeroes should be equal", expected, result);
	}
	/**
     * Test 5 for createNumberArray: Using assertArrayEquals directly.
     * Purpose: Verify that the whole returned array exactly matches the expected array.
     * (This test is concise but may fail if there’s a type mismatch.)
     */
    @Test
    public void createNumberArrayAssertArrayEquals() {
        double[] input = {3.3, 4.4, 5.5};
        // Explicitly declare expected as Double[] so that type matches.
        Double[] expected = {3.3, 4.4, 5.5};
        Number[] result = DataUtilities.createNumberArray(input);
        assertArrayEquals("Arrays should be equal", expected, result);
    }

    /**
     * Test for DataUtilities.createNumberArray2D with valid values.
     * This test verifies that a 2D array of doubles is correctly converted to a 2D Number array.
     */
    @Test
    public void createNumberArray2DWithValidValues() {
        double[][] input = {
            {1.5, 2.5},
            {3.5, 4.5}
        };
        // Define the expected 2D array. (You might need to use a loop to compare the rows if assertArrayEquals does not handle 2D arrays directly.)
        Number[][] expected = {
            {1.5, 2.5},
            {3.5, 4.5}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        // Verify that the result is not null.
        assertNotNull("Result should not be null", result);
        // Check that the outer array length matches.
        assertEquals("Outer array length should match", input.length, result.length);

        // Compare each row.
        for (int i = 0; i < input.length; i++) {
            // Check that each row is not null and has the correct length.
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row length for row " + i + " should match", input[i].length, result[i].length);

            // Compare each element in the row.
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test for DataUtilities.createNumberArray2D with an empty 2D array.
     * Verifies that an empty 2D input array returns an empty 2D Number array.
     */
    @Test
    public void createNumberArray2DWithEmptyArray() {
        double[][] input = {};
        Number[][] expected = {};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals("Empty 2D arrays should be equal", expected, result);
    }
    /**
     * Test 3 for createNumberArray2D: 2D array with negative numbers.
     * Purpose: Check that a 2D array containing negative values is converted correctly.
     */
    @Test
    public void createNumberArray2DNegativeNumbers() {
        double[][] input = {
            {-1.1, -2.2},
            {-3.3, -4.4}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test 4 for createNumberArray2D: 2D array with all zero values.
     * Purpose: Verify that a 2D array of zeros is converted correctly,
     * with every element equal to 0.0.
     */
    @Test
    public void createNumberArray2DZeroValues() {
        double[][] input = {
            {0.0, 0.0},
            {0.0, 0.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }

    /**
     * Test 5 for createNumberArray2D: 2D array with mixed values.
     * Purpose: Verify that a 2D array containing positive, negative, and zero values
     * is converted correctly into a 2D Number array.
     */
    @Test
    public void createNumberArray2DMixedValues() {
        double[][] input = {
            {1.1, 0.0, -1.1},
            {2.2, -2.2, 0.0}
        };
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Outer array length should match", input.length, result.length);
        for (int i = 0; i < input.length; i++) {
            assertNotNull("Row " + i + " should not be null", result[i]);
            assertEquals("Row " + i + " length should match", input[i].length, result[i].length);
            for (int j = 0; j < input[i].length; j++) {
                assertNotNull("Element at (" + i + "," + j + ") should not be null", result[i][j]);
                assertEquals("Mismatch at (" + i + "," + j + ")",
                             input[i][j], result[i][j].doubleValue(), 0.00001);
            }
        }
    }
    
	// clone(double[][]) 
	
    @Test
    public void testCloneArrayWithEmptyData() {
        double[][] originalArray = { {} };
        double[][] clonedArray = DataUtilities.clone(originalArray);
        assertArrayEquals("The cloned array should be an empty 2D array { {} } but it failed.", originalArray, clonedArray);
    }

    @Test
    public void testCloneArrayWithNull() {
        double[][] originalArray = { { 8, 12, 20 }, null };
        double[][] clonedArray = DataUtilities.clone(originalArray);
        assertArrayEquals("The cloned array should be { {8, 12, 20}, null } but it failed.", originalArray, clonedArray);
    }

 // equal testing
    @Test
    public void testEqualArrays() {
            double[][] exampleArrayOne = {{1.0, 2.0, 3.0},
                                                                           {4.0, 5.0, 6.0},
                                                                           {7.0, 8.0, 9.0}};
            double[][] exampleArrayTwo = exampleArrayOne;
            boolean result = DataUtilities.equal(exampleArrayOne, exampleArrayTwo);
            assertTrue(result);
    }
    @Test
    public void testEqualWithNullInputA() {
            double[][] exampleArrayOne = {{1.0, 2.0, 3.0},
                                   {4.0, 5.0, 6.0},
                                   {7.0, 8.0, 9.0}};
            boolean result = DataUtilities.equal(null, exampleArrayOne);
            assertFalse(result);
    }
    @Test
    public void testEqualWithNullInputB() {
            double[][] exampleArrayOne = {{1.0, 2.0, 3.0},
                                   {4.0, 5.0, 6.0},
                                   {7.0, 8.0, 9.0}};
            boolean result = DataUtilities.equal(exampleArrayOne, null);
            assertFalse(result);
    }
   
    @Test
    public void testEqualWithNullInputBoth() {
            boolean result = DataUtilities.equal(null, null);
            assertTrue(result);
    }
   
    @Test
    public void testEqualWithUnequalLengthsCol() {
            double[][] exampleArrayOne = {{1.0, 2.0, 3.0},
	                                       {4.0, 5.0, 6.0},
	                                       {7.0, 8.0, 9.0}};
            double[][] exampleArrayTwo = {{1.0, 2.0},
                                           {4.0, 5.0},
                                           {7.0, 8.0}};
            boolean result = DataUtilities.equal(exampleArrayOne, exampleArrayTwo);
            assertFalse(result);
    }
   
    @Test
    public void testEqualWithUnequalLengthsRow() {
            double[][] exampleArrayOne = {{1.0, 2.0, 3.0},
	                                       {4.0, 5.0, 6.0},
	                                       {7.0, 8.0, 9.0}};
            double[][] exampleArrayTwo = {{1.0, 2.0, 3.0},
	                                       {4.0, 5.0, 6.0}};
            boolean result = DataUtilities.equal(exampleArrayOne, exampleArrayTwo);
            assertFalse(result);
    }
    
 // CalculateRowTotalValidCols testing
    @Test
    public void noMockSimpleCalculateRowTotalNullValidCols() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(1.0, 0, 0);
            exampleDataUlt.addValue(3.0, 1, 0);
            int[] validColumns = {};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 0, validColumns);
            assertEquals(0.0, result, .000000001d);
    }
    
    @Test
    public void noMockSimpleCalculateRowTotalValidCols() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(1.0, 0, 0);
            exampleDataUlt.addValue(3.0, 1, 0);
            int[] validColumns = {0};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 0, validColumns);
            assertEquals(1.0, result, .000000001d);
    }
    @Test
    public void noMocknegativeValueCalculateRowTotalValidCols() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            int[] validColumns = {0};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1, validColumns);
            assertEquals(-7.0, result, .000000001d);
    }
    
    @Test
    public void noMockCalculateRowTotalOf3x2TableValidCols() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(5.0, 0, 1);
            exampleDataUlt.addValue(7.0, 1, 0);
            exampleDataUlt.addValue(11.0, 1, 1);
            exampleDataUlt.addValue(7.0, 2, 0);
            exampleDataUlt.addValue(11.0, 2, 1);
            int[] validColumns = {1};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1, validColumns);
            assertEquals(11.0, result, .000000001d);
    }
    
 // CalculateColumnTotal testing

    @Test
    public void noMockSimpleCalculateColumnTotalNullValidRows() {
        // Test with no valid rows (empty validRows array)
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(1.0, 0, 0);
        exampleDataUlt.addValue(3.0, 1, 0);
        int[] validRows = {};
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(0.0, result, .000000001d); // No rows selected, should be 0
    }

    @Test
    public void noMockSimpleCalculateColumnTotalValidRows() {
        // Test with one valid row and one column
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(1.0, 0, 0);
        exampleDataUlt.addValue(3.0, 1, 0);
        int[] validRows = {0};
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(1.0, result, .000000001d); // Only the value at row 0, column 0 should be included
    }

    @Test
    public void noMockNegativeValueCalculateColumnTotalValidRows() {
        // Test with negative values in the column
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(3.0, 0, 0);
        exampleDataUlt.addValue(-7.0, 1, 0);
        int[] validRows = {0, 1};
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(-4.0, result, .000000001d); // 3.0 + (-7.0) = -4.0
    }

    @Test
    public void noMockCalculateColumnTotalOf3x2TableValidRows() {
        // Test with a 3x2 table, checking a specific column with valid rows
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(3.0, 0, 0);
        exampleDataUlt.addValue(5.0, 0, 1);
        exampleDataUlt.addValue(7.0, 1, 0);
        exampleDataUlt.addValue(11.0, 1, 1);
        exampleDataUlt.addValue(7.0, 2, 0);
        exampleDataUlt.addValue(11.0, 2, 1);
        int[] validRows = {0, 1, 2}; // All rows are valid
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(17.0, result, .000000001d); // 3.0 + 7.0 + 7.0 = 17.0
    }

    @Test
    public void noMockCalculateColumnTotalWithOutOfRangeRow() {
        // Test with an out-of-range row in the validRows array
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(1.0, 0, 0);
        exampleDataUlt.addValue(3.0, 1, 0);
        int[] validRows = {0, 10}; // Row 10 does not exist
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(1.0, result, .000000001d); // Only row 0 should contribute
    }

    @Test
    public void noMockCalculateColumnTotalAllRowsInvalid() {
        // Test with no valid rows (invalid validRows array)
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(3.0, 0, 0);
        exampleDataUlt.addValue(5.0, 0, 1);
        exampleDataUlt.addValue(7.0, 1, 0);
        exampleDataUlt.addValue(11.0, 1, 1);
        int[] validRows = {}; // No valid rows
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(0.0, result, .000000001d); // No rows selected, total should be 0
    }

    @Test
    public void noMockCalculateColumnTotalNullValues() {
        // Test when the values in the column are null
        DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
        exampleDataUlt.addValue(null, 0, 0);
        exampleDataUlt.addValue(null, 1, 0);
        int[] validRows = {0, 1};
        double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 0, validRows);
        assertEquals(0.0, result, .000000001d); // No valid values, total should be 0
    }
    
    @Test
    public void noMocknegativeValueCalculateRowTotalWithNull() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            exampleDataUlt.addValue(10.0, 2, 0); 
            exampleDataUlt.addValue(3.0, 0, 1);
            exampleDataUlt.addValue(null, 1, 1);
            exampleDataUlt.addValue(1.0, 2, 1); 
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1);
            assertEquals(-7.0, result, .000000001d);
    }
    @Test
    public void noMocknegativeValueCalculateColsTotalWithNull() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            exampleDataUlt.addValue(10.0, 2, 0); 
            exampleDataUlt.addValue(3.0, 0, 1);
            exampleDataUlt.addValue(null, 1, 1);
            exampleDataUlt.addValue(-7, 2, 1);           
            double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 1);
            assertEquals(-4.0, result, .000000001d);
    }
    
    @Test
    public void noMocknegativeValueCalculateRowTotalValidColsWithNull() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            exampleDataUlt.addValue(10.0, 2, 0); 
            exampleDataUlt.addValue(3.0, 0, 1);
            exampleDataUlt.addValue(null, 1, 1);
            exampleDataUlt.addValue(1.0, 2, 1); 
            int[] validColumns = {0,1};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 1, validColumns);
            assertEquals(-7.0, result, .000000001d);
    }
    @Test
    public void noMocknegativeValueCalculateColsTotalValidColsWithNull() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(-7.0, 1, 0);
            exampleDataUlt.addValue(10.0, 2, 0); 
            exampleDataUlt.addValue(3.0, 0, 1);
            exampleDataUlt.addValue(null, 1, 1);
            exampleDataUlt.addValue(-7, 2, 1);           
            int[] validRows = {1,2};
            double result = DataUtilities.calculateColumnTotal(exampleDataUlt, 1, validRows);
            assertEquals(-7.0, result, .000000001d);
    }
    @Test
    public void noMocknegativeValueCalculateRowTotalValidColsMoreThanColTotal() {
            DefaultKeyedValues2D exampleDataUlt = new DefaultKeyedValues2D();
            exampleDataUlt.addValue(3.0, 0, 0);
            exampleDataUlt.addValue(3.0, 0, 1);
            int[] validColumns = {0,1,2,3};
            double result = DataUtilities.calculateRowTotal(exampleDataUlt, 0, validColumns);
            assertEquals(6.0, result, .000000001d);
    }
    
    @Test 
    public void attemptNullValuesInNumberArray() {
    	try {
    		Number[] faultyArray = DataUtilities.createNumberArray(null);
    		fail();
    	} catch (Exception e){
    		return;
    	}

    }

    @Test 
    public void attemptNullValuesInNumberArray2D() {
    	try {
    		Number[][] faultyArray = DataUtilities.createNumberArray2D(null);
    		fail();
    	} catch (Exception e){
    		return;
    	}

    }
    
    @Test
    public void attemptNullDataCalculateRow() {
    	try {
    		DefaultKeyedValues2D nullDataUlt = null;
            double result = DataUtilities.calculateRowTotal(nullDataUlt, 0);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    
    @Test
    public void attemptNullDataCalculateRowValidCols() {
    	try {
    		DefaultKeyedValues2D nullDataUlt = null;
    		int[] tempArr = {0,1};
            double result = DataUtilities.calculateRowTotal(nullDataUlt, 0, tempArr);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    
    @Test
    public void attemptNullDataCalculateCol() {
    	try {
    		DefaultKeyedValues2D nullDataUlt = null;
            double result = DataUtilities.calculateColumnTotal(nullDataUlt, 0);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    @Test
    public void attemptNullDataCalculateColValidRows() {
    	try {
    		DefaultKeyedValues2D nullDataUlt = null;
    		int[] tempArr = {0,1};
            double result = DataUtilities.calculateColumnTotal(nullDataUlt, 0, tempArr);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    @Test
    public void attemptNullDataGetCumulativePercentages() {
    	try {
    		DefaultKeyedValues nullDataUlt = null;
    		KeyedValues result = new DefaultKeyedValues();
    		result = DataUtilities.getCumulativePercentages(nullDataUlt);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    
    @Test
    public void attemptNullDataClone() {
    	try {
    		double[][] nullDataUlt = null;
            double[][] result = DataUtilities.clone(nullDataUlt);
            fail();
    	} catch (Exception e) {
    		return;
    	} 
    }
    
}
