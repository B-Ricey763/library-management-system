module bricey {
    requires javafx.controls;
    requires com.google.gson;
    opens bricey to com.google.gson;
    exports bricey;
}