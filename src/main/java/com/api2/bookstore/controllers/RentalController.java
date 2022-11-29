package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.rentaldto.RentalDeliveryDto;
import com.api2.bookstore.dtos.rentaldto.RentalReqDelDto;
import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;
import com.api2.bookstore.dtos.rentaldto.RentalResponseDto;
import com.api2.bookstore.services.RentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api2/rental")
@CrossOrigin(origins = "*")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalResponseDto rent(@RequestBody @Valid RentalRequestDto rentalRequestDto) {
        return rentalService.rent(rentalRequestDto);
    }

    @GetMapping("/{id}")
    public RentalResponseDto getById(@PathVariable Long id) {
        return rentalService.getById(id);
    }

    @GetMapping
    public Page<RentalResponseDto> getAll(Pageable pageable) {
        return rentalService.getAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rentalService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDeliveryDto delivery(@PathVariable Long id, @RequestBody @Valid RentalReqDelDto rentalReqDelDto) {
        return rentalService.delivery(id, rentalReqDelDto);
    }
}
