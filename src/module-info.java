module HunterPaceTimes {
	exports Application;
	exports Classes;

	opens Classes to javafx.base, com.google.gson;
	opens Application to com.google.gson;

	requires javafx.base;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires java.desktop;
	requires java.base;
	requires com.google.gson;
	requires net.harawata.appdirs;
}