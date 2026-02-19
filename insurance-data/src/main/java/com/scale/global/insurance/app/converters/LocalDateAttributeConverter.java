package com.scale.global.insurance.app.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * This converter is not required for PostgreSQL as it natively supports LocalDate.
 * Kept for backward compatibility but can be removed if not used elsewhere.
 */
@Converter(autoApply = false)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return locDate == null ? null : Date.valueOf(locDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}
