package com.example.JavafxSpringboot.controller;

import com.example.JavafxSpringboot.model.Product;
import com.example.JavafxSpringboot.service.ProductService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ProductController implements Initializable {
    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, String> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, JFXButton> deleteColumn;

    @FXML
    private JFXTextField idField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField priceField;

    @FXML
    private JFXButton addProductBtn;

    private final ProductService service;
    private ObservableList<Product> products;

    public ProductController(ProductService service) {
        this.service = service;
        this.products = FXCollections.observableArrayList();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        products.clear();
        initTable();
        validate();
        disableButton();
        deleteButton();

        products.addAll(service.getAllProduct());

        productTableView.setItems(products);

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    @FXML
    void editNameColumn(ActionEvent event) {

    }

    @FXML
    void editPriceColumn(ActionEvent event) {

    }

    public void initTable(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void disableButton(){
        addProductBtn.disableProperty().bind(
                idField.textProperty().isEmpty()
                        .or(nameField.textProperty().isEmpty()
                                .or(priceField.textProperty().isEmpty()))
        );
    }

    public void validate(){
        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*\\.?[0-9]*")) {
                priceField.setText(oldValue);
            }
        });
    }

    public void deleteButton(){
        deleteColumn.setCellFactory(ActionButtonTableCell.<Product>forTableColumn("Delete", (Product p) -> {
            productTableView.getItems().remove(p);
            service.deleteProduct(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Product "+p.getName()+" deleted");
            alert.showAndWait();
            return p;
        }));
    }

    @FXML
    public void addingProduct(ActionEvent event){

        String id = idField.getText();
        String name = nameField.getText();
        String price = priceField.getText();


        Product product = new Product(id, name, Double.parseDouble(price));
        if (service.isExist(product.getProductId())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Product Id already exist");
            alert.showAndWait();
        }else {
            products.add(product);
            service.addProduct(product);
        }

    }

    @FXML
    void editNameColumn(TableColumn.CellEditEvent event) {
        Product personSelected =  productTableView.getSelectionModel().getSelectedItem();
        personSelected.setName(event.getNewValue().toString());
        service.addProduct(personSelected);
    }

    @FXML
    void editPriceColumn(TableColumn.CellEditEvent event) {
        Product personSelected =  productTableView.getSelectionModel().getSelectedItem();
        personSelected.setPrice((Double) event.getNewValue());
        service.addProduct(personSelected);
    }


}
