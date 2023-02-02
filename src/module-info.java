/**
 * 
 */
/**
 * @author milo
 *
 */
module LichSuVietNam {
	requires org.jsoup;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.annotation;
	requires javafx.fxml;
	requires javafx.controls;
	
	exports lichsuvietnam.model to com.fasterxml.jackson.databind;
	exports lichsuvietnam to javafx.graphics;
    exports lichsuvietnam.service.dao to javafx.graphics;
}