package weatherapp;

import java.text.ParseException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
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
public class WeatherApp_Small extends Application {

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
        background[0] = new Image(getClass().getResourceAsStream("/res/CloudyBackground_Small.png"));
        background[1] = new Image(getClass().getResourceAsStream("/res/NightBackground_Small.png"));
        background[2] = new Image(getClass().getResourceAsStream("/res/RainyBackground_Small.png"));
        background[3] = new Image(getClass().getResourceAsStream("/res/SunnyCloudyBackground_Small.png"));
        background[4] = new Image(getClass().getResourceAsStream("/res/SunnyBackground_Small.png"));

        //Weather Icons...
        weatherIcon[0] = new Image(getClass().getResourceAsStream("/res/icons/cloudy_icon.png"));
        weatherIcon[1] = new Image(getClass().getResourceAsStream("/res/icons/fog_icon.png"));
        weatherIcon[2] = new Image(getClass().getResourceAsStream("/res/icons/moon_icon.png"));
        weatherIcon[3] = new Image(getClass().getResourceAsStream("/res/icons/partcloudy_day_icon.png"));
        weatherIcon[4] = new Image(getClass().getResourceAsStream("/res/icons/partcloudy_night_icon.png"));
        weatherIcon[5] = new Image(getClass().getResourceAsStream("/res/icons/rain_icon.png"));
        weatherIcon[6] = new Image(getClass().getResourceAsStream("/res/icons/sleet_icon.png"));
        weatherIcon[7] = new Image(getClass().getResourceAsStream("/res/icons/snow_icon.png"));
        weatherIcon[8] = new Image(getClass().getResourceAsStream("/res/icons/sunny_icon.png"));
        weatherIcon[9] = new Image(getClass().getResourceAsStream("/res/icons/wind_icon.png"));

