package pl.edu.agh.ztb.mod2.model;

import java.sql.Timestamp;

public class Error {

	private int id;
	private int fixture_id;
	private int driver_id;
	private Timestamp timestamp;
	private String error_type;

	

	public Error(int fixture_id, int driver_id, Timestamp timestamp,
			String error_type) {
		super();
		this.fixture_id = fixture_id;
		this.driver_id = driver_id;
		this.timestamp = timestamp;
		this.error_type = error_type;
	}



	public Error(int id, int fixture_id, int driver_id, Timestamp timestamp,
			String error_type) {
		this.id = id;
		this.fixture_id = fixture_id;
		this.driver_id = driver_id;
		this.timestamp = timestamp;
		this.error_type = error_type;
	}

	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Error error = (Error) o;

		if (driver_id != error.driver_id)
			return false;
		if (id != error.id)
			return false;
		if (fixture_id != error.fixture_id)
			return false;
		if (error_type != null ? !error_type.equals(error.error_type)
				: error.error_type != null)
			return false;
		if (timestamp != null ? !timestamp.equals(error.timestamp)
				: error.timestamp != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + fixture_id;
		result = 31 * result + driver_id;
		result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
		result = 31 * result + (error_type != null ? error_type.hashCode() : 0);
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFixture_id() {
		return fixture_id;
	}

	public void setFIXTURE_id(int fixture_id) {
		this.fixture_id = fixture_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getError_type() {
		return error_type;
	}

	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	@Override
	public String toString() {
		return "Error[" + "id=" + id + ", fixture_id=" + fixture_id
				+ ", driver_id=" + driver_id + ", timestamp=" + timestamp
				+ ", error_type='" + error_type + '\'' + ']';
	}
}
