package com.muhammad.bookapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.muhammad.bookapi.modell.Book;
import com.muhammad.bookapi.repositories.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			Book updatedBook = optionalBook.get();
			updatedBook.setTitle(title);
			updatedBook.setDescription(desc);
			updatedBook.setNumberOfPages(numOfPages);
			updatedBook.setLanguage(lang);
           bookRepository.save(updatedBook);
           return updatedBook;
           } else {
            return null;
        }
	}
	
	public Book updateBook(Book book) {
		Optional<Book> optionalBook = bookRepository.findById(book.getId());
		if(optionalBook.isPresent()) {
			Book updatedBook = optionalBook.get();
			updatedBook.setTitle(book.getTitle());
			updatedBook.setDescription(book.getDescription());
			updatedBook.setNumberOfPages(book.getNumberOfPages());
			updatedBook.setLanguage(book.getLanguage());
			bookRepository.save(updatedBook);
			return updatedBook;
			} else {
				return null;
				}
	}

	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
}
