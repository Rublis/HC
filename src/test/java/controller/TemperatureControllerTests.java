package controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import database.DataBaseService;
import pojo.Switch;
import pojo.Temperature;
import pojo.TemperatureType;
import pojo.SwitchStatus;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureControllerTests {
	
	@Mock
	ConfigurationService configurationService;
	
	@Mock
	DataBaseService dataBaseService;
	
	@InjectMocks
	TemperatureService temperatureService;
	
	@Test
	public void StoreHeaterStatus_ReturnsHeaterSwitch() {
		Switch heaterSwitch = new Switch();
		Date date = new Date();
		heaterSwitch.setLogDate(date);
		heaterSwitch.setStatus(SwitchStatus.ON);				
		assertEquals(temperatureService.storeHeaterStatus(heaterSwitch), heaterSwitch);		
	}
	
	
	public void getCalculatedHeaterStatus(Double currentTemp, Double desiredTemp, String lastStatus, Double tempTrashold, String calculatedStatus) {
		Switch heaterSwitch = new Switch();
		Temperature currentTemperature = new Temperature();	
		Switch lasetHeaterSwitchStatus = new Switch();
				
		currentTemperature.setValue(currentTemp);
		lasetHeaterSwitchStatus.setStatus(SwitchStatus.valueOf(lastStatus));		
		
		Mockito.when(dataBaseService.getLastTemperature(TemperatureType.MEASURED)).thenReturn(currentTemperature);
		Mockito.when(configurationService.getPropertyAsDouble("DESIRED_TEMPERATURE")).thenReturn(desiredTemp);
		Mockito.when(configurationService.getPropertyAsDouble("TEMPERATURE_TRASHOLD")).thenReturn(tempTrashold);
		Mockito.when(dataBaseService.getLastSwitchStatus()).thenReturn(lasetHeaterSwitchStatus);
		
		heaterSwitch = temperatureService.getCalculatedHeaterStatus();		
		assertEquals(heaterSwitch.getStatus(), SwitchStatus.valueOf(calculatedStatus));	
	}
	
	@Test
	public void getCalculatedHeaterStatusTests() {
		
		Double currentTemp = 21.4;
		Double desiredTemp = 21.0;
		String lastStatus = "ON";
		Double tempTrashold = 0.5;
		String calculatedStatus = "ON";
		getCalculatedHeaterStatus(currentTemp, desiredTemp, lastStatus, tempTrashold, calculatedStatus);
		
		currentTemp = 22.1;
		desiredTemp = 21.0;
		lastStatus = "ON";
		tempTrashold = 0.5;
		calculatedStatus = "OFF";
		getCalculatedHeaterStatus(currentTemp, desiredTemp, lastStatus, tempTrashold, calculatedStatus);
		
		currentTemp = 21.4;
		desiredTemp = 21.0;
		lastStatus = "OFF";
		tempTrashold = 0.5;
		calculatedStatus = "OFF";
		getCalculatedHeaterStatus(currentTemp, desiredTemp, lastStatus, tempTrashold, calculatedStatus);
		
		currentTemp = 20.5;
		desiredTemp = 21.0;
		lastStatus = "OFF";
		tempTrashold = 0.5;
		calculatedStatus = "OFF";
		getCalculatedHeaterStatus(currentTemp, desiredTemp, lastStatus, tempTrashold, calculatedStatus);
		
		currentTemp = 20.4;
		desiredTemp = 21.0;
		lastStatus = "OFF";
		tempTrashold = 0.5;
		calculatedStatus = "ON";
		getCalculatedHeaterStatus(currentTemp, desiredTemp, lastStatus, tempTrashold, calculatedStatus);
	}	
}
