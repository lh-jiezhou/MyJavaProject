package com.lh.controller;

import com.lh.pojo.Books;
import com.lh.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    // controller调service层

    // 配置方式
//    @Autowired // 注入
//    @Qualifier("BooksServiceImpl") // 别名 spring-service.xml
//    private BooksService booksService;

    // 注解方式
    @Autowired
    private BooksService booksService;


    // 查全部书籍
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> list = booksService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook"; // 到页面
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBook";
    }

    // 添加书籍
    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("addBook=>"+books);
        booksService.addBook(books);
        return "redirect:/books/allBook"; // 重定向到controller
    }

    // 跳转修改页面（RestFul风格）
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable int id, Model model){
        Books book = booksService.queryBookById(id);
        model.addAttribute("QBook", book);
        return "updateBook";
    }

    // 修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println("updateBook=>"+books);
        booksService.updateBook(books);
        return "redirect:/books/allBook";
    }

    // 删除书籍（传统风格）url+?传值
    @RequestMapping("/deleteBook")
    public String deleteBook(int id){
        booksService.deleteBook(id);
        return "redirect:/books/allBook";
    }

    // 查询书籍
    @RequestMapping("/queryBook")
    public String queryBook(Model model, String queryBookName){
        List<Books> list = booksService.queryBookByName(queryBookName);
        System.out.println("words : "+queryBookName);
        model.addAttribute("list", list);
        return "allBook";
    }


}
