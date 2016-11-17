package weatherapp;

import com.github.dvdme.ForecastIOLib.FIOHourly;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//This class requests information from forecast.io and sets the data in instance variables 
public class weatherAPI{
    
    private String[] date = new String[5]; //Hold the date
    private String[] time = new String[5]; //Hold the time
    private String location; //Hold the location
    private String[] icon = new String[5]; //Hold the icon
    private String[] weather = new String[5]; //Hold the summary of the weather
    private int[] temp = new int[5]; //Hold the temperature forecast
            
    public void runForecastIO(String loc) throws ParseException{
        ForecastIO fio = new ForecastIO("fc6558e3e5d99b387cfae3f0cf8ca228"); //instantiate the class with the API key. 
        fio.setUnits(ForecastIO.UNITS_SI);             //sets the units as SI - optional
        fio.setExcludeURL("daily,minutely");             //excluded the minutely and hourly reports from the reply
        if(loc.equals("London")){fio.getForecast("51.5072", "0.1275"); } //Sets Longitude and Latitude for London
        if(loc.equals("New York")){fio.getForecast("40.7127", "-74.0059"); } //Sets Longitude and Latitude for New York
        if(loc.equals("Sydney")){fio.getForecast("-33.8650", "151.2094"); } //Sets Longitude and Latitude for Sydney
        FIOHourly hourly = new FIOHourly(fio);
        //In case there is no hourly data available
        if(hourly.hours()<0){
            System.out.println("No hourly data.");
        }
        else{
            //Print hourly data
            int Counter = 0; //Count what position of array to insert data into
            for(int i = 0; i<15; i+=3){

                setDateTime(hourly.getHour(i).time(), Counter, loc);
                
                setLocation(fio.getTimezone());

                setIcon(hourly.getHour(i).icon(), Counter);

                setSummary(hourly.getHour(i).summary(), Counter);

                setTemperature((int) Math.round(hourly.getHour(i).temperature()), Counter);
                Counter++;
            }
        }
    }
    
    //SETTERS
    private void setDateTime(String d, int i, String loc) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm"); //Default TimeZone is GMT
        if (loc.equals("New York")){	//Set TimeZone to New York
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+5"));
		} 
        else if (loc.equals("Sydney")){	//Set TimeZone to Sydney
			sdf.setTimeZone(TimeZone.getTimeZone("GMT-11"));
		} 
        Date toDate = sdf.parse(d); 
        
        //Date
        sdf = new SimpleDateFormat("EEEE d MMM");
        date[i] = sdf.format(toDate).toUpperCase().toUpperCase();
        //Time
        sdf = new SimpleDateFormat("HH:mm");

        time[i] = sdf.format(toDate).toUpperCase().toUpperCase();

    }
    
    private void setLocation(String l){
        location = l;
    } 
    private void setIcon(String ico, int i){
        icon[i] = ico.replace("\"", "");
    }
    private void setSummary(String s, int i){
        weather[i] = s.replace("\"", "").toUpperCase();
    }
    private void setTemperature(int t, int i){
        temp[i] = t;
    }
    
    //GETTERS
    public String[] getDate(){
        return date;
    }
    public String[] getTime(){
        return time;
    }
    public String getLocation(){
        return location;
    }
    public String[] getIcon(){
        return icon;
    }
    public String[] getWeather(){
        return weather;
    }
    public int[] getTemperature(){
        return temp;
    }

}