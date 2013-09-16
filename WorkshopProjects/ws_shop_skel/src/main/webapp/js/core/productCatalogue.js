/***********************************************
 *  
 *  A proxy representing the product service (model)
 *   NOTE: All methods return deferred object, see JQuery
 *  
 */
// var ProductCatalogue 

// Constructor Function
var ProductCatalogue = function(baseUri) {
    this.bUri = baseUri;
};

ProductCatalogue.prototype = (function () {
    
   return {
     getAll : function() {
        var me = this;
        return $.getJSON(me.bUri);
     },
             
     find : function(id) {
        var me = this;
        return $.getJSON(me.bUri + "/"+ id);
     },
     
     getRange : function(fst, max) {
        var me = this;
        return $.getJSON(me.bUri + "/range", {fst: fst, max: max});
     },
             
     getCount : function() {
        var me = this;
        return $.getJSON(me.bUri + "/count");
     },
     
     add : function(name, price) {
        var me = this;
        return $.ajax( { type: 'POST',
            url: me.bUri,
            data: {name : name, price : price }
        } );
     },
     
     update : function(oldId, newId, name, price) {
        var me = this;
        return $.ajax( { type: 'PUT',
            url: me.bUri + "/" + oldId,
            data: {id: newId, name: name, price: price}
        });
     },
     
     delete : function(id) {
        var me = this;
        return $.ajax( { type: 'DELETE',
            url: me.bUri + "/" + id
        });
     }
   };
})();



