package in.ctrlplussubmit.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

	@Override
	public LocalDateTime read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return LocalDateTime.parse(arg0.nextString());
	}

	@Override
	public void write(JsonWriter out, LocalDateTime value) throws IOException {
		// TODO Auto-generated method stub
		//Jb bhi local date time json me convert ho to isame btate hai
		if(value == null) {
			out.nullValue();
			return;
		}
		out.value(value.toString());
	}

}
