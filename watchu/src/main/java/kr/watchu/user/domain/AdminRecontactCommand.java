package kr.watchu.user.domain;

import java.sql.Date;

public class AdminRecontactCommand {
	private int recontact_num; 	//��� ��ȣ
	private int contact_num;	//�� ��ȣ
	private String recontent;	//��۳���
	private Date reg_date;		//�����
	private String id;			//�亯 ID
	
	public int getRecontact_num() {
		return recontact_num;
	}
	public void setRecontact_num(int recontact_num) {
		this.recontact_num = recontact_num;
	}
	public int getContact_num() {
		return contact_num;
	}
	public void setContact_num(int contact_num) {
		this.contact_num = contact_num;
	}
	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "AdminRecontactCommand [recontact_num=" + recontact_num + ", contact_num=" + contact_num + ", recontent="
				+ recontent + ", reg_date=" + reg_date + ", id=" + id + "]";
	}
}
