package kr.watchu.user.domain;

import java.sql.Date;

public class ReportCommand {
	private Integer report_num;
	private String id;
	private String report_user;
	private String report_content;
	private String report_category;
	private Date reg_date;
	private String report_title;
	
	public Integer getReport_num() {
		return report_num;
	}
	public void setReport_num(Integer report_num) {
		this.report_num = report_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReport_user() {
		return report_user;
	}
	public void setReport_user(String report_user) {
		this.report_user = report_user;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getReport_category() {
		return report_category;
	}
	public void setReport_category(String report_category) {
		this.report_category = report_category;
	}
	public String getReport_title() {
		return report_title;
	}
	public void setReport_title(String report_title) {
		this.report_title = report_title;
	}
	@Override
	public String toString() {
		return "ReportCommand [report_num=" + report_num + ", id=" + id + ", report_user=" + report_user
				+ ", report_content=" + report_content + ", report_category=" + report_category + ", reg_date="
				+ reg_date + ", report_title=" + report_title + "]";
	}
}
