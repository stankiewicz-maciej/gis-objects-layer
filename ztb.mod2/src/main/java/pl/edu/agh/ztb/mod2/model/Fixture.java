package pl.edu.agh.ztb.mod2.model;

import java.sql.Timestamp;

public class Fixture {

	private int id;
	private int location_id;
	private int segment_ctrl_id;

	private String actual_state;
	private String dim_level;
	private double hours_on;
	private Timestamp time_of_last_switch_on;
	private Timestamp time_of_last_switch_off;
	private String hid_status;
	private String device_type;
	private String ballasts_and_work_type;
	private String voltage_reset;
	private String min_dim_level;

	public Fixture(int location_id, int segment_ctrl_id, String actual_state,
			String dim_level, double hours_on,
			Timestamp time_of_last_switch_on,
			Timestamp time_of_last_switch_off, String hid_status,
			String device_type, String ballasts_and_work_type,
			String voltage_reset, String min_dim_level) {
		super();
		this.location_id = location_id;
		this.segment_ctrl_id = segment_ctrl_id;
		this.actual_state = actual_state;
		this.dim_level = dim_level;
		this.hours_on = hours_on;
		this.time_of_last_switch_on = time_of_last_switch_on;
		this.time_of_last_switch_off = time_of_last_switch_off;
		this.hid_status = hid_status;
		this.device_type = device_type;
		this.ballasts_and_work_type = ballasts_and_work_type;
		this.voltage_reset = voltage_reset;
		this.min_dim_level = min_dim_level;
	}

