package br.com.ecom.rep.v1

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.validation.ValidationError
import play.api.mvc.SimpleResult
import play.api.mvc.ResponseHeader
import play.api.libs.iteratee.Enumerator
import play.api.mvc.Request
import play.api.mvc.RequestHeader
import utils.RestUtil
import play.api.db.slick.RequestWithDbSession

case class Product(
  id: Option[Long],
  storeId: Long,
  name: String,
  hash: String) {
}

object Product {

	val version = "application/br.com.ecom.rep.product-v1+json"
  	  
	implicit val reads: Reads[Product] = (
	  (__ \ "id").readNullable[Long] and
	  (__ \ "storeId").read[Long] and
	  (__ \ "name").read[String] and
	  (__ \ "hash").read[String]
	)(Product.apply _)  
	  
	implicit val writes = new Writes[Product] {
	    def writes(p: Product): JsValue = {
	      Json.obj(
	        "id" -> p.id,
	        "storeId" -> p.storeId,
	        "name" -> p.name,
	        "hash" -> p.hash)
	    }
	}

	
  /**
   * Renders a br.com.ecom.rep.v1.Product as JSON
   * @param p - br.com.ecom.rep.v1.Product
   */
  def renderV1(p: br.com.ecom.rep.v1.Product)(r: RequestHeader) = { 
    
  	SimpleResult(
    		header = ResponseHeader(RestUtil.getCode(r.method), 
    		    Map("Content-Type" -> br.com.ecom.rep.v1.Product.version)), 
    		body = Enumerator(Json.toJson(p))
    )
    
  }
	
	
}