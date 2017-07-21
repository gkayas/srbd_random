package test;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import dna.sequence.*;

public class StudentTest {

	private SequenceSet S;

	/**
	 *
	 *
	 * @param l1
	 * @param l2
	 * @return true if the two lists are equal (an empty list and a null list
	 *         are considered equal).
	 */
	private static boolean equal(LinkedList<Sequence> l1, LinkedList<Sequence> l2) {
		if (l1 == l2) {
			return true;
		}
		if (((l1 == null) && l2.empty()) || ((l2 == null) && l1.empty())) {
			return true;
		}
		if ((l1 == null) || (l2 == null)) {
			return false;
		}
		if (l1.empty() != l2.empty()) {
			return false;
		}
		if (l1.empty()) {
			return true;
		}
		int n1 = 0;
		l1.findFirst();
		while (!l1.last()) {
			n1++;
			l1.findNext();
		}
		int n2 = 0;
		l2.findFirst();
		while (!l2.last()) {
			n2++;
			l2.findNext();
		}
		if (n1 != n2) {
			return false;
		}
		// return subset(l1, l2) && subset(l2, l1);
		l1.findFirst();
		l2.findFirst();
		while (!l1.last()) {
			if (!(l1.retrieve().getHeader().equals(l2.retrieve().getHeader())
					&& l1.retrieve().getSeq().equals(l2.retrieve().getSeq()))) {
				return false;
			} else {
				l1.findNext();
				l2.findNext();
			}
		}
		if (!(l1.retrieve().getHeader().equals(l2.retrieve().getHeader())
				&& l1.retrieve().getSeq().equals(l2.retrieve().getSeq()))) {
			return false;
		}
		return true;
	}

	private static boolean equal(Usage u1, Usage u2) {
		if (u1 == u2) {
			return true;
		}
		if (u1 == null && u2 == null) {
			return true;
		}
		if ((u1 == null) || (u2 == null)) {
			return false;
		}
		LinkedList<Pair<String, Integer>> l1 = u1.getCounts();
		LinkedList<Pair<String, Integer>> l2 = u2.getCounts();
		int n1 = 0;
		l1.findFirst();
		while (!l1.last()) {
			n1++;
			l1.findNext();
		}
		int n2 = 0;
		l2.findFirst();
		while (!l2.last()) {
			n2++;
			l2.findNext();
		}
		if (n1 != n2) {
			return false;
		}
		// return subset(l1, l2) && subset(l2, l1);
		Pair<String, Integer> p1;
		Pair<String, Integer> p2;
		l1.findFirst();
		l2.findFirst();
		while (!l1.last()) {
			p1 = l1.retrieve();
			p2 = l2.retrieve();
			if (!equal(p1, p2)) {
				return false;
			} else {
				l1.findNext();
				l2.findNext();
			}
		}
		p1 = l1.retrieve();
		p2 = l2.retrieve();
		if (!equal(p1, p2)) {
			return false;
		}
		return true;
	}

	private static boolean equal(Pair<String, Integer> p1, Pair<String, Integer> p2) {
		return p1.first.equals(p2.first) && p1.second.equals(p2.second);
	}

	public StudentTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	// SequenceSet test
	@Test
	public void testLoad1() {
		try {
			S = SequenceSet.load("s2.fasta");
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}
	}

	@Test
	public void testLoad2() {
		try {
			S = SequenceSet.load("s4.fasta");
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}
	}

	@Test
	public void testGetUsage1() {
		try {
			S = SequenceSet.load("s2.fasta");
			Usage actual = S.getUsage(3, 1);
			Usage expected = new Usage();
			expected.add("GAT", 5);
			expected.add("ATT", 5);
			expected.add("TTA", 1);
			expected.add("TAC", 1);
			expected.add("ACA", 1);
			expected.add("CAG", 1);
			expected.add("AGA", 1);
			expected.add("TTC", 1);
			expected.add("TCC", 1);
			expected.add("CCA", 1);
			expected.add("CAC", 3);
			expected.add("ACT", 1);
			expected.add("CTG", 1);
			expected.add("TGC", 1);
			expected.add("CTC", 2);
			expected.add("TCA", 2);
			expected.add("ACC", 2);
			expected.add("CCG", 2);
			expected.add("CGA", 2);
			expected.add("TTG", 2);
			assertTrue("The load does not give the correct results", equal(expected, actual));
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}
	}

	@Test
	public void testGetUsage2() {
		try {
			S = SequenceSet.load("s2.fasta");
			Usage actual = S.getUsage(4, 2);
			Usage expected = new Usage();
			expected.add("GATT", 4);
			expected.add("TTAC", 1);
			expected.add("ACAG", 1);
			expected.add("AGAT", 1);
			expected.add("TTCC", 1);
			expected.add("CCAC", 1);
			expected.add("ACTG", 1);
			expected.add("CTCA", 2);
			expected.add("CACC", 2);
			expected.add("CCGA", 2);

			assertTrue("The getUsage method does not give the correct results", equal(expected, actual));
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}
	}
	// Usage Class

	@Test
	public void testAdd1() {
		try {
			S = SequenceSet.load("s2.fasta");
			Usage actual = S.getUsage(4, 2);
			actual.add("AGAT", 1);
			actual.add("TTTT", 1);
			System.out.println(actual.getCount("GATT"));
			System.out.println(actual.getCount("TTAC"));
			System.out.println(actual.getCount("ACAG"));
			System.out.println(actual.getCount("AGAT"));
			System.out.println(actual.getCount("TTCC"));
			System.out.println(actual.getCount("CCAC"));
			System.out.println(actual.getCount("ACTG"));
			System.out.println(actual.getCount("CTCA"));
			System.out.println(actual.getCount("CACC"));
			System.out.println(actual.getCount("CCGA"));
			System.out.println(actual.getCount("TTTT"));
					
			Usage expected = new Usage();
			expected.add("GATT", 4);
			expected.add("TTAC", 1);
			expected.add("ACAG", 1);
			expected.add("AGAT", 2);
			expected.add("TTCC", 1);
			expected.add("CCAC", 1);
			expected.add("ACTG", 1);
			expected.add("CTCA", 2);
			expected.add("CACC", 2);
			expected.add("CCGA", 2);
			expected.add("TTTT", 1);

			assertTrue("The add method does not give the correct results", equal(expected, actual));
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}

	}

	@Test
	public void TestGetCount1() {
		try {
			S = SequenceSet.load("s2.fasta");
			int actual = S.getUsage(4, 2).getCount("GATT");
			int expected = 4;
			assertEquals("The method getCount does not give the correct results", expected, actual);
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}
	}

	// test Sequence

	@Test
	public void TestGetSeqUsage1() {
		try {
			S = SequenceSet.load("s2.fasta");
			LinkedList<Sequence> l1 = S.getSequences();
			l1.findFirst();
			Usage actual = l1.retrieve().getUsage(4, 2);
			Usage expected = new Usage();
			expected.add("GATT", 1);
			expected.add("TTAC", 1);
			expected.add("ACAG", 1);
			expected.add("AGAT", 1);

			assertTrue("The load does not give the correct results", equal(expected, actual));
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			fail("Your code threw an exception: " + errors);
		}

	}

}
