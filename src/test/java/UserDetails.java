
public class UserDetails {
	private int id;
	 private String first_name;
	 private String last_name;
	 private String email;
	 private String ip_address;
	 private float latitude;
	 private float longitude;
	 private String city;


	 // Getter Methods 

	 public int getId() {
	  return id;
	 }

	 public String getFirst_name() {
	  return first_name;
	 }

	 public String getLast_name() {
	  return last_name;
	 }

	 public String getEmail() {
	  return email;
	 }

	 public String getIp_address() {
	  return ip_address;
	 }

	 public float getLatitude() {
	  return latitude;
	 }

	 public float getLongitude() {
	  return longitude;
	 }
	 
	 public String getCity() {
		 return city;
	 }

	 // Setter Methods 

	 public void setId(int id) {
	  this.id = id;
	 }

	 public void setFirst_name(String first_name) {
	  this.first_name = first_name;
	 }

	 public void setLast_name(String last_name) {
	  this.last_name = last_name;
	 }

	 public void setEmail(String email) {
	  this.email = email;
	 }

	 public void setIp_address(String ip_address) {
	  this.ip_address = ip_address;
	 }

	 public void setLatitude(float latitude) {
	  this.latitude = latitude;
	 }

	 public void setLongitude(float longitude) {
	  this.longitude = longitude;
	 }
	 
	 public void setCity(String city) {
	  this.city = city;
	 }
}