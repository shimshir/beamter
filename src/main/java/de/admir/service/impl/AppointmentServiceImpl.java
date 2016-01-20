package de.admir.service.impl;

import de.admir.async.CheckAppointmentsTask;
import de.admir.dto.AppointmentData;
import de.admir.service.AppointmentService;
import de.admir.util.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */
@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

	private static LocalDateTime[] dateTimes;
	private static String[] unixTimes;
	private ConcurrentMap<String, Integer> freeAppointmentsMap = new ConcurrentHashMap<>();
	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(unixTimes.length);

	static {
		dateTimes = new LocalDateTime[2];
		dateTimes[0] = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
		dateTimes[1] = dateTimes[0].plusMonths(2);

		unixTimes = new String[2];
		unixTimes[0] = "";
		unixTimes[1] = String.valueOf(dateTimes[1].toEpochSecond(ZoneOffset.of("+1")));
	}

	@PostConstruct
	private void init() {
		for (int i = 0; i < unixTimes.length; i++) {
			scheduler.scheduleAtFixedRate(new CheckAppointmentsTask(i + 1, Constants.BASE_APPOINTMENT_URL +
					unixTimes[i], freeAppointmentsMap), 0, Constants.INTERVAL, TimeUnit.SECONDS);
		}
	}

	@Override
	public void updateFreeAppointmentsMap() {
		new CheckAppointmentsTask(1, Constants.BASE_APPOINTMENT_URL + unixTimes[0], freeAppointmentsMap).run();
		new CheckAppointmentsTask(2, Constants.BASE_APPOINTMENT_URL + unixTimes[1], freeAppointmentsMap).run();
	}

	@Override
	public List<AppointmentData> getAppointmentDatas() {
		List<AppointmentData> appointmentDatas = new ArrayList<>();
		for (int i = 0; i < unixTimes.length; i++) {
			String monthDisplay = dateTimes[i].getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " - " +
					dateTimes[i].plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			appointmentDatas.add(new AppointmentData(monthDisplay, Constants.BASE_APPOINTMENT_URL + unixTimes[i],
					freeAppointmentsMap.get(Constants.BASE_APPOINTMENT_URL + unixTimes[i])));
		}
		return appointmentDatas;
	}
}
