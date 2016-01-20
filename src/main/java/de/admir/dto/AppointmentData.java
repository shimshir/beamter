package de.admir.dto;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */
public class AppointmentData {
	private String monthsDisplay;
	private String url;
	private Integer amount;

	public AppointmentData(String monthsDisplay, String url, Integer amount) {
		this.monthsDisplay = monthsDisplay;
		this.url = url;
		this.amount = amount;
	}

	public String getMonthsDisplay() {
		return monthsDisplay;
	}

	public void setMonthsDisplay(String monthsDisplay) {
		this.monthsDisplay = monthsDisplay;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
