package de.admir.controller;

import de.admir.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */

@Controller
public class MainController {

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getIndex(
					@RequestParam(name = "forceupdate", defaultValue = "false") boolean forceUpdate, ModelMap model) {
		if (forceUpdate)
			appointmentService.updateFreeAppointmentsMap();
		model.addAttribute("appointmentDatas", appointmentService.getAppointmentDatas());
		return "index";
	}
}
