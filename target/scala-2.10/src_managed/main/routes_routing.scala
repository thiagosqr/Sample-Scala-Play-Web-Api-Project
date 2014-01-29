// @SOURCE:/Volumes/MacintoshHD/Users/thiago/Desktop/Sample-Scala-Play-Web-Api-Project/conf/routes
// @HASH:71a4e08c09c99be24dbe0bdbf2fda33149b4cd1d
// @DATE:Tue Jan 28 22:46:23 BRST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:5
private[this] lazy val controllers_ProductResource_get0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("product/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:7
private[this] lazy val controllers_ProductsResource_post1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("product"))))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """product/$id<[^/]+>""","""controllers.ProductResource.get(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """product""","""controllers.ProductsResource.post""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:5
case controllers_ProductResource_get0(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.ProductResource.get(id), HandlerDef(this, "controllers.ProductResource", "get", Seq(classOf[Long]),"GET", """""", Routes.prefix + """product/$id<[^/]+>"""))
   }
}
        

// @LINE:7
case controllers_ProductsResource_post1(params) => {
   call { 
        invokeHandler(controllers.ProductsResource.post, HandlerDef(this, "controllers.ProductsResource", "post", Nil,"POST", """""", Routes.prefix + """product"""))
   }
}
        
}

}
     