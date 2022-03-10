module GUI {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires javafx.media;
	requires javafx.graphics;
	requires java.xml;
	
	opens application to javafx.graphics, javafx.fxml;
}
