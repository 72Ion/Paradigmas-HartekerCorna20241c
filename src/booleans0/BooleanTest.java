package booleans0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BooleanTest {

	@Test void testTrueAndOnTrue() {
		assertEquals( Boolean.True().yy( Boolean.True() ), Boolean.True() );
	}

	@Test void testTrueAndOnFalse() {
		assertEquals( Boolean.True().yy( Boolean.False() ), Boolean.False() );
	}

	@Test void testTrueOrOnTrue() {
		assertEquals( Boolean.True().oo( Boolean.True() ), Boolean.True() );
	}

	@Test void testTrueOrOnFalse() {
		assertEquals( Boolean.True().oo( Boolean.False() ), Boolean.True() );
	}

	@Test void testTrueNot() {
		assertEquals( Boolean.True().not(), Boolean.False() );
	}

  @Test void testFalseAndOnTrue() {
    assertEquals( Boolean.False().yy( Boolean.True() ), Boolean.False() );
  }

  @Test void testFalseAndOnFalse() {
    assertEquals( Boolean.False().yy( Boolean.False() ), Boolean.False() );
  }

  @Test void testFalseOrOnTrue() {
    assertEquals( Boolean.False().oo( Boolean.True() ), Boolean.True() );
  }

  @Test void testFalseOrOnFalse() {
    assertEquals( Boolean.False().oo( Boolean.False() ), Boolean.False() );
  }

  @Test void testFalseNot() {
    assertEquals( Boolean.False().not(), Boolean.True() );
  }


}


/*
* Los constructores nos permite a partir de una clase generar una instancia.
* */