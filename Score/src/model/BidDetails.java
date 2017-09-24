package model;

public class BidDetails {

	String BidId;
//	String companyName;
	public BidDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BidDetails(String bidId) {
		super();
		BidId = bidId;
//		this.companyName = companyName;
	}
	public String getBidId() {
		return BidId;
	}
	public void setBidId(String bidId) {
		BidId = bidId;
	}
//	public String getCompanyName() {
//		return companyName;
//	}
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
	
	
}
