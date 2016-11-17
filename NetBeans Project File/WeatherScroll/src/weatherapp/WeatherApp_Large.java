package weatherapp;

import java.text.ParseException;
import java.util.Calendar;
import javafx.application.Application;
import javafx.event.EventHandler;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author GROUP 30 - NOT ANOTHER COURSEWORK
 */
public class WeatherApp_Large extends Application {
    
    private Image[] background = new Image[5];
    private Image[] weatherIcon = new Image[10];
    //CurrentZone
    private String[] date = new String[5];
    private String[] time = new String[5];
    private String location;
    private String[] icon = new String[5];
    private String[] weather = new String[5];
    private int[] temp = new int[5];
    private int[] holdC = new int[5];
    private int[] holdF = new int[5];
    //London
    private String[] Ldate = new String[5];
    private String[] Ltime = new String[5];
    private String Llocation;
    private String[] Licon = new String[5];
    private String[] Lweather = new String[5];
    private int[] Ltemp = new int[5];
    private int[] LholdC = new int[5];
    private int[] LholdF = new int[5];
    //New York
    private String[] Ndate = new String[5];
    private String[] Ntime = new String[5];
    private String Nlocation;
    private String[] Nicon = new String[5];
    private String[] Nweather = new String[5];
    private int[] Ntemp = new int[5];
    private int[] NholdC = new int[5];
    private int[] NholdF = new int[5];
    //Sydney
    private String[] Sdate = new String[5];
    private String[] Stime = new String[5];
    private String Slocation;
    private String[] Sicon = new String[5];
    private String[] Sweather = new String[5];
    private int[] Stemp = new int[5];
    private int[] SholdC = new int[5];
    private int[] SholdF = new int[5];
    
    private int currentIndex = 0;
    private boolean menuStatus = false;
    private String selectedTemperature = "°C", selectedTime = "24", selectedZone = "London";

