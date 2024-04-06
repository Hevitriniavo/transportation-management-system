package hei.shool.transportationmanagementsystem.advices;

import hei.shool.transportationmanagementsystem.exceptions.EntityBadRequestException;
import hei.shool.transportationmanagementsystem.exceptions.EntityInternalServerException;
import hei.shool.transportationmanagementsystem.exceptions.EntityNotFoundException;
import hei.shool.transportationmanagementsystem.exceptions.EntityUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EntityBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody  ApplicationErrorMessage handleEntityBadRequestException(EntityBadRequestException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ApplicationErrorMessage handlerException(EntityNotFoundException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(EntityInternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody  ApplicationErrorMessage handlerException(EntityInternalServerException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }


    @ExceptionHandler(EntityUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ApplicationErrorMessage handlerException(EntityUnauthorizedException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.UNAUTHORIZED.value()
        );
    }
}