	public Fixture(int id, int location_id, int segment_ctrl_id,
			String actual_state, String dim_level, double hours_on,
			Timestamp time_of_last_switch_on,
			Timestamp time_of_last_switch_off, String hid_status,
			String device_type, String ballasts_and_work_type,
			String voltage_reset, String min_dim_level) {
		super();
		this.id = id;
		this.location_id = location_id;
		this.segment_ctrl_id = segment_ctrl_id;
		this.actual_state = actual_state;
		this.dim_level = dim_level;
		this.hours_on = hours_on;
		this.time_of_last_switch_on = time_of_last_switch_on;
		this.time_of_last_switch_off = time_of_last_switch_off;
		this.hid_status = hid_status;
		this.device_type = device_type;
		this.ballasts_and_work_type = ballasts_and_work_type;
		this.voltage_reset = voltage_reset;
		this.min_dim_level = min_dim_level;
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

	public int getSegment_ctrl_id() {
		return segment_ctrl_id;
	}

	public void setSegment_ctrl_id(int segment_ctrl_id) {
		this.segment_ctrl_id = segment_ctrl_id;
	}

	public String getActual_state() {
		return actual_state;
	}

	public void setActual_state(String actual_state) {
		this.actual_state = actual_state;
	}

	public String getDim_level() {
		return dim_level;
	}

	public void setDim_level(String dim_level) {
		this.dim_level = dim_level;
	}

	public double getHours_on() {
		return hours_on;
	}

	public void setHours_on(double hours_on) {
		this.hours_on = hours_on;
	}

	public Timestamp getTime_of_last_switch_on() {
		return time_of_last_switch_on;
	}

	public void setTime_of_last_switch_on(Timestamp time_of_last_switch_on) {
		this.time_of_last_switch_on = time_of_last_switch_on;
	}

	public Timestamp getTime_of_last_switch_off() {
		return time_of_last_switch_off;
	}

	public void setTime_of_last_switch_off(Timestamp time_of_last_switch_off) {
		this.time_of_last_switch_off = time_of_last_switch_off;
	}

	public String getHid_status() {
		return hid_status;
	}

	public void setHid_status(String hid_status) {
		this.hid_status = hid_status;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getBallasts_and_work_type() {
		return ballasts_and_work_type;
	}

	public void setBallasts_and_work_type(String ballasts_and_work_type) {
		this.ballasts_and_work_type = ballasts_and_work_type;
	}

	public String getVoltage_reset() {
		return voltage_reset;
	}

	public void setVoltage_reset(String voltage_reset) {
		this.voltage_reset = voltage_reset;
	}

	public String getMin_dim_level() {
		return min_dim_level;
	}

	public void setMin_dim_level(String min_dim_level) {
		this.min_dim_level = min_dim_level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actual_state == null) ? 0 : actual_state.hashCode());
		result = prime
				* result
				+ ((ballasts_and_work_type == null) ? 0
						: ballasts_and_work_type.hashCode());
		result = prime * result
				+ ((device_type == null) ? 0 : device_type.hashCode());
		result = prime * result
				+ ((dim_level == null) ? 0 : dim_level.hashCode());
		result = prime * result
				+ ((hid_status == null) ? 0 : hid_status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(hours_on);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + location_id;
		result = prime * result
				+ ((min_dim_level == null) ? 0 : min_dim_level.hashCode());
		result = prime * result + segment_ctrl_id;
		result = prime
				* result
				+ ((time_of_last_switch_off == null) ? 0
						: time_of_last_switch_off.hashCode());
		result = prime
				* result
				+ ((time_of_last_switch_on == null) ? 0
						: time_of_last_switch_on.hashCode());
		result = prime * result
				+ ((voltage_reset == null) ? 0 : voltage_reset.hashCode());
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
		Fixture other = (Fixture) obj;
		if (actual_state == null) {
			if (other.actual_state != null)
				return false;
		} else if (!actual_state.equals(other.actual_state))
			return false;
		if (ballasts_and_work_type == null) {
			if (other.ballasts_and_work_type != null)
				return false;
		} else if (!ballasts_and_work_type.equals(other.ballasts_and_work_type))
			return false;
		if (device_type == null) {
			if (other.device_type != null)
				return false;
		} else if (!device_type.equals(other.device_type))
			return false;
		if (dim_level == null) {
			if (other.dim_level != null)
				return false;
		} else if (!dim_level.equals(other.dim_level))
			return false;
		if (hid_status == null) {
			if (other.hid_status != null)
				return false;
		} else if (!hid_status.equals(other.hid_status))
			return false;
		if (Double.doubleToLongBits(hours_on) != Double
				.doubleToLongBits(other.hours_on))
			return false;
		if (id != other.id)
			return false;
		if (location_id != other.location_id)
			return false;
		if (min_dim_level == null) {
			if (other.min_dim_level != null)
				return false;
		} else if (!min_dim_level.equals(other.min_dim_level))
			return false;
		if (segment_ctrl_id != other.segment_ctrl_id)
			return false;
		if (time_of_last_switch_off == null) {
			if (other.time_of_last_switch_off != null)
				return false;
		} else if (!time_of_last_switch_off
				.equals(other.time_of_last_switch_off))
			return false;
		if (time_of_last_switch_on == null) {
			if (other.time_of_last_switch_on != null)
				return false;
		} else if (!time_of_last_switch_on.equals(other.time_of_last_switch_on))
			return false;
		if (voltage_reset == null) {
			if (other.voltage_reset != null)
				return false;
		} else if (!voltage_reset.equals(other.voltage_reset))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fixture [id=" + id + ", location_id=" + location_id
				+ ", segment_ctrl_id=" + segment_ctrl_id + ", actual_state="
				+ actual_state + ", dim_level=" + dim_level + ", hours_on="
				+ hours_on + ", time_of_last_switch_on="
				+ time_of_last_switch_on + ", time_of_last_switch_off="
				+ time_of_last_switch_off + ", hid_status=" + hid_status
				+ ", device_type=" + device_type + ", ballasts_and_work_type="
				+ ballasts_and_work_type + ", voltage_reset=" + voltage_reset
				+ ", min_dim_level=" + min_dim_level + "]";
	}

}
