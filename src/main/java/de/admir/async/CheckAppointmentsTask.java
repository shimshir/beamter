package de.admir.async;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */
public class CheckAppointmentsTask implements Runnable {
	private int taskId;
	private final String url;
	private final ConcurrentMap<String, Integer> freeAppointmentsMap;
	private final static String SELECTOR = "div.calendar-table div.calendar-month-table>table>tbody>tr>td.buchbar";
	private final static Logger LOG = Logger.getLogger(CheckAppointmentsTask.class);

	public CheckAppointmentsTask(int taskId, String url, ConcurrentMap<String, Integer> freeAppointmentsMap) {
		this.taskId = taskId;
		this.url = url;
		this.freeAppointmentsMap = freeAppointmentsMap;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).timeout(14 * 1000).get();
			freeAppointmentsMap.put(url, doc.select(SELECTOR).size());
			LOG.info("Parsed URL_" + taskId + ", result: " + freeAppointmentsMap.get(url));
		} catch (IOException e) {
			LOG.error("Exception while connecting to url.", e);
		}
	}
}