        for (int i = 0; i < 5; i++) {
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

        for (int i = 0; i < temp.length; i++) {
            holdF[i] = ((9 * temp[i]) / 5) + 32;
            LholdF[i] = ((9 * Ltemp[i]) / 5) + 32;
            NholdF[i] = ((9 * Ntemp[i]) / 5) + 32;
            SholdF[i] = ((9 * Stemp[i]) / 5) + 32;
        }

        //[MAIN FRAME]----------------------------------------------------------
        int i = setImage(icon[0]);
        ImageView Image_1 = new ImageView(background[i]);
        Image_1.setFitWidth(320);
        Image_1.setFitHeight(480);

        int j = setIcon(icon[0]);
        ImageView wIcon = new ImageView(weatherIcon[j]);
        wIcon.setLayoutY(180);

        i = setImage(icon[1]);
        ImageView Image_2 = new ImageView(background[i]);
        Image_2.setFitWidth(320);
        Image_2.setFitHeight(480);

        i = setImage(icon[2]);
        ImageView Image_3 = new ImageView(background[i]);
        Image_3.setFitWidth(320);
        Image_3.setFitHeight(480);

        i = setImage(icon[3]);
        ImageView Image_4 = new ImageView(background[i]);
        Image_4.setFitWidth(320);
        Image_4.setFitHeight(480);

        i = setImage(icon[4]);
        ImageView Image_5 = new ImageView(background[i]);
        Image_5.setFitWidth(320);
        Image_5.setFitHeight(480);

        //Elements...
        ///// SETTINGS BUTTON//
        Image settingsIcon = new Image(getClass().getResourceAsStream("/res/icons/settings_icon.png"));
        ImageView settings = new ImageView(settingsIcon);
        settings.setLayoutX(8);
        settings.setLayoutY(20);
        ///// TIME /////
        Label timeLabel = new Label(time[0]);
        timeLabel.setLayoutX(20.50);
        timeLabel.setLayoutY(69.50);
        timeLabel.getStyleClass().add("outline");
        ///// DATE ////
        Label dateLabel = new Label(date[0]);
        dateLabel.setLayoutX(20.50);
        dateLabel.setLayoutY(99.50);
        dateLabel.getStyleClass().add("outline");
        ///// LOCATION /////
        Label locationLabel = new Label(location.toUpperCase());
        locationLabel.setLayoutX(20.50);
        locationLabel.setLayoutY(129.50);
        locationLabel.getStyleClass().add("outline");
        ///// SUMMARY /////
        Label weatherLabel = new Label(weather[0]);
        weatherLabel.setMinWidth(320);
        weatherLabel.setLayoutY(410.50);
        weatherLabel.setAlignment(Pos.CENTER);
        if (weather[0].length() > 13) {
            weatherLabel.getStyleClass().add("outlinesmaller");
        } else {
            weatherLabel.getStyleClass().add("outline");
        }
        ///// TEMPERATURE /////
        Label tempLabel = new Label(temp[0] + selectedTemperature);
        tempLabel.setMinWidth(320);
        tempLabel.setLayoutY(440.50);
        tempLabel.setAlignment(Pos.CENTER);
        tempLabel.getStyleClass().add("outline");

        //----------------------------------------------------------------------
        
        //[SETTINGS]------------------------------------------------------------     
        Pane settingsMenu = new Pane();
        settingsMenu.setId("settings_pane");
        settingsMenu.setPrefSize(200, 300);
        settingsMenu.setLayoutX(50);
        settingsMenu.setLayoutY(20);
        settingsMenu.setVisible(false);

        Pane settingsBackground = new Pane();
        settingsBackground.setId("settings_background");
        settingsBackground.setPrefSize(200, 300);
        settingsBackground.setLayoutX(50);
        settingsBackground.setLayoutY(20);
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
        settings_label.setAlignment(Pos.CENTER);
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

        Button switchMode = new Button(" Tablet Mode ");
        switchMode.setLayoutX(35);
        switchMode.setId("settings_BUTTON");
        hb4.getChildren().add(switchMode);
        //----------------------------------------------------------------------

        //Panel...
        Group root = new Group();
        Scene scene = new Scene(root, 320, 480);

        //Add elements...
        root.getChildren().add(Image_5);//IMAGE_5
        root.getChildren().add(Image_4);//IMAGE_4
        root.getChildren().add(Image_3);//IMAGE_3
        root.getChildren().add(Image_2);//IMAGE_2
        root.getChildren().add(Image_1);//IMAGE_1

        root.getChildren().add(settings);

        root.getChildren().add(timeLabel);
        root.getChildren().add(dateLabel);
        root.getChildren().add(locationLabel);

        root.getChildren().add(wIcon);

        root.getChildren().add(weatherLabel);
        root.getChildren().add(tempLabel);

        root.getChildren().add(settingsBackground);
        root.getChildren().add(settingsMenu);

        //SETTINGS MENU FUNCTIONALITY
        settings.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {

                if (menuStatus == false) {
                    settingsBackground.setOpacity(0.95);
                    settingsMenu.setVisible(true);
                    menuStatus = true;
                } else {
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

        cel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                cel.setGraphic(new ImageView(celcius_Pressed));
                fah.setGraphic(new ImageView(fahrenheit_notPressed));
                if (selectedTemperature.equals("°F")) {
                    temp = holdC;
                }
                selectedTemperature = "°C";
                tempLabel.setText(temp[currentIndex] + selectedTemperature);
            }
        });

