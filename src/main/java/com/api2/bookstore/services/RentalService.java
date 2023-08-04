package com.api2.bookstore.services;

import com.api2.bookstore.dtos.rentaldto.RentalDeliveryDto;
import com.api2.bookstore.dtos.rentaldto.RentalReqDelDto;
import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;
import com.api2.bookstore.exception.bookexception.InvalidQuantityException;
import com.api2.bookstore.exception.rentalexception.*;
import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import com.api2.bookstore.models.RentalModel;
import com.api2.bookstore.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class RentalService {

//    private final static RentalMapper rentalMapper = RentalMapper.INSTANCE;

    private RentalRepository rentalRepository;

    private BookService bookService;

    private ClientService clientService;

    private ModelMapper mapper;

    //Esse código fazia parte do rentedBookDeletionValidation
//    private Long deleteValidityIndex;


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
        rentalToSave.setDeliveryDate(null);
        rentalToSave.setBookModel(foundBook);
        rentalToSave.setClientModel(foundClient);
        rentalToSave.setStatus("Pendente");
        verifyAmount(foundBook);
        foundClient.setSituation(1);
        foundBook.setSituation(1);

        verifyIfItIsPossibleRent(rentalRequestDto);
        RentalModel savedRental = rentalRepository.save(rentalToSave);
        return mapper.map(savedRental, RentalDeliveryDto.class);

    }

    private void verifyAmount(BookModel foundBook) {
        if (foundBook.getAmount() == 0) {
            throw new InvalidQuantityException();
        } else {
            foundBook.setAmount(foundBook.getAmount() - 1);
            foundBook.setRentedAmount(foundBook.getRentedAmount() + 1);
        }
    }


    public RentalDeliveryDto getById(Long id) {
        RentalModel foundRental = verifyAndGetRental(id);
        return mapper.map(foundRental, RentalDeliveryDto.class);
    }


    public List<RentalDeliveryDto> getAll() {
        return rentalRepository.findAll()
                .stream()
                .map(RentalModel -> mapper.map(RentalModel, RentalDeliveryDto.class))
                .collect(Collectors.toList());

    }


    public void delete(Long id) {
        verifyAndGetRental(id);
        //Esse código fazia parte do método rentedBookDeletionValidation
//        if (!Objects.equals(deleteValidityIndex, id)) {
//            throw new InvalidDeleteException();
//        }

        rentalRepository.deleteById(id);
    }

    public RentalDeliveryDto delivery(Long id, @NotNull RentalReqDelDto rentalReqDelDto) {
        RentalModel foundRental = verifyAndGetRental(id);
        rentalReqDelDto.setId(foundRental.getId());
        BookModel foundBook = bookService.verifyAndGetIfExists(rentalReqDelDto.getBookModelId());
        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalReqDelDto.getClientModelId());
        RentalModel rentalToUpdate = mapper.map(rentalReqDelDto, RentalModel.class);

        LocalDate deliveryDate = rentalReqDelDto.getDeliveryDate();
        LocalDate expectedDeliveryDate = rentalToUpdate.getExpectedDeliveryDate();
        rentalToUpdate.setDeliveryDate(deliveryDate);
        if (deliveryDate.equals(expectedDeliveryDate)) {
            rentalToUpdate.setStatus("Entegue no prazo");
        } else if (deliveryDate.isBefore(expectedDeliveryDate)) {
            rentalToUpdate.setStatus("Entregue antecipadamente");
        } else if(deliveryDate.isAfter(expectedDeliveryDate)) {
            rentalToUpdate.setStatus("Entregue com atraso");
        }

        rentalToUpdate.setBookModel(foundBook);
        rentalToUpdate.setClientModel(foundClient);
//        foundBook.setBookStatus("Disponível");
//        duplicateDeliveryValidation(foundBook);
        foundBook.setAmount(foundBook.getAmount() + 1);
        foundBook.setRentedAmount(foundBook.getRentedAmount() - 1);
//        foundRental.setValue(foundRental.getId());
//        rentedBookDeletionValidation(foundRental);
        foundClient.setSituation(0);
        foundBook.setSituation(0);

       
        verifyIfItIsPossibleDelivery(rentalReqDelDto);
         RentalModel updatedRRental = rentalRepository.save(rentalToUpdate);
        return mapper.map(updatedRRental, RentalDeliveryDto.class);
    }

    /*Esse método tinha um bug (não sei qual, nem onde, mas as vezes não funcionava).
     Passou a ser desnecessário depois do esquema de alternância dos botões e devolver/excluir
     no front*/

