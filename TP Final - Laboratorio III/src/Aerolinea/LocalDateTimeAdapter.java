
package Aerolinea;

////////////////////////////////////////////////////////////
// FUNCION PARA PODER CARGAR EL LOCALDATETIME A JSON //////
///////////////////////////////////////////////////////////

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value != null) {
            out.value(formatter.format(value));
        }
        else {
            out.nullValue();
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String value = in.nextString();
        if (value != null) {
            return LocalDateTime.parse(value, formatter);
        }
        return null;
    }
}
