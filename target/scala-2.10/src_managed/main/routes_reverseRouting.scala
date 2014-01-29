// @SOURCE:/Volumes/MacintoshHD/Users/thiago/Desktop/Sample-Scala-Play-Web-Api-Project/conf/routes
// @HASH:71a4e08c09c99be24dbe0bdbf2fda33149b4cd1d
// @DATE:Tue Jan 28 22:46:23 BRST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:7
// @LINE:5
package controllers {

// @LINE:7
class ReverseProductsResource {
    

// @LINE:7
def post(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "product")
}
                                                
    
}
                          

// @LINE:5
class ReverseProductResource {
    

// @LINE:5
def get(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "product/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                
    
}
                          
}
                  


// @LINE:7
// @LINE:5
package controllers.javascript {

// @LINE:7
class ReverseProductsResource {
    

// @LINE:7
def post : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProductsResource.post",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "product"})
      }
   """
)
                        
    
}
              

// @LINE:5
class ReverseProductResource {
    

// @LINE:5
def get : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ProductResource.get",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "product/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:7
// @LINE:5
package controllers.ref {


// @LINE:7
class ReverseProductsResource {
    

// @LINE:7
def post(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProductsResource.post(), HandlerDef(this, "controllers.ProductsResource", "post", Seq(), "POST", """""", _prefix + """product""")
)
                      
    
}
                          

// @LINE:5
class ReverseProductResource {
    

// @LINE:5
def get(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ProductResource.get(id), HandlerDef(this, "controllers.ProductResource", "get", Seq(classOf[Long]), "GET", """""", _prefix + """product/$id<[^/]+>""")
)
                      
    
}
                          
}
        
    