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

import springduino.mvc.entities.Author;
import springduino.mvc.services.AuthorService;
import springduino.mvc.utils.EntityList;
import springduino.mvc.utils.JsonResponseBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/authors")
@RestController
public class AuthorsRestController {
    
    @Autowired
    AuthorService authorService;
    
    @GetMapping("/")
    public ResponseEntity<JsonResponseBody> showAll(){
        return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (List<Author>)authorService.getAuthors()), HttpStatus.OK);
    }

    @GetMapping("/xml")
    public ResponseEntity<EntityList> showAllXml(){
        return new ResponseEntity<>(new EntityList(authorService.getAuthors()), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<JsonResponseBody> addAuthor(@RequestBody Author author){
        
        Author inserted=authorService.addAuthor(author);
        if (inserted!=null){
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (Author) inserted), HttpStatus.OK);
        }
        return new ResponseEntity<>(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), (String) "Author "+author.getId()+" is already contained"), HttpStatus.FORBIDDEN);
    }
    
    @PutMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseBody> updateAuthor(@RequestBody Author author){
       Author updated=authorService.updateById(author);
       if (updated!=null)
           return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (Author) updated), HttpStatus.OK);
       return new ResponseEntity<>(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), (String) "Author "+author.getId()+" is not updated"), HttpStatus.FORBIDDEN);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResponseBody> deleteAuthor(@PathVariable Long id){
        Author removed=authorService.deleteById(id);
        if (removed!=null)
            return new ResponseEntity<>(new JsonResponseBody(HttpStatus.OK.value(), (Author)removed), HttpStatus.OK);
        return new ResponseEntity<>(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), (String)"Author "+id+" not found!"), HttpStatus.NO_CONTENT);
    }
    
    

}
