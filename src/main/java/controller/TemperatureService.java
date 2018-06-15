package controller;

import java.util.Date;

import pojo.HeaterSwitch;
import pojo.Temperature;

public class TemperatureService {
	
	private static Temperature currentTemperature;	
	private static Temperature desiredTemperature;
	private static Temperature temperatureThershold;		
	
	public static TemperatureService instance;
	
		
	public static TemperatureService get() {		
			if(instance == null) {
				instance = new TemperatureService();
			}		
		return instance;
	}
	
	public Temperature storeTemperature(Temperature temperature) {		
		currentTemperature.setValue(temperature.getValue());
		currentTemperature.setLogDate(temperature.getLogDate());		
		return temperature;
	}
	
	public HeaterSwitch getHeaterStatus() {
		
		HeaterSwitch heaterSwitch = new HeaterSwitch();
		Date logDate = new Date();		
		heaterSwitch.setLogDate(logDate);		
		if(desiredTemperature.getValue() - currentTemperature.getValue() > temperatureThershold.getValue()) {
			heaterSwitch.setStatus("ON");
			return heaterSwitch;
		} else if(heaterSwitch.getStatus() == "ON" && currentTemperature.getValue() - desiredTemperature.getValue() < temperatureThershold.getValue()){
			heaterSwitch.setStatus("ON");
			return heaterSwitch;
		} else {
			heaterSwitch.setStatus("OFF");
			return heaterSwitch;
		}
	}
	
	public Temperature getCurrentTemperature() {		
		return currentTemperature;
	}
	
	public Temperature getDesiredTemperature() {
		return desiredTemperature;
	}	
		
	public Temperature setDesiredTemperature(Temperature temperature) {
		desiredTemperature.setValue(temperature.getValue());
		desiredTemperature.setLogDate(temperature.getLogDate());
		return desiredTemperature;
	}
	
	public Temperature getTemperatureThershold() {
		return temperatureThershold;
	}

	public Temperature setTemperatureThershold(Temperature temperature) {
		temperatureThershold.setValue(temperature.getValue());
		temperatureThershold.setLogDate(temperature.getLogDate());
		return temperatureThershold;
	}	
}
