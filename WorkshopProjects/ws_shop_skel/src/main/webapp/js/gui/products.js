/*
 * All js for the products.html page 
 * 
 * NOTE: Last in file a comment to enable debugging
 * because this is dynamically loaded (doesn't work by default to debug
 * dynamic scripts in Chrome at least...)
 * 
 */
// Run after DOM constructed (same as $(document).ready())
$(function() {
    var nav = new Navigator(shop.getProductCatalogue());
    nav.refresh(createTable, function() {
        createErrorDialog("Can't list!!").dialog("open");
    });
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    $("#next-button")
            .button()
            .click(function() {
        nav.next(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!").dialog("open");
        }
    });

    $("#prev-button")
            .button()
            .click(function() {
        nav.prev(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!").dialog("open");
        }

    });


    $("#add-product")
            .button()
            .click(function() {
        createAddDialog().dialog("open");
    });

<<<<<<< HEAD
    $("#products").click(function(e) {
=======

    $("#products tbody").click(function(e) {
>>>>>>> Mhmhm
        var prod = shop.getProductCatalogue().find(e.target.parentNode.cells[0].innerText);
        prod.done(function(product) {
            createEditDeleteDialog(product).dialog("open");
        });
    });

    /**********************************************
     *   
     *   Functions for redering tables, dialogs and helper functions
     */
    function createTable(products) {
        // Use JQuery and HTML
        $("#products").contents().remove();
        $(products).each(function() {
            $("#products").append("<tr><td>" + this.id
                    + "</td><td>" + this.name
                    + "</td><td>" + this.price
                    + "</td></tr>");
        });
    }

    function createAddDialog() {
        var ret = $("#dialog-form").dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Save: function() {
                    var prod = getFormDialogData();
                    if (!validate(prod)) {
                        createErrorDialog("Wrong input!").dialog("open");
                    }
                    else {
                        shop.getProductCatalogue().add(prod.name, prod.price);
                        nav.refresh(createTable, fail);
                        function fail() {
                            createErrorDialog("Can't list!!").dialog("open");
                        }
                        clearValidationErrors();
                        clearFormDialogData();
                        $(this).dialog("close");
                    }
                },
                Cancel: function() {
                    clearValidationErrors();
                    clearFormDialogData();
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-form').dialog('option', 'title', 'Add New Product');
        return ret;
    }

    // Possible to both edit and delet from same dialog
    function createEditDeleteDialog(product) {
        var ret = $("#dialog-form").dialog({
            autoOpen: false,
//        height: 300,
//        width: 350,
            modal: true,
            buttons: {
                Save: function() {
                    var prod = getFormDialogData();
                    if (!validate(prod)) {
                        createErrorDialog("Wrong input!").dialog("open");
                    }
                    else {
                        shop.getProductCatalogue().update(prod.id, prod.id, prod.name, prod.price);
                        nav.refresh(createTable, fail);
                        function fail() {
                            createErrorDialog("Can't list!!").dialog("open");
                        }
                        clearValidationErrors();
                        clearFormDialogData();
                        $(this).dialog("close");
                    }
                },
                Cancel: function() {
                    clearValidationErrors();
                    clearFormDialogData();
                    $(this).dialog("close");
                },
                Delete: function() {
                    createConfirmDeleteDialog(product.id).dialog("open");
                }
            }
        });
        $('#dialog-form').dialog('option', 'title', 'Edit or Delete Product');
        setFormDialogData(product);
        return ret;
    }

    // If delete in above dialog, have to confirm.
    function createConfirmDeleteDialog(id) {
        // Use JQueryUI dialog
        $("#dialog-message").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            buttons: {
                Ok: function() {
                    shop.getProductCatalogue().delete(id);
                    clearFormDialogData();
                    nav.refresh(createTable, fail);
                    function fail() {
                        createErrorDialog("Can't list!!").dialog("open");
                    }
                    $(this).dialog("close");
                    $("#dialog-form").dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'WARNING!');
        $("#dialog-message #msg").text("Are you sure you want to delete product with id: " + id);
        return $('#dialog-message');
    }

    function createErrorDialog(message) {
        // Using JQueryUI dialog
        $("#dialog-message").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            buttons: {
                Ok: function() {
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Something went wrong!');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    function validate(product) {
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/; // A number
        if (!regex.test(product.price)) {
            $("#dialog-form #price").addClass("ui-state-error");
            return false;
        }
        return true;
    }

    function clearValidationErrors() {
        $("#dialog-form input").each(function() {
            $(this).removeClass("ui-state-error");
        });
    }

    function getFormDialogData() {
        var product = {};
        product.id = $("#dialog-form #id").val();
        product.name = $("#dialog-form #pname").val();
        product.price = $("#dialog-form #price").val();
        return product;
    }

    function setFormDialogData(product) {
        $("#dialog-form #id").val(product.id);
        $("#dialog-form #pname").val(product.name);
        $("#dialog-form #price").val(product.price);
    }

    function clearFormDialogData() {
        $("#dialog-form #id").val("");
        $("#dialog-form #pname").val("");
        $("#dialog-form #price").val("");
    }

});

// This is neede to debug dynamically downloaded JS in Chrome
//@ sourceURL=products.js
