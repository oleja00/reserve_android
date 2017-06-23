package com.maxkudla.reserve.domain.book;


import com.maxkudla.reserve.models.book.BookData;

import io.reactivex.Single;

public interface BookRepository {

    Single<BookData> getServiceById(String id);

}
