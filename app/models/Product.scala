package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.JsValue
import play.api.i18n.Messages
import play.api.mvc.RequestHeader

case class Product(id: Option[Long], storeId: Long, name: String, hash: String) {

  def asV1(): br.com.ecom.rep.v1.Product = {
    new br.com.ecom.rep.v1.Product(id, storeId, name, hash)
  }

}

object Products extends Table[Product]("ecombase_products") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def storeId = column[Long]("store_id", O.NotNull)
  def name = column[String]("name", O.NotNull)
  def hash = column[String]("hash", O.NotNull)
  def * = id.? ~ storeId ~ name ~ hash <> (Product.apply _, Product.unapply _)
  def autoInc = * returning id
  val byId = createFinderBy(_.id)

  val parseError = new Exception(Messages("json.parse.error"))
  
  def insert(product: Product)(implicit s: Session) {
    Products.autoInc.insert(product)
  }

  def findById(id: Long)(implicit s: Session): Option[Product] =
    Products.byId(id).firstOption
    
    
  def marshall(p: Product, r: RequestHeader) = {
   
    r.accept.mkString match {

      case br.com.ecom.rep.v1.Product.version => {
        
    	  br.com.ecom.rep.v1.Product.renderV1(p.asV1)(r)
        
      } case _ => throw new IllegalArgumentException(Messages("http.not.acceptable"))

    }
    
  }
    
    
  def unmarshall(json: JsValue, contentType: String): Product = {

    contentType match {

      case br.com.ecom.rep.v1.Product.version => {

        json.validate[br.com.ecom.rep.v1.Product].map { j =>

          new Product(j.id, j.storeId, j.name, j.hash)

        }.recoverTotal {
          e => throw parseError
        }

      } case _ => throw new IllegalArgumentException(Messages("http.not.acceptable"))

    }

  }

}