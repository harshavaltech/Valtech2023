package day3;



public class Department {
 
	private String deptNO;
	private String deptName;
	private String Location;
	private String MGRNO;
	private String ADMRDEPT;
	public String getDeptNO() {
		return deptNO;
	}
	public void setDeptNO(String deptNO) {
		this.deptNO = deptNO;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getMGRNO() {
		return MGRNO;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String deptNO, String deptName, String location, String mGRNO, String aDMRDEPT) {
		super();
		this.deptNO = deptNO;
		this.deptName = deptName;
		Location = location;
		MGRNO = mGRNO;
		ADMRDEPT = aDMRDEPT;
	}
	public void setMGRNO(String mGRNO) {
		MGRNO = mGRNO;
	}
	public String getADMRDEPT() {
		return ADMRDEPT;
	}
	public void setADMRDEPT(String aDMRDEPT) {
		ADMRDEPT = aDMRDEPT;
	
}
	@Override
	public String toString() {
		return "Department [deptNO=" + deptNO + ", deptName=" + deptName + ", Location=" + Location + ", MGRNO=" + MGRNO
				+ ", ADMRDEPT=" + ADMRDEPT + "]";
	}
	
}

