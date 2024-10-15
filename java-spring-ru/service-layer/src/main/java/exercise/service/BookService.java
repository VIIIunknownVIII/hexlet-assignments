package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO>  getAll() {
        var books = bookRepository.findAll();
        return books.stream().map(bookMapper::map).toList();
    }

    public BookDTO getBook(Long id) {
        var currentBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id" + id + "not found"));
        return bookMapper.map(currentBook);
    }

    public BookDTO createBook(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        var newBook = bookRepository.save(book);
        return bookMapper.map(newBook);
    }

    public BookDTO updateBook(Long id, BookUpdateDTO bookData) {
        var currentBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id" + id + "not found"));
        bookMapper.update(bookData, currentBook);
        var newBook = bookRepository.save(currentBook);
        return bookMapper.map(newBook);
    }

    public void deleteBok(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}