package meanMCQ.configurations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by red on 12/26/14.
 */
//CustomDateSerializer class
public class DateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException, JsonProcessingException {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mma");
        String formattedDate = formatter.format(value);

        gen.writeString(formattedDate);

    }
}