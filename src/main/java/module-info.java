module ftp {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires org.controlsfx.controls;
  exports ftp;
  opens ftp to javafx.fxml;
  exports ftp.View;
  opens ftp.View to com.fasterxml.jackson.databind, javafx.fxml;
  exports ftp.Controller;
  opens ftp.Controller to com.fasterxml.jackson.databind, javafx.fxml;
  exports ftp.Model;
  opens ftp.Model to com.fasterxml.jackson.databind, javafx.fxml;
  exports ftp.Model.Json;
  opens ftp.Model.Json to com.fasterxml.jackson.databind;

}