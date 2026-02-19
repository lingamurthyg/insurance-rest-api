package com.scale.global.insurance.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    @Schema(description = "This is the insurance number")
    private Integer insuranceNumber;

    @Schema(description = "This is the first name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Marsellus")
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String firstName;

    @Schema(description = "This is the last name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Wallace")
    @Size(min=2, max=100, message = "Min size is 2 and max size is 100")
    private String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "This is the date of birth", requiredMode = Schema.RequiredMode.REQUIRED, example = "16-01-1989")
    @PastOrPresent(message = "Date should be in past or to be present date")
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Schema(description = "This is the inception date", requiredMode = Schema.RequiredMode.REQUIRED, example = "23-04-2017")
    @PastOrPresent(message = "Date should be in past or to be present date")
    private LocalDate inceptionOfThePolicy;

    @Schema(description = "This is customer's monthly rate", accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal rate;

}