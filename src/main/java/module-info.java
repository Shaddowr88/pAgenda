module cda26.projet1.agenda {
    requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	opens model to javafx.fxml;
	exports model;
	exports views;
	exports metier;
    exports cda26.projet1.agenda;
    
}
