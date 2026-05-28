package in.ctrlplussubmit.util;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class GSONDemo2 {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
		User user = new User("Suraj","Suraj@gmail.com","Suraj123", LocalDateTime.now());
		String userJson = gson.toJson(user);
		System.out.println(userJson);
	}
}//TypeAdapter (abstract class)
	class User{
		private String userName;
		private String enailId;
		private String password;
		private LocalDateTime createdAt;
		public User(String userName, String enailId, String password, LocalDateTime createdAt) {
			super();
			this.userName = userName;
			this.enailId = enailId;
			this.password = password;
			this.createdAt = createdAt;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEnailId() {
			return enailId;
		}
		public void setEnailId(String enailId) {
			this.enailId = enailId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public String getRole() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
