package de.admir.controller;

import de.admir.dto.AppointmentData;
import de.admir.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */

@RestController
public class MainController {

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<AppointmentData>> index(
			@RequestParam(name="forceupdate", defaultValue = "false") boolean forceUpdate) {
		if (forceUpdate)
			appointmentService.updateFreeAppointmentsMap();
		return ResponseEntity.ok(appointmentService.getAppointmentDatas());
	}
}
