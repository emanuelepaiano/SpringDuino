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
package springduino.mvc.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="libro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable{

    @XmlElement
    private Long id;

    @XmlElement
    private String isbn;

    @XmlElement
    private String titolo;

    @XmlElement
    private String genere;

    @XmlElement
    private int copie;

    @XmlElement
    private Author autore;

    public Book() {
        super();
    }

    public Book(String isbn, String titolo, Author autore, String genere, int copie) {
        super();
        this.isbn = isbn;
        this.titolo = titolo;
        this.genere = genere;
        this.copie = copie;
        this.autore = autore;
    }
    
      public Book(Long id, String isbn, String titolo, Author autore, String genere, int copie) {
        super();
        
        this.isbn = isbn;
        this.titolo = titolo;
        this.genere = genere;
        this.copie = copie;
        this.autore = autore;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getCopie() {
        return copie;
    }

    public void setCopie(int copie) {
        this.copie = copie;
    }

    public Author getAutore() {
        return autore;
    }

    public void setAutore(Author autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "Libro [isbn=" + isbn + ", titolo=" + titolo + ", genere=" + genere + ", copie=" + copie + ", autore="
                + autore + "]";
    }

}
