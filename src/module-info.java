module HunterPaceTimes {
	exports Application;
	exports Classes;
	exports Settings;

	opens Classes to javafx.base, com.google.gson;
	opens Settings to com.google.gson;

	requires javafx.base;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires java.desktop;
	requires java.base;
	requires com.google.gson;
	requires net.harawata.appdirs;
}