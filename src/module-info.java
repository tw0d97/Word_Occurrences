module WordOccurrencs {
	requires javafx.controls;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires org.jsoup;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
