package hei.shool.transportationmanagementsystem.advices;

import hei.shool.transportationmanagementsystem.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody  ApplicationErrorMessage handleEntityBadRequestException(BadRequestException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler({NotFoundException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ApplicationErrorMessage handlerException(NotFoundException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody ApplicationErrorMessage handleForbiddenException(ForbiddenException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.FORBIDDEN.value()
        );
    }
    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody  ApplicationErrorMessage handlerException(InternalServerException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ApplicationErrorMessage handlerException(UnauthorizedException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.UNAUTHORIZED.value()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ApplicationErrorMessage handlerException(IllegalArgumentException ex) {
        return new ApplicationErrorMessage(
                ex.getMessage(),
                LocalDate.now(),
                HttpStatus.BAD_REQUEST.value()
        );
    }
}
