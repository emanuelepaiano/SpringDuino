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
package springduino.mvc.listdaos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import springduino.mvc.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookListDao {
	
	private List<Book> books;

	public BookListDao() {
		super();
		this.books=new ArrayList<Book>();
	}
	
	public Book addLibro(Book libro) {
                if(libro.getId()==null)
                    libro.setId(new Long(this.books.size()));
		this.books.add(libro);
		return libro;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
