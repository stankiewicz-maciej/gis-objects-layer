package pl.edu.agh.ztb.mod2.model;

public class Cabinet {

	private int id;
	private int location_id;
	private String number;
	
	
	
	public Cabinet(int location_id, String number) {
		super();
		this.location_id = location_id;
		this.number = number;
	}
	public Cabinet(int id, int location_id, String number) {
		super();
		this.id = id;
		this.location_id = location_id;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + location_id;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cabinet other = (Cabinet) obj;
		if (id != other.id)
			return false;
		if (location_id != other.location_id)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cabinet [id=" + id + ", location_id=" + location_id
				+ ", number=" + number + "]";
	}
	
	
	
}
