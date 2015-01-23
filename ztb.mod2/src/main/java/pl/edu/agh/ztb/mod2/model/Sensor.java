package pl.edu.agh.ztb.mod2.model;

import java.util.Properties;

public class Sensor {
	
	private int id;
	private int segment_ctrl_id;
	private int location_id;
	private int sensor_type_id;
	private Properties sensor_data;
	
	
	
	
	public Sensor(int segment_ctrl_id, int location_id, int sensor_type_id) {
		super();
		this.segment_ctrl_id = segment_ctrl_id;
		this.location_id = location_id;
		this.sensor_type_id = sensor_type_id;
	}
	public Sensor(int id, int segment_ctrl_id, int location_id,
			int sensor_type_id) {
		super();
		this.id = id;
		this.segment_ctrl_id = segment_ctrl_id;
		this.location_id = location_id;
		this.sensor_type_id = sensor_type_id;
	}
	public Sensor(int segment_ctrl_id, int location_id, int sensor_type_id,
			Properties sensor_data) {
		super();
		this.segment_ctrl_id = segment_ctrl_id;
		this.location_id = location_id;
		this.sensor_type_id = sensor_type_id;
		this.sensor_data = sensor_data;
	}
	public Sensor(int id, int segment_ctrl_id, int location_id,
			int sensor_type_id, Properties sensor_data) {
		super();
		this.id = id;
		this.segment_ctrl_id = segment_ctrl_id;
		this.location_id = location_id;
		this.sensor_type_id = sensor_type_id;
		this.sensor_data = sensor_data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSegment_ctrl_id() {
		return segment_ctrl_id;
	}
	public void setSegment_ctrl_id(int segment_ctrl_id) {
		this.segment_ctrl_id = segment_ctrl_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getSensor_type_id() {
		return sensor_type_id;
	}
	public void setSensor_type_id(int sensor_type_id) {
		this.sensor_type_id = sensor_type_id;
	}
	public Properties getSensor_data() {
		return sensor_data;
	}
	public void setSensor_data(Properties sensor_data) {
		this.sensor_data = sensor_data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + location_id;
		result = prime * result + segment_ctrl_id;
		result = prime * result
				+ ((sensor_data == null) ? 0 : sensor_data.hashCode());
		result = prime * result + sensor_type_id;
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
		Sensor other = (Sensor) obj;
		if (id != other.id)
			return false;
		if (location_id != other.location_id)
			return false;
		if (segment_ctrl_id != other.segment_ctrl_id)
			return false;
		if (sensor_data == null) {
			if (other.sensor_data != null)
				return false;
		} else if (!sensor_data.equals(other.sensor_data))
			return false;
		if (sensor_type_id != other.sensor_type_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", segment_ctrl_id=" + segment_ctrl_id
				+ ", location_id=" + location_id + ", sensor_type_id="
				+ sensor_type_id + ", sensor_data=" + sensor_data + "]";
	}
	
	

}
