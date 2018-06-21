/*
 * Copyright 2018 Emanuele Paiano.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package springduino.mvc.controllers;

import springduino.mvc.utils.EntityList;
import springduino.mvc.utils.JsonResponseBody;
import springduino.mvc.entities.Author;
import springduino.mvc.entities.Book;
import springduino.mvc.services.AuthorService;
import springduino.mvc.services.BookService;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lele
 */
@RequestMapping("/api/books")
@RestController
public class BooksRestController {
    
    @Autowired
    BookService bookService;
    
    @Autowired
    AuthorService authorService;
    
    
    @PostConstruct
    public void init(){
        authorService.addAuthor(new Author("Isaac", "Asimov"));
	bookService.addBook(new Book("973-6", "Fondazione", authorService.getAuthors().get(0), "Fantascienza", 5));
        bookService.addBook(new Book("973-7", "Notturno", authorService.getAuthors().get(0), "Fantascienza", 5));    
    }
    
    @PostMapping("/create")
    public ResponseEntity<JsonResponseBody> create(@RequestBody Book libro) {
            bookService.addBook(libro);
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (Book) libro) , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<JsonResponseBody> showAll() {
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (List<Book>) bookService.getBooks()) , HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ROOT')")
    @GetMapping(value="/xml", produces=MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EntityList> bookXml(){
        return new ResponseEntity<>(new EntityList<Book>(bookService.getBooks()), HttpStatus.OK);
    }
   
    @DeleteMapping(value="/{id}")
    public ResponseEntity<JsonResponseBody> deleteBook(@PathVariable Long id){
        Book toDelete=bookService.deleteById(id);
                if(toDelete!=null)
                    return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), toDelete), HttpStatus.OK);
        return new ResponseEntity<>(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), (String) "Book with ID "+id+" not found!"), HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseBody> putBook(@RequestBody Book book){
        
        Book updated=bookService.updateById(book);
        
        if(updated==null)
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), (String) "Book "+book.getId()+" not found") , HttpStatus.NO_CONTENT);
        else{
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (Book) updated) , HttpStatus.OK);
        }
    }
    
}