//    private void duplicateDeliveryValidation(BookModel foundBook) {
//        if (foundBook.getRentedAmount() == 0) {
//            throw new InvalidDeliveryException();
//        }
//    }

    /*Esse método tinha um bug, assim como o duplicateDeliveryValidation acima
    (não sei qual, nem onde, mas as vezes não funcionava). Passou a ser desnecessário
     depois do esquema de alternância dos botões e devolver/excluir no front*/

//    private void rentedBookDeletionValidation(RentalModel foundRental) {
//        if (Objects.equals(foundRental.getValue(), foundRental.getId())) {
//            deleteValidityIndex = foundRental.getId();
//        }
//
//    }

//    public void verifyStatusRent(@NotNull RentalModel rentalModel) {
//        LocalDate foundDeliveryDate = rentalModel.getDeliveryDate();
//        LocalDate foundExpectedDeliveryDate = rentalModel.getExpectedDeliveryDate();
//        RentalDeliveryDto rentalDeliveryDto = new RentalDeliveryDto();
//        if (foundDeliveryDate.isAfter(foundExpectedDeliveryDate)) {
//                 rentalModel.setStatus("Atraso");
//             } else {
//                 rentalModel.setStatus("No prazo");
//             }
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

//         private void verifyIfItsLate(@NotNull RentalModel rentalModel) {
//             LocalDate foundDeliveryDate = rentalModel.getDeliveryDate();
//             LocalDate foundExpectedDeliveryDate = rentalModel.getExpectedDeliveryDate();
//             RentalDeliveryDto rentalDeliveryDto = new RentalDeliveryDto();
//             if (foundDeliveryDate.isAfter(foundExpectedDeliveryDate)) {
//                 rentalDeliveryDto.setStatus("Atraso");
//
//             } else {
//                 rentalDeliveryDto.setStatus("No prazo");
//             }
//         }

//---- Código da Udemy -------------------------------------------------------------------------------

//        public RentalDeliveryDto rent(@NotNull RentalRequestDto rentalRequestDto) {
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

//    public RentalDeliveryDto getById(Long id) {
//        RentalModel foundRental = verifyAndGetRental(id);
//        return rentalMapper.toDTO(foundRental);
//    }
//
//    public List<RentalDeliveryDto> getAll() {
//        return rentalRepository.findAll()
//                .stream()
//                .map(rentalMapper::toDTO)
//                .collect(Collectors.toList());
//
//    }

//    public RentalDeliveryDto delivery(Long id, @NotNull RentalReqDelDto rentalReqDelDto) {
//        RentalModel foundRental = verifyAndGetRental(id);
//        rentalReqDelDto.setId(foundRental.getId());
//        BookModel foundBook = bookService.verifyAndGetIfExists(rentalReqDelDto.getBookModelId());
//        ClientModel foundClient = clientService.verifyAndGetIfExists(rentalReqDelDto.getClientModelId());
//        RentalModel rentalToUpdate = rentalMapper.toModel(rentalReqDelDto);
//        rentalToUpdate.setBookModel(foundBook);
//        rentalToUpdate.setClientModel(foundClient);
//        foundBook.setBookStatus("Disponível");
//        foundBook.setAmount(foundBook.getAmount() + 1);
//        foundBook.setRentedAmount(foundBook.getRentedAmount() - 1);
//
//
//
////        if (rentalToUpdate.getDeliveryDate().isAfter(rentalToUpdate.getExpectedDeliveryDate())) {
////            rentalToUpdate.setDelayStatus("Entregue com atraso");
////        } else if (rentalToUpdate.getExpectedDeliveryDate().isBefore(rentalToUpdate.getDeliveryDate())) {
////            rentalToUpdate.setDelayStatus("Entregue no prazo");
////        }
//
//        verifyIfItIsPossibleDelivery(rentalReqDelDto);
//        RentalModel updatedRental = rentalRepository.save(rentalToUpdate);
//        return rentalMapper.toDTO(updatedRental);
//    }

//    public void delete(Long id) {
//        verifyAndGetRental(id);
//        rentalRepository.deleteById(id);
//    }


}














