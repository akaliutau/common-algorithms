package org.algorithm;

/**
 * Fraction Sum 100M by the Kahan Summation Algorithm 
 *	Use to compute the sum 
 *  1/d + 2/d + 3/d + ... + n/d
 *  with high precision
 */
public class KahanSum {
	private static final int MAX = 100000000; // 100M 
	
	/** Implement Kahan's Summation Algorithm for the float type. */
	public static class KahanSummation {
		/** the current running sum */
		private float sum;
		/** the current correction */
		private float correction;
		/** the current corrected added */
		private float correctedAdd;

		public KahanSummation() {
		}

		/**
		 * Return the current corrected value of the running sum. * @return the
		 * running sum's value
		 */
		public float value() {
			return sum + correction;
		}

		/**
		 * Return the corrected value of the current addend. * @return the corrected
		 * addend value
		 */
		public float correctedAddend() {
			return correctedAdd;
		}

		/**
		 * Add the value of an addend to the running sum. * @param the addend value
		 */
		public void add(float addend) { // Correct the addend value and add it to the running sum.
			correctedAdd = addend + correction;
			float tempSum = sum + correctedAdd; // Compute the
			// next correction and set the running sum. // The parentheses are necessary to
			// compute the high-order // bits of the addend.
			correction = correctedAdd - (tempSum - sum);
			sum = tempSum;
		}

		/**
		 * Clear the running sum and the // correction.
		 */
		public void clear() {
			sum = 0;
			correction = 0;
		}
	}
	
	
	public static void main(String args[]) { 
		float directSum = 0.0f;
		float denom = (0.5f*MAX)*(MAX + 1); 
		KahanSummation kSum = new KahanSummation(); 
												
		System.out.println("denominator:"+denom);
		for (int i = 1; i <= MAX; ++i) { 
				float fraction = i/denom;
				kSum.add(fraction);
				directSum += fraction;
		}
												
		System.out.println("Using Kahan's Summation Algorithm:"+kSum.value());
		System.out.println("Using direct approach:"+directSum);

	}
}