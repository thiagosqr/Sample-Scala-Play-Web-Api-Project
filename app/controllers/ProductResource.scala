package controllers

import models.Product
import models.Products
import play.api._
import play.api.Play.current
import play.api.db.slick._
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json
import play.api.mvc._

/**
 *  Consumes Products as JSON
 *
 * @author Thiago Siqueira - thiago.sqr@gmail.com <br><br>
 *
 * Copyright © 2013
 *
 */
object ProductResource extends Controller {

  /**
   * Finds a product by ID
   * @param id - Product ID
   * Accepts - application/br.com.ecom.rep.product-v1+json
   */
  def get(id: Long) = DBAction { implicit rs =>

    try {
      
	  Products.findById(id).map { p =>
	     
	  	Products.marshall(p,rs.request)
	    
	  }.getOrElse(NotFound)
      
    } catch {

      case iae: IllegalArgumentException => {
    	 iae.printStackTrace()
        Status(415)
      }case e: Exception => {
        e.printStackTrace()
        Status(422)
      }
      
    }
    
  }
 
}