package com.stepping.step5.controllers;


import com.stepping.step5.entity.models.Librarian;
import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.repository.LibrariansRepository;
import com.stepping.step5.entity.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/librarian")
public class LibrariansRestController {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibrariansRepository librariansRepository;

    @RequestMapping("/")
    @ResponseBody
    public String getAllLibrarians(){
        ArrayList<Librarian> collection = new ArrayList<>();
        try{
            collection.addAll(librariansRepository.findAll());
        }catch (Exception ex){
            return "can't get list with librarians: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Librarian librarian : collection) {
                res += librarian.toString();
            }
            return res;
        }else return "There is no librarian!";
    }

    @RequestMapping("/library")
    @ResponseBody
    public String getAllLibraryLibrarians(int id){
        ArrayList<Librarian> librarians = new ArrayList<>();
        try{
            Library library = libraryRepository.findOne(id);
            librarians.addAll(library.getLibrarien());
        }catch (Exception ex){
            return "Can't get all library librarians: " + ex.toString();
        }
        String res = "";
        if (librarians.size()!=0){
            for(Librarian librarian: librarians){
                res += librarian.toString();
            }
            return res;
        }else return "This library has no librarians!";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getLibrarianWithId(int id){
        Librarian librarian;
        try {
            librarian = librariansRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find librarian: " + ex.toString();
        }
        return librarian.toString();

    }

    @RequestMapping("/create")
    @ResponseBody
    public String createLibrarian(String name1, String name2, String name3, int id){
        try{
            Library library = libraryRepository.getOne(id);
            Librarian librarian = new Librarian();
            librarian.setFirstName(name1);
            librarian.setSecondName(name2);
            librarian.setPoBatkyovy(name3);
            librarian.setLibrary(library);
            libraryRepository.save(library);
            librariansRepository.save(librarian);
        }catch (Exception ex){
            return "Error creating the librarian: " + ex.toString();
        }
        return "Librarian succesfully created!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteLibrarian(int id){
        try{
            Librarian librarian = librariansRepository.findOne(id);
            Library library = librarian.getLibrary();
            library.deleteLibrarian(librarian);
            libraryRepository.save(library);
            librariansRepository.delete(librarian);
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "Library succesfully deleted!";
    }
}
