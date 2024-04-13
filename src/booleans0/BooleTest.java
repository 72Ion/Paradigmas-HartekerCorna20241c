package booleans0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BooleTest {

	@Test void testDeMorganAndOnVF() {
		assertEquals(         Boolean.True().yy( Boolean.False() ),
			    	      ( Boolean.True().not().oo( Boolean.False().not() ) ).not() );
	}

	@Test void testDeMorganAndOnFV() {
		assertEquals(         Boolean.False().yy( Boolean.True() ),
				          ( Boolean.False().not().oo( Boolean.True().not() ) ).not() );
	}

	@Test void testDeMorganOrOnVF() {
		assertEquals(         Boolean.True().oo( Boolean.False() ),                 //    v | f    -> v
				          ( Boolean.True().not().yy( Boolean.False().not() ) ).not() );	// -(-v & -f ) -> ?
	}                                                                             // -( f &  v ) -> v
 
	@Test void testDeMorganOrOnFV() {
		assertEquals(         Boolean.False().oo( Boolean.True() ),
				          ( Boolean.False().not().yy( Boolean.True().not() ) ).not() );
	}

}
