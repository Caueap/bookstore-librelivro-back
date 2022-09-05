package com.api2.bookstore.services;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;
import com.api2.bookstore.dtos.rentaldto.RentalResponseDto;
import com.api2.bookstore.exception.publexception.PublisherNotFoundException;
import com.api2.bookstore.exception.rentalexception.RentalNotFoundException;
import com.api2.bookstore.mappers.BookMapper;
import com.api2.bookstore.mappers.RentalMapper;
import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.models.RentalModel;
import com.api2.bookstore.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final static RentalMapper rentalMapper = RentalMapper.INSTANCE;

    private RentalRepository rentalRepository;

    private BookService bookService;

    private ClientService clientService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookService bookService, ClientService clientService) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.clientService = clientService;
    }

    public RentalResponseDto rent(@NotNull RentalRequestDto rentalRequestDto) {
        BookModel foundBook = bookService.verifyAndGetIfExists(rentalRequestDto.getBookModelId());
        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalRequestDto.getClientModelId());
        RentalModel rentalToSave = rentalMapper.toModel(rentalRequestDto);
        rentalToSave.setBookModel(foundBook);
        rentalToSave.setClientModel(foundClient);
        RentalModel savedRental = rentalRepository.save(rentalToSave);
        return rentalMapper.toDTO(savedRental);
    }

    public RentalResponseDto getById(Long id) {
        RentalModel foundRental = verifyAndGetRental(id);
        return rentalMapper.toDTO(foundRental);
    }

    public List<RentalResponseDto> getAll() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        verifyAndGetRental(id);
        rentalRepository.deleteById(id);
    }

        private RentalModel verifyAndGetRental(Long id) {
            RentalModel foundRentalModel = rentalRepository.findById(id)
                    .orElseThrow(() -> new RentalNotFoundException(id));
            return foundRentalModel;
        }











}
