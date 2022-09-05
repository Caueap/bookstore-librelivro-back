package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;
import com.api2.bookstore.dtos.rentaldto.RentalResponseDto;
import com.api2.bookstore.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api2/rental")
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
    public List<RentalResponseDto> getAll() {
        return rentalService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        rentalService.delete(id);
    }
}
