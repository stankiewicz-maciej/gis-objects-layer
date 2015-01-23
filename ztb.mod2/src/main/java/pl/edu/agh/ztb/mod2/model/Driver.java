package pl.edu.agh.ztb.mod2.model;

import java.sql.Timestamp;

public class Driver {
	
	private int id;
	private int fixtureId;
	private String temperature;
	private String connectionQuality;
	private Timestamp systemTime;
	private String powerUsage;
	private String voltage;
	private String current;
	private String power;
	private String cosValue;
	private String zigbeeAddress;
	private String firmware;
	private String serialNumber;
	private String productType;
	private Timestamp deploymentDate;
	private String netState;
	private String dataAcceptanceState;
	private String parametrizationState;
	private String dataSearchingState;
	public Driver(int id, int fixtureId, String temperature,
			String connectionQuality, Timestamp systemTime, String powerUsage,
			String voltage, String current, String power, String cosValue,
			String zigbeeAddress, String firmware, String serialNumber,
			String productType, Timestamp deploymentDate, String netState,
			String dataAcceptanceState, String parametrizationState,
			String dataSearchingState) {
		super();
		this.id = id;
		this.fixtureId = fixtureId;
		this.temperature = temperature;
		this.connectionQuality = connectionQuality;
		this.systemTime = systemTime;
		this.powerUsage = powerUsage;
		this.voltage = voltage;
		this.current = current;
		this.power = power;
		this.cosValue = cosValue;
		this.zigbeeAddress = zigbeeAddress;
		this.firmware = firmware;
		this.serialNumber = serialNumber;
		this.productType = productType;
		this.deploymentDate = deploymentDate;
		this.netState = netState;
		this.dataAcceptanceState = dataAcceptanceState;
		this.parametrizationState = parametrizationState;
		this.dataSearchingState = dataSearchingState;
	}
	public Driver(int fixtureId, String temperature, String connectionQuality,
			Timestamp systemTime, String powerUsage, String voltage,
			String current, String power, String cosValue,
			String zigbeeAddress, String firmware, String serialNumber,
			String productType, Timestamp deploymentDate, String netState,
			String dataAcceptanceState, String parametrizationState,
			String dataSearchingState) {
		super();
		this.fixtureId = fixtureId;
		this.temperature = temperature;
		this.connectionQuality = connectionQuality;
		this.systemTime = systemTime;
		this.powerUsage = powerUsage;
		this.voltage = voltage;
		this.current = current;
		this.power = power;
		this.cosValue = cosValue;
		this.zigbeeAddress = zigbeeAddress;
		this.firmware = firmware;
		this.serialNumber = serialNumber;
		this.productType = productType;
		this.deploymentDate = deploymentDate;
		this.netState = netState;
		this.dataAcceptanceState = dataAcceptanceState;
		this.parametrizationState = parametrizationState;
		this.dataSearchingState = dataSearchingState;
	}
	
	public Driver(int id, int fixtureId) {
		super();
		this.id = id;
		this.fixtureId = fixtureId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFixtureId() {
		return fixtureId;
	}
	public void setFixtureId(int fixtureId) {
		this.fixtureId = fixtureId;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getConnectionQuality() {
		return connectionQuality;
	}
	public void setConnectionQuality(String connectionQuality) {
		this.connectionQuality = connectionQuality;
	}
	public Timestamp getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Timestamp systemTime) {
		this.systemTime = systemTime;
	}
	public String getPowerUsage() {
		return powerUsage;
	}
	public void setPowerUsage(String powerUsage) {
		this.powerUsage = powerUsage;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getCosValue() {
		return cosValue;
	}
	public void setCosValue(String cosValue) {
		this.cosValue = cosValue;
	}
	public String getZigbeeAddress() {
		return zigbeeAddress;
	}
	public void setZigbeeAddress(String zigbeeAddress) {
		this.zigbeeAddress = zigbeeAddress;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Timestamp getDeploymentDate() {
		return deploymentDate;
	}
	public void setDeploymentDate(Timestamp deploymentDate) {
		this.deploymentDate = deploymentDate;
	}
	public String getNetState() {
		return netState;
	}
	public void setNetState(String netState) {
		this.netState = netState;
	}
	public String getDataAcceptanceState() {
		return dataAcceptanceState;
	}
	public void setDataAcceptanceState(String dataAcceptanceState) {
		this.dataAcceptanceState = dataAcceptanceState;
	}
	public String getParametrizationState() {
		return parametrizationState;
	}
	public void setParametrizationState(String parametrizationState) {
		this.parametrizationState = parametrizationState;
	}
	public String getDataSearchingState() {
		return dataSearchingState;
	}
	public void setDataSearchingState(String dataSearchingState) {
		this.dataSearchingState = dataSearchingState;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fixtureId;
		result = prime * result + id;
		result = prime * result
				+ ((serialNumber == null) ? 0 : serialNumber.hashCode());
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
		Driver other = (Driver) obj;
		if (fixtureId != other.fixtureId)
			return false;
		if (id != other.id)
			return false;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Driver [id=" + id + ", fixtureId=" + fixtureId
				+ ", temperature=" + temperature + ", connectionQuality="
				+ connectionQuality + ", systemTime=" + systemTime
				+ ", powerUsage=" + powerUsage + ", voltage=" + voltage
				+ ", current=" + current + ", power=" + power + ", cosValue="
				+ cosValue + ", zigbeeAddress=" + zigbeeAddress + ", firmware="
				+ firmware + ", serialNumber=" + serialNumber
				+ ", productType=" + productType + ", deploymentDate="
				+ deploymentDate + ", netState=" + netState
				+ ", dataAcceptanceState=" + dataAcceptanceState
				+ ", parametrizationState=" + parametrizationState
				+ ", dataSearchingState=" + dataSearchingState + "]";
	}
	
	

}
