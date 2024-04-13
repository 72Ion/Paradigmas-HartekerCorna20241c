package biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

public class LibraryTest {

  @Test public void testNewLibraryHasNoAuthors() {
    assertTrue( new Library().searchAvailableByAuthor( "Anonimous" ).isEmpty() );
  }

  @Test public void testNewLibraryHasNoGenres() {
    assertTrue( new Library().searchAvailableByGenre( "SiFy" ).isEmpty() );
  }

  @Test public void testNewLibraryHasNoStock() {
    assertEquals( 0, new Library().available() );
  }

  @Test public void testLibraryWhitABookHasStock() {
      assertEquals( 1, libAddFowlerJ4D().available() );
  }


  @Test public void testLibraryWhitABookFindsItsGenre() {
      assertTrue( libAddFowlerJ4D().searchAvailableByGenre( fowlerJ4D().getGenre() ).contains(fowlerJ4D()) );
  }

  @Test public void testLibraryWhitABookFindsItsAuthor() {
      assertTrue( libAddFowlerJ4D().searchAvailableByAuthor( fowlerJ4D().getAuthor() ).contains(fowlerJ4D()) );
  }

  @Test public void testLibraryWhitABookRemoved() {
    Library library = libAddFowlerJ4D();

    assertEquals( 1, library.available() );

    library.removeBook(fowlerJ4D());

    assertEquals( 0, library.available() );
  }

  @Test public void testLibraryWhitNoBooktoRemove() {
      assertThrowsLike("cannot remove, book not registered", ()-> new Library().removeBook(fowlerJ4D()));
  }
  
  @Test public void testLibraryWhitTwiceABook() {
    assertThrowsLike("book already registered", ()-> libAddFowlerJ4D().addBook(fowlerJ4D()));
    assertEquals( 1, libAddFowlerJ4D().available() );

  }

  @Test public void testFiltersAuthorsOnLibrary() {
      filteredAuthorAssertions(completeLibrary(), completeLibrary().searchAvailableByAuthor("Martin Fowler"));
  }


  @Test public void testFiltersGenreOnLibrary() {
      filteredGenreAssertions(completeLibrary(), completeLibrary().searchAvailableByGenre("IT"));
  }


  // nuevo feature, alquiler de libros!
  
  @Test public void testSucessfulRent() {
    Library library = libAddFowlerJ4D();
    library.rentBook(fowlerJ4D());

    assertEquals( 0, library.available() );
  }

  @Test public void testUnexistentRent() {
      assertThrowsLike("book unavailable", () -> new Library().rentBook(fowlerJ4D()));
  }

  @Test public void testRentTwice() {
    Library library = libAddFowlerJ4D();
    library.rentBook(fowlerJ4D());

    assertThrowsLike("book unavailable", ()-> library.rentBook(fowlerJ4D()));

  }

  @Test public void testRestoreRented() {
    Library library = libAddFowlerJ4D();

    library.rentBook(fowlerJ4D());

    library.returnBook(fowlerJ4D());
      
    assertEquals( 1, library.available() );
  }

  @Test public void testRestoreUnrented() {
    Library library = libAddFowlerJ4D();

    assertThrowsLike("book not rented", ()->library.returnBook(fowlerJ4D()));
    assertEquals( 1, library.available() );
  }

  @Test public void testLibraryWhitARentedBookRemoved() {
    Library library = libAddFowlerJ4D();

    library.rentBook(fowlerJ4D());

    library.removeBook(fowlerJ4D());

    assertThrowsLike("book not rented", ()->library.returnBook(fowlerJ4D()));
  }

  private static Book fowlerJ4D() {
    return new Book("Martin Fowler", "Java4Dummies", "IT");
  }

  private static Book liuPFD() {
    return new Book("Chamond Liu", "pythonForDummies", "IT");
  }

  private static Book fowlerTU() {
    return new Book("Martin Fowler", "TolkienUniverse", "SiFi");
  }

  private static Library libAddFowlerJ4D() {
    Library library = new Library();
    library.addBook(fowlerJ4D());
    return library;
  }

  private static void assertThrowsLike(String errorMessage, Executable bodyToEval) {
    assertEquals(errorMessage, assertThrows(Exception.class, bodyToEval).getMessage());
  }


  private static Library completeLibrary() {
    Library library = libAddFowlerJ4D();
    library.addBook(liuPFD());
    library.addBook(fowlerTU());
    return library;
  }


  private static void filteredAuthorAssertions(Library library, List<Book> authorName) {
    assertEquals( 3, library.available() );
    assertTrue( authorName.contains(fowlerJ4D()) );
    assertTrue( authorName.contains(fowlerTU()) );
    assertFalse( authorName.contains(liuPFD()) );
  }


  private static void filteredGenreAssertions(Library library, List<Book> chosenGenre) {
    assertEquals( 3, library.available() );
    assertTrue( chosenGenre.contains(fowlerJ4D()) );
    assertTrue( chosenGenre.contains(liuPFD()) );
    assertFalse( chosenGenre.contains(fowlerTU()) );
  }


}
