package utils

import org.jboss.netty.handler.codec.http.HttpMethod
import play.mvc.Http.Status
import play.api.mvc.RawBuffer
import play.api.mvc.Result
import play.api.i18n.Messages
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.JsString

object RestUtil {

    val defaultResponses = Map(HttpMethod.GET.toString -> Status.OK,
      						   HttpMethod.POST.toString -> Status.CREATED,
		  				       HttpMethod.DELETE.toString -> Status.OK,
		  				       HttpMethod.PUT.toString -> Status.NO_CONTENT
  )
  
  def getCode(m: String): Int = {
    defaultResponses.get(m).get
  }
  
  
  def toJson(raw: Option[RawBuffer]): JsValue = {
    
	  raw.map { bytes =>
	    
	    bytes.asBytes(1024*100).map{ bytArray => 
	      
	      Json.parse(bytArray)
	      
	    }.getOrElse {
	      throw new Exception(Messages("json.parse.error"))
	    }
	    
	  }.getOrElse {
	    throw new Exception(Messages("json.parse.error"))
	  }      
    	
  } 
  
}