package de.admir.util;

/**
 * Author:  Admir Memic
 * Date:    20.01.16
 * E-Mail:  admir.memic@dmc.de
 */
public interface Constants {
	String BASE_APPOINTMENT_URL = "https://service.berlin.de/terminvereinbarung/termin/tag.php?" +
			"termin=1&anliegen[]=120686&dienstleisterlist=122210,122217,122219,122227,122231,122238,122243,122252," +
			"122260, 122262,122254,122271,122273,122277,122280,122282,122284,122291,122285,122286,122296,150230," +
			"122301,122297,122294,122312,122314,122304,122311,122309,317869,324433,325341,324434,122281,324414," +
			"122283,122279,122276,122274,122267,122246,122251,122257,122208,122226&Datum=";
	Integer INTERVAL = 60;
}
