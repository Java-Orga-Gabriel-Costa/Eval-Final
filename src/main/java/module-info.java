module com.example.costagabrielevaljavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.costagabrielevaljavafx to javafx.fxml;
    exports com.example.costagabrielevaljavafx;
}