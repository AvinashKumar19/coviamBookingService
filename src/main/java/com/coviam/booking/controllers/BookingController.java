package com.coviam.booking.controllers;


import com.coviam.booking.dto.BookingDTO;
import com.coviam.booking.dto.PaymentStatusDTO;
import com.coviam.booking.entity.Booking;
import com.coviam.booking.service.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BookingController {

    @Autowired
    public BookingService bookingService;


    @RequestMapping(
            value = "/createBooking",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(newBooking, OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getBooking(@PathVariable("id") String id) {
        Booking booking = bookingService.getBookingFromId(id);
        BookingDTO bookingDTO =new BookingDTO();
        BeanUtils.copyProperties(booking,bookingDTO);
        return new ResponseEntity<>(bookingDTO, OK);
    }


    @RequestMapping(value = "/updateBookingPayment",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updatePaymentStatus(@RequestBody PaymentStatusDTO paymentStatusDTO) {
        Booking booking = bookingService.getBookingFromSuperPnr(paymentStatusDTO.getSuperPnr());
        Booking updatedBooking = bookingService.updateBookingPaymentStatus(paymentStatusDTO,booking);
        BookingDTO bookingDTO =new BookingDTO();
        BeanUtils.copyProperties(updatedBooking,bookingDTO);
        return new ResponseEntity<BookingDTO>(bookingDTO, OK);
    }
}
