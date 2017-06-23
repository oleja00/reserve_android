package com.maxkudla.reserve.domain.book;

import com.maxkudla.reserve.domain.baseinteractor.BaseSingleInteractor;
import com.maxkudla.reserve.models.book.BookData;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBookByIdInteractor extends BaseSingleInteractor<BookData, String>{

    private BookRepository mBookRepository;

    @Inject
    public GetBookByIdInteractor(BookRepository bookRepository) {
        mBookRepository = bookRepository;
    }

    @Override
    protected Single<BookData> getSingle(String bookData) {
        return mBookRepository.getServiceById(bookData);
    }
}
