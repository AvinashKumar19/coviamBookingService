package com.coviam.booking.service;

import com.coviam.booking.dto.PaymentStatusDTO;
import com.coviam.booking.entity.Booking;

public interface BookingService {

    Booking getBookingFromId(String id);

    /**
     * GET A SINGLE BOOKING
     * @param superPnr : ID OF THE BOOKING
     * @return : BOOKING OBJECT
     */
    Booking getBookingFromSuperPnr(String superPnr);

    /**
     * INSERT A NEW BOOKING IN BOOKING TABLE
     * @param booking : DATA RECEIVED FROM FRONT END TO BE INSERTED
     * @return : THE DTO THAT WAS PASSED
     */
    Booking createBooking(Booking booking);

    /**
     * UPDATE THE STATUS OF BOOKING THAT IS REFERENCED BY BOOKING ID
     * @param paymentStatusDTO : DTO RECEIVED FROM CALLER
     * @param bookingDTO
     * @return : THE SAME DTO OBJECT THAT WAS PASSED
     */
    Booking updateBookingPaymentStatus(PaymentStatusDTO paymentStatusDTO, Booking bookingDTO);

}
