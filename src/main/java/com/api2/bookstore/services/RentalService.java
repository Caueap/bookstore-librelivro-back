package com.api2.bookstore.services;

import com.api2.bookstore.dtos.rentaldto.RentalDeliveryDto;
import com.api2.bookstore.dtos.rentaldto.RentalReqDelDto;
import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;
import com.api2.bookstore.dtos.rentaldto.RentalResponseDto;
import com.api2.bookstore.exception.rentalexception.InvalidDeliveryDateException;
import com.api2.bookstore.exception.rentalexception.InvalidExpectedDeliveryDateException;
import com.api2.bookstore.exception.rentalexception.RentalNotFoundException;
import com.api2.bookstore.mappers.RentalMapper;
import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import com.api2.bookstore.models.RentalModel;
import com.api2.bookstore.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RentalService {

    private final static RentalMapper rentalMapper = RentalMapper.INSTANCE;

    private RentalRepository rentalRepository;

    private BookService bookService;

    private ClientService clientService;

    private ModelMapper mapper;


    @Autowired
    public RentalService(RentalRepository rentalRepository, BookService bookService,
                         ClientService clientService, ModelMapper mapper) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.clientService = clientService;
        this.mapper = mapper;
    }


    public RentalDeliveryDto rent(@NotNull RentalRequestDto rentalRequestDto) {
        BookModel foundBook = bookService.verifyAndGetIfExists(rentalRequestDto.getBookModelId());
        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalRequestDto.getClientModelId());
        RentalModel rentalToSave = mapper.map(rentalRequestDto, RentalModel.class);
        rentalToSave.setBookModel(foundBook);
        rentalToSave.setClientModel(foundClient);
        foundBook.setBookStatus("Alugado");
        foundBook.setAmount(foundBook.getAmount() - 1);
        foundBook.setRentedAmount(foundBook.getRentedAmount() + 1);


        verifyIfItIsPossibleRent(rentalRequestDto);
        RentalModel savedRental = rentalRepository.save(rentalToSave);
        RentalDeliveryDto savedRentalDto = mapper.map(rentalToSave, RentalDeliveryDto.class);
        return savedRentalDto;

    }
//    public RentalDeliveryDto rent(@NotNull RentalRequestDto rentalRequestDto) {
//        BookModel foundBook = bookService.verifyAndGetIfExists(rentalRequestDto.getBookModelId());
//        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalRequestDto.getClientModelId());
//        RentalModel rentalToSave = rentalMapper.toModel(rentalRequestDto);
//        rentalToSave.setBookModel(foundBook);
//        rentalToSave.setClientModel(foundClient);
//        foundBook.setBookStatus("Alugado");
//        foundBook.setAmount(foundBook.getAmount() - 1);
//        foundBook.setRentedAmount(foundBook.getRentedAmount() + 1);
//
//
//        verifyIfItIsPossibleRent(rentalRequestDto);
//        RentalModel savedRental = rentalRepository.save(rentalToSave);
//        return rentalMapper.toDTO(savedRental);
//    }


    public RentalDeliveryDto getById(Long id) {
        RentalModel foundRental = verifyAndGetRental(id);
        return rentalMapper.toDTO(foundRental);
    }

    public List<RentalDeliveryDto> getAll() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDTO)
                .collect(Collectors.toList());

    }

    public void delete(Long id) {
        verifyAndGetRental(id);
        rentalRepository.deleteById(id);
    }

    public RentalDeliveryDto delivery(Long id, @NotNull RentalReqDelDto rentalReqDelDto) {
        RentalModel foundRental = verifyAndGetRental(id);
        rentalReqDelDto.setId(foundRental.getId());
        BookModel foundBook = bookService.verifyAndGetIfExists(rentalReqDelDto.getBookModelId());
        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalReqDelDto.getClientModelId());
        RentalModel rentalToUpdate = rentalMapper.toModel(rentalReqDelDto);
        rentalToUpdate.setBookModel(foundBook);
        rentalToUpdate.setClientModel(foundClient);
        foundBook.setBookStatus("Disponível");
        foundBook.setAmount(foundBook.getAmount() + 1);
        foundBook.setRentedAmount(foundBook.getRentedAmount() - 1);



//        if (rentalToUpdate.getDeliveryDate().isAfter(rentalToUpdate.getExpectedDeliveryDate())) {
//            rentalToUpdate.setDelayStatus("Entregue com atraso");
//        } else if (rentalToUpdate.getExpectedDeliveryDate().isBefore(rentalToUpdate.getDeliveryDate())) {
//            rentalToUpdate.setDelayStatus("Entregue no prazo");
//        }

        verifyIfItIsPossibleDelivery(rentalReqDelDto);
        RentalModel updatedRental = rentalRepository.save(rentalToUpdate);
        return rentalMapper.toDTO(updatedRental);
    }


//    public void verifyStatusRent(@NotNull RentalReqDelDto ) {
//        RentalDeliveryDto rentalDeliveryDto = new RentalDeliveryDto();
//        LocalDate foundDeliveryDate = rentalReqDelDto.getDeliveryDate();
//        LocalDate foundExpectedDeliveryDate = rentalReqDelDto.getExpectedDeliveryDate();
//        if (foundDeliveryDate.isBefore(foundExpectedDeliveryDate)) {
//            rentalDeliveryDto.setDelayStatus("Entregue com atraso");
//        } else if (foundExpectedDeliveryDate.isBefore(foundDeliveryDate)) {
//            rentalDeliveryDto.setDelayStatus("Entregue no prazo");
//        }
//    }

    public RentalModel verifyAndGetRental(Long id) {
        RentalModel foundRentalModel = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));
        return foundRentalModel;
    }


    private void verifyIfItIsPossibleDelivery(RentalReqDelDto rentalReqDelDto) {
        LocalDate foundDeliveryDate = rentalReqDelDto.getDeliveryDate();
        LocalDate foundRentalDate = rentalReqDelDto.getRentalDate();
        if (foundDeliveryDate.isBefore(foundRentalDate)) {
            throw new InvalidDeliveryDateException(rentalReqDelDto);
        }
    }

    private void verifyIfItIsPossibleRent(RentalRequestDto rentalRequestDto) {
        LocalDate foundRentalDate = rentalRequestDto.getRentalDate();
        LocalDate foundExpectedDeliveryDate = rentalRequestDto.getExpectedDeliveryDate();
        if (foundExpectedDeliveryDate.isBefore(foundRentalDate)) {
            throw new InvalidExpectedDeliveryDateException(rentalRequestDto);
        }
    }

         /*private void verifyIfItsLate(@NotNull RentalModel rentalModel) {
             LocalDate foundDeliveryDate = rentalModel.getDeliveryDate();
             LocalDate foundExpectedDeliveryDate = rentalModel.getExpectedDeliveryDate();
             RentalDeliveryDto rentalDeliveryDto = new RentalDeliveryDto();
             if (foundDeliveryDate.isAfter(foundExpectedDeliveryDate)) {
                 rentalDeliveryDto.setStatus("Atraso");

             } else {
                 rentalDeliveryDto.setStatus("No prazo");
             }
         }*/


}














