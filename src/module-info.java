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
	
	exports models to com.fasterxml.jackson.databind;
}