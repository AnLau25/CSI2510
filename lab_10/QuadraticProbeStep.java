// ==========================================================================
// CSI2110 Lab code: Hash Tables
// ==========================================================================
// (C)opyright:
//
//   Lachlan Plant
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada.
//   http://www.site.uottawa.ca
//
// Creator: lplant (Lachlan Plant)
// Email:   lplan053@uottawa.ca
// ==========================================================================
public class QuadraticProbeStep implements SteppingFunction {
	public long step( int i, long data, int size ) {
		return (long) ((Math.pow(i, 2))*data)%size;
	} /* step */


	public String getType() {
		return "Quadratic Probing";
	} /* getType */


}
