module ftp {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  exports ftp;
  opens ftp to javafx.fxml;
  exports ftp.View;
  opens ftp.View to javafx.fxml;
    exports ftp.Controller;
    opens ftp.Controller to javafx.fxml;
}