    @Override
    public void start(Stage primaryStage) {//Get weather info from API
        
        weatherAPI getInfo = new weatherAPI();
        weatherAPI getInfo2 = new weatherAPI();
        weatherAPI getInfo3 = new weatherAPI();
        try {
            getInfo.runForecastIO("London");
            getInfo2.runForecastIO("New York");
            getInfo3.runForecastIO("Sydney");
            
        } catch (ParseException ex) {
            System.exit(0);
        }
        
        //Background Images...
        background[0] = new Image(getClass().getResourceAsStream("/res/CloudyBackground_Large.png"));
        background[1] = new Image(getClass().getResourceAsStream("/res/NightBackground_Large.png"));
        background[2] = new Image(getClass().getResourceAsStream("/res/RainyBackground_Large.png"));
        background[3] = new Image(getClass().getResourceAsStream("/res/SunnyCloudyBackground_Large.png"));
        background[4] = new Image(getClass().getResourceAsStream("/res/SunnyBackground_Large.png"));
        
        //Weather Icons...
        weatherIcon[0] = new Image(getClass().getResourceAsStream("/res/icons/cloudy_icon_LARGE.png"));
        weatherIcon[1] = new Image(getClass().getResourceAsStream("/res/icons/fog_icon_LARGE.png"));
        weatherIcon[2] = new Image(getClass().getResourceAsStream("/res/icons/moon_icon_LARGE.png"));
        weatherIcon[3] = new Image(getClass().getResourceAsStream("/res/icons/partcloudy_day_icon_LARGE.png"));
        weatherIcon[4] = new Image(getClass().getResourceAsStream("/res/icons/partcloudy_night_icon_LARGE.png"));
        weatherIcon[5] = new Image(getClass().getResourceAsStream("/res/icons/rain_icon_LARGE.png"));
        weatherIcon[6] = new Image(getClass().getResourceAsStream("/res/icons/sleet_icon_LARGE.png"));
        weatherIcon[7] = new Image(getClass().getResourceAsStream("/res/icons/snow_icon_LARGE.png"));
        weatherIcon[8] = new Image(getClass().getResourceAsStream("/res/icons/sunny_icon_LARGE.png"));
        weatherIcon[9] = new Image(getClass().getResourceAsStream("/res/icons/wind_icon_LARGE.png"));
        
		//GET AND SET API Information...
        for (int i=0; i<5; i++){
            time = getInfo.getTime();
            date = getInfo.getDate();
            location = getInfo.getLocation();
            icon = getInfo.getIcon();
            weather = getInfo.getWeather();
            temp = getInfo.getTemperature();
            holdC = getInfo.getTemperature();
            Ltime = getInfo.getTime();
            Ldate = getInfo.getDate();
            Llocation = getInfo.getLocation();
            Licon = getInfo.getIcon();
            Lweather = getInfo.getWeather();
            Ltemp = getInfo.getTemperature();
            LholdC = getInfo.getTemperature();
            Ntime = getInfo2.getTime();
            Ndate = getInfo2.getDate();
            Nlocation = getInfo2.getLocation();
            Nicon = getInfo2.getIcon();
            Nweather = getInfo2.getWeather();
            Ntemp = getInfo2.getTemperature();
            NholdC = getInfo2.getTemperature();
            Stime = getInfo3.getTime();
            Sdate = getInfo3.getDate();
            Slocation = getInfo3.getLocation();
            Sicon = getInfo3.getIcon();
            Sweather = getInfo3.getWeather();
            Stemp = getInfo3.getTemperature();
            SholdC = getInfo3.getTemperature();
        }
        
        for (int i = 0; i<temp.length; i++)
        {
            holdF[i] = ((9*temp[i])/5)+32;
            LholdF[i] = ((9*Ltemp[i])/5)+32;
            NholdF[i] = ((9*Ntemp[i])/5)+32;
            SholdF[i] = ((9*Stemp[i])/5)+32;
        }
        
        int i = setImage(icon[0]);
        ImageView Image_1 = new ImageView(background[i]);
        Image_1.setFitWidth(1024);
        Image_1.setFitHeight(768);
        
        int j = setIcon(icon[0]);
        ImageView wIcon = new ImageView(weatherIcon[j]);
        wIcon.setLayoutY(195);
        wIcon.setLayoutX(320);
        
        
        i = setImage(icon[1]);
        ImageView Image_2 = new ImageView(background[i]);
        Image_2.setFitWidth(1024);
        Image_2.setFitHeight(768);
        
        i = setImage(icon[2]);
        ImageView Image_3 = new ImageView(background[i]);
        Image_3.setFitWidth(1024);
        Image_3.setFitHeight(768);
        
        i = setImage(icon[3]);
        ImageView Image_4 = new ImageView(background[i]);
        Image_4.setFitWidth(1024);
        Image_4.setFitHeight(768);
        
        i = setImage(icon[4]);
        ImageView Image_5 = new ImageView(background[i]);
        Image_5.setFitWidth(1024);
        Image_5.setFitHeight(768);
        
        //////////////////////Time/////////////////////////////////
        Calendar cal = Calendar.getInstance();   
        int nowHour = cal.get(Calendar.HOUR_OF_DAY);
        ////////////////////////////////////////////////////////////
       
        //Elements...
        
        //[SETTINGS]------------------------------------------------------------     
        Pane settingsMenu = new Pane();
        settingsMenu.setId("settings_pane");
        
        settingsMenu.setPrefSize(200,300);   
        settingsMenu.setLayoutX(75);   
        settingsMenu.setLayoutY(25);
        settingsMenu.setVisible(false);
        
        Pane settingsBackground = new Pane();
        settingsBackground.setId("settings_background");
        settingsBackground.setPrefSize(200,300);   
        settingsBackground.setLayoutX(75);   
        settingsBackground.setLayoutY(25);
        settingsBackground.setOpacity(0);
        
        //Settings Icons...
        Image celcius_Pressed = new Image(getClass().getResourceAsStream("/res/C1.png"));
        Image celcius_notPressed = new Image(getClass().getResourceAsStream("/res/C2.png"));
        
        Image fahrenheit_Pressed = new Image(getClass().getResourceAsStream("/res/F1.png"));
        Image fahrenheit_notPressed = new Image(getClass().getResourceAsStream("/res/F2.png"));
    
        Image H24_Pressed = new Image(getClass().getResourceAsStream("/res/24A.png"));
        Image H24_notPressed = new Image(getClass().getResourceAsStream("/res/24B.png"));
        
        Image H12_Pressed = new Image(getClass().getResourceAsStream("/res/12A.png"));
        Image H12_notPressed = new Image(getClass().getResourceAsStream("/res/12B.png"));
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(5, 5, 5, 20));
        vb.setSpacing(3);
        settingsMenu.getChildren().add(vb);
        
        Label settings_label = new Label();
        settings_label.setText("OPTIONS");
        settings_label.setId("settings_HEADER");
        vb.getChildren().add(settings_label);
        
