package controllers

import scala.Option.option2Iterable
import models.Products
import play.api.Play.current
import play.api.db.slick.DBAction
import play.api.db.slick.requestWithDbSession2session
import play.api.libs.json.JsValue
import play.api.mvc.Controller
import utils.RestUtil
import play.api.mvc.SimpleResult
import play.api.mvc.ResponseHeader
import play.api.i18n.Messages
import play.api.libs.iteratee.Enumerator

/**
 *  Consumes Products as JSON
 *
 * @author Thiago Siqueira - thiago.sqr@gmail.com <br><br>
 *
 * Copyright © 2013
 *
 */
object ProductsResource extends Controller {

  /**
   * Finds a product by ID
   * @param id - Product ID
   * Accepts - application/br.com.ecom.rep.product-v1+json
   */
  def post = DBAction { implicit rs =>

    try {

      val json: JsValue = RestUtil.toJson(rs.request.body.asRaw)
      val p = Products.unmarshall(json, rs.request.contentType.mkString)
      val id = Products.autoInc.insert(p)
      val location = Messages("play.application.url")+"/"+id
      
      SimpleResult(
    		header = ResponseHeader(201, Map("Location" -> location)),
    		body = Enumerator[String]("")
      )

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