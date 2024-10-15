package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getAuthor(Long id) {
        var currentAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + "not found!"));
        return authorMapper.map(currentAuthor);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorData) {
        var author = authorMapper.map(authorData);
        var newAuthor = authorRepository.save(author);
        return authorMapper.map(newAuthor);
    }

    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO authorData) {
        var currentAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + "not found!"));
        authorMapper.update(authorData, currentAuthor);
        authorRepository.save(currentAuthor);
        return authorMapper.map(currentAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}