        ///// 12/24 HOUR CLOCK TOGGLE /////
        Text hour_Text = new Text("12/24 Hour Clock:");
        hour_Text.setId("settings_TEXT");
        vb.getChildren().add(hour_Text);
        
        HBox hb = new HBox();
        hb.setPadding(new Insets(5, 5, 5, 5));
        hb.setSpacing(0);
        vb.getChildren().add(hb);
        
        Label t24 = new Label();
        t24.setGraphic(new ImageView(H24_Pressed));
        hb.getChildren().add(t24);
    
        Label t12 = new Label();
        t12.setGraphic(new ImageView(H12_notPressed));
        hb.getChildren().add(t12);
        
        ///// TEMPERATURE TOGGLE /////
        Text temp_Text = new Text("Temperature:");
        temp_Text.setId("settings_TEXT");
        vb.getChildren().add(temp_Text);
        
        HBox hb2 = new HBox();
        hb2.setPadding(new Insets(5, 5, 5, 5));
        hb2.setSpacing(0);
        vb.getChildren().add(hb2);
        
        Label cel = new Label();
        cel.setGraphic(new ImageView(celcius_Pressed));
        hb2.getChildren().add(cel);
    
        Label fah = new Label();
        fah.setGraphic(new ImageView(fahrenheit_notPressed));
        hb2.getChildren().add(fah);
        
        ///// SET LOCATION /////
        Text loc_Text = new Text("Location:");
        loc_Text.setId("settings_TEXT");
        vb.getChildren().add(loc_Text);
        
        HBox hb3 = new HBox();
        hb3.setPadding(new Insets(5, 5, 5, 5));
        hb3.setSpacing(0);
        vb.getChildren().add(hb3);
        
        ComboBox<String> select_loc = new ComboBox<String>();
        System.setProperty("glass.accessible.force", "false");
        select_loc.getItems().addAll(
        "London",
        "New York",
        "Sydney"
        );
        select_loc.setPromptText("London");
        hb3.getChildren().add(select_loc);
        
        ///// SWITCH MODE /////
        HBox hb4 = new HBox();
        hb4.setPadding(new Insets(5, 5, 5, 5));
        hb4.setSpacing(20);
        vb.getChildren().add(hb4);
        
        Button switchMode = new Button("Mobile Mode");
        switchMode.setLayoutX(35);
        switchMode.setId("settings_BUTTON");
        hb4.getChildren().add(switchMode);
        
        //----------------------------------------------------------------------
   
		///// SETTINGS //
        Image settingsIcon = new Image(getClass().getResourceAsStream("/res/icons/settings_icon_LARGE.png"));
        ImageView settings = new ImageView(settingsIcon);
        settings.setLayoutX(25);
        settings.setLayoutY(20);
        ///// TIME /////
        Label timeLabel = new Label(time[0]);
        timeLabel.setMinWidth(1024);
        timeLabel.setAlignment(Pos.CENTER);
        timeLabel.setLayoutY(85);
        timeLabel.getStyleClass().add("largeApp");
        ///// DATE ////
        Label dateLabel = new Label(date[0]);
        dateLabel.setMinWidth(1024);
        dateLabel.setAlignment(Pos.CENTER);
        dateLabel.setLayoutY(125);
        dateLabel.getStyleClass().add("largeApp");
        ///// Location /////
        Label locationLabel = new Label(location.toUpperCase());
        locationLabel.setMinWidth(1024);
        locationLabel.setAlignment(Pos.CENTER);
        locationLabel.setLayoutY(165);
        locationLabel.getStyleClass().add("largeApp");
        ///// Summary /////
        Label weatherLabel = new Label(weather[0]);
        weatherLabel.setMinWidth(1024);
        weatherLabel.setLayoutY(540.50);
        weatherLabel.setAlignment(Pos.CENTER);
        weatherLabel.getStyleClass().add("largeApp");
        ///// Temp /////
        Label tempLabel = new Label(temp[0] + "°C");
        tempLabel.setMinWidth(1024);
        tempLabel.setLayoutY(570.50); 
        tempLabel.setAlignment(Pos.CENTER);
        tempLabel.getStyleClass().add("largeApp");
        //////////SliderLabels///////////////////////////////////////////
        Label sliderHour1 = new Label(time[0]);
        sliderHour1.setLayoutX(40);
        sliderHour1.setLayoutY(670);
        sliderHour1.getStyleClass().add("sliderLabel");
        
