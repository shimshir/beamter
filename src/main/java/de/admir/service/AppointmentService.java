package de.admir.service;


import de.admir.dto.AppointmentData;

import java.util.List;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */

public interface AppointmentService {
	void updateFreeAppointmentsMap();
	List<AppointmentData> getAppointmentDatas();
}
