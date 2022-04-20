package mk.ukim.finki.wpproject.service.impl;

import mk.ukim.finki.wpproject.model.Category;
import mk.ukim.finki.wpproject.model.City;
import mk.ukim.finki.wpproject.model.User;
import mk.ukim.finki.wpproject.model.ads.BookAd;
import mk.ukim.finki.wpproject.model.enums.AdType;
import mk.ukim.finki.wpproject.model.enums.Condition;
import mk.ukim.finki.wpproject.model.enums.Genre;
import mk.ukim.finki.wpproject.model.exceptions.AdNotFoundException;
import mk.ukim.finki.wpproject.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wpproject.model.exceptions.CityNotFoundException;
import mk.ukim.finki.wpproject.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wpproject.repository.BookAdRepository;
import mk.ukim.finki.wpproject.repository.CategoryRepository;
import mk.ukim.finki.wpproject.repository.CityRepository;
import mk.ukim.finki.wpproject.repository.UserRepository;
import mk.ukim.finki.wpproject.service.BookAdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAdServiceImpl implements BookAdService {

    private final BookAdRepository bookAdRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public BookAdServiceImpl(BookAdRepository bookAdRepository, CategoryRepository categoryRepository,
                             UserRepository userRepository, CityRepository cityRepository) {
        this.bookAdRepository = bookAdRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public List<BookAd> findAll() {
        return this.bookAdRepository.findAll();
    }

    @Override
    public Optional<BookAd> findById(Long id) {
        return this.bookAdRepository.findById(id);
    }

    @Override
    public Optional<BookAd> save(BookAd bookAd) {
        return Optional.of(bookAdRepository.save(bookAd));
    }

    @Override
    public Optional<BookAd> save(String title, String description, boolean isExchangePossible, boolean isDeliveryPossible,
                                 Double price, String cityId, AdType type, Condition condition, Long categoryId, Long userId,
                                 String author, int yearMade, int numPages, Genre genre) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));

        BookAd bookAd = new BookAd(title, description, isExchangePossible, isDeliveryPossible,
                price, city, type, condition, category, user, author, yearMade, numPages, genre);

        return Optional.of(bookAdRepository.save(bookAd));

    }

    @Override
    public Optional<BookAd> edit(Long adId, String title, String description, boolean isExchangePossible, boolean isDeliveryPossible,
                                 Double price, String cityId, AdType type, Condition condition, Long categoryId, String author,
                                 int yearMade, int numPages, Genre genre) {

        BookAd bookAd = this.bookAdRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));

        AdSetterClass.adEditing(bookAd, title, description, isExchangePossible, isDeliveryPossible, price, city, type, condition, category);

        bookAd.setAuthor(author);
        bookAd.setYearMade(yearMade);
        bookAd.setNumPages(numPages);
        bookAd.setGenre(genre);

        return this.save(bookAd);
    }

    @Override
    public void deleteById(Long id) {
        BookAd bookAd = this.bookAdRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));
        this.bookAdRepository.delete(bookAd);
    }
}