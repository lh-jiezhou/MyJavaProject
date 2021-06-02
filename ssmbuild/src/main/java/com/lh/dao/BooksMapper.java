package com.lh.dao;

import com.lh.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper {

    // 增加
    int addBook(Books books);
    // 删除
    int deleteBook(@Param("bookId") int id);
    // 更新
    int updateBook(Books books);
    // 查询(根据id)
    Books queryBookById(@Param("bookId") int id);
    // 查询全部
    List<Books> queryAllBook();

    // 根据名字查
    List<Books> queryBookByName(String bookName);
}
