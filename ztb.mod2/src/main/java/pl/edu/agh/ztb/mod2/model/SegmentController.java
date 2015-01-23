package pl.edu.agh.ztb.mod2.model;

public class SegmentController {

    private int id;
    private int cabinet_id;
    private String firmware;
    private String product_code;
    private String number;

    public SegmentController(int id, int cabinet_id, String firmware, String product_code, String number) {
        this.cabinet_id = cabinet_id;
        this.id = id;
        this.firmware = firmware;
        this.product_code = product_code;
        this.number = number;
    }

        
    
    public SegmentController(int cabinet_id, String firmware,
			String product_code, String number) {
		super();
		this.cabinet_id = cabinet_id;
		this.firmware = firmware;
		this.product_code = product_code;
		this.number = number;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SegmentController that = (SegmentController) o;

        if (cabinet_id != that.cabinet_id) return false;
        if (id != that.id) return false;
        if (firmware != null ? !firmware.equals(that.firmware) : that.firmware != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (product_code != null ? !product_code.equals(that.product_code) : that.product_code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cabinet_id;
        result = 31 * result + (firmware != null ? firmware.hashCode() : 0);
        result = 31 * result + (product_code != null ? product_code.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCabinet_id() {
        return cabinet_id;
    }

    public void setCabinet_id(int cabinet_id) {
        this.cabinet_id = cabinet_id;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SegmentController [id=" + id + ", cabinet_id=" + cabinet_id
                + ", firmware=" + firmware + ", product_code=" + product_code + ", number=" + number+ "]";
    }


}
