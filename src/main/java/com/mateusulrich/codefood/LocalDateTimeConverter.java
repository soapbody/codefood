package com.mateusulrich.codefood;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
	private static final ZoneId SYSTEM_DEFAULT_ZONE_ID = ZoneId.systemDefault();

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		if (attribute != null) {
			// Convert to UTC before storing
			LocalDateTime utcDateTime = attribute.atZone(SYSTEM_DEFAULT_ZONE_ID).withZoneSameInstant(UTC_ZONE_ID).toLocalDateTime();
			return Timestamp.valueOf(utcDateTime);
		}
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		if (dbData != null) {
			// Convert to system default time zone when retrieving
			Instant instant = dbData.toInstant();
			return instant.atZone(UTC_ZONE_ID).withZoneSameInstant(SYSTEM_DEFAULT_ZONE_ID).toLocalDateTime();
		}
		return null;
	}
}