        Label sliderHour2 = new Label(time[1]);
        sliderHour2.setLayoutX(275);
        sliderHour2.setLayoutY(670);
        sliderHour2.getStyleClass().add("sliderLabel");
        
        Label sliderHour3 = new Label(time[2]);
        sliderHour3.setLayoutX(485);
        sliderHour3.setLayoutY(670);
        sliderHour3.getStyleClass().add("sliderLabel");
        
        Label sliderHour4 = new Label(time[3]);
        sliderHour4.setLayoutX(718);
        sliderHour4.setLayoutY(670);
        sliderHour4.getStyleClass().add("sliderLabel");
        
        Label sliderHour5 = new Label(time[4]);
        sliderHour5.setLayoutX(925);
        sliderHour5.setLayoutY(670);
        sliderHour5.getStyleClass().add("sliderLabel");
        
        
        /////////////////////////Slider//////////////////////////////////
        Slider slider = new Slider();
        slider.getStyleClass().add("slider");
        slider.setLayoutX(60);
        slider.setLayoutY(650);
        slider.setMinWidth(900);
        slider.setMin(nowHour);
        slider.setMax(nowHour+12);
        slider.setValue(1);
        slider.setShowTickLabels(false);
        slider.setMajorTickUnit(3);
        slider.setBlockIncrement(30);
        slider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                double sliderVal = slider.getValue();
                
