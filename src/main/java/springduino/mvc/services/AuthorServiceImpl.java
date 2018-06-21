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

import springduino.mvc.entities.Author;
import springduino.mvc.listdaos.AuthorListDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lele
 */
@Service
public class AuthorServiceImpl implements AuthorService{
    
    @Autowired
    AuthorListDao authorDao;
    
    
    public Author addAuthor(Author author){
        if (!authorDao.getAuthors().contains(author))
            return authorDao.addAutore(author);
        return null;
    }
    
    public Author updateById(Author author){
        
        authorDao.getAuthors().stream()
                .filter((Author current)->current.getId().equals(author.getId()))
                .forEach((Author current)->{
        
            current.setId(author.getId());
            current.setNome(author.getNome());
            current.setCognome(author.getCognome());
        
        });

        return authorDao.getAuthors()
                .stream()
                .filter((Author current)->current.getId().equals(author.getId()))
                .distinct()
                .findFirst()
                .get();
    }
    
    public Author deleteById(Long id){
        Author toRemove= authorDao.getAuthors()
                .stream()
                .filter((Author current)->current.getId().equals(id))
                .findFirst()
                .get();
        
        if (toRemove!=null) 
            authorDao.getAuthors().remove(toRemove);
        
        return toRemove;
        
    }
    
    
    public List<Author> getAuthors(){
        return authorDao.getAuthors();
    }
}
