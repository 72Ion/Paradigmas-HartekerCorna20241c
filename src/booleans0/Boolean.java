package booleans0;

public class Boolean {
  public boolean value;
  
  public Boolean( boolean b ) {
    value = b;
  }
  
  static Boolean True() {
    return new Boolean( true );
  }

  static Boolean False() {
    return new Boolean( false );
  }

  public boolean equals( Object other ) {
    return other.getClass() == getClass() && value == ((Boolean)other).value;
  }

  public Boolean yy( Boolean v ) {
    if ( value ) {
      return v;
    } else {
      return False();
    }
  }
  
  public Boolean oo( Boolean v ) {
    if ( value ) {
      return True();
    } else {
      return v;
    }  
  }
  public Boolean not() {
    if ( value ) {
      return False();
    } else {
      return True();
    }
  }
  
}

/**/
