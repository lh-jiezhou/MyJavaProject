package com.lh.service.impl;

import com.lh.dao.BooksMapper;
import com.lh.pojo.Books;
import com.lh.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("booksService")
public class BooksServiceImpl implements BooksService {

    // 业务层 调用 Dao 层 (组合)

    // 配置模式 (配置文件中通过set注入)
//    private BooksMapper booksMapper;
//    public void setBooksMapper(BooksMapper booksMapper) {
//        this.booksMapper = booksMapper;
//    }

    // 注解模式
    @Autowired
    private BooksMapper booksMapper;

    @Override
    public int addBook(Books books) {
        // 事务处理

        // 调用
        return booksMapper.addBook(books);
    }

    @Override
    public int deleteBook(int id) {
        return booksMapper.deleteBook(id);
    }

    @Override
    public int updateBook(Books books) {
        return booksMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return booksMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return booksMapper.queryAllBook();
    }

    @Override
    public List<Books> queryBookByName(String bookName) {
        return booksMapper.queryBookByName(bookName);
    }
}
