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
package springduino.mvc.services;

import springduino.mvc.entities.Book;
import springduino.mvc.listdaos.BookListDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lele
 */
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookListDao bookDao;
    
    public Book addBook(Book book){
        if (!bookDao.getBooks().contains(book))
            return bookDao.addLibro(book);
        return null;
    }
    
    public Book updateById(Book book){
        
        bookDao.getBooks().stream()
                .filter((Book b)->b.getId().equals(book.getId()))
                .forEach((Book b)->{
        
            b.setTitolo(book.getTitolo());
            b.setAutore(book.getAutore());
            b.setIsbn(book.getIsbn());
            b.setGenere(book.getGenere());
            b.setCopie(book.getCopie()); 
        
        });

        return bookDao.getBooks()
                .stream()
                .filter((Book b)->b.getId().equals(book.getId()))
                .distinct()
                .findFirst()
                .get();
    }
    
    public Book deleteById(Long id){
        Book toRemove= bookDao.getBooks()
                .stream()
                .filter((Book b)->b.getId().equals(id))
                .distinct()
                .findFirst()
                .get();
        
        if (toRemove!=null) bookDao.getBooks().remove(toRemove);
        
        return toRemove;
        
    }
    
    
    public List<Book> getBooks(){
        return bookDao.getBooks();
    }
    
    
}
