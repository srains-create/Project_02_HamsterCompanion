package com.example.project_02_hamstercompanion.database.typeConverters;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalDataTypeConverter {
    @TypeConverter
    public Long convertDateToLong(LocalDateTime dateTime){
        if (dateTime == null) {
            return null;
        }
        ZoneId ZoneID;
        ZonedDateTime zdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }
    @TypeConverter
    public LocalDateTime convertLongToDate(Long epochMilli){
        if (epochMilli == null) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

    }
}