                if (sliderVal >= nowHour) {
                        
                        Image_1.setOpacity(1);
                        Image_2.setOpacity(1);
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                        
                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);
                    
                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                        if (sliderVal >= nowHour+0.25) {
                            Image_1.setOpacity(0.90);
                        }
                        if (sliderVal >= nowHour+0.5) {
                            Image_1.setOpacity(0.80);
                        }
                        if (sliderVal >= nowHour+0.75) {
                            Image_1.setOpacity(0.70);
                        }
                        if (sliderVal >= nowHour+1) {
                            Image_1.setOpacity(0.60);
                        }
                        if (sliderVal >= nowHour+1.25) {
                            Image_1.setOpacity(0.50);
                        }
                        if (sliderVal >= nowHour+1.5) {
                            Image_1.setOpacity(0.40);
                        }
                        if (sliderVal >= nowHour+1.75) {
                            Image_1.setOpacity(0.30);
                        }
                        if (sliderVal >= nowHour+2) {
                            Image_1.setOpacity(0.20);
                        }
                        if (sliderVal >= nowHour+2.25) {
                            Image_1.setOpacity(0.15);
                        }
                        if (sliderVal >= nowHour+2.5) {
                            Image_1.setOpacity(0.10);
                        }
                }
                if (sliderVal >= nowHour+3) {
                    
                        Image_1.setOpacity(0);
                        Image_2.setOpacity(1);
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                        
                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);
                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                        
                        if (sliderVal >= nowHour+3.25) {
                            Image_2.setOpacity(0.90);
                        }
                        if (sliderVal >= nowHour+3.5) {
                            Image_2.setOpacity(0.80);
                        }
                        if (sliderVal >= nowHour+3.75) {
                            Image_2.setOpacity(0.70);
                        }
                        if (sliderVal >= nowHour+4) {
                            Image_2.setOpacity(0.60);
                        }
                        if (sliderVal >= nowHour+4.25) {
                            Image_2.setOpacity(0.50);
                        }
                        if (sliderVal >= nowHour+4.5) {
                            Image_2.setOpacity(0.40);
                        }
                        if (sliderVal >= nowHour+4.75) {
                            Image_2.setOpacity(0.20);
                        }
                        if (sliderVal >= nowHour+5) {
                            Image_2.setOpacity(0.10);
                        }
                        if (sliderVal >= nowHour+5.5) {
                            Image_2.setOpacity(0.10);
                        }
                }
                if (sliderVal >= nowHour+6) {
                    
                        Image_1.setOpacity(0);
                        Image_2.setOpacity(0);
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                        
                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);
                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                        
                        if (sliderVal >= nowHour+6.25) {
                            Image_3.setOpacity(0.90);
                        }
                        if (sliderVal >= nowHour+6.5) {
                            Image_3.setOpacity(0.80);
                        }
                        if (sliderVal >= nowHour+6.75) {
                            Image_3.setOpacity(0.70);
                        }
                        if (sliderVal >= nowHour+7) {
                            Image_3.setOpacity(0.60);
                        }
                        if (sliderVal >= nowHour+7.25) {
                            Image_3.setOpacity(0.50);
                        }
                        if (sliderVal >= nowHour+7.5) {
                            Image_3.setOpacity(0.40);
                        }
                        if (sliderVal >= nowHour+7.75) {
                            Image_3.setOpacity(0.30);
                        }
                        if (sliderVal >= nowHour+8) {
                            Image_3.setOpacity(0.20);
                        }
                        if (sliderVal >= nowHour+8.5) {
                            Image_3.setOpacity(0.10);
                        }
                }
                if (sliderVal >= nowHour+9) {
                    
                        Image_1.setOpacity(0);
                        Image_2.setOpacity(0);
                        Image_3.setOpacity(0);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                        
                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);
                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                        
                        if (sliderVal >= nowHour+9.25) {
                            Image_4.setOpacity(0.90);
                        }
                        if (sliderVal >= nowHour+9.5) {
                            Image_4.setOpacity(0.80);
                        }
                        if (sliderVal >= nowHour+9.75) {
                            Image_4.setOpacity(0.70);
                        }
                        if (sliderVal >= nowHour+10) {
                            Image_4.setOpacity(0.60);
                        }
                        if (sliderVal >= nowHour+10.25) {
                            Image_4.setOpacity(0.50);
                        }
                        if (sliderVal >= nowHour+10.5) {
                            Image_4.setOpacity(0.40);
                        }
                        if (sliderVal >= nowHour+10.75) {
                            Image_4.setOpacity(0.30);
                        }
                        if (sliderVal >= nowHour+11) {
                            Image_4.setOpacity(0.20);
                        }
                        if (sliderVal >= nowHour+11.5) {
                            Image_4.setOpacity(0.10);
                        }
                }
                if (sliderVal >= nowHour+12) {
                    
                        Image_1.setOpacity(0);
                        Image_2.setOpacity(0);
                        Image_3.setOpacity(0);
                        Image_4.setOpacity(0);
                        Image_5.setOpacity(1);
                        
                        currentIndex = 4;
                        int j = setIcon(icon[4]);
                        wIcon.setImage(weatherIcon[j]);
                        timeLabel.setText(formatTime(time[4]));
                        dateLabel.setText(date[4]);

                        weatherLabel.setText(weather[4]);
                        tempLabel.setText(temp[4] + selectedTemperature);
                }
            }
        });     
        
        
        //Panels...
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768);
        //Add elements...
        root.getChildren().add(Image_5);//IMAGE_5
        root.getChildren().add(Image_4);//IMAGE_4
        root.getChildren().add(Image_3);//IMAGE_3
        root.getChildren().add(Image_2);//IMAGE_2
        root.getChildren().add(Image_1);//IMAGE_1
        
        
        root.getChildren().add(settings);
        
        root.getChildren().add(wIcon);
        root.getChildren().add(slider);
        root.getChildren().add(sliderHour1);//Slider Labels
        root.getChildren().add(sliderHour2);
        root.getChildren().add(sliderHour3);
        root.getChildren().add(sliderHour4);
        root.getChildren().add(sliderHour5);
        
        root.getChildren().add(timeLabel);
        root.getChildren().add(dateLabel);
        root.getChildren().add(locationLabel);
         
        root.getChildren().add(weatherLabel);
        root.getChildren().add(tempLabel);
        
        root.getChildren().add(settingsBackground);
        root.getChildren().add(settingsMenu);

        //SETTINGS MENU FUNCTIONALITY
        settings.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent mouseEvent){
                    if (menuStatus == false){
                        settingsBackground.setOpacity(0.95);
                        settingsMenu.setVisible(true);
                        menuStatus = true;
                    }
                    else{
                        settingsBackground.setOpacity(0);
                        settingsMenu.setVisible(false);
                        menuStatus = false;
                    }
                }    
        });
        
        settings.addEventFilter(MouseEvent.MOUSE_PRESSED, 
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent r) {
                            settings.setOpacity(0.5);
                        }
                    });
         settings.addEventFilter(MouseEvent.MOUSE_RELEASED, 
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent r) {
                            settings.setOpacity(1); 
                        }
                    });
        //Celcius Button
        cel.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent mouseEvent){
                    cel.setGraphic(new ImageView(celcius_Pressed));
                    fah.setGraphic(new ImageView(fahrenheit_notPressed));
                    if(selectedTemperature.equals("°F"))
                    {
                        temp = holdC;
                    }
                    selectedTemperature = "°C";
                    tempLabel.setText(temp[currentIndex] + selectedTemperature);
                }    
        });
        //Fahrenheight Button
        fah.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent mouseEvent){
                    fah.setGraphic(new ImageView(fahrenheit_Pressed));
                    cel.setGraphic(new ImageView(celcius_notPressed));
                    if(selectedTemperature.equals("°C"))
                    {
                        temp = holdF;
                    }
                    selectedTemperature = "°F";
                    tempLabel.setText(temp[currentIndex] + selectedTemperature);
                }    
        });
        //24-Hour Clock Button
        t24.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent mouseEvent){
                    t24.setGraphic(new ImageView(H24_Pressed));
                    t12.setGraphic(new ImageView(H12_notPressed));

                    selectedTime = "24";
                    sliderHour1.setText(formatTime(time[0]));
                    sliderHour2.setText(formatTime(time[1]));
                    sliderHour3.setText(formatTime(time[2]));
                    sliderHour4.setText(formatTime(time[3]));
                    sliderHour5.setText(formatTime(time[4]));
                    timeLabel.setText(formatTime(time[currentIndex]));
                }    
        });
        //12-Hour Clock Button
        t12.setOnMouseClicked(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent mouseEvent){
                    t12.setGraphic(new ImageView(H12_Pressed));
                    t24.setGraphic(new ImageView(H24_notPressed));
                    
                    selectedTime = "12";
                    sliderHour1.setText(formatTime(time[0]));
                    sliderHour2.setText(formatTime(time[1]));
                    sliderHour3.setText(formatTime(time[2]));
                    sliderHour4.setText(formatTime(time[3]));
                    sliderHour5.setText(formatTime(time[4]));
                    timeLabel.setText(formatTime(time[currentIndex]));
                }    
        });
        //Location Combobox
        select_loc.valueProperty().addListener(new ChangeListener<String>() {
        @Override 
        public void changed(ObservableValue ov, String t, String output) {
            
            output = select_loc.getSelectionModel().getSelectedItem(); 
                        if(output.equals("London") && !selectedZone.equals("London"))
                        {
                            temp = Ltemp;
                            time = Ltime;
                            date = Ldate;
                            location = Llocation;
                            icon = Licon;
                            weather = Lweather;
                            temp = Ltemp;
                            if(selectedTemperature.equals("°F"))
                            {
                                temp = LholdF;
                            }
                            holdC = LholdC;
                            holdF = LholdF;
                            selectedZone = "London";
                            int i = setImage(icon[0]);
                            Image_1.setImage(background[i]);
                            i = setImage(icon[1]);
                            Image_2.setImage(background[i]);
                            i = setImage(icon[2]);
                            Image_3.setImage(background[i]);
                            i = setImage(icon[3]);
                            Image_4.setImage(background[i]);
                            i = setImage(icon[4]);
                            Image_5.setImage(background[i]);
                            timeLabel.setText(formatTime(time[currentIndex]));
                            dateLabel.setText(date[currentIndex]);
                            locationLabel.setText(location.toUpperCase());
                            weatherLabel.setText(weather[currentIndex]);
                            tempLabel.setText(temp[currentIndex] + selectedTemperature);
                            if (currentIndex == 0){
                                Image_1.setOpacity(1);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[0]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 1){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[1]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 2){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[2]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 3){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[3]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 4){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(0);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[4]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            sliderHour1.setText(formatTime(time[0]));
                            sliderHour2.setText(formatTime(time[1]));
                            sliderHour3.setText(formatTime(time[2]));
                            sliderHour4.setText(formatTime(time[3]));
                            sliderHour5.setText(formatTime(time[4]));
                        }
                        if(output.equals("New York") && !selectedZone.equals("New York"))
                        {
                            temp = Ntemp;
                            time = Ntime;
                            date = Ndate;
                            location = Nlocation;
                            icon = Nicon;
                            weather = Nweather;
                            temp = Ntemp;
                            if(selectedTemperature.equals("°F"))
                            {
                                temp = NholdF;
                            }
                            holdC = NholdC;
                            holdF = NholdF;
                            selectedZone = "New York";
                            int i = setImage(icon[0]);
                            Image_1.setImage(background[i]);
                            i = setImage(icon[1]);
                            Image_2.setImage(background[i]);
                            i = setImage(icon[2]);
                            Image_3.setImage(background[i]);
                            i = setImage(icon[3]);
                            Image_4.setImage(background[i]);
                            i = setImage(icon[4]);
                            Image_5.setImage(background[i]);
                            timeLabel.setText(formatTime(time[currentIndex]));
                            dateLabel.setText(date[currentIndex]);
                            locationLabel.setText(location.toUpperCase());
                            weatherLabel.setText(weather[currentIndex]);
                            tempLabel.setText(temp[currentIndex] + selectedTemperature);
                            if (currentIndex == 0){
                                Image_1.setOpacity(1);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[0]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 1){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[1]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 2){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[2]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 3){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[3]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 4){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(0);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[4]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            sliderHour1.setText(formatTime(time[0]));
                            sliderHour2.setText(formatTime(time[1]));
                            sliderHour3.setText(formatTime(time[2]));
                            sliderHour4.setText(formatTime(time[3]));
                            sliderHour5.setText(formatTime(time[4]));
                        }
                        if(output.equals("Sydney") && !selectedZone.equals("Sydney"))
                        {
                            temp = Stemp;
                            time = Stime;
                            date = Sdate;
                            location = Slocation;
                            icon = Sicon;
                            weather = Sweather;
                            temp = Stemp;
                            if(selectedTemperature.equals("°F"))
                            {
                                temp = SholdF;
                            }
                            holdC = SholdC;
                            holdF = SholdF;
                            selectedZone = "Sydney";
                            int i = setImage(icon[0]);
                            Image_1.setImage(background[i]);
                            i = setImage(icon[1]);
                            Image_2.setImage(background[i]);
                            i = setImage(icon[2]);
                            Image_3.setImage(background[i]);
                            i = setImage(icon[3]);
                            Image_4.setImage(background[i]);
                            i = setImage(icon[4]);
                            Image_5.setImage(background[i]);
                            timeLabel.setText(formatTime(time[currentIndex]));
                            dateLabel.setText(date[currentIndex]);
                            locationLabel.setText(location.toUpperCase());
                            weatherLabel.setText(weather[currentIndex]);
                            tempLabel.setText(temp[currentIndex] + selectedTemperature);
                            if (currentIndex == 0){
                                Image_1.setOpacity(1);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[0]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 1){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(1);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[1]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 2){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(1);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[2]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 3){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(1);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[3]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                            if (currentIndex == 4){
                                Image_1.setOpacity(0);
                                Image_2.setOpacity(0);
                                Image_3.setOpacity(0);
                                Image_4.setOpacity(0);
                                Image_5.setOpacity(1);
                                int j = setIcon(icon[4]);
                                wIcon.setImage(weatherIcon[j]);
                            }
                        }
                        sliderHour1.setText(formatTime(time[0]));
                        sliderHour2.setText(formatTime(time[1]));
                        sliderHour3.setText(formatTime(time[2]));
                        sliderHour4.setText(formatTime(time[3]));
                        sliderHour5.setText(formatTime(time[4]));
        }
        });
        
        //Switch Mode Button
        switchMode.setOnMousePressed(new EventHandler<MouseEvent>(){
 
            public void handle(MouseEvent mouseEvent){
                
                Stage stage = (Stage) switchMode.getScene().getWindow();
                stage.close();
                
                new WeatherApp_Small().start(new Stage());
            }
        });
        
        
        //Stage Properties...
        scene.getStylesheets().add("/res/main.css");
        primaryStage.setTitle("WeatherApp");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //SET RELEVANT IMAGE
    public int setImage(String i){
        int imageNumb = -1;
        
        if (i.equals("clear-day")){
            imageNumb = 4;
        }
        else if (i.equals("clear-night") || i.equals("partly-cloudy-night")){
            imageNumb = 1;
        }
        else if (i.equals("rain") || i.equals("snow") || i.equals("sleet")){
            imageNumb = 2;
        }
        else if (i.equals("cloudy") || i.equals("wind") || i.equals("fog")){
            imageNumb = 0;
        }
        else if (i.equals("partly-cloudy-day")){
            imageNumb = 3;
        }
        return imageNumb;
    }
    //SET RELEVANT ICON
    public int setIcon(String j){
        int iconNumb = 0;
        
        if (j.equals("clear-day")){
            iconNumb = 8;
        }
        else if (j.equals("clear-night")){
            iconNumb = 2;
        }
        else if (j.equals("partly-cloudy-night")){
            iconNumb = 4;
        }
        else if (j.equals("rain")){
            iconNumb = 5;
        }
        else if (j.equals("snow")){
            iconNumb = 7;
        }
        else if (j.equals("sleet")){
            iconNumb = 6;
        }
        else if (j.equals("cloudy")){
            iconNumb = 0;
        }
        else if (j.equals("wind")){
            iconNumb = 9;
        }
        else if (j.equals("fog")){
            iconNumb = 1;
        }
        else if (j.equals("partly-cloudy-day")){
            iconNumb = 3;
        }
        
        return iconNumb;
    }   
	//Format time according to user selection
    public String formatTime(String t){
        if (selectedTime.equals("24")) {
            if (t.equals("12:00 PM")) {
                t = "12:00";
            }
            if (t.equals("1:00 PM")) {
                t = "13:00";
            }
            if (t.equals("2:00 PM")) {
                t = "14:00";
            }
            if (t.equals("3:00 PM")) {
                t = "15:00";
            }
            if (t.equals("4:00 PM")) {
                t = "16:00";
            }
            if (t.equals("5:00 PM")) {
                t = "17:00";
            }
            if (t.equals("6:00 PM")) {
                t = "18:00";
            }
            if (t.equals("7:00 PM")) {
                t = "19:00";
            }
            if (t.equals("8:00 PM")) {
                t = "20:00";
            }
            if (t.equals("9:00 PM")) {
                t = "21:00";
            }
            if (t.equals("10:00 PM")) {
                t = "22:00";
            }
            if (t.equals("11:00 PM")) {
                t = "23:00";
            }
            if (t.equals("12:00 AM")) {
                t = "00:00";
            }
            if (t.equals("1:00 AM")) {
                t = "01:00";
            }
            if (t.equals("2:00 AM")) {
                t = "02:00";
            }
            if (t.equals("3:00 AM")) {
                t = "03:00";
            }
            if (t.equals("4:00 AM")) {
                t = "04:00";
            }
            if (t.equals("5:00 AM")) {
                t = "05:00";
            }
            if (t.equals("6:00 AM")) {
                t = "06:00";
            }
            if (t.equals("7:00 AM")) {
                t = "07:00";
            }
            if (t.equals("8:00 AM")) {
                t = "08:00";
            }
            if (t.equals("9:00 AM")) {
                t = "09:00";
            }
            if (t.equals("10:00 AM")) {
                t = "10:00";
            }
            if (t.equals("11:00 AM")) {
                t = "11:00";
            }
        }
        else if (selectedTime.equals("12")) {
            if (t.equals("12:00")) {
                t = "12:00 PM";
            }
            if (t.equals("13:00")) {
                t = "1:00 PM";
            }
            if (t.equals("14:00")) {
                t = "2:00 PM";
            }
            if (t.equals("15:00")) {
                t = "3:00 PM";
            }
            if (t.equals("16:00")) {
                t = "4:00 PM";
            }
            if (t.equals("17:00")) {
                t = "5:00 PM";
            }
            if (t.equals("18:00")) {
                t = "6:00 PM";
            }
            if (t.equals("19:00")) {
                t = "7:00 PM";
            }
            if (t.equals("20:00")) {
                t = "8:00 PM";
            }
            if (t.equals("21:00")) {
                t = "9:00 PM";
            }
            if (t.equals("22:00")) {
                t = "10:00 PM";
            }
            if (t.equals("23:00")) {
                t = "11:00 PM";
            }
            if (t.equals("00:00")) {
                t = "12:00 AM";
            }
            if (t.equals("01:00")) {
                t = "1:00 AM";
            }
            if (t.equals("02:00")) {
                t = "2:00 AM";
            }
            if (t.equals("03:00")) {
                t = "3:00 AM";
            }
            if (t.equals("04:00")) {
                t = "4:00 AM";
            }
            if (t.equals("05:00")) {
                t = "5:00 AM";
            }
            if (t.equals("06:00")) {
                t = "6:00 AM";
            }
            if (t.equals("07:00")) {
                t = "7:00 AM";
            }
            if (t.equals("08:00")) {
                t = "8:00 AM";
            }
            if (t.equals("09:00")) {
                t = "9:00 AM";
            }
            if (t.equals("10:00")) {
                t = "10:00 AM";
            }
            if (t.equals("11:00")) {
                t = "11:00 AM";
            }
            
        }
        return t;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
