module bricey {
    requires javafx.controls;
    requires
    requires com.google.gson;
    opens bricey to com.google.gson;
    exports bricey;
}