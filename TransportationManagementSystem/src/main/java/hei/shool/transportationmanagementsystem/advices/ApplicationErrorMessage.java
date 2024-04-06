package hei.shool.transportationmanagementsystem.advices;

import java.time.LocalDate;

public record ApplicationErrorMessage (
        String message,

        LocalDate localDate,

        int status
){}
