package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

  private List<Book> books;
  private List<Book> rented;

  public Library() {
    books = new ArrayList<>();
    rented = new ArrayList<>();
  }

  public void addBook( Book book ) {
    if (isRegistered(book)) {
      throw new RuntimeException( "book already registered" );
    }

    books.add( book );
  }


  public void rentBook( Book book ) {
    if (!books.contains( book )) { 
      throw new RuntimeException( "book unavailable" );
    }

    rented.add( book );
    books.remove(book);
  }

  public void returnBook( Book book ) {
    if (!rented.contains( book )) { 
      throw new RuntimeException( "book not rented" );
    }

    books.add( book );
    rented.remove(book);
  }

  public void removeBook( Book book ) {
    if (!isRegistered(book)) {
      throw new RuntimeException( "cannot remove, book not registered" ); 
    }

    books.remove(book);
    rented.remove(book);
  }

  public List<Book> searchAvailableByGenre( String genre ) {
      return books.stream().filter(each -> each.getGenre().equals(genre)).collect(Collectors.toList());
  }

  public List<Book> searchAvailableByAuthor( String author ) {
    return books.stream().filter(each -> each.getAuthor().equals(author)).collect(Collectors.toList());
  }

  public int available() {
    return books.size();
  }

  private boolean isRegistered(Book book) {
    return books.contains(book) || rented.contains(book);
  }

}