        fah.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                fah.setGraphic(new ImageView(fahrenheit_Pressed));
                cel.setGraphic(new ImageView(celcius_notPressed));
                if (selectedTemperature.equals("°C")) {
                    temp = holdF;
                }
                selectedTemperature = "°F";
                tempLabel.setText(temp[currentIndex] + selectedTemperature);
            }
        });
        t24.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                t24.setGraphic(new ImageView(H24_Pressed));
                t12.setGraphic(new ImageView(H12_notPressed));

                selectedTime = "24";
                timeLabel.setText(formatTime(time[currentIndex]));
            }
        });

        t12.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                t12.setGraphic(new ImageView(H12_Pressed));
                t24.setGraphic(new ImageView(H24_notPressed));

                selectedTime = "12";
                timeLabel.setText(formatTime(time[currentIndex]));
            }
        });
        
        select_loc.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String output) {

                output = select_loc.getSelectionModel().getSelectedItem();
                if (!output.equals("null")) {
                    if (output.equals("London") && !selectedZone.equals("London")) {
                        temp = Ltemp;
                        time = Ltime;
                        date = Ldate;
                        location = Llocation;
                        icon = Licon;
                        weather = Lweather;
                        temp = Ltemp;
                        if (selectedTemperature.equals("°F")) {
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
                        timeLabel.setText(time[currentIndex]);
                        dateLabel.setText(date[currentIndex]);
                        locationLabel.setText(location.toUpperCase());
                        weatherLabel.setText(weather[currentIndex]);
                        tempLabel.setText(temp[currentIndex] + selectedTemperature);
                        if (currentIndex == 0) {//Only when right side is dragged...
                            Image_1.setOpacity(1);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[0]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 1) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[1]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 2) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[2]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 3) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[3]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 4) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(0);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[4]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                    }
                    if (output.equals("New York") && !selectedZone.equals("New York")) {
                        temp = Ntemp;
                        time = Ntime;
                        date = Ndate;
                        location = Nlocation;
                        icon = Nicon;
                        weather = Nweather;
                        temp = Ntemp;
                        if (selectedTemperature.equals("°F")) {
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
                        if (currentIndex == 0) {//Only when right side is dragged...
                            Image_1.setOpacity(1);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[0]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 1) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[1]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 2) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[2]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 3) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[3]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 4) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(0);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[4]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                    }
                    if (output.equals("Sydney") && !selectedZone.equals("Sydney")) {
                        temp = Stemp;
                        time = Stime;
                        date = Sdate;
                        location = Slocation;
                        icon = Sicon;
                        weather = Sweather;
                        temp = Stemp;
                        if (selectedTemperature.equals("°F")) {
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
                        if (currentIndex == 0) {//Only when right side is dragged...
                            Image_1.setOpacity(1);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[0]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 1) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(1);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[1]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 2) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(1);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[2]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 3) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(1);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[3]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                        if (currentIndex == 4) {//Only when right side is dragged...
                            Image_1.setOpacity(0);
                            Image_2.setOpacity(0);
                            Image_3.setOpacity(0);
                            Image_4.setOpacity(0);
                            Image_5.setOpacity(1);
                            int j = setIcon(icon[4]);
                            wIcon.setImage(weatherIcon[j]);
                        }
                    }
                }
            }
        });

        switchMode.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {

                Stage stage = (Stage) switchMode.getScene().getWindow();
                stage.close();

                new WeatherApp_Large().start(new Stage());
            }
        });
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSceneX() > 265) {
                    //System.out.println("Mouse Pressed: " + mouseEvent.getSceneX() + "<---X Y--->" + mouseEvent.getSceneY());
                    if (mouseEvent.getSceneY() < 119) {//Only when right side is dragged...
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
                    }
                    if (mouseEvent.getSceneY() < 239 && mouseEvent.getSceneY() > 119) {//Only when right side is dragged...
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
                    }
                    if (mouseEvent.getSceneY() < 359 && mouseEvent.getSceneY() > 239) {//Only when right side is dragged...
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
                    }
                    if (mouseEvent.getSceneY() < 479 && mouseEvent.getSceneY() > 359) {//Only when right side is dragged...
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
                    }
                    if (mouseEvent.getSceneY() > 479) {//Only when right side is dragged...
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
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                //System.out.println(mouseEvent.getSceneX() + "<---X Y--->" +mouseEvent.getSceneY());
                //IMAGE_1
                if (mouseEvent.getSceneX() > 265) {//Only when right side is dragged...
                    if (mouseEvent.getSceneY() < 10) {//Only when right side is dragged...
                        Image_1.setOpacity(0.92);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 20 && mouseEvent.getSceneY() >= 10) {//Only when right side is dragged...
                        Image_1.setOpacity(0.84);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 30 && mouseEvent.getSceneY() >= 20) {//Only when right side is dragged...
                        Image_1.setOpacity(0.75);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 40 && mouseEvent.getSceneY() >= 30) {//Only when right side is dragged...
                        Image_1.setOpacity(0.67);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 50 && mouseEvent.getSceneY() >= 40) {//Only when right side is dragged...
                        Image_1.setOpacity(0.59);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 60 && mouseEvent.getSceneY() >= 50) {//Only when right side is dragged...
                        Image_1.setOpacity(0.5);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 70 && mouseEvent.getSceneY() >= 60) {//Only when right side is dragged...
                        Image_1.setOpacity(0.42);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 80 && mouseEvent.getSceneY() >= 70) {//Only when right side is dragged...
                        Image_1.setOpacity(0.34);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 90 && mouseEvent.getSceneY() >= 80) {//Only when right side is dragged...
                        Image_1.setOpacity(0.26);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 100 && mouseEvent.getSceneY() >= 90) {//Only when right side is dragged...
                        Image_1.setOpacity(0.17);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 110 && mouseEvent.getSceneY() >= 100) {//Only when right side is dragged...
                        Image_1.setOpacity(0.09);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 120 && mouseEvent.getSceneY() >= 110) {//Only when right side is dragged...
                        Image_1.setOpacity(0);

                        currentIndex = 0;
                        int j = setIcon(icon[0]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[0]));
                        dateLabel.setText(date[0]);

                        weatherLabel.setText(weather[0]);
                        tempLabel.setText(temp[0] + selectedTemperature);
                    }
                    //IMAGE_2
                    if (mouseEvent.getSceneY() < 130 && mouseEvent.getSceneY() >= 120) {//Only when right side is dragged...
                        Image_2.setOpacity(0.92);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 140 && mouseEvent.getSceneY() >= 130) {//Only when right side is dragged...
                        Image_2.setOpacity(0.84);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 150 && mouseEvent.getSceneY() >= 140) {//Only when right side is dragged...
                        Image_2.setOpacity(0.75);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 160 && mouseEvent.getSceneY() >= 150) {//Only when right side is dragged...
                        Image_2.setOpacity(0.67);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 170 && mouseEvent.getSceneY() >= 160) {//Only when right side is dragged...
                        Image_2.setOpacity(0.59);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 180 && mouseEvent.getSceneY() >= 170) {//Only when right side is dragged...
                        Image_2.setOpacity(0.5);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 190 && mouseEvent.getSceneY() >= 180) {//Only when right side is dragged...
                        Image_2.setOpacity(0.42);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 200 && mouseEvent.getSceneY() >= 190) {//Only when right side is dragged...
                        Image_2.setOpacity(0.34);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 210 && mouseEvent.getSceneY() >= 200) {//Only when right side is dragged...
                        Image_2.setOpacity(0.26);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 220 && mouseEvent.getSceneY() >= 210) {//Only when right side is dragged...
                        Image_2.setOpacity(0.17);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 230 && mouseEvent.getSceneY() >= 220) {//Only when right side is dragged...
                        Image_2.setOpacity(0.09);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 240 && mouseEvent.getSceneY() >= 230) {//Only when right side is dragged...
                        Image_2.setOpacity(0);

                        currentIndex = 1;
                        int j = setIcon(icon[1]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[1]));
                        dateLabel.setText(date[1]);

                        weatherLabel.setText(weather[1]);
                        tempLabel.setText(temp[1] + selectedTemperature);
                    }
                    //IMAGE_3
                    if (mouseEvent.getSceneY() < 250 && mouseEvent.getSceneY() >= 240) {//Only when right side is dragged...
                        Image_3.setOpacity(0.92);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 260 && mouseEvent.getSceneY() >= 250) {//Only when right side is dragged...
                        Image_3.setOpacity(0.84);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 270 && mouseEvent.getSceneY() >= 260) {//Only when right side is dragged...
                        Image_3.setOpacity(0.75);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 280 && mouseEvent.getSceneY() >= 270) {//Only when right side is dragged...
                        Image_3.setOpacity(0.67);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 290 && mouseEvent.getSceneY() >= 280) {//Only when right side is dragged...
                        Image_3.setOpacity(0.59);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 300 && mouseEvent.getSceneY() >= 290) {//Only when right side is dragged...
                        Image_3.setOpacity(0.5);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 310 && mouseEvent.getSceneY() >= 300) {//Only when right side is dragged...
                        Image_3.setOpacity(0.42);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 320 && mouseEvent.getSceneY() >= 310) {//Only when right side is dragged...
                        Image_3.setOpacity(0.34);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 330 && mouseEvent.getSceneY() >= 320) {//Only when right side is dragged...
                        Image_3.setOpacity(0.26);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 340 && mouseEvent.getSceneY() >= 330) {//Only when right side is dragged...
                        Image_3.setOpacity(0.17);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 350 && mouseEvent.getSceneY() >= 340) {//Only when right side is dragged...
                        Image_3.setOpacity(0.09);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 360 && mouseEvent.getSceneY() >= 350) {//Only when right side is dragged...
                        Image_3.setOpacity(0);

                        currentIndex = 2;
                        int j = setIcon(icon[2]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[2]));
                        dateLabel.setText(date[2]);

                        weatherLabel.setText(weather[2]);
                        tempLabel.setText(temp[2] + selectedTemperature);
                    }
                    //IMAGE_4
                    if (mouseEvent.getSceneY() < 370 && mouseEvent.getSceneY() >= 360) {//Only when right side is dragged...
                        Image_4.setOpacity(0.92); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 380 && mouseEvent.getSceneY() >= 370) {//Only when right side is dragged...
                        Image_4.setOpacity(0.84); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 390 && mouseEvent.getSceneY() >= 380) {//Only when right side is dragged...
                        Image_4.setOpacity(0.75); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 400 && mouseEvent.getSceneY() >= 390) {//Only when right side is dragged...
                        Image_4.setOpacity(0.67); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 410 && mouseEvent.getSceneY() >= 400) {//Only when right side is dragged...
                        Image_4.setOpacity(0.59); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 420 && mouseEvent.getSceneY() >= 410) {//Only when right side is dragged...
                        Image_4.setOpacity(0.5); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 430 && mouseEvent.getSceneY() >= 420) {//Only when right side is dragged...
                        Image_4.setOpacity(0.42); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 440 && mouseEvent.getSceneY() >= 430) {//Only when right side is dragged...
                        Image_4.setOpacity(0.34); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 450 && mouseEvent.getSceneY() >= 440) {//Only when right side is dragged...
                        Image_4.setOpacity(0.26); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 460 && mouseEvent.getSceneY() >= 450) {//Only when right side is dragged...
                        Image_4.setOpacity(0.17); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 470 && mouseEvent.getSceneY() >= 460) {//Only when right side is dragged...
                        Image_4.setOpacity(0.09); //If there was another image under it

                        currentIndex = 3;
                        int j = setIcon(icon[3]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[3]));
                        dateLabel.setText(date[3]);

                        weatherLabel.setText(weather[3]);
                        tempLabel.setText(temp[3] + selectedTemperature);
                    }
                    if (mouseEvent.getSceneY() < 480 && mouseEvent.getSceneY() >= 470) {//Only when right side is dragged...
                        Image_4.setOpacity(0); //If there was another image under it

                        currentIndex = 4;
                        int j = setIcon(icon[4]);
                        wIcon.setImage(weatherIcon[j]);

                        timeLabel.setText(formatTime(time[4]));
                        dateLabel.setText(date[4]);

                        weatherLabel.setText(weather[4]);
                        tempLabel.setText(temp[4] + selectedTemperature);
                    }
                }
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSceneX() > 265) {
                    //System.out.println("Mouse Released: " + mouseEvent.getSceneX() + "<---X Y--->" + mouseEvent.getSceneY());
                    if (mouseEvent.getSceneY() < 119) {//Only when right side is dragged...
                        Image_1.setOpacity(1);
                        Image_2.setOpacity(1);
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                    }
                    if (mouseEvent.getSceneY() < 239) {//Only when right side is dragged...
                        Image_2.setOpacity(1);
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                    }
                    if (mouseEvent.getSceneY() < 359) {//Only when right side is dragged...
                        Image_3.setOpacity(1);
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                    }
                    if (mouseEvent.getSceneY() < 479) {//Only when right side is dragged...
                        Image_4.setOpacity(1);
                        Image_5.setOpacity(1);
                    }
                    if (mouseEvent.getSceneY() > 479) {//Only when right side is dragged...
                        Image_5.setOpacity(1);
                    }
                }
            }
        });

        //Stage Properties...
        scene.getStylesheets().add("/res/main.css");
        primaryStage.setTitle("Weather Scroll");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
	
	//SETS RELEAVANT WEATHER BACKGROUND
    public int setImage(String i) {
        int imageNumb = -1;

        if (i.equals("clear-day")) {
            imageNumb = 4;
        } else if (i.equals("clear-night") || i.equals("partly-cloudy-night")) {
            imageNumb = 1;
        } else if (i.equals("rain") || i.equals("snow") || i.equals("sleet")) {
            imageNumb = 2;
        } else if (i.equals("cloudy") || i.equals("wind") || i.equals("fog")) {
            imageNumb = 0;
        } else if (i.equals("partly-cloudy-day")) {
            imageNumb = 3;
        }
        return imageNumb;
    }
	
	//SETS RELEAVANT WEATHER ICON
    public int setIcon(String j) {
        int iconNumb = 0;

        if (j.equals("clear-day")) {
            iconNumb = 8;
        } else if (j.equals("clear-night")) {
            iconNumb = 2;
        } else if (j.equals("partly-cloudy-night")) {
            iconNumb = 4;
        } else if (j.equals("rain")) {
            iconNumb = 5;
        } else if (j.equals("snow")) {
            iconNumb = 7;
        } else if (j.equals("sleet")) {
            iconNumb = 6;
        } else if (j.equals("cloudy")) {
            iconNumb = 0;
        } else if (j.equals("wind")) {
            iconNumb = 9;
        } else if (j.equals("fog")) {
            iconNumb = 1;
        } else if (j.equals("partly-cloudy-day")) {
            iconNumb = 3;
        }

        return iconNumb;
    }

    //change format of time on button click
    public String formatTime(String t) {
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
        } else if (selectedTime.equals("12")) {
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
