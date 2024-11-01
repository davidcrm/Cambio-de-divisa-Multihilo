module com.example.ut2_act3_pgv_davidcarrenomacias {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;

    opens com.example.ut2_act3_pgv_davidcarrenomacias to javafx.fxml;
    exports com.example.ut2_act3_pgv_davidcarrenomacias;
}