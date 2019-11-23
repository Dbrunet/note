package app.note.data.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Classe para conversão de data do banco de dados Room
 */
public class DateConverter {

    //recebe um time da data e retorna um Date
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
    //recebe um Date da data e retorna um time
